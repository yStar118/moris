package com.repositories;

import com.entity.BaseStudent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生
 */
public interface BaseStudentRepository extends CrudRepository<BaseStudent,Integer> ,BaseStudentRepositoryCustom{
}
