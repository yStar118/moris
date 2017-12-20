package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.app.dto.AppdAddressBook;
import com.entity.OrganizationJobs;
import com.repositories.OrganizationJobsRepositoryCustom;
import com.searchVO.OrganizationJobsSearchVO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1553280431@qq.com on 2017/6/24.
 * 岗位
 */
@Repository
public class OrganizationJobsRepositoryImpl extends JdbcDaoSupport implements OrganizationJobsRepositoryCustom {

    @Autowired
    public OrganizationJobsRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<OrganizationJobs> getList(OrganizationJobsSearchVO searchVO) {
        String sql = "SELECT j.*,(SELECT username FROM sys_user WHERE id=j.createUser) AS  'createUserName'," +
                "(SELECT name FROM organization_enterprise WHERE code=j.enterpriseCode LIMIT 0,1) AS  'enterpriseName'," +
                "(SELECT name FROM organization_department WHERE code=j.departmentCode LIMIT 0,1) AS  'departmentName'," +
                "(SELECT username FROM sys_user WHERE id=j.updateUser) AS  'updateUserName'" +
                " FROM organization_jobs j where 1=1  ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by j.createDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(OrganizationJobs.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public OrganizationJobs findOneByCode(String code) {
        String sql = "SELECT * FROM organization_jobs WHERE code=:code;";
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        List<OrganizationJobs> list = new NamedParameterJdbcTemplate(getJdbcTemplate()).query(sql, map, new BeanPropertyRowMapper<>(OrganizationJobs.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return new OrganizationJobs();
    }

    @Override
    public List<AppdAddressBook> getListForApp() {
        String sql = "SELECT code as 'id',name,departmentCode as 'pId' FROM organization_jobs";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(AppdAddressBook.class));
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(OrganizationJobsSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))//判断是否为null或者""
            sql += " and  j.name like :nameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        if (StringUtil.isNotNullOrEmpty(searchVO.getDepartmentCode()))//判断是否为null或者""
            sql += " and  j.departmentCode =:departmentCode";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        return sql;
    }
}
