package com.service;

import com.common.service.BaseService;
import com.entity.ContingencyScene;
import com.repositories.ContingencySceneRepository;
import com.searchVO.ContingencySceneSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/8.
 * 现场处置方案
 */
@Service
public class ContingencySceneService implements BaseService<ContingencyScene, Integer> {

    private final ContingencySceneRepository contingencySceneRepository;

    @Autowired
    public ContingencySceneService(ContingencySceneRepository contingencySceneRepository) {
        this.contingencySceneRepository = contingencySceneRepository;
    }

    @Override
    public <S extends ContingencyScene> S save(S entity) {
        return contingencySceneRepository.save(entity);
    }

    @Override
    public ContingencyScene findOne(Integer primaryKey) {
        return contingencySceneRepository.findOne(primaryKey);
    }

    @Override
    public List<ContingencyScene> findAll() {
        return IterableUtils.toList(contingencySceneRepository.findAll());
    }

    public List<ContingencyScene> getList(ContingencySceneSearchVO searchVO) {
        return contingencySceneRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return contingencySceneRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        contingencySceneRepository.delete(primaryKey);
    }
}
