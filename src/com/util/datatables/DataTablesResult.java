package com.util.datatables;

/**
 * Created by 1553280431@qq.com on 2017/5/25.
 * <p>
 * datatable返回参数
 */
public class DataTablesResult {
    private int draw;  //防止跨站脚本（XSS）攻击
    private int recordsTotal; //即没有过滤的记录数（数据库里总共记录数）
    private int recordsFiltered; //过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
    private Object data; //结果集

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
