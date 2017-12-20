package com.repositories;

import com.entity.YStarStudent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wyx-pc on 2017/12/18.
 */
public interface YStarStudentRepository extends CrudRepository<YStarStudent, Integer>,YStarStudentRepositoryCustom {


}
