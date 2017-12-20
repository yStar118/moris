package com.repositories;

import com.entity.StatCheckPoint;
import com.searchVO.StatCheckPointSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/6 16:29:51
 */
public interface StatCheckPointRepositoryCustom {

    /**
     * 每日定时任务查询统计数据
     *
     * @return 统计数据集合
     */
    // @Procedure(name = "proc_voucher_deleteById")
    List<StatCheckPoint> getListForJob();

    /**
     * 检查行为日报表
     *
     * @param searchVO 查询VO
     * @return 检查行为统计结果集合
     */
    List<StatCheckPoint> getListForDay(StatCheckPointSearchVO searchVO);

    /**
     * 检查行为月报表
     *
     * @param searchVO 查询VO
     * @return 检查行为统计结果集合
     */
    List<StatCheckPoint> getListForMonth(StatCheckPointSearchVO searchVO);

    /**
     * 检查行为年报表
     *
     * @param searchVO 查询VO
     * @return 检查行为统计结果集合
     */
    List<StatCheckPoint> getListForYear(StatCheckPointSearchVO searchVO);
}
