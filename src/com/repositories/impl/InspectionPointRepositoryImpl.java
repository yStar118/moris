package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.InspectionPoint;
import com.repositories.InspectionPointRepositoryCustom;
import com.searchVO.InspectionPointSearchVO;
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
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
@Repository
public class InspectionPointRepositoryImpl extends JdbcDaoSupport implements InspectionPointRepositoryCustom {

    @Autowired
    public InspectionPointRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionPoint> getList(InspectionPointSearchVO searchVO) {
        String sql = "SELECT ip.*,(SELECT username FROM sys_user WHERE id=ip.createUser) AS  'createUserName'," +
                "(SELECT username FROM sys_user WHERE id=ip.updateUser) AS  'updateUserName'" +
                " FROM inspection_point ip where 1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by ip.tag,ip.displayOrder ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionPoint.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<String> getCategoryName() {
        String sql = "SELECT categoryName FROM inspection_point GROUP BY categoryName ";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(String.class));
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionPointSearchVO searchVO) {
        String sql = "";
        if (searchVO.getDepartmentCodes() != null && searchVO.getDepartmentCodes().size() > 0)
            sql += " and ip.departmentCode in (:departmentCodes) ";
        if (StringUtil.isNotNullOrEmpty(searchVO.getJobsCode()))
            sql += " and ip.jobsCode =:jobsCode ";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))
            sql += " and ip.name like :nameParam ";
        if (searchVO.getCategoryList() != null && searchVO.getCategoryList().size() > 0) {
            sql += " and (1=2 ";
            for (String s : searchVO.getCategoryList()) {
                sql += " or ip.categoryName like '" + getParam(s) + "'";
            }
            sql += " ) ";
        }
        if (searchVO.getAttributeList() != null && searchVO.getAttributeList().size() > 0) {
            sql += " and (1=2 ";
            for (String s : searchVO.getAttributeList()) {
                sql += " or ip.attributeName like '" + getParam(s) + "'";
            }
            sql += " ) ";
        }
        return sql;
    }

    private String getParam(String s) {
        return "%" + s + "%";
    }
}
