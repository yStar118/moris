package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
public class BaseNoticeSearchVO extends CommonSearchVO{

    private String name;//通知名称
    private Integer type;  //通知类型   1:报警预警   2：系统通知  3 命令通知

    public String getName() {
        return name;
    }

    /**
     * 这里拼接模糊查询的字段值
     *
     * @return 字段值
     */
    public String getNameParam() {
        return "%" + name + "%";
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
