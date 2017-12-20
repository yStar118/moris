package com.repositories;

import com.entity.DictionaryTag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 高宇飞 on 2017/8/31 11:29:00
 * 字典- tag
 */
public interface DictionaryTagRepository extends CrudRepository<DictionaryTag,Integer> ,DictionaryTagRepositoryCustom  {
}
