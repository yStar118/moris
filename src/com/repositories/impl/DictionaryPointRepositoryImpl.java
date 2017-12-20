package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.DictionaryPoint;
import com.repositories.DictionaryPointRepositoryCustom;
import com.searchVO.DictionaryPointSearchVO;
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
 * Created by 高宇飞 on 2017/8/31 14:03:03
 */
@Repository
public class DictionaryPointRepositoryImpl extends JdbcDaoSupport implements DictionaryPointRepositoryCustom {


    @Autowired
    public DictionaryPointRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<DictionaryPoint> getList(DictionaryPointSearchVO searchVO) {
        String sql = " select * from dictionary_point WHERE 1=1 ";
        sql += createSearchSql(searchVO);
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(searchVO);
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);
        int count = jdbcTemplate.queryForObject(countSql, parameterSource, Integer.class);
        if (count > 0) {
            searchVO.setTotal(count);
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());
            return jdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(DictionaryPoint.class));
        }
        return new ArrayList<>();
    }


    private String createSearchSql(DictionaryPointSearchVO searchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(searchVO.getName())) {
            sql += " and name like :nameParam ";
        }
        if (searchVO.getType() != null) {
            sql += " and type = :type";
        }
        return sql;
    }
}
