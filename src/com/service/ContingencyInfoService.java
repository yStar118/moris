package com.service;

import com.common.service.BaseService;
import com.entity.ContingencyInfo;
import com.repositories.ContingencyInfoRepository;
import com.searchVO.ContingencyInfoSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案
 */
@Service
public class ContingencyInfoService implements BaseService<ContingencyInfo, Integer> {

    @Autowired
    private ContingencyInfoRepository contingencyInfoRepository;

    @Override
    public <S extends ContingencyInfo> S save(S entity) {
        return contingencyInfoRepository.save(entity);
    }

    @Override
    public ContingencyInfo findOne(Integer primaryKey) {
        return contingencyInfoRepository.findOne(primaryKey);
    }

    @Override
    public List<ContingencyInfo> findAll() {
        return IterableUtils.toList(contingencyInfoRepository.findAll());
    }

    public List<ContingencyInfo> getList(ContingencyInfoSearchVO searchVO) {
        return contingencyInfoRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return contingencyInfoRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        contingencyInfoRepository.delete(primaryKey);
    }

    public void setFilePathAndPathNameById(String filePath, String fileName, Integer id) {
        contingencyInfoRepository.setFilePathAndPathNameById(filePath, fileName, id);
    }
}
