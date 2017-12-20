package com.service;

import com.common.service.BaseService;
import com.entity.BaseDocument;
import com.repositories.BaseDocumentRepository;
import com.searchVO.BaseDocumentSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
@Service
public class BaseDocumentService implements BaseService<BaseDocument, String> {

    @Autowired
    private BaseDocumentRepository baseDocumentRepository;

    @Override
    public <S extends BaseDocument> S save(S entity) {
        return baseDocumentRepository.save(entity);
    }

    @Override
    public BaseDocument findOne(String primaryKey) {
        return baseDocumentRepository.findOne(primaryKey);
    }

    @Override
    public List<BaseDocument> findAll() {
        return IterableUtils.toList(baseDocumentRepository.findAll());
    }

    @Override
    public long count() {
        return baseDocumentRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        baseDocumentRepository.delete(primaryKey);
    }

    public List<BaseDocument> getList(BaseDocumentSearchVO searchVO) {
        return baseDocumentRepository.getList(searchVO);
    }
}
