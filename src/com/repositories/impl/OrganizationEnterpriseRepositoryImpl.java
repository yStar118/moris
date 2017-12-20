package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.OrganizationEnterprise;
import com.repositories.OrganizationEnterpriseRepositoryCustom;
import com.searchVO.OrganizationEnterpriseSearchVO;
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
 * Created by 1553280431@qq.com on 2017/6/22.
 * 企业
 */
@Repository
public class OrganizationEnterpriseRepositoryImpl extends JdbcDaoSupport implements OrganizationEnterpriseRepositoryCustom {

    @Autowired
    public OrganizationEnterpriseRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Override
    public List<OrganizationEnterprise> getList(OrganizationEnterpriseSearchVO searchVO) {
        String sql = "SELECT e.*,(SELECT username FROM sys_user WHERE id=e.createUser) AS  'createUserName'," +
                "(SELECT username FROM sys_user WHERE id=e.updateUser) AS  'updateUserName'" +
                " FROM organization_enterprise e where 1=1  ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by e.createDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(OrganizationEnterprise.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(OrganizationEnterpriseSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))//判断是否为null或者""
            sql += " and  e.name like :nameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getCode()))
            sql += " and  e.code like :codeParam";
        return sql;
    }
}
