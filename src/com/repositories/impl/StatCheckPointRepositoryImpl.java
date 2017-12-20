package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.StatCheckPoint;
import com.repositories.StatCheckPointRepositoryCustom;
import com.searchVO.StatCheckPointSearchVO;
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
 * Created by 高宇飞 on 2017/11/6 16:50:09
 */
@Repository
public class StatCheckPointRepositoryImpl extends JdbcDaoSupport implements StatCheckPointRepositoryCustom {

    @Autowired
    public StatCheckPointRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Override
    public List<StatCheckPoint> getListForDay(StatCheckPointSearchVO searchVO) {
        String sql = "SELECT scp.departmentCode,scp.departmentName,scp.jobsCode,scp.jobsName,scp.pointId,scp.pointName, " +
                "SUM(scp.finishQuantity) as 'finishQuantity' ,AVG(scp.finishRate) as 'finishRate'," +
                "SUM(scp.leakageQuantity) as 'leakageQuantity' ,AVG(scp.leakageRate) as 'leakageRate'," +
                "SUM(scp.emptyQuantity) as 'emptyQuantity' ,AVG(scp.emptyRate) as 'emptyRate'," +
                "SUM(scp.weakQuantity) as 'weakQuantity' ,AVG(scp.weakRate) as 'weakRate' ," +
                "SUM(scp.totalQuantity) as 'totalQuantity' from stat_check_point scp  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scp.pointId ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckPoint.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckPoint> getListForMonth(StatCheckPointSearchVO searchVO) {
        String sql = "SELECT scp.departmentCode,scp.departmentName,scp.jobsCode,scp.jobsName,scp.pointId,scp.pointName, " +
                "SUM(scp.finishQuantity) as 'finishQuantity' ,AVG(scp.finishRate) as 'finishRate'," +
                "SUM(scp.leakageQuantity) as 'leakageQuantity' ,AVG(scp.leakageRate) as 'leakageRate'," +
                "SUM(scp.emptyQuantity) as 'emptyQuantity' ,AVG(scp.emptyRate) as 'emptyRate'," +
                "SUM(scp.weakQuantity) as 'weakQuantity' ,AVG(scp.weakRate) as 'weakRate' ," +
                "SUM(scp.totalQuantity) as 'totalQuantity' from stat_check_point scp  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scp.pointId,DATE_FORMAT(scp.statDate,'%Y-%m')";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckPoint.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<StatCheckPoint> getListForYear(StatCheckPointSearchVO searchVO) {
        String sql = "SELECT scp.departmentCode,scp.departmentName,scp.jobsCode,scp.jobsName,scp.pointId,scp.pointName, " +
                "SUM(scp.finishQuantity) as 'finishQuantity' ,AVG(scp.finishRate) as 'finishRate'," +
                "SUM(scp.leakageQuantity) as 'leakageQuantity' ,AVG(scp.leakageRate) as 'leakageRate'," +
                "SUM(scp.emptyQuantity) as 'emptyQuantity' ,AVG(scp.emptyRate) as 'emptyRate'," +
                "SUM(scp.weakQuantity) as 'weakQuantity' ,AVG(scp.weakRate) as 'weakRate' ," +
                "SUM(scp.totalQuantity) as 'totalQuantity' from stat_check_point scp  WHERE  1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " group by scp.pointId,DATE_FORMAT(scp.statDate,'%Y')";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckPoint.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(StatCheckPointSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getDepartmentName()))//判断是否为null或者""
            sql += " and  scp.departmentName  like :departmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (searchVO.getStatStartDate() != null) {
            sql += " and scp.statDate >= :statStartDate ";
        }
        if (searchVO.getStatEndDate() != null) {
            sql += " and date_add(scp.statDate, INTERVAL -1 DAY)  <= :statEndDate ";
        }
        if (searchVO.getMonth() != null) {
            sql += " and DATE_FORMAT(scp.statDate,'%Y-%m') = :monthParam ";
        }
        if (searchVO.getType() != null && searchVO.getType() == 3) {
            sql += " and DATE_FORMAT(scp.statDate,'%Y') = :year ";
        }
        return sql;
    }


    @Override
    public List<StatCheckPoint> getListForJob() {
        String sql = "SELECT ts.departmentCode,ts.departmentName,ts.jobsCode,ts.jobsName," +
                "ts.pointId,ts.pointName, COUNT(CASE WHEN (t.`status` = 3) " +
                "THEN ts.id END ) AS 'finishQuantity', COUNT( CASE WHEN (t.`status` = 3) " +
                "THEN ts.id END)/COUNT(ts.pointId)*100 AS 'finishRate', " +
                "COUNT( CASE WHEN (t.checkUserId IS NULL OR t.checkUserId =0  ) " +
                "THEN ts.id END) AS 'leakageQuantity'," +
                "COUNT( CASE WHEN (t.checkUserId IS NULL OR t.checkUserId =0  )" +
                " THEN ts.id END )/COUNT(ts.pointId)*100 AS 'leakageRate', " +
                "COUNT(  CASE WHEN (t.`status` = 4) THEN ts.id END ) AS 'emptyQuantity'," +
                " COUNT( CASE WHEN (t.checkUserId IS NULL OR t.checkUserId =0  ) " +
                "THEN  ts.id END )/COUNT(ts.pointId)*100 AS 'emptyRate'," +
                " COUNT(ts.pointId) AS 'totalQuantity', " +
                "DATE_FORMAT(DATE_SUB(curdate(),INTERVAL 1 DAY),'%Y-%m-%d') AS 'statDate' " +
                "FROM inspection_task_sub ts LEFT JOIN inspection_task t" +
                " ON ts.taskId = t.id WHERE CASE WHEN ts.checkDate IS NULL " +
                "THEN ( TO_DAYS(NOW())-1 = TO_DAYS(t.startDate) OR TO_DAYS(NOW())-1 = TO_DAYS(t.endDate) )" +
                " ELSE TO_DAYS(NOW())-1 = TO_DAYS(ts.checkDate) END " +
                "GROUP BY ts.pointId";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(StatCheckPoint.class));//执行sql 返回数据
    }
}
