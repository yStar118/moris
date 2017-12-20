package com.common.vo;

/**
 * Created by 1553280431@qq.com on 2017/6/8.
 * 导入错误原因VO
 */
public class ErrorImportVO {
    private int rowIndex;//错误行号
    private String reason; //错误原因

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
