package com.searchVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/9 15:32:54
 */
public class StatCheckResultCustomSearchVO extends CommonSearchVO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    //检查对象分类名称
    private String categoryName;
    //检查对象属性
    private String attributeName;
    //检查结果   0  正常  1异常
    private Integer checkResult;
    //检查点部门名称
    private String departmentName;
    //检查点岗位名称
    private String jobsName;
    //检查点设备名称
    private String equipmentName;
    //检查对象名称
    private String objectName;
    //检查点名称
    private String pointName;
    //任务执行人id
    private Integer userId;

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

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryNameParam() {
        return "%" + categoryName + "%";
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeNameParam() {
        return "%" + attributeName + "%";
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public String getCheckResultParam() {
        return "%" + checkResult + "%";
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentNameParam() {
        return "%" + departmentName + "%";
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobsName() {
        return jobsName;
    }

    public String getJobsNameParam() {
        return "%" + jobsName + "%";
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentNameParam() {
        return "%" + equipmentName + "%";
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getObjectNameParam() {
        return "%" + objectName + "%";
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPointName() {
        return pointName;
    }

    public String getPointNameParam() {
        return "%" + pointName + "%";
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
