package com.searchVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/8 15:41:26
 */
public class StatCheckResultSearchVO extends CommonSearchVO {

    private String departmentName;
    private String jobsName;
    private String equipmentName;
    private String objectName;
    private String pointName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    private Integer type;  //   1 按岗位      2 按设备   3 按检查对象   4按检查点

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
}
