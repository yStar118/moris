package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/11.
 * 检查任务子表
 */
public class InspectionTaskSubSearchVO extends CommonSearchVO {

    /*任务id*/
    private String taskId;
    /*检查点名称*/
    private String pointName;
    //检查结果   0  正常  1异常
    private Integer checkResult;
    //状态   1 巡检单元   2 临时
    private Integer type;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
