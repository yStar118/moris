package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.InspectionTaskSub;
import com.model.InspectionTaskAlarm;
import com.repositories.InspectionTaskSubRepositoryCustom;
import com.searchVO.InspectionTaskSubSearchVO;
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
 * Created by 高宇飞 on 2017/8/11.
 * 任务子表
 */
@Repository
public class InspectionTaskSubRepositoryImpl extends JdbcDaoSupport implements InspectionTaskSubRepositoryCustom {

    @Autowired
    public InspectionTaskSubRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionTaskSub> getList(InspectionTaskSubSearchVO searchVO) {
        String sql = "SELECT its.*, it.name FROM inspection_task_sub its LEFT JOIN inspection_task it ON  its.taskId = it.id where 1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " GROUP BY its.tag,its.id  ORDER BY its.tag,its.displayOrder ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionTaskSub.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<InspectionTaskSub> findByTaskId(String taskId) {
        String sql = "SELECT its.*,(SELECT tagValue FROM dictionary_tag WHERE tag=its.tag) AS 'tagValue' " +
                "FROM inspection_task_sub its WHERE its.taskId = ?;";
        List<InspectionTaskSub> list = getJdbcTemplate().query(sql, new Object[]{taskId}, new BeanPropertyRowMapper(InspectionTaskSub.class));
        if (list.size() > 0) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public InspectionTaskAlarm findByPointIdForAlarm(String pointId) {
        String sql = "SELECT it.`name` AS 'taskName',it.startDate,it.endDate,its.departmentCode, its.equipmentName," +
                "its.objectName,its.pointName,its.checkResult" +
                " FROM inspection_task_sub its LEFT JOIN" +
                " inspection_task it ON its.taskId = it.id " +
                "WHERE its.checkDate>DATE_ADD(NOW(),INTERVAL -4 HOUR) " +
                "AND its.checkResult !=2  AND its.pointId = ? ORDER BY its.checkDate DESC LIMIT 0,1 ";
        List<InspectionTaskAlarm> list = getJdbcTemplate().query(sql, new Object[]{pointId}, new BeanPropertyRowMapper(InspectionTaskAlarm.class));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }


    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionTaskSubSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getTaskId()))
            sql += " and its.taskId = :taskId ";
        if (StringUtil.isNotNullOrEmpty(searchVO.getPointName()))
            sql += " and its.pointName like :pointNameParam ";
        if (searchVO.getCheckResult() != null)
            sql += " and its.checkResult = :checkResult ";
        if (searchVO.getType() != null)
            sql += " and it.type = :type ";
        return sql;
    }
}
