package com.repositories;

import com.entity.ContingencyCategory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案分类
 */
public interface ContingencyCategoryRepository extends CrudRepository<ContingencyCategory,Integer> ,ContingencyCategoryRepositoryCustom {
}
