package com.repositories;

import com.entity.ContingencyScene;
import com.searchVO.ContingencySceneSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/8.
 * 现场处置方案
 */
public interface ContingencySceneRepositoryCustom {
    
    List<ContingencyScene> getList(ContingencySceneSearchVO searchVO);
}
