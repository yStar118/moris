package com.repositories;

import com.entity.DictionaryPoint;
import com.searchVO.DictionaryPointSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/31 14:01:53
 * 字典  检查点分类属性
 */
public interface DictionaryPointRepositoryCustom {

    List<DictionaryPoint> getList(DictionaryPointSearchVO searchVO);
}
