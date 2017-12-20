package com.repositories;

import com.app.dto.InspectionTaskDTO;
import com.entity.InspectionTask;
import com.searchVO.InspectionTaskSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
public interface InspectionTaskRepositoryCustom {

    List<InspectionTask> getListByTask(InspectionTaskSearchVO searchVO);

    List<InspectionTask> getListByProject(InspectionTaskSearchVO searchVO);

    List<InspectionTask> getHistoryTask(int beforeDays, int userId);

    List<InspectionTaskDTO> getListByProjectForApi(String departmentCode);

    List<InspectionTask> getResultList(InspectionTaskSearchVO searchVO);

    List<InspectionTask> findByCheckUserId(int userId);

    /**
     * 查找没有分配的任务，按照部门编号分组
     */
    List<InspectionTask> findByNotAllotmentTask();

    List<InspectionTask> findByEmptyCheckTask();
}
