package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
public class InspectionTaskSearchVO extends CommonSearchVO {

    //任务类型  1 巡检单元   2 隐患排查
    private Integer type;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
