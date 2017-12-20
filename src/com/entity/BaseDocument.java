package com.entity;

import com.app.views.DocumentViews;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
@Entity
@Table(name = "base_document")
public class BaseDocument {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonView(DocumentViews.GetExchangeList.class)
    private String id;
    //文件分类
    @JsonView(DocumentViews.GetExchangeList.class)
    private String category;
    //文件名称
    @JsonView(DocumentViews.GetExchangeList.class)
    private String fileName;
    //文件路径
    @JsonView(DocumentViews.GetExchangeList.class)
    private String filePath;
    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /*创建人*/
    private Integer createUser;
    /*创建人名字*/
    @Transient
    private String createUserName;

    /**
     * 新增时执行的函数
     */
    @PrePersist
    void preInsert() {
        if (createDate == null) {
            createDate = new Date();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
