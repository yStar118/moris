package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/6/8.
 * 部门查询VO
 */
public class OrganizationDepartmentSearchVO extends CommonSearchVO{
    private String code;
    private String name;

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

    public String getCode() {
        return code;
    }

    public String getCodeParam() {
        return "%" + code + "%";
    }

    public void setCode(String code) {
        this.code = code;
    }
}
