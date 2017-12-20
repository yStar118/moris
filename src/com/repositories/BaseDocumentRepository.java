package com.repositories;

import com.entity.BaseDocument;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
public interface BaseDocumentRepository extends CrudRepository<BaseDocument,String> , BaseDocumentRepositoryCustom{
}
