package com.repositories;

import com.entity.ContingencyInfo;
import com.searchVO.ContingencyInfoSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案
 */
public interface ContingencyInfoRepositoryCustom {

    List<ContingencyInfo> getList(ContingencyInfoSearchVO searchVO);

    void setFilePathAndPathNameById(String filePath, String fileName, Integer id);
}
