package com.repositories;

import com.model.StatCheckResultCustom;
import com.searchVO.StatCheckResultCustomSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/9 15:09:39
 * 自定义报表
 */
public interface StatCheckResultCustomRepository {

    List<StatCheckResultCustom> getList(StatCheckResultCustomSearchVO statCheckResultSearchVO);
}
