package com.entity;

import javax.persistence.*;

/**
 * Created by 高宇飞 on 2017/8/31 11:26:10
 * 字典- tag
 */
@Entity
@Table(name = "dictionary_tag")
public class DictionaryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*tag编号*/
    private String tag;
    /*tag值*/
    private String tagValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
