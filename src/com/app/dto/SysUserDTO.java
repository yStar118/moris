package com.app.dto;

/**
 * Created by 高宇飞 on 2017/8/14.
 * 用户关联通知 DTO
 */
public class SysUserDTO {

    private int id;
    private String realname;//姓名
    private Integer enterpriseId;//企业ID
    private Integer departmentId;//部门ID
    private Integer jobsId;//岗位ID

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobsId() {
        return jobsId;
    }

    public void setJobsId(Integer jobsId) {
        this.jobsId = jobsId;
    }
}
