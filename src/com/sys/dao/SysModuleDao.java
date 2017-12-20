package com.sys.dao;

import com.sys.model.SysModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysModuleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SysModuleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(SysModule sysModule) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "INSERT INTO sys_module(name,code,parent_id,url,target,iconImg,display_order) VALUES(:name,:code,:parent_id,:url,:target,:iconImg,:display_order)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysModule);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int update(SysModule sysModule) {
        String sql = "UPDATE sys_module SET name=:name,code=:code,url=:url,parent_id=:parent_id,target=:target,iconImg=:iconImg,display_order=:display_order WHERE id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysModule);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int delete(int id) {
        String sql = "DELETE FROM sys_module WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysModule get(int id) {
        String sql = "SELECT * FROM sys_module WHERE id=?";
        Object[] params = new Object[]{id};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysModule> list() {
        String sql = "SELECT m.*,(SELECT count(*) FROM sys_module WHERE parent_id=m.id) cnt FROM sys_module m ORDER BY parent_id, display_order";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysModule.class));
    }

    /**
     * 获取下级节点总数
     *
     * @param id
     * @return
     */
    public int getChildCount(int id) {
        String sql = "SELECT count(*) FROM sys_module WHERE parent_id=?";
        Object[] objects = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * 根据user_id获取模块
     *
     * @param user_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysModule> listByUser_id(int user_id) {
        String sql = "SELECT m.*,(SELECT count(*) FROM sys_module WHERE parent_id=m.id) cnt FROM sys_module m WHERE id"
                + " IN (SELECT  module_id FROM sys_role_module WHERE role_id IN (SELECT role_id FROM sys_user WHERE id =?))" + " ORDER BY parent_id, display_order";
        Object[] params = new Object[]{user_id};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysModule.class));
    }

    /**
     * 根据角色id获取模块
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysModule> listByRole_id(int role_id) {
        String sql = "select m.* from sys_module m";
        sql += " where id in (select  module_id from sys_role_module where role_id =?)";
        sql += " order by parent_id, display_order";
        Object[] params = new Object[]{role_id};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysModule.class));
    }

    /**
     * 根据模块代码获取模块信息
     *
     * @param code
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysModule getByModuleCode(String code) {
        String sql = "SELECT * FROM sys_module WHERE code=?";
        Object[] params = new Object[]{code};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 根据角色id获取模块
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysModule getByUrl(String url) {
        String sql = "SELECT m.* FROM sys_module m WHERE url=? ";
        Object[] params = new Object[]{url};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
