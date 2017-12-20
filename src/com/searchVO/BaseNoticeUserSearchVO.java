package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
public class BaseNoticeUserSearchVO extends CommonSearchVO{

    //通知id
    private String noticeId;
    //用户id
    private int userId;
    //通知类型   1:报警预警   2：系统通知  3 命令通知
    private Integer type;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
