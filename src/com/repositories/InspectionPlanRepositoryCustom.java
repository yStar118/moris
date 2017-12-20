package com.repositories;

import com.entity.InspectionPlan;
import com.searchVO.InspectionPlanSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/5.
 */
public interface InspectionPlanRepositoryCustom {

    List<InspectionPlan> getList(InspectionPlanSearchVO searchVO);
}
