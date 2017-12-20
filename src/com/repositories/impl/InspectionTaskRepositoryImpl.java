package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.app.dto.InspectionTaskDTO;
import com.entity.InspectionTask;
import com.repositories.InspectionTaskRepositoryCustom;
import com.searchVO.InspectionTaskSearchVO;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
@Repository
public class InspectionTaskRepositoryImpl extends JdbcDaoSupport implements InspectionTaskRepositoryCustom {

    @Autowired
    public InspectionTaskRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionTask> getListByTask(InspectionTaskSearchVO searchVO) {
        String sql = "SELECT it.*,(SELECT COUNT(1) from inspection_task_sub where taskId=it.id) 'pointQuantity'," +
                "(SELECT realname from sys_user where id=it.checkUserId) 'checkUserName' " +
                "FROM inspection_task it where it.checkUserId is not null and  it.checkUserId != 0";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " ORDER BY it.startDate desc";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<InspectionTask> getListByProject(InspectionTaskSearchVO searchVO) {
        String sql = "SELECT it.*,(SELECT COUNT(1) from inspection_task_sub where taskId=it.id) " +
                "'pointQuantity' FROM inspection_task it where (it.checkUserId is null or it.checkUserId= 0) ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += "  ORDER BY it.startDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<InspectionTask> getHistoryTask(int beforeDays, int userId) {
        String sql = "SELECT it.*,(SELECT realname  FROM  sys_user WHERE id=it.checkUserId) as 'checkUserName'," +
                "(SELECT COUNT(its.id) from inspection_task_sub its where its.taskId=it.id) AS 'pointQuantity'," +
                "ifnull((SELECT COUNT(1) from inspection_task_sub where taskId=it.id and checkResult=1),0) 'pointQuantityAbnormality' " +
                "FROM inspection_task it " +
                "WHERE " +
                " it. STATUS > 2 " +
                "AND it.endDate >= date_add(curdate(), INTERVAL ? DAY) " +
                "AND it.checkUserId = ? ";
        sql += "  ORDER BY it.startDate desc ";//拼接sql 排序
        return getJdbcTemplate().query(sql, new Object[]{beforeDays, userId}, new BeanPropertyRowMapper<>(InspectionTask.class));
    }


    @Override
    public List<InspectionTaskDTO> getListByProjectForApi(String departmentCode) {
        String sql = "SELECT it.*  FROM inspection_task it where it.status =0  and departmentCode =? ";
        sql += "  ORDER BY it.startDate  ";//拼接sql 排序
        return getJdbcTemplate().query(sql, new Object[]{departmentCode}, new BeanPropertyRowMapper<>(InspectionTaskDTO.class));//执行sql 返回数据
    }

    @Override
    public List<InspectionTask> getResultList(InspectionTaskSearchVO searchVO) {
        String sql = "SELECT it.*,(SELECT COUNT(1) from inspection_task_sub where taskId=it.id) 'pointQuantity'," +
                "(SELECT realname from sys_user where id=it.checkUserId) 'checkUserName'," +
                "ifnull((SELECT COUNT(1) from inspection_task_sub where taskId=it.id and checkResult=1),0) 'pointQuantityAbnormality' " +
                "FROM inspection_task it  where it.status in(3,4) ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += "  ORDER BY it.endDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<InspectionTask> findByCheckUserId(int userId) {
        String sql = "SELECT it.*  FROM inspection_task it where it.status in (1,2) and checkUserId =? ";
        sql += "  ORDER BY it.startDate  ";//拼接sql 排序
        return getJdbcTemplate().query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionTaskSearchVO searchVO) {
        String sql = "";
        if (searchVO.getType() == null) {
            sql += " and it.type = 1";
        } else {
            sql += " and it.type = :type";
        }
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))//判断是否为null或者""
            sql += " and  it.name like :nameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        return sql;
    }

    @Override
    public List<InspectionTask> findByNotAllotmentTask() {
        String sql = "SELECT it.*  FROM inspection_task it WHERE it.status =0 " +
                "AND it.startDate <= ADDDATE(NOW(),INTERVAL 30 MINUTE) GROUP BY it.departmentCode ";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
    }

    @Override
    public List<InspectionTask> findByEmptyCheckTask() {
        String sql = "SELECT it.* FROM inspection_task it WHERE it. STATUS = 4 " +
                "AND it.endDate >= ADDDATE(NOW(), INTERVAL -4 HOUR) ";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(InspectionTask.class));//执行sql 返回数据
    }

}
