package com.app.views;

/**
 * Created by Bodil on 2016/10/11.
 * 基础Views
 */
public final class BaseViews {
    /**
     * 标志所有对象都可以直接访问
     */
    public interface Public {
    }

    /**
     * ListDTO对象的子集可以直接访问
     */
    public interface ForListDTO extends Public {
    }
}
