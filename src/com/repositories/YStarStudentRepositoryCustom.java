package com.repositories;

import com.entity.YStarStudent;

import com.searchVO.YStarStudentSearchVO;

import java.util.List;

/**
 * Created by wyx-pc on 2017/12/18.
 */
public interface YStarStudentRepositoryCustom {
    List <YStarStudent> getList(YStarStudentSearchVO searchVO);
}
