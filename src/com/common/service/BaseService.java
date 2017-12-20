package com.common.service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/5/11.
 * 基础Service
 */
public interface BaseService<T, ID> {
    /**
     * 保存传入的单个对象
     *
     * @param entity Model对象
     * @param <S>    继承自Model的对象
     * @return 保存后的继承自Model的对象
     */
    <S extends T> S save(S entity);

    /**
     * 根据主键获取一个对象
     *
     * @param primaryKey 主键
     * @return 对象
     */
    T findOne(ID primaryKey);

    /**
     * 获取对象迭代（集合）
     *
     * @return 对象迭代（集合）
     */
    List<T> findAll();

    /**
     * 获取对象总数
     *
     * @return 对象总数
     */
    long count();

    /**
     * 删除一个对象
     *
     * @param primaryKey 删除对象id
     */
    void delete(ID primaryKey);

}
