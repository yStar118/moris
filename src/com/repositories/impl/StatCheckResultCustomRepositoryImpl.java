package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.model.StatCheckResultCustom;
import com.repositories.StatCheckResultCustomRepository;
import com.searchVO.StatCheckResultCustomSearchVO;
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
 * Created by 高宇飞 on 2017/11/9 15:10:15
 * 自定义报表
 */
@Repository
public class StatCheckResultCustomRepositoryImpl extends JdbcDaoSupport implements StatCheckResultCustomRepository {

    @Autowired
    public StatCheckResultCustomRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Override
    public List<StatCheckResultCustom> getList(StatCheckResultCustomSearchVO searchVO) {
        String sql = "SELECT it.planId,(SELECT  NAME FROM inspection_plan ip WHERE ip.id = it.planId) AS 'planName'," +
                "it.id as 'taskId', it.`name` as 'taskName',it.startDate,it.endDate,it.checkUserId as 'userId'," +
                "(SELECT su.realname from sys_user su WHERE su.id = it.checkUserId) as 'userName'," +
                "its.departmentCode,its.departmentName, its.jobsCode,its.jobsName," +
                "its.equipmentName,its.objectName,its.pointId,its.pointName,its.isSubJudge," +
                "its.subjectiveJudgment, ip.categoryName as 'categoryName'," +
                "ip.attributeName as 'attributeName',its.checkResultValue,its.checkResult,its.checkDate " +
                "FROM inspection_task_sub its " +
                "LEFT JOIN inspection_task it " +
                "ON its.taskId = it.id  " +
                "LEFT JOIN inspection_point ip " +
                "on its.pointId = ip.id " +
                "WHERE it.`status` > 2 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StatCheckResultCustom.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }


    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(StatCheckResultCustomSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getDepartmentName()))//判断是否为null或者""
            sql += " and  its.departmentName  like :departmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (searchVO.getStartDate() != null) {
            sql += "  and ((its.checkDate >=:startDate and  date_add(its.checkDate, INTERVAL -1 DAY)  <= :endDate)" +
                    " or (it.startDate >= :startDate and date_add(it.endDate, INTERVAL -1 DAY)  <= :endDate)) ";
        }
        if (StringUtil.isNotNullOrEmpty(searchVO.getCategoryName()))//判断是否为null或者""
            sql += " and  ip.categoryName  like :categoryNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getDepartmentName()))//判断是否为null或者""
            sql += " and  its.departmentName  like :departmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getJobsName()))//判断是否为null或者""
            sql += " and  its.jobsName  like :jobsNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getAttributeName()))//判断是否为null或者""
            sql += " and  ip.attributeName  like :attributeNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getEquipmentName()))//判断是否为null或者""
            sql += " and  its.equipmentName  like :equipmentNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getObjectName()))//判断是否为null或者""
            sql += " and  its.objectName  like :objectNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getPointName()))//判断是否为null或者""
            sql += " and  its.pointName  like :pointNameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (searchVO.getUserId() != null)//判断是否为null或者""
            sql += " and  it.checkUserId  like :userIdParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        return sql;
    }

}
