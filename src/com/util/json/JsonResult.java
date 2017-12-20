package com.util.json;

@SuppressWarnings("rawtypes")
public class JsonResult {
    private int status;
    private String message;//
    private int total;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonResult(int status, String message, int total) {
        this(status, message);
        this.total = total;
    }

    public JsonResult(int status, String message, Object data) {
        this(status, message);
        this.data = data;
    }

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
