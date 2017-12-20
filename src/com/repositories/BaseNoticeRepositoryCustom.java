package com.repositories;

import com.entity.BaseNotice;
import com.searchVO.BaseNoticeSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
public interface BaseNoticeRepositoryCustom {

    List<BaseNotice> getList(BaseNoticeSearchVO searchVO);

    void setFilePathAndPathNameById(String filePath, String fileName, int id);
}
