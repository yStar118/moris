package com.sys.dao;

import com.sys.model.SysFunction;
import com.sys.model.SysRoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SysFunctionDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SysFunctionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(SysFunction sysFunction) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "INSERT INTO sys_function(module_id,name,code,url,type,description,display_order) " +
                "VALUES(:module_id,:name,:code,:url,:type,:description,:display_order)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysFunction);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int update(SysFunction sysFunction) {
        String sql = "UPDATE sys_function SET module_id=:module_id,name=:name,code=:code,url=:url,type=:type,description=:description,display_order=:display_order WHERE id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysFunction);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int delete(int id) {
        String sql = "DELETE FROM sys_function WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * 按模块id删除
     *
     * @param module_id
     * @return
     */
    public int deleteByModule_id(int module_id) {
        String sql = "DELETE FROM sys_function WHERE module_id=?";
        return jdbcTemplate.update(sql, module_id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysFunction get(int id) {
        String sql = "SELECT * FROM sys_function WHERE id=?";
        Object[] params = new Object[]{id};
        List<SysFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysFunction.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> list(int module_id) {
        String sql = "SELECT * FROM sys_function  WHERE module_id=? ORDER BY display_order";
        Object[] objects = new Object[]{module_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper(SysFunction.class));
        return list;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> list() {
        String sql = "SELECT * FROM sys_function  ORDER BY display_order";
        List<SysFunction> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysFunction.class));
        return list;
    }

    /**
     * 根据角色获取功能
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysRoleFunction> listRoleFunctionByRole_id(int role_id) {
        String sql = "SELECT * FROM sys_role_function  WHERE role_id=?";
        Object[] objects = new Object[]{role_id};
        List<SysRoleFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper(SysRoleFunction.class));
        return list;
    }

    /**
     * 根据角色获取功能,返回hashmap
     * 缓存设置
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public HashMap<String, String> hashRoleFunctionByRole_id(int role_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String sql = "SELECT * FROM sys_function WHERE id IN (SELECT function_id FROM sys_role_function  WHERE role_id=?)";
        Object[] objects = new Object[]{role_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper(SysFunction.class));
        for (SysFunction sysFunction : list) {
            hashMap.put(sysFunction.getCode(), sysFunction.getUrl());
        }
        return hashMap;
    }

    /**
     * 根据模块获取功能
     *
     * @param module_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> listByModule_id(int module_id) {
        String sql = "SELECT * FROM sys_function  WHERE module_id=? ORDER BY display_order";
        Object[] objects = new Object[]{module_id};
        return jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper(SysFunction.class));
    }

    /**
     * 根据模块获取所有写操作按钮功能
     *
     * @param module_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> listWriteByModule_id(int module_id) {
        String sql = "SELECT * FROM sys_function  WHERE module_id=? AND type=1 ORDER BY display_order";
        Object[] objects = new Object[]{module_id};
        return jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper(SysFunction.class));
    }

    /**
     * 根据角色id获取模块
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> listByRole_id(int role_id) {
        String sql = "SELECT m.* FROM sys_function m WHERE id IN (SELECT  function_id FROM sys_role_function WHERE role_id =?) ";
        Object[] params = new Object[]{role_id};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysFunction.class));
    }

    /**
     * 取只读功能的所有按钮id
     *
     * @return
     */
    public List<Integer> listReadByModule_id(String moduleArrIn) {
        String sql = "select id from sys_function  where module_id in (" + moduleArrIn + ") and type=0 order by display_order";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    /**
     * 根据url来获取funtion信息,只返回type=1即写操作
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> getAll() {
        String sql = "SELECT *,(SELECT name FROM sys_module WHERE id=module_id) module_name FROM sys_function WHERE type=1 ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysFunction.class));
    }

    /**
     * 根据按钮代码取按钮
     *
     * @param code
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysFunction getByCode(String code) {
        String sql = "SELECT * FROM sys_Function WHERE code=?";
        Object[] params = new Object[]{code};
        List<SysFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysFunction.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }


}
