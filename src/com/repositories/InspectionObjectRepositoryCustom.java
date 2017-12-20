package com.repositories;

import com.entity.InspectionObject;
import com.searchVO.InspectionObjectSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
public interface InspectionObjectRepositoryCustom {

    List<InspectionObject> getList(InspectionObjectSearchVO searchVO);

    List<InspectionObject> findByJobsCodeGroupByEquipmentName(String jobsCode);
}
