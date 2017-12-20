package com.common.dao;

import com.common.vo.ComboboxVO;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 下拉列表
     *
     * @param table_name 数据库表名
     * @param value      编号
     * @param content    显示内容
     * @param order      按照什么排序
     * @param sort       排序是升序还是降序
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ComboboxVO> listCombobox(String table_name, String value, String content, String order, String sort, String condition) {
        String strSql = "select " + value + " value," + content + " content from " + table_name;
        if (StringUtil.isNotNullOrEmpty(condition))
            strSql += " where " + condition;
        if (StringUtil.isNotNullOrEmpty(order))
            strSql += " order by " + order;
        if (StringUtil.isNotNullOrEmpty(sort))
            strSql += sort;
        return jdbcTemplate.query(strSql, new BeanPropertyRowMapper(ComboboxVO.class));
    }

}
