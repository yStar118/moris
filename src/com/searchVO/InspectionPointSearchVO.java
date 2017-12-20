package com.searchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
public class InspectionPointSearchVO extends CommonSearchVO {

    //部门编号
    private  List<String> departmentCodes;
    //岗位编号
    private String jobsCode;
    //检查点名称
    private String name;
    //检查对象分类
    private List<String> categoryList;
    //检查对象属性
    private List<String> attributeList;

    public String getJobsCode() {
        return jobsCode;
    }

    public void setJobsCode(String jobsCode) {
        this.jobsCode = jobsCode;
    }


    public List<String> getDepartmentCodes() {
        return departmentCodes;
    }

    public void setDepartmentCodes(List<String> departmentCodes) {
        this.departmentCodes = departmentCodes;
    }

    /**
     * 这里拼接模糊查询的字段值
     *
     * @return 字段值
     */
    public String getNameParam() {
        return "%" + name + "%";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<String> attributeList) {
        this.attributeList = attributeList;
    }
}
