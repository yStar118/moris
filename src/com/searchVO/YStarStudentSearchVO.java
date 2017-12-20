package com.searchVO;

/**
 * Created by wyx-pc on 2017/12/18.
 */
public class YStarStudentSearchVO extends YStarCommonSearchVO{


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNameParam(){ return "%"+name+"%";}




}
