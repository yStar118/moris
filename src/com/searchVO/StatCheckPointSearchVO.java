package com.searchVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/6 17:41:17
 */
public class StatCheckPointSearchVO extends CommonSearchVO {

    private String departmentName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statEndDate;
    private Integer type; //   1 日统计    2 月统计  3  年统计
    private Integer year; //   年
    private Integer month; //  月

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentNameParam() {
        return "%" + departmentName + "%";
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getStatStartDate() {
        return statStartDate;
    }

    public void setStatStartDate(Date statStartDate) {
        this.statStartDate = statStartDate;
    }

    public Date getStatEndDate() {
        return statEndDate;
    }

    public void setStatEndDate(Date statEndDate) {
        this.statEndDate = statEndDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public String getMonthParam() {
        return year + "-" + month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
