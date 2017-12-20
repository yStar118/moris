package com.searchVO;

/**
 * Created by wyx-pc on 2017/12/18.
 * 公共查询
 */
public class YStarCommonSearchVO {
     private int page = 1;//页码
     private int index = 0;//下标
     private int length = 10;//每页显示多少条
    private int total = 0;//总条数
    private int draw;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
}
