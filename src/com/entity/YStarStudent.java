package com.entity;

import javax.persistence.*;

/**
 * Created by wyx-pc on 2017/12/18.
 * 学生实体类
 */
@Entity
@Table(name = "base_student")
public class YStarStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //名称
    private String name;
    //性别 (0 nv  1  nan)
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
