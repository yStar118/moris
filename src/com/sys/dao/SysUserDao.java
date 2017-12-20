package com.sys.dao;

import com.app.dto.AppdAddressBook;
import com.app.dto.SysUserDTO;
import com.common.vo.ComboboxVO;
import com.sys.model.SysUser;
import com.sys.vo.SysUserSearchVO;
import com.util.page.PageUtil;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SysUserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SysUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(SysUser sysUser) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "INSERT INTO sys_user(username,password,telephone,email,enterpriseId,departmentId," +
                "jobsId,randomcode,status,realname,create_date,create_person,role_id)" +
                " VALUES(:username,:password,:telephone,:email,:enterpriseId,:departmentId," +
                ":jobsId,:randomcode,1,:realname,now(),:create_person,:role_id)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUser);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    public int updateByAdmin(SysUser sysUser) {
        String sql = "UPDATE sys_user SET realname=:realname,role_id=:role_id WHERE id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUser);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public int update(SysUser sysUser) {
        String sql = "UPDATE sys_user SET realname=:realname,role_id=:role_id," +
                "email=:email,enterpriseId=:enterpriseId,departmentId=:departmentId," +
                "jobsId=:jobsId WHERE id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUser);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public <S extends SysUser> Iterable<S> save(List<S> entities) {
        String sql = "INSERT INTO sys_user(username,password,sex,telephone,email,enterpriseId,departmentId," +
                "jobsId,randomcode,status,realname,create_date,create_person,role_id)" +
                " VALUES(:username,:password,:sex,:telephone,:email,:enterpriseId,:departmentId," +
                ":jobsId,:randomcode,1,:realname,now(),:create_person,:role_id)";
        SqlParameterSource[] params_array = SqlParameterSourceUtils.createBatch(entities.toArray());
        int[] results = new NamedParameterJdbcTemplate(jdbcTemplate).batchUpdate(sql, params_array);

        return entities;
    }

    /**
     * 修改密码
     *
     * @return
     */
    public int updatePass(int id, String newPass, String randowmcode) {
        String sql = "UPDATE sys_user SET password=?,randomcode=?  WHERE id=? ";
        Object[] objects = new Object[]{newPass, randowmcode, id};
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * 修改个人信息，用户自己操作
     *
     * @param sysUser
     * @return
     */
    public int updateInfo(SysUser sysUser) {
        String sql = "UPDATE sys_user SET realname=:realname,telephone=:telephone WHERE id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUser);
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    /**
     * 修改状态
     *
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        String sql = "UPDATE sys_user SET status=?  WHERE id=?";
        Object[] objects = new Object[]{status, id};
        return jdbcTemplate.update(sql, objects);
    }

    public int delete(int id) {
        String sql = "UPDATE sys_user SET status = 101 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysUser get(int id) {
        String sql = "SELECT * FROM sys_user WHERE id=?";
        Object[] params = new Object[]{id};
        List<SysUser> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUser.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 根据username获取sysUser
     *
     * @param username
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysUser getByUsername(String username) {
        String sql = "SELECT *,(SELECT name FROM sys_role WHERE id=role_id) role_name ," +
                "(SELECT name FROM organization_enterprise WHERE  id=enterpriseId)  enterpriseName ," +
                " (SELECT name FROM organization_department WHERE  id=departmentId)  departmentName ," +
                " (SELECT name FROM organization_jobs WHERE  id=jobsId)  jobsName  FROM sys_user WHERE username=?";
        Object[] params = new Object[]{username};
        List<SysUser> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUser.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 查询该用户名是否已存在,根据email来判断
     *
     * @param username
     * @return
     */
    public int getUsernameCount(String username) {
        String sql = "SELECT count(*) FROM sys_user WHERE username=? ";
        Object[] objects = new Object[]{username};
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * 查询用户信息
     *
     * @param sysUserSearchVO
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO, int pageIndex, int pageSize) {
        String sql = "select *,(select name from sys_role where id=role_id) role_name ," +
                "(SELECT name from organization_enterprise WHERE  id=enterpriseId)  enterpriseName ," +
                "(SELECT name from organization_department WHERE  id=departmentId)  departmentName ," +
                "(SELECT name from organization_jobs WHERE  id=jobsId)  jobsName   from sys_user where username != 'admin' ";
        sql += createSearchSql(sysUserSearchVO);
        sql += " order by id asc";
        sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUserSearchVO);
        return namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(SysUser.class));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> listAll() {
        String sql = "select *,(select name from sys_role where id=role_id) role_name  from sys_user where username != 'admin'  ";
        sql += " order by id asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysUser.class));
    }

    /**
     * 查询用户总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int listCount(SysUserSearchVO sysUserSearchVO) {
        String sql = "select count(*) from sys_user where username != 'admin' and status=1 ";
        sql += createSearchSql(sysUserSearchVO);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUserSearchVO);
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
    }

    private String createSearchSql(SysUserSearchVO sysUserSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername())) {
            sql += " and username=:username";
        }
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname())) {
            sql += " and realname like '%" + StringUtil.filterSpecialCharacter(sysUserSearchVO.getRealname()) + "%'";
        }
        if (sysUserSearchVO.getRole_id() != null) {
            sql += " and role_id=:role_id";
        }
        if (sysUserSearchVO.getStatus() != null) {
            sql += " and status=:status";
        }
        return sql;
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ComboboxVO> listAllUser() {
        String sql = "SELECT id value,username content,realname name FROM sys_user WHERE username != 'admin' ORDER BY id ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(ComboboxVO.class));
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> searchByUsername(String username) {
        String sql = "SELECT id,username FROM sys_user WHERE username LIKE ? ";
        return jdbcTemplate.query(sql, new Object[]{"%" + username + "%"}, new BeanPropertyRowMapper(SysUser.class));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> searchByRealName(String realName) {
        String sql = "SELECT id,realname FROM sys_user WHERE realname LIKE ? ";
        return jdbcTemplate.query(sql, new Object[]{"%" + realName + "%"}, new BeanPropertyRowMapper(SysUser.class));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUserDTO> searchByDepartmentIdAndRoleId(Integer departmentId, Integer roleId) {
        String sql = "SELECT id,realname,enterpriseId,departmentId,jobsId FROM sys_user WHERE departmentId = ? AND role_id <=? AND username != 'admin' ";
        return jdbcTemplate.query(sql, new Object[]{departmentId, roleId}, new BeanPropertyRowMapper(SysUserDTO.class));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> searchByDepartmentCodeAndRoleId(String departmentCode, Integer roleId) {
        String sql = "SELECT id, telephone FROM sys_user WHERE departmentId = " +
                "(SELECT id FROM organization_department WHERE `code` = ?) AND role_id = ? AND username != 'admin' ";
        return jdbcTemplate.query(sql, new Object[]{departmentCode, roleId}, new BeanPropertyRowMapper(SysUser.class));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<AppdAddressBook> getListForApp() {
        String sql = "SELECT s.username AS 'id',s.realname AS 'name',(SELECT code FROM organization_jobs WHERE id=s.jobsId ) " +
                "AS 'pId' FROM sys_user  s  WHERE s.departmentId IS NOT NULL" +
                " AND s.jobsId IS NOT NULL    AND s.username != 'admin' ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(AppdAddressBook.class));
    }

    /**
     * 查询用户信息
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> listByDepartmentId(List<Integer> departmentIds) {
        String sql = "SELECT *  FROM sys_user WHERE departmentId IN (:departmentIds) AND username != 'admin' ";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> map = new HashMap<>();
        map.put("departmentIds", departmentIds);
        return namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper(SysUser.class));
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysUser> findByJobsId(Integer jobsId) {
        String sql = " SELECT *,(SELECT name FROM organization_enterprise WHERE  id=enterpriseId)  enterpriseName ," +
                "(SELECT name FROM organization_department WHERE  id=departmentId)  departmentName ," +
                "(SELECT name FROM organization_jobs WHERE  id=jobsId)  jobsName   FROM sys_user    WHERE username != 'admin'AND jobsId=?";
        return jdbcTemplate.query(sql, new Object[]{jobsId}, new BeanPropertyRowMapper(SysUser.class));
    }
}
