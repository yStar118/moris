package com.repositories;

import com.entity.ContingencyScene;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/7/8.
 * 现场处置方案
 */
public interface ContingencySceneRepository extends CrudRepository<ContingencyScene,Integer>,ContingencySceneRepositoryCustom {
}
