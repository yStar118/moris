package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/5/25.
 * 公共查询VO
 */
public class CommonSearchVO {
    private int page = 1;//页码
    private int index = 0;//
    private int length = 15;//每页条目数
    private int total = 0;//总条目数
    private int draw;


    public int getPageParams() {
        return index >= length ? ((index - (index % length)) / length + 1) : 1;
    }

    public int getIndexParams() {
        return page <= 1 ? 0 : (page - 1) * length;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
