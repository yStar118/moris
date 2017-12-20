package com.repositories;

import com.entity.StatCheckPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 高宇飞 on 2017/11/6 16:27:47
 * 检查点统计  （检查行为统计报表）
 */
public interface StatCheckPointRepository extends CrudRepository<StatCheckPoint, Integer>, StatCheckPointRepositoryCustom {
}
