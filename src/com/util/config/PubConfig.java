package com.util.config;

import org.springframework.stereotype.Component;

@Component
public class PubConfig {
    private String imageServer;//图片显示服务器地址
    private String imageUploadPath;//图片路径
    private String staticServer;//
    private String dynamicServer;//
    private Integer saveTaskDayNumber;//APP查看历史记录天数

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
        this.imageUploadPath = imageUploadPath;
    }

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getDynamicServer() {
        return dynamicServer;
    }

    public void setDynamicServer(String dynamicServer) {
        this.dynamicServer = dynamicServer;
    }

    public Integer getSaveTaskDayNumber() {
        return saveTaskDayNumber;
    }

    public void setSaveTaskDayNumber(Integer saveTaskDayNumber) {
        this.saveTaskDayNumber = saveTaskDayNumber;
    }
}
