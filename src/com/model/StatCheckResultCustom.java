package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/8 20:16:27
 * 自定义报表 实体
 */
public class StatCheckResultCustom {
    //方案id
    private String planId;
    //方案名称
    private String planName;
    //任务id
    private String taskId;
    //任务名称
    private String taskName;
    //任务开始时间
    private String startDate;
    //任务结束时间
    private String endDate;
    //任务执行人id
    private String userId;
    //任务执行人名称
    private String userName;
    //检查点部门编号
    private String departmentCode;
    //检查点部门名称
    private String departmentName;
    //检查点岗位编号
    private String jobsCode;
    //检查点岗位名称
    private String jobsName;
    //检查点设备名称
    private String equipmentName;
    //检查对象名称
    private String objectName;
    //检查点id
    private String pointId;
    //检查点名称
    private String pointName;
    //是否主观判断
    private Integer isSubJudge;
    //主观判断结果
    private Integer subjectiveJudgment;
    //检查对象分类名称
    private String categoryName;
    //检查对象属性
    private String attributeName;
    //检查结果参数
    private String checkResultValue;
    //检查结果   0  正常  1异常
    private Integer checkResult;
    //检查时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkDate;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Integer getIsSubJudge() {
        return isSubJudge;
    }

    public void setIsSubJudge(Integer isSubJudge) {
        this.isSubJudge = isSubJudge;
    }

    public Integer getSubjectiveJudgment() {
        return subjectiveJudgment;
    }

    public void setSubjectiveJudgment(Integer subjectiveJudgment) {
        this.subjectiveJudgment = subjectiveJudgment;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getCheckResultValue() {
        return checkResultValue;
    }

    public void setCheckResultValue(String checkResultValue) {
        this.checkResultValue = checkResultValue;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
