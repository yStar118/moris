package com.common.vo;

import com.app.views.BaseViews;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Bodil on 2016/9/12.
 * Json结果
 */
public class JsonResult {
    @JsonView(BaseViews.Public.class)
    private boolean success;//是否成功
    @JsonView(BaseViews.Public.class)
    private String message;//消息
    @JsonView(BaseViews.Public.class)
    private Object data;//额外的数据

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonResult(boolean success, String message, Object data) {
        this(success, message);
        this.data = data;
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String message) {
        this(success);
        this.message = message;
    }

    /**
     * 成功的Json结果
     */
    private static final JsonResult success_instance = new JsonResult(true);

    /**
     * 获取成功的Json结果
     * @return 成功的Json结果
     */
    public static JsonResult getSuccess_instance() {
        return success_instance;
    }
}
