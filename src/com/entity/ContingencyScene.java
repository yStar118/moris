package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 现场处置方案
 */
@Entity
@Table(name = "contingency_scene")
public class ContingencyScene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*名称*/
    private String name;
    /*内容*/
    private String content;
    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(updatable = false)
    private Date createDate;
    /*修改时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    /*创建人*/
    @Column(updatable = false)
    private Integer createUser;
    /*创建人名字*/
    @Transient
    private String createUserName;
    /*修改人*/
    private Integer updateUser;
    /*修改人名字*/
    @Transient
    private String updateUserName;
    @Transient
    private String contentTxt;

    /**
     * 新增时执行的函数
     */
    @PrePersist
    void preInsert() {
        if (updateDate == null) {
            updateDate = new Date();
        }
        if (createDate == null) {
            createDate = new Date();
        }
    }

    /**
     * 修改时执行的函数
     */
    @PreUpdate
    void preUpdate() {
        if (updateDate == null) {
            updateDate = new Date();
        }
    }

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

    public String getContent() {
        return content;
    }

    /**
     * 获取回答内容摘要（移除html标签后的文本）
     *
     * @return 回答内容摘要
     */
    public String getContent_summary() {
        if (StringUtils.isEmpty(this.content)) return null;

        String clean_content = Jsoup.parse(this.content).text();
        if (clean_content.length() <= 240) return clean_content;

        return clean_content.substring(0, 240) + "……";
    }

    @SuppressWarnings("Duplicates")
    @JsonIgnore
    public String getReplaceContent() {
        if (StringUtils.isEmpty(this.content)) {
            return "";
        }
        Pattern p_img = Pattern.compile("(<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>)");
        Matcher m_img = p_img.matcher(content);
        while (m_img.find()) {
            String img = m_img.group(1); //m_img.group(1) 为获得整个img标签  m_img.group(2) 为获得src的值
            content = content.replace(img, "<a class=\"replaceImg\" data-src=\"" + m_img.group(2) + "\">[图片]</a>");
        }
        Pattern p_pbr = Pattern.compile("(<p> | </p>)");
        Matcher m_pbr = p_pbr.matcher(content);
        while (m_pbr.find()) {
            String pbr = m_pbr.group(1); //替换掉p标签  防止换行
            content = content.replace(pbr, " ");
        }
        content = content.replaceAll("<p>|</p>", "");
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getContentTxt() {
        return getReplaceContent();
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }
}
