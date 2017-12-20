package com.service;

import com.model.StatCheckResultCustom;
import com.repositories.StatCheckResultCustomRepository;
import com.searchVO.StatCheckResultCustomSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/10 11:24:14
 */
@Service
public class StatCheckResultCustomService {


    @Autowired
    private StatCheckResultCustomRepository statCheckResultCustomRepository;

    public List<StatCheckResultCustom> getList(StatCheckResultCustomSearchVO searchVO) {
        return statCheckResultCustomRepository.getList(searchVO);
    }
}
