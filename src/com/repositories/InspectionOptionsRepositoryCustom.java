package com.repositories;

import com.entity.InspectionOptions;
import com.searchVO.InspectionOptionsSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/30.
 * 检查项
 */
public interface InspectionOptionsRepositoryCustom {

    List<InspectionOptions> getList(InspectionOptionsSearchVO searchVO);
}
