package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.app.dto.AppStatcheckResultDTO;
import com.entity.StatCheckResult;
import com.model.StatCheckBehavior;
import com.repositories.StatCheckResultRepositoryCustom;
import com.searchVO.StatCheckResultCustomSearchVO;
import com.searchVO.StatCheckResultSearchVO;
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
 * Created by 高宇飞 on 2017/11/8 14:24:43
 */
@Repository
public class StatCheckResultRepositoryImpl extends JdbcDaoSupport implements StatCheckResultRepositoryCustom {

    @Autowired
    public StatCheckResultRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Override
    public List<StatCheckResult> getListForJob() {
        String sql = "SELECT t.planId,ts.taskId,ts.pointId,ts.pointName,ts.departmentCode," +
                "ts.departmentName,ts.jobsCode,ts.jobsName,ts.equipmentName,ts.objectName, " +
                " COUNT(CASE WHEN ( ts.checkResultValue IS NOT NULL AND ts.checkResult IN(0,2)) " +
                "THEN ts.id END ) AS 'correctQuantity'," +
                " COUNT( CASE WHEN ( ts.checkResultValue IS NOT NULL OR ts.checkResult IN(0,2) )" +
                " THEN ts.id END) / COUNT(ts.pointId) * 100 AS 'correctRate'," +
                "COUNT(ts.id) AS 'totalQuantity'," +
                " DATE_FORMAT(DATE_SUB(curdate(),INTERVAL 1 DAY) , '%Y-%m-%d') AS 'statDate' " +
                "FROM inspection_task_sub ts " +
                "LEFT JOIN inspection_task t" +
                " ON ts.taskId = t.id " +
                " WHERE CASE WHEN " +
                "ts.checkDate IS NULL" +
                " THEN( TO_DAYS(NOW()) - 1 = TO_DAYS(t.startDate) OR TO_DAYS(NOW()) - 1 = TO_DAYS(t.endDate) )" +
                "ELSE TO_DAYS(NOW()) - 1 = TO_DAYS(ts.checkDate)" +
                " END " +
                "GROUP BY ts.pointId";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(StatCheckResult.class));
    }

    @Override
    public List<StatCheckResult> getListForJobs(StatCheckResultSearchVO searchVO) {
        String sql = "SELECT scr.departmentCode,scr.departmentName,scr.jobsCode,scr.jobsName," +
                "SUM(scr.correctQuantity) as 'correctQuantity' ,AVG(scr.correctRate) as 'correctRate'," +
                "SUM(scr.totalQuantity) as 'totalQuantity' from stat_check_result scr  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scr.jobsCode ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckResult.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckResult> getListForEquipment(StatCheckResultSearchVO searchVO) {
        String sql = "SELECT scr.departmentCode,scr.departmentName,scr.jobsCode,scr.jobsName,scr.equipmentName," +
                "SUM(scr.correctQuantity) as 'correctQuantity' ,AVG(scr.correctRate) as 'correctRate'," +
                "SUM(scr.totalQuantity) as 'totalQuantity' from stat_check_result scr  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scr.equipmentName ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckResult.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckResult> getListForObject(StatCheckResultSearchVO searchVO) {
        String sql = "SELECT scr.departmentCode,scr.departmentName,scr.jobsCode,scr.jobsName,scr.equipmentName,scr.objectName," +
                "SUM(scr.correctQuantity) as 'correctQuantity' ,AVG(scr.correctRate) as 'correctRate'," +
                "SUM(scr.totalQuantity) as 'totalQuantity' from stat_check_result scr  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scr.equipmentName,scr.objectName ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckResult.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckResult> getListForPoint(StatCheckResultSearchVO searchVO) {
        String sql = "SELECT scr.departmentCode,scr.departmentName,scr.jobsCode,scr.jobsName," +
                "scr.equipmentName,scr.objectName,scr.pointName," +
                "SUM(scr.correctQuantity) as 'correctQuantity' ,AVG(scr.correctRate) as 'correctRate'," +
                "SUM(scr.totalQuantity) as 'totalQuantity' from stat_check_result scr  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scr.pointId ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckResult.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    private String getSelectSQL(Integer typeValue) {
        // type   1 岗位   2 设备   3检查对象  4检查点
        if (typeValue == null || typeValue == 1) {
            return "SELECT its.jobsName as 'statName', ";
        } else if (typeValue == 2) {
            return "SELECT its.equipmentName as 'statName', ";
        } else if (typeValue == 3) {
            return "SELECT its.objectName as 'statName', ";
        } else if (typeValue == 4) {
            return "SELECT its.pointName as 'statName', ";
        } else {
            return null;
        }
    }

    private String getGroupBySQL(Integer typeValue) {
        // type   1 岗位   2 设备   3检查对象  4检查点
        if (typeValue == null || typeValue == 1) {
            return " GROUP BY its.jobsCode ";
        } else if (typeValue == 2) {
            return " GROUP BY its.jobsCode,its.equipmentName ";
        } else if (typeValue == 3) {
            return " GROUP BY its.jobsCode,its.equipmentName,its.objectName ";
        } else if (typeValue == 4) {
            return " GROUP BY its.pointId";
        } else {
            return null;
        }
    }

    @Override
    public List<AppStatcheckResultDTO> getListForAppByWeek(Integer typeValue) {
        String sql = getSelectSQL(typeValue);
        if (sql == null) {
            return new ArrayList<>();
        }
        sql += "COUNT(case when its.checkResult in(0,2) and it.`status` !=4 THEN its.id end )" +
                "/COUNT(its.id) as 'correctRate',COUNT(case when its.checkResult not in (0,2) " +
                "or it.`status` =4 THEN its.id end) as 'errorQuantity' " +
                "from inspection_task_sub its " +
                "LEFT JOIN" +
                " inspection_task it" +
                " on its.taskId = it.id" +
                " WHERE  (its.checkDate >=date_add(NOW(), INTERVAL -7 DAY)) " +
                "or (it.startDate >= date_add(NOW(), INTERVAL -7 DAY) and date_add(it.endDate, INTERVAL -1 DAY)  <= NOW())";
        sql += getGroupBySQL(typeValue);
        List<AppStatcheckResultDTO> query = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(AppStatcheckResultDTO.class));
        if (query.size() > 0) {
            return query;
        }
        return new ArrayList<>();
    }

    @Override
    public List<AppStatcheckResultDTO> getListForAppByMonth(Integer typeValue) {
        String sql = getSelectSQL(typeValue);
        if (sql == null) {
            return new ArrayList<>();
        }
        sql += "COUNT(case when its.checkResult in(0,2) and it.`status` !=4 THEN its.id end )" +
                "/COUNT(its.id) as 'correctRate',COUNT(case when its.checkResult not in (0,2) " +
                "or it.`status` =4 THEN its.id end) as 'errorQuantity' " +
                "from inspection_task_sub its " +
                "LEFT JOIN" +
                " inspection_task it" +
                " on its.taskId = it.id" +
                " WHERE  (its.checkDate >=date_add(NOW(), INTERVAL -1 MONTH)) " +
                "or (it.startDate >= date_add(NOW(), INTERVAL -1 MONTH) and date_add(it.endDate, INTERVAL -1 DAY)  <= NOW())";
        sql += getGroupBySQL(typeValue);
        List<AppStatcheckResultDTO> query = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(AppStatcheckResultDTO.class));
        if (query.size() > 0) {
            return query;
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckBehavior> getListForCheckBehavior(StatCheckResultCustomSearchVO searchVO) {
        String sql = "SELECT od.name AS 'departmentName',oj.`name` AS 'jobsName',su.realname AS 'userName'," +
                " ifnull(COUNT(CASE WHEN its.checkBehavior =1 THEN its.id END),0) AS 'lieAboutCount'," +
                " ifnull(COUNT(CASE WHEN its.checkBehavior =2 THEN its.id END),0) AS 'badJudgmentCount'  " +
                " FROM inspection_task it " +
                "RIGHT JOIN inspection_task_sub its" +
                " ON it.id = its.taskId" +
                " LEFT JOIN organization_department od " +
                "ON it.checkUserDepartmentId=od.id" +
                " LEFT JOIN organization_jobs oj " +
                "ON it.checkUserJobsId=oj.id " +
                "LEFT JOIN sys_user su " +
                "ON it.checkUserId=su.id " +
                "WHERE it.checkUserId  !=0   GROUP BY it.checkUserId ";
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckBehavior.class));//执行sql 返回数据
    }


    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(StatCheckResultSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getDepartmentName()))//判断是否为null或者""
            sql += " and  scr.departmentName  like :departmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getJobsName()))//判断是否为null或者""
            sql += " and  scr.jobsName  like :jobsNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getEquipmentName()))//判断是否为null或者""
            sql += " and  scr.equipmentName  like :equipmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getObjectName()))//判断是否为null或者""
            sql += " and  scr.objectName  like :objectNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getPointName()))//判断是否为null或者""
            sql += " and  scr.pointName  like :pointNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (searchVO.getStartDate() != null) {
            sql += " and scr.statDate >= :startDate ";
        }
        if (searchVO.getEndDate() != null) {
            sql += " and date_add(scr.statDate, INTERVAL -1 DAY)  <= :endDate ";
        }
        return sql;
    }
}
