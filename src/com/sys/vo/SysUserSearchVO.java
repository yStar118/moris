package com.sys.vo;

import com.searchVO.CommonSearchVO;

import java.util.List;

/**
 * 用户管理查询条件
 *
 * @author gaoyf
 */
public class SysUserSearchVO extends CommonSearchVO {
    private String username;//email
    private Integer status;//状态
    private Integer role_id;//角色
    private String realname;//姓名
    private List<Integer> departmentIds;//部门id列表

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
