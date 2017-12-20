package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
@Entity
@Table(name = "inspection_task")
public class InspectionTask {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    //任务名称
    private String name;
    //任务描述
    private String content;
    //任务检查点数量
    @Transient
    private Integer pointQuantity;
    //任务检查点异常数量
    @Transient
    private Integer pointQuantityAbnormality;
    //任务执行部门编号
    private String departmentCode;
    //任务执行部门名称
    private String departmentName;
    //任务执行岗位编号
    private String jobsCode;
    //任务执行岗位名称
    private String jobsName;
    //方案id
    private String planId;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;
    //任务类型  1 巡检单元   2 隐患排查
    private Integer type;
    //状态   0 未分配待分配    1 已分配待确认    2已确认待执行  3已执行
    private Integer status;
    //检查人  企业id
    private Integer checkUserEnterpriseId;
    //检查人部门id
    private Integer checkUserDepartmentId;
    //检查人岗位id
    private Integer checkUserJobsId;
    //检查人id
    private Integer checkUserId;
    //检查人名称
    @Transient
    private String checkUserName;
    //上传时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date uploadDate;
    @Transient
    private List<InspectionTaskSub> taskSubList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCheckUserEnterpriseId() {
        return checkUserEnterpriseId;
    }

    public void setCheckUserEnterpriseId(Integer checkUserEnterpriseId) {
        this.checkUserEnterpriseId = checkUserEnterpriseId;
    }

    public Integer getCheckUserDepartmentId() {
        return checkUserDepartmentId;
    }

    public void setCheckUserDepartmentId(Integer checkUserDepartmentId) {
        this.checkUserDepartmentId = checkUserDepartmentId;
    }

    public Integer getCheckUserJobsId() {
        return checkUserJobsId;
    }

    public void setCheckUserJobsId(Integer checkUserJobsId) {
        this.checkUserJobsId = checkUserJobsId;
    }

    public Integer getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Integer checkUserId) {
        this.checkUserId = checkUserId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getPointQuantity() {
        return pointQuantity;
    }

    public void setPointQuantity(Integer pointQuantity) {
        this.pointQuantity = pointQuantity;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobsCode() {
        return jobsCode;
    }

    public void setJobsCode(String jobsCode) {
        this.jobsCode = jobsCode;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public Integer getPointQuantityAbnormality() {
        return pointQuantityAbnormality;
    }

    public void setPointQuantityAbnormality(Integer pointQuantityAbnormality) {
        this.pointQuantityAbnormality = pointQuantityAbnormality;
    }

    public List<InspectionTaskSub> getTaskSubList() {
        return taskSubList;
    }

    public void setTaskSubList(List<InspectionTaskSub> taskSubList) {
        this.taskSubList = taskSubList;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
