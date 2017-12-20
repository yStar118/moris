package com.service;

import com.common.service.BaseService;
import com.entity.BaseStudent;
import com.repositories.BaseStudentRepository;
import com.searchVO.BaseStudentSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生
 */
@Service
public class BaseStudentService implements BaseService<BaseStudent, Integer> {

    @Autowired
    private BaseStudentRepository baseStudentRepository;

    @Override
    public <S extends BaseStudent> S save(S entity) {
        return baseStudentRepository.save(entity);
    }

    @Override
    public BaseStudent findOne(Integer primaryKey) {
        return baseStudentRepository.findOne(primaryKey);
    }

    @Override
    public List<BaseStudent> findAll() {
        return IterableUtils.toList(baseStudentRepository.findAll());
    }

    @Override
    public long count() {
        return baseStudentRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        baseStudentRepository.delete(primaryKey);
    }

    public List<BaseStudent> getList(BaseStudentSearchVO searchVO) {
        return baseStudentRepository.getList(searchVO);
    }
}
