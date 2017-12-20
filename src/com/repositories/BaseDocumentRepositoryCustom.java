package com.repositories;

import com.entity.BaseDocument;
import com.searchVO.BaseDocumentSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
public interface BaseDocumentRepositoryCustom {

    List<BaseDocument> getList(BaseDocumentSearchVO searchVO);

}
