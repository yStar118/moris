package com.app.dto;

import java.math.BigDecimal;

/**
 * Created by 高宇飞 on 2017/11/13 01:27:11
 */
public class AppStatcheckResultDTO {
    //统计名称    设备   岗位  检查对象 检查点
    private String statName;
    //正常率
    private BigDecimal correctRate;
    //正常数量
    private int errorQuantity;

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public BigDecimal getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(BigDecimal correctRate) {
        this.correctRate = correctRate;
    }

    public int getErrorQuantity() {
        return errorQuantity;
    }

    public void setErrorQuantity(int errorQuantity) {
        this.errorQuantity = errorQuantity;
    }
}
