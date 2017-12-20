package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.InspectionObject;
import com.repositories.InspectionObjectRepositoryCustom;
import com.searchVO.InspectionObjectSearchVO;
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
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
@Repository
public class InspectionObjectRepositoryImpl extends JdbcDaoSupport implements InspectionObjectRepositoryCustom {

    @Autowired
    public InspectionObjectRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionObject> getList(InspectionObjectSearchVO searchVO) {
        String sql = "SELECT io.*,(SELECT name from organization_department where code=io.departmentCode) as 'departmentName'," +
                "(SELECT name from organization_jobs where code=io.jobsCode) as 'jobsName'," +
                "(SELECT username FROM sys_user WHERE id=io.createUser) AS  'createUserName'," +
                "(SELECT username FROM sys_user WHERE id=io.updateUser) AS  'updateUserName'" +
                " FROM inspection_object io where 1=1  ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by io.createDate desc ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionObject.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public List<InspectionObject> findByJobsCodeGroupByEquipmentName(String jobsCode) {
        String sql = "SELECT * FROM inspection_object WHERE jobsCode=? GROUP BY equipmentName";
        return getJdbcTemplate().query(sql, new Object[]{jobsCode}, new BeanPropertyRowMapper<>(InspectionObject.class));//执行sql 返回数据
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionObjectSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName()))//判断是否为null或者""
            sql += " and  io.name like :nameParam";//注入参数  这里用的模糊查询  防注入 所以在bean里面拼接
        return sql;
    }
}
