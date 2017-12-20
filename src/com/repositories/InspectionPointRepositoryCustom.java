package com.repositories;

import com.entity.InspectionPoint;
import com.searchVO.InspectionPointSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
public interface InspectionPointRepositoryCustom {

    List<InspectionPoint> getList(InspectionPointSearchVO searchVO);

    List<String> getCategoryName();
}
