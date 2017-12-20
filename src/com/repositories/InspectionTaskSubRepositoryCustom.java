package com.repositories;

import com.entity.InspectionTaskSub;
import com.model.InspectionTaskAlarm;
import com.searchVO.InspectionTaskSubSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/11.
 */
public interface InspectionTaskSubRepositoryCustom {

    List<InspectionTaskSub> getList(InspectionTaskSubSearchVO searchVO);

    List<InspectionTaskSub> findByTaskId(String taskId);

    @SuppressWarnings({"unchecked", "rawtypes"})
    InspectionTaskAlarm findByPointIdForAlarm(String pointId);
}
