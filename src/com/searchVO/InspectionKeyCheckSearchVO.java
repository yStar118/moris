package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/7/6.
 * 关键检查对象
 */
public class InspectionKeyCheckSearchVO extends CommonSearchVO {

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
}
