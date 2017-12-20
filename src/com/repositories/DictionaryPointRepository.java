package com.repositories;

import com.entity.DictionaryPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 高宇飞 on 2017/8/31 14:01:16
 * 字典  检查点分类属性
 */
public interface DictionaryPointRepository extends CrudRepository<DictionaryPoint,Integer> ,DictionaryPointRepositoryCustom {
}
