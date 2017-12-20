package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/31 14:00:38
 * 字典  检查点分类属性
 */
public class DictionaryPointSearchVO extends CommonSearchVO {

    private String name;
    private Integer type;


    public String getNameParam() {
        return "%" + name + "%";
    }

    public String getName() {
        return name;
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
