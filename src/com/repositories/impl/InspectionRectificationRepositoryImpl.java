package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.entity.InspectionRectification;
import com.repositories.InspectionRectificationRepositoryCustom;
import com.searchVO.InspectionRectificationSearchVO;
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
 * Created by 高宇飞 on 2017/8/24 14:12:12
 */
@Repository
public class InspectionRectificationRepositoryImpl extends JdbcDaoSupport implements InspectionRectificationRepositoryCustom {

    @Autowired
    public InspectionRectificationRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<InspectionRectification> getList(InspectionRectificationSearchVO searchVO) {
        String sql = "SELECT ir.*,(SELECT realname FROM sys_user WHERE id=ir.rectificationUserId) AS  'rectificationUserName' " +
                " FROM inspection_rectification ir where 1=1 ";
        sql += createSearchSql(searchVO);//调用创建查询sql方法
        sql += " order by ir.createDate ";//拼接sql 排序
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(InspectionRectification.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

    @Override
    public void updateRectification(String id, String rectificationImg) {
        String sql = "UPDATE  inspection_rectification  SET rectificationDate = now(),rectificationImg = ?,status=1 WHERE  id=? ";
        getJdbcTemplate().update(sql, rectificationImg, id);
    }

    @Override
    public List<InspectionRectification> findByLastDate() {
        String sql = "SELECT * from inspection_rectification ir WHERE ir.`status` = 0 and ir.lastDate <DATE_FORMAT(NOW(),'%Y%m%d')";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(InspectionRectification.class));
    }

    /**
     * 创建查询sql
     *
     * @param searchVO 查询条件
     * @return 查询sql
     */
    private String createSearchSql(InspectionRectificationSearchVO searchVO) {
        String sql = "";
        if (searchVO.getRectificationUserId() != null) {
            sql += " and ir.rectificationUserId = :rectificationUserId ";
        }
        return sql;
    }
}
