package com.service;

import com.common.service.BaseService;
import com.entity.DictionaryPoint;
import com.repositories.DictionaryPointRepository;
import com.searchVO.DictionaryPointSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/31 14:17:38
 * 字典  检查点分类属性
 */
@Service
public class DictionaryPointService implements BaseService<DictionaryPoint, Integer> {

    @Autowired
    private DictionaryPointRepository dictionaryPointRepository;

    @Override
    public <S extends DictionaryPoint> S save(S entity) {
        return dictionaryPointRepository.save(entity);
    }

    @Override
    public DictionaryPoint findOne(Integer primaryKey) {
        return dictionaryPointRepository.findOne(primaryKey);
    }

    @Override
    public List<DictionaryPoint> findAll() {
        return IterableUtils.toList(dictionaryPointRepository.findAll());
    }

    public List<DictionaryPoint> getList(DictionaryPointSearchVO searchVO) {
        return dictionaryPointRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return dictionaryPointRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        dictionaryPointRepository.delete(primaryKey);
    }
}
