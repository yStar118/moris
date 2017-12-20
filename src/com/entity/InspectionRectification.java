package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 高宇飞 on 2017/8/24 13:34:18
 * 整改任务
 */
@Entity
@Table(name = "inspection_rectification")
public class InspectionRectification {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    //整改任务名称
    private String name;
    //整改任务描述
    private String remark;
    //检查点id
    @Column(updatable = false)
    private String pointId;
    //检查点名称
    @Column(updatable = false)
    private String pointName;
    //部门编号
    @Column(updatable = false)
    private String departmentCode;
    //部门名称
    @Column(updatable = false)
    private String departmentName;
    //岗位编号
    @Column(updatable = false)
    private String jobsCode;
    //岗位名称
    @Column(updatable = false)
    private String jobsName;
    //设备名称
    @Column(updatable = false)
    private String equipmentName;
    //检查对象名称
    @Column(updatable = false)
    private String objectName;
    //异常图片
    @Column(updatable = false)
    private String abnormalityImg;
    //整改人企业id
    private Integer rectificationEnterpriseId;
    //整改人部门id
    private Integer rectificationDepartmentId;
    //整改人岗位id
    private Integer rectificationJobsId;
    //整改人用户id
    private Integer rectificationUserId;
    //整改人用户id
    @Transient
    private String rectificationUserName;
    //专项资金
    private String specialFunds;
    //截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastDate;
    //预警通知手机号
    private String telephone;
    //是否整改完成
    private Integer status;
    //整改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date rectificationDate;
    //整改后图片
    private String rectificationImg;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(updatable = false)
    private Date createDate;

    /**
     * 新增时执行的函数
     */
    @PrePersist
    void preInsert() {
        if (createDate == null) {
            createDate = new Date();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAbnormalityImg() {
        return abnormalityImg;
    }

    public void setAbnormalityImg(String abnormalityImg) {
        this.abnormalityImg = abnormalityImg;
    }

    public Integer getRectificationEnterpriseId() {
        return rectificationEnterpriseId;
    }

    public void setRectificationEnterpriseId(Integer rectificationEnterpriseId) {
        this.rectificationEnterpriseId = rectificationEnterpriseId;
    }

    public Integer getRectificationDepartmentId() {
        return rectificationDepartmentId;
    }

    public void setRectificationDepartmentId(Integer rectificationDepartmentId) {
        this.rectificationDepartmentId = rectificationDepartmentId;
    }

    public Integer getRectificationJobsId() {
        return rectificationJobsId;
    }

    public void setRectificationJobsId(Integer rectificationJobsId) {
        this.rectificationJobsId = rectificationJobsId;
    }

    public Integer getRectificationUserId() {
        return rectificationUserId;
    }

    public void setRectificationUserId(Integer rectificationUserId) {
        this.rectificationUserId = rectificationUserId;
    }

    public String getRectificationUserName() {
        return rectificationUserName;
    }

    public void setRectificationUserName(String rectificationUserName) {
        this.rectificationUserName = rectificationUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRectificationDate() {
        return rectificationDate;
    }

    public void setRectificationDate(Date rectificationDate) {
        this.rectificationDate = rectificationDate;
    }

    public String getRectificationImg() {
        return rectificationImg;
    }

    public void setRectificationImg(String rectificationImg) {
        this.rectificationImg = rectificationImg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSpecialFunds() {
        return specialFunds;
    }

    public void setSpecialFunds(String specialFunds) {
        this.specialFunds = specialFunds;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
