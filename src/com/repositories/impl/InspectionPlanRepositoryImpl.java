package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.InspectionPlan;
import com.repositories.InspectionPlanRepositoryCustom;
import com.searchVO.InspectionPlanSearchVO;
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
 * Created by 高宇飞 on 2017/8/5.
 * 检查方案
 */
@Repository
public class InspectionPlanRepositoryImpl extends JdbcDaoSupport implements InspectionPlanRepositoryCustom {

    @Autowired
    public InspectionPlanRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionPlan> getList(InspectionPlanSearchVO searchVO) {
        String sql = "SELECT ip.*,(SELECT username FROM sys_user WHERE id=ip.createUser) AS  'createUserName'," +
                "(SELECT username FROM sys_user WHERE id=ip.updateUser) AS  'updateUserName'" +
                " FROM inspection_plan ip where 1=1  ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by ip.createDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionPlan.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionPlanSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))
            sql += " and ip.name like :nameParam";
        if (searchVO.getType() == null) {
            sql += " and ip.type = 1";
        } else {
            sql += " and ip.type = :type";
        }
        return sql;
    }
}
