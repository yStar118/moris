package com.entity;

import javax.persistence.*;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生  表
 */
@Entity
@Table(name = "base_student")
public class BaseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*名称*/
    private String name;
    /*性别  0: 男   1：女*/
    private Integer sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
