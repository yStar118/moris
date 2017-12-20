package com.repositories;

import com.app.dto.AppStatcheckResultDTO;
import com.entity.StatCheckResult;
import com.model.StatCheckBehavior;
import com.searchVO.StatCheckResultCustomSearchVO;
import com.searchVO.StatCheckResultSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/8 14:23:51
 * 检查结果报表
 */
public interface StatCheckResultRepositoryCustom {

    /**
     * 每日定时任务查询统计数据
     *
     * @return 统计数据集合
     */
    List<StatCheckResult> getListForJob();

    /**
     * 根据岗位分组  统计检查结果数据
     *
     * @param searchVO 查询VO
     * @return 检查结果数据集合
     */
    List<StatCheckResult> getListForJobs(StatCheckResultSearchVO searchVO);

    /**
     * 根据设备分组  统计检查结果数据
     *
     * @param searchVO 查询VO
     * @return 检查结果数据集合
     */
    List<StatCheckResult> getListForEquipment(StatCheckResultSearchVO searchVO);


    /**
     * 根据检查对象分组  统计检查结果数据
     *
     * @param searchVO 查询VO
     * @return 检查结果数据集合
     */
    List<StatCheckResult> getListForObject(StatCheckResultSearchVO searchVO);


    /**
     * 根据检查点分组  统计检查结果数据
     *
     * @param searchVO 查询VO
     * @return 检查结果数据集合
     */
    List<StatCheckResult> getListForPoint(StatCheckResultSearchVO searchVO);

    /**
     * app获取检查结果报表  周
     *
     * @return 查询结果list
     */
    List<AppStatcheckResultDTO> getListForAppByWeek(Integer typeValue);

    /**
     * app获取检查结果报表  月
     *
     * @return 查询结果list
     */
    List<AppStatcheckResultDTO> getListForAppByMonth(Integer typeValue);


    /**
     * 谎报误判统计  月
     *
     * @return 查询结果list
     */
    List<StatCheckBehavior> getListForCheckBehavior(StatCheckResultCustomSearchVO searchVO);


}
