package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.YStarStudent;
import com.repositories.YStarStudentRepositoryCustom;


import com.searchVO.YStarStudentSearchVO;
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
 * Created by wyx-pc on 2017/12/18.
 */
@Repository
public class YStarStudentRepositoryImpl extends JdbcDaoSupport implements YStarStudentRepositoryCustom{
    @Autowired
    public YStarStudentRepositoryImpl (DataSource dataSource){ super.setDataSource(dataSource);}

    @Override
    public List<YStarStudent> getList(YStarStudentSearchVO searchVO) {
        String sql ="select * from base_student where 1=1";
        sql += createSearchSql(searchVO);//调用查询sql的方法
        SqlParameterSource  params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据
        NamedParameterJdbcTemplate jdbcTemplate= new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql= PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count= jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行返回的结果
        if(count>0){
            searchVO.setTotal(count);//设置一个总数到查询VO的父类
            //工具类创建sql分页
            sql=PagerUtils.limit(sql,JdbcConstants.MYSQL,searchVO.getIndex(),searchVO.getLength());
            //执行sql返回数据
            return jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<>(YStarStudent.class));

        }



        return new ArrayList<>();
    }

    private String createSearchSql(YStarStudentSearchVO searchVO) {
        String sql="";
      if(StringUtil.isNotNullOrEmpty(searchVO.getName()))
          sql += " and name like nameParam";
      return sql;
    }



}
