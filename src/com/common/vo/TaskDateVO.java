package com.common.vo;

import java.util.Date;

/**
 * Created by 高宇飞 on 2017/8/11.
 * 检查任务时间VO
 */
public class TaskDateVO {

    //是否超出制定时间
    private boolean isBeyond;
    //开始时间
    private Date startDate;
    //结束时间
    private Date endDate;

    public boolean isSuccess() {
        return isBeyond;
    }

    public TaskDateVO(boolean isBeyond, Date startDate, Date endDate) {
        this.isBeyond = isBeyond;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TaskDateVO(boolean isBeyond) {
        this.isBeyond = isBeyond;
    }

    public boolean isBeyond() {
        return isBeyond;
    }

    public void setBeyond(boolean beyond) {
        isBeyond = beyond;
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
}
