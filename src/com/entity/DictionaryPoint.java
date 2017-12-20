package com.entity;

import javax.persistence.*;

/**
 * Created by 高宇飞 on 2017/8/31 13:57:59
 * 字典  检查点分类属性
 */
@Entity
@Table(name = "dictionary_point")
public class DictionaryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*名称*/
    private String name;
    /*类型  1检查对象分类   2检查对象属性*/
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
