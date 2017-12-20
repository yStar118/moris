package com.app.dto;

import com.app.views.BaseViews;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsy on 2016/10/11.
 * 列表DTO
 */
public class ListDTO<T> {
    @JsonView(BaseViews.ForListDTO.class)
    private int total;//总条目数
    @JsonView(BaseViews.ForListDTO.class)
    private List<T> list = new ArrayList<>();//结果列表

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
