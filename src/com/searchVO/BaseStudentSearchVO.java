package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生
 */
public class BaseStudentSearchVO extends CommonSearchVO {

    private String name;

    public String getName() {
        return name;
    }

    public String getNameParam() {
        return "%" + name + "%";
    }

    public void setName(String name) {
        this.name = name;
    }
}
