package com.repositories;

import com.entity.InspectionPointExternal;
import com.searchVO.InspectionPointExternalSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/6.
 * 检查点关联
 */
public interface InspectionPointExternalRepositoryCustom {

    List<InspectionPointExternal> getList(InspectionPointExternalSearchVO searchVO);

}
