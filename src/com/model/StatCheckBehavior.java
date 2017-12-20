package com.model;

/**
 * Created by 高宇飞 on 2017/11/14 17:33
 */
public class StatCheckBehavior {
    private String departmentName;
    private String jobsName;
    private String userName;
    private int lieAboutCount;
    private int badJudgmentCount;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLieAboutCount() {
        return lieAboutCount;
    }

    public void setLieAboutCount(int lieAboutCount) {
        this.lieAboutCount = lieAboutCount;
    }

    public int getBadJudgmentCount() {
        return badJudgmentCount;
    }

    public void setBadJudgmentCount(int badJudgmentCount) {
        this.badJudgmentCount = badJudgmentCount;
    }
}
