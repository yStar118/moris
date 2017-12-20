package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wyx-pc on 2017/12/18.
 */

@Entity
@Table(name="inspection_options")
public class YStarInspectionOptions {
    @Id
    //GeneratedValue jpa为实体设置的主键。strategy = GenerationType.IDENTITY四中方式之一 表示主键自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //编号
    private String code;
    /*名称*/
    private String name;
    /*检查内容描述*/
    private String content;
    /*检查手段*/
    private String method;
    /*备注*/
    private String remark;
    //是否主管判断 （0 否 1 是）
    private Integer isSubJudge;
    //主管检查标准
    private String subJudgeStandard;
    //检查项的意义
    private String meaning;
    //是否启用（0 否 1 是）
    private Integer isStart;
    //检查项属性(1 日常检查 2 特殊检查)
    private Integer type;
    /* 创建时间

     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Column(updatable = false)
    private Date createDate;
    //修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    //创建人
    @Column(updatable = false)
    private Integer createUser;
    //创建人姓名
    @Transient   //@Transient表示不可被序列化
    private String createUserName;
    //修改人
    private Integer updateUser;
    //x修改人姓名
    @Transient
    private String updateUserName;
    //导入行号
    @Transient
    private int row_index;
    /*
    新增时执行的函数
     */

}





