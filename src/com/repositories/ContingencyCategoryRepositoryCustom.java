package com.repositories;

import com.entity.ContingencyCategory;
import com.searchVO.ContingencyCategorySearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案分类
 */
public interface ContingencyCategoryRepositoryCustom {

    List<ContingencyCategory> getList(ContingencyCategorySearchVO searchVO);
}
