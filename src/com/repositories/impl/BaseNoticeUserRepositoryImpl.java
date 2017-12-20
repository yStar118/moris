package com.repositories.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.app.dto.BaseNoticeUserDTO;
import com.entity.BaseNoticeUser;
import com.repositories.BaseNoticeUserRepositoryCustom;
import com.searchVO.BaseNoticeUserSearchVO;
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
 * 通知 用户关联
 */
@Repository
public class BaseNoticeUserRepositoryImpl extends JdbcDaoSupport implements BaseNoticeUserRepositoryCustom {

    @Autowired
    public BaseNoticeUserRepositoryImpl(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void deleteByNoticeId(String noticeId) {
        String sql = "DELETE FROM base_notice_user WHERE noticeId=?";
        getJdbcTemplate().update(sql, noticeId);
    }

    @Override
    public List<BaseNoticeUser> getListByNoticeId(BaseNoticeUserSearchVO searchVO) {
        String sql = "SELECT bnu.*,(SELECT name FROM organization_department WHERE id=bnu.departmentId) AS  'departmentName'," +
                "(SELECT name FROM organization_jobs WHERE id=bnu.jobsId) AS  'jobsName'" +
                " FROM base_notice_user bnu WHERE bnu.noticeId=:noticeId  ";
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(BaseNoticeUser.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }


    public List<BaseNoticeUserDTO> getListByUserId(BaseNoticeUserSearchVO searchVO) {
        String sql = "SELECT bnu.id,bnu.noticeId,bnu.userId,bnu.isRead,bn.name AS 'noticeName'," +
                "bn.content AS 'noticeContent',bn.fileName,bn.filePath,bn.isConfirm AS 'isConfirm'," +
                "bnu.createDate FROM base_notice_user bnu RIGHT JOIN base_notice bn ON bnu.noticeId=bn.id  " +
                " WHERE bnu.userId = :userId AND bn.type =:type";
        SqlParameterSource params = new BeanPropertySqlParameterSource(searchVO);//实例化一个查询类，用来注入查询数据 根据（:+字段值）
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());//对象构造器初始化
        String countSql = PagerUtils.count(sql, JdbcConstants.MYSQL);//工具类自动创建查询总数sql
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);//执行sql返回查询结果
        if (count > 0) {
            searchVO.setTotal(count);//set一个总数到查询VO的父类
            sql = PagerUtils.limit(sql, JdbcConstants.MYSQL, searchVO.getIndex(), searchVO.getLength());//工具类创建分页sql
            return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(BaseNoticeUserDTO.class));//执行sql 返回数据
        }
        return new ArrayList<>();
    }

}
