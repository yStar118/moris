package com.service;

import com.common.service.BaseService;
import com.entity.YStarStudent;
import com.repositories.YStarStudentRepository;
import com.searchVO.YStarStudentSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wyx-pc on 2017/12/18.
 */
@Service
public class YStarStudentService implements BaseService<YStarStudent, Integer>{
    @Autowired
   private YStarStudentRepository yStarStudentRepository;

    @Override
    public <S extends YStarStudent> S save(S entity) {
        return yStarStudentRepository.save(entity);
    }

    @Override
    public YStarStudent findOne(Integer primaryKey) {
        return yStarStudentRepository.findOne(primaryKey);
    }

    @Override
    public List<YStarStudent> findAll() {
        return IterableUtils.toList(yStarStudentRepository.findAll());
    }

    @Override
    public long count() {
        return yStarStudentRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        yStarStudentRepository.delete(primaryKey);

    }
    public List<YStarStudent>getList(YStarStudentSearchVO searchVO){
        return yStarStudentRepository.getList(searchVO);
    }
}
