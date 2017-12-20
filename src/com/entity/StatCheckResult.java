package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/6 13:24:59
 * 检查结果报表
 */
@Entity
@Table(name = "stat_check_result")
public class StatCheckResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //方案id
    private String planId;
    //任务id
    private String taskId;
    //部门编号
    private String departmentCode;
    //部门名称
    private String departmentName;
    //岗位编号
    private String jobsCode;
    //岗位名称
    private String jobsName;
    //设备名称
    private String equipmentName;
    //检查对象名称
    private String objectName;
    //检查点id
    private String pointId;
    //检查点名称
    private String pointName;
    //正确数量
    private Integer correctQuantity;
    //正确数量
    private BigDecimal correctRate;
    //总数
    private Integer totalQuantity;
    //统计时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Integer getCorrectQuantity() {
        if (correctQuantity != null) {
            return correctQuantity;
        } else {
            return 0;
        }
    }

    public void setCorrectQuantity(Integer correctQuantity) {
        this.correctQuantity = correctQuantity;
    }

    public BigDecimal getCorrectRate() {
        if (correctRate != null) {
            return correctRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public void setCorrectRate(BigDecimal correctRate) {
        this.correctRate = correctRate;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }
}