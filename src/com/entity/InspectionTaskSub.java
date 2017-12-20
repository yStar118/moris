package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
@Entity
@Table(name = "inspection_task_sub")
public class InspectionTaskSub {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    //任务id
    private String taskId;
    //任务id
    @Transient
    private String taskName;
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
    /*tag值*/
    @Transient
    private String tagValue;
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
    //检查结果参数
    private String checkResultValue;
    //检查结果   0  正常  1异常
    private Integer checkResult;
    //检查人姓名
    private String checkUserName;
    //检查时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date checkDate;
    //备注
    private String remark;
    //附件文件路径
    private String filePath;
    //检查行为   0 默认  1谎报  2误判
    private Integer checkBehavior;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getContingencyScene() {
        return contingencyScene;
    }

    public void setContingencyScene(String contingencyScene) {
        this.contingencyScene = contingencyScene;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public Integer getCheckBehavior() {
        return checkBehavior;
    }

    public void setCheckBehavior(Integer checkBehavior) {
        this.checkBehavior = checkBehavior;
    }
}
