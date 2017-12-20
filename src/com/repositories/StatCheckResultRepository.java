package com.repositories;

import com.entity.StatCheckResult;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 高宇飞 on 2017/11/8 14:22:54
 *
 */
public interface StatCheckResultRepository extends CrudRepository<StatCheckResult,Integer>, StatCheckResultRepositoryCustom{
}
