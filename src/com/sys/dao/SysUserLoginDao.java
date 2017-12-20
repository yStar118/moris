package com.sys.dao;

import com.sys.model.SysUserLogin;
import com.util.page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysUserLoginDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public SysUserLoginDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void add(SysUserLogin sysUserLogin) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String sql = "insert into sys_user_login(user_id,login_date,login_ip,terminal,explorerType,explorerVersion)";
		sql += " values(:user_id,:login_date,:login_ip,:terminal,:explorerType,:explorerVersion)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUserLogin);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * 取得最后登录信息
	 * @param user_id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SysUserLogin getLastLogin(int user_id) {
		String sql = "select * from sys_user_login where user_id=?";
		Object[] params = new Object[] { user_id };
		List<SysUserLogin> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUserLogin.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysUserLogin> list(int user_id, int pageIndex, int pageSize) {
		String sql = "select * from sys_user_login where user_id=? order by login_date desc ";
		sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
		Object[] params = new Object[] { user_id };
		return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUserLogin.class));
	}

	/**
	 * 查询用户登录总数
	 * 
	 * @param user_id
	 * @return
	 */
	public int listCount(int user_id) {
		String sql = "select count(*) from sys_user_login where user_id=? order by login_date desc ";
		Object[] params = new Object[] { user_id };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

}
