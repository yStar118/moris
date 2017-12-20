package com.repositories;

import com.entity.BaseWarning;
import com.searchVO.BaseWarningSearchVO;

import java.util.List;

public interface BaseWarningRepositoryCustom {
    List<BaseWarning> getList(BaseWarningSearchVO searchVO);
}
