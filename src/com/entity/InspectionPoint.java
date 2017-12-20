package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
@Entity
@Table(name = "inspection_point")
public class InspectionPoint {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    //检查点名称
    private String name;
    //部门编号
    private String departmentCode;
    //部门名称
    private String departmentName;
    //岗位编号
    private String jobsCode;
    //岗位名称
    private String jobsName;
    //设备编号
    private String equipmentCode;
    //设备名称
    private String equipmentName;
    //检查对象编号
    private String objectCode;
    //检查对象名称
    private String objectName;
    //型号规格
    private String specifications;
    //备注
    private String remark;
    //tag
    private String tag;
    //gps
    private String gps;
    //检测周期
    private String checkCycle;
    //检查次数
    private String checkFrequency;
    //检测时间
    private String checkTime;
    //检查间隔（最小间隔）
    private String checkInterval;
    //检查项ID
    private Integer checkOptionId;
    //检查项名称
    private String checkOptionName;
    //单位
    private String unit;
    //最小值
    private String minValue;
    //标准值
    private String standardValue;
    //最大值
    private String bigValue;
    //黄色预警值
    private String yellowWarning;
    //橙色预警值
    private String orangeWarning;
    //红色预警值
    private String redWarning;
    //是否主观判断
    private Integer isSubJudge;
    //主观判断结果
    private Integer subjectiveJudgment;
    //排序值
    private Integer displayOrder;
    //检查对象分类名称
    private String categoryName;
    //检查对象属性
    private String attributeName;
    //是否启用    0否 1是
    @Column(updatable = false)
    private Integer isStart;
    //启用时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(updatable = false)
    private Date startDate;
    //现场处置方案內容
    private String contingencyScene;
    //应急预案编号
    private String contingencyInfoCode;
    //应急预案名称
    private String contingencyInfoName;
    //重要等级
    private String importantLevel;
    //危险等级
    private String dangerLevel;
    //政府政策
    private String governmentPolicy;
    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(updatable = false)
    private Date createDate;
    /*修改时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    /*创建人*/
    @Column(updatable = false)
    private Integer createUser;
    /*创建人名字*/
    @Transient
    private String createUserName;
    /*修改人*/
    private Integer updateUser;
    /*修改人名字*/
    @Transient
    private String updateUserName;

    /**
     * 新增时执行的函数
     */
    @PrePersist
    void preInsert() {
        if (updateDate == null) {
            updateDate = new Date();
        }
        if (createDate == null) {
            createDate = new Date();
        }
    }

    /**
     * 修改时执行的函数
     */
    @PreUpdate
    void preUpdate() {
        if (updateDate == null) {
            updateDate = new Date();
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getCheckCycle() {
        return checkCycle;
    }

    public void setCheckCycle(String checkCycle) {
        this.checkCycle = checkCycle;
    }

    public String getCheckFrequency() {
        return checkFrequency;
    }

    public void setCheckFrequency(String checkFrequency) {
        this.checkFrequency = checkFrequency;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckInterval() {
        return checkInterval;
    }

    public void setCheckInterval(String checkInterval) {
        this.checkInterval = checkInterval;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public String getYellowWarning() {
        return yellowWarning;
    }

    public void setYellowWarning(String yellowWarning) {
        this.yellowWarning = yellowWarning;
    }

    public String getOrangeWarning() {
        return orangeWarning;
    }

    public void setOrangeWarning(String orangeWarning) {
        this.orangeWarning = orangeWarning;
    }

    public String getRedWarning() {
        return redWarning;
    }

    public void setRedWarning(String redWarning) {
        this.redWarning = redWarning;
    }

    public Integer getSubjectiveJudgment() {
        return subjectiveJudgment;
    }

    public void setSubjectiveJudgment(Integer subjectiveJudgment) {
        this.subjectiveJudgment = subjectiveJudgment;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getContingencyInfoCode() {
        return contingencyInfoCode;
    }

    public void setContingencyInfoCode(String contingencyInfoCode) {
        this.contingencyInfoCode = contingencyInfoCode;
    }

    public String getContingencyInfoName() {
        return contingencyInfoName;
    }

    public void setContingencyInfoName(String contingencyInfoName) {
        this.contingencyInfoName = contingencyInfoName;
    }

    public Integer getCheckOptionId() {
        return checkOptionId;
    }

    public void setCheckOptionId(Integer checkOptionId) {
        this.checkOptionId = checkOptionId;
    }

    public String getCheckOptionName() {
        return checkOptionName;
    }

    public void setCheckOptionName(String checkOptionName) {
        this.checkOptionName = checkOptionName;
    }

    public Integer getIsSubJudge() {
        return isSubJudge;
    }

    public void setIsSubJudge(Integer isSubJudge) {
        this.isSubJudge = isSubJudge;
    }

    public String getBigValue() {
        return bigValue;
    }

    public void setBigValue(String bigValue) {
        this.bigValue = bigValue;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String getGovernmentPolicy() {
        return governmentPolicy;
    }

    public void setGovernmentPolicy(String governmentPolicy) {
        this.governmentPolicy = governmentPolicy;
    }

    public String getJobsCode() {
        return jobsCode;
    }

    public void setJobsCode(String jobsCode) {
        this.jobsCode = jobsCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getContingencyScene() {
        return contingencyScene;
    }

    public void setContingencyScene(String contingencyScene) {
        this.contingencyScene = contingencyScene;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
