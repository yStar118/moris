package com.service;

import com.common.service.BaseService;
import com.entity.BaseWarning;
import com.repositories.BaseWarningRepository;
import com.searchVO.BaseWarningSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/9/1 22:41
 */
@Service
public class BaseWarningService implements BaseService<BaseWarning, Integer> {

    @Autowired
    private BaseWarningRepository baseWarningRepository;

    @Override
    public <S extends BaseWarning> S save(S entity) {
        return baseWarningRepository.save(entity);
    }

    @Override
    public BaseWarning findOne(Integer primaryKey) {
        return baseWarningRepository.findOne(primaryKey);
    }

    @Override
    public List<BaseWarning> findAll() {
        return IterableUtils.toList(baseWarningRepository.findAll());
    }

    @Override
    public long count() {
        return baseWarningRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        baseWarningRepository.delete(primaryKey);
    }

    public List<BaseWarning> getList(BaseWarningSearchVO searchVO) {
        return baseWarningRepository.getList(searchVO);
    }
}
