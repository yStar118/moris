package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/5.
 * 检查方案查询VO
 */
public class InspectionPlanSearchVO extends CommonSearchVO {
    private String name;
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
