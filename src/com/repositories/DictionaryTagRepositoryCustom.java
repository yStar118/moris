package com.repositories;

import com.entity.DictionaryTag;
import com.searchVO.DictionaryTagSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/31 11:29:28
 * 字典- tag
 */
public interface DictionaryTagRepositoryCustom {

    List<DictionaryTag> getList(DictionaryTagSearchVO searchVO);
}
