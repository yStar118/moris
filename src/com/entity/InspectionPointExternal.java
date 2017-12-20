package com.entity;

import javax.persistence.*;

/**
 * Created by 高宇飞 on 2017/8/5.
 * 检查点关联表
 */
@Entity
@Table(name = "inspection_point_external")
public class InspectionPointExternal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //外部编号（关联表编号）
    private String externalCode;
    //检查点id
    private String pointId;
    //检查点名称
    private String pointName;
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
    //tag
    private String tag;
    //gps
    private String gps;
    //是否主观判断   0 否  1  是
    private Integer isSubJudge;
    //主观判断结果   0 错  1 对
    private Integer subjectiveJudgment;
    //单位
    private String unit;
    //最小值
    private String minValue;
    //标准值
    private String standardValue;
    //最大值v
    private String bigValue;
    //黄色预警值
    private String yellowWarning;
    //橙色预警值
    private String orangeWarning;
    //红色预警值
    private String redWarning;
    //排序值
    private Integer displayOrder;
    //现场处置方案内容
    private String contingencyScene;
    //应急预案编号
    private String contingencyInfoCode;
    //重要等级
    private String importantLevel;
    //危险等级
    private String dangerLevel;
    //政府政策
    private String governmentPolicy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
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

    public String getBigValue() {
        return bigValue;
    }

    public void setBigValue(String bigValue) {
        this.bigValue = bigValue;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getContingencyInfoCode() {
        return contingencyInfoCode;
    }

    public void setContingencyInfoCode(String contingencyInfoCode) {
        this.contingencyInfoCode = contingencyInfoCode;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getJobsCode() {
        return jobsCode;
    }

    public void setJobsCode(String jobsCode) {
        this.jobsCode = jobsCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getContingencyScene() {
        return contingencyScene;
    }

    public void setContingencyScene(String contingencyScene) {
        this.contingencyScene = contingencyScene;
    }
}
