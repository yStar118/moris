package com.sys.dao;

import com.sys.model.SysLog;
import com.sys.vo.SysLogSearchVO;
import com.util.page.PageUtil;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统日志dao
 *
 * @author chykong
 */
@Repository
public class SysLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(SysLog sysLog) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "INSERT INTO sys_log(user_id,opera_date,opera_ip,module_name,opera_name,opera_url,opera_type,opera_params)"
                + " VALUES(:user_id,:opera_date,:opera_ip,:module_name,:opera_name,:opera_url,:opera_type,:opera_params)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLog);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    /**
     * 查询
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO, int pageIndex, int pageSize) {
        String sql = "select l.*,u.realname realname  from sys_log l,sys_user u where l.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
        return namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(SysLog.class));
    }

    /**
     * 查询所有，不分页
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        String sql = "select l.*,u.realname realname  from sys_log l,sys_user u where l.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
        return namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(SysLog.class));
    }

    /**
     * 查询
     */
    public int listCount(SysLogSearchVO sysLogSearchVO) {
        String sql = "select count(*) from sys_log where 1=1 ";
        sql += createSearchSql(sysLogSearchVO);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
    }

    private String createSearchSql(SysLogSearchVO sysLogSearchVO) {
        String sql = "";
        if (sysLogSearchVO.getUser_id() != null)
            sql += " and user_id=:user_id";
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getS_date()))
            sql += " and date(opera_date)>=:s_date";
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getE_date()))
            sql += " and date(opera_date)<=:e_date";
        return sql;
    }
}
