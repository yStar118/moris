package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/6/24.
 * 岗位
 */
public class OrganizationJobsSearchVO extends CommonSearchVO {
    private String name;
    private String departmentCode;

    public String getName() {
        return name;
    }

    /**
     * 这里拼接模糊查询的字段值
     *
     * @return 字段值
     */
    public String getNameParam() {
        return "%" + name + "%";
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
