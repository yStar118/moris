package com.common.vo;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 上传文件返回JSON
 */
public class UploadResult {
    private boolean success;//是否成功
    private String message;//消息
    private String createFileName;//文件名称
    private String createFilePath;//文件路径

    public UploadResult() {
    }

    public UploadResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

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

    public String getCreateFileName() {
        return createFileName;
    }

    public void setCreateFileName(String createFileName) {
        this.createFileName = createFileName;
    }

    public String getCreateFilePath() {
        return createFilePath;
    }

    public void setCreateFilePath(String createFilePath) {
        this.createFilePath = createFilePath;
    }
}
