package com.repositories;

import com.entity.BaseStudent;
import com.searchVO.BaseStudentSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生
 */
public interface BaseStudentRepositoryCustom {

    List<BaseStudent> getList(BaseStudentSearchVO searchVO);
}
