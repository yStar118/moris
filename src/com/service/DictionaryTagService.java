package com.service;

import com.common.service.BaseService;
import com.entity.DictionaryTag;
import com.repositories.DictionaryTagRepository;
import com.searchVO.DictionaryTagSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/31 11:30:26
 * 字典- tag
 */
@Service
public class DictionaryTagService implements BaseService<DictionaryTag, Integer> {

    @Autowired
    private DictionaryTagRepository dictionaryTagRepository;

    @Override
    public <S extends DictionaryTag> S save(S entity) {
        return dictionaryTagRepository.save(entity);
    }

    public List<DictionaryTag> save(List<DictionaryTag> dictionaryTagList) {
        return IterableUtils.toList(dictionaryTagRepository.save(dictionaryTagList));
    }

    @Override
    public DictionaryTag findOne(Integer primaryKey) {
        return dictionaryTagRepository.findOne(primaryKey);
    }

    @Override
    public List<DictionaryTag> findAll() {
        return IterableUtils.toList(dictionaryTagRepository.findAll());
    }

    public List<DictionaryTag> getList(DictionaryTagSearchVO searchVO) {
        return dictionaryTagRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return dictionaryTagRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        dictionaryTagRepository.delete(primaryKey);
    }
}
