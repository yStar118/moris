package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/6.
 * 检查点关联表
 */
public class InspectionPointExternalSearchVO extends CommonSearchVO {

    /*外部编号（关联表编号）*/
    private String externalCode;
    private String planId;
    private String name;

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getNameParam() {
        return "%" + name + "%";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
