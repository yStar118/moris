package com.sys.dao;

import com.common.vo.ComboboxVO;
import com.sys.model.SysRole;
import com.sys.model.SysRoleFunction;
import com.sys.model.SysRoleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRoleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SysRoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(SysRole sysRole) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "insert into sys_role(name,description,create_date,is_delete,create_person)" + " values(:name,:description,now(),:is_delete,:create_person)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysRole);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    public int update(SysRole sysRole) {
        String sql = "update sys_role set name=:name,description=:description where id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysRole);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int delete(int id) {
        String sql = "delete from sys_role where id=?";
        Object[] params = new Object[]{id};
        return jdbcTemplate.update(sql, params);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysRole get(int id) {
        String sql = "select * from sys_role where id=? ";
        Object[] params = new Object[]{id};
        List<SysRole> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysRole.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 角色列表
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysRole> list() {
        String sql = "select t.* from sys_role t order by id ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysRole.class));
    }

    /**
     * 角色列表
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ComboboxVO> listCombo() {
        String sql = "select id value,name content from sys_role order by id ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(ComboboxVO.class));
    }

    /**
     * 根据角色id获取所有模块
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysRoleModule> listRoleModule(int role_id) {
        String sql = "select * from sys_role_module where role_id=? ";
        Object[] params = new Object[]{role_id};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysRoleModule.class));
    }

    /**
     * 删除角色对应的模块
     *
     * @param role_id
     */
    public void deleteRoleModule(int role_id) {
        jdbcTemplate.update("delete from sys_role_module where role_id=?", role_id);
    }

    /**
     * 新增角色对应模块
     *
     * @param role_id
     * @param module_id
     */
    public void addRoleModule(int role_id, int module_id) {
        String sql = "insert into sys_role_module(role_id,module_id) values(?,?)";
        Object[] params = new Object[]{role_id, module_id};
        jdbcTemplate.update(sql, params);
    }

    /**
     * 删除角色对应的功能
     *
     * @param role_id
     */
    public void deleteRoleFunction(int role_id) {
        jdbcTemplate.update("delete from sys_role_function where role_id=?", role_id);
    }

    /**
     * 新增角色对应功能
     *
     * @param role_id
     * @param function_id
     */
    public void addRoleFunction(int role_id, int function_id) {
        String sql = "insert into sys_role_function(role_id,function_id) values(?,?)";
        Object[] params = new Object[]{role_id, function_id};
        jdbcTemplate.update(sql, params);
    }

    /**
     * 根据角色id获取所有功能
     *
     * @param role_id
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysRoleFunction> listRoleFunction(int role_id) {
        String sql = "select * from sys_role_function where role_id=? ";
        Object[] params = new Object[]{role_id};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysRoleFunction.class));
    }

}