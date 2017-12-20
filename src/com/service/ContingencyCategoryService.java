package com.service;

import com.common.service.BaseService;
import com.entity.ContingencyCategory;
import com.repositories.ContingencyCategoryRepository;
import com.searchVO.ContingencyCategorySearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案分类
 */
@Service
public class ContingencyCategoryService implements BaseService<ContingencyCategory, Integer> {

    @Autowired
    private ContingencyCategoryRepository contingencyCategoryRepository;

    @Override
    public <S extends ContingencyCategory> S save(S entity) {
        return contingencyCategoryRepository.save(entity);
    }

    @Override
    public ContingencyCategory findOne(Integer primaryKey) {
        return contingencyCategoryRepository.findOne(primaryKey);
    }

    @Override
    public List<ContingencyCategory> findAll() {
        return IterableUtils.toList(contingencyCategoryRepository.findAll());
    }

    public List<ContingencyCategory> getList(ContingencyCategorySearchVO searchVO) {
        return contingencyCategoryRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return contingencyCategoryRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        contingencyCategoryRepository.delete(primaryKey);
    }
}
