package com.service;

import com.common.service.BaseService;
import com.entity.BaseNotice;
import com.repositories.BaseNoticeRepository;
import com.searchVO.BaseNoticeSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
@Service
public class BaseNoticeService implements BaseService<BaseNotice, String> {


    @Autowired
    private BaseNoticeRepository baseNoticeRepository;

    @Override
    public <S extends BaseNotice> S save(S entity) {
        return baseNoticeRepository.save(entity);
    }

    @Override
    public BaseNotice findOne(String primaryKey) {
        return baseNoticeRepository.findOne(primaryKey);
    }

    @Override
    public List<BaseNotice> findAll() {
        return IterableUtils.toList(baseNoticeRepository.findAll());
    }

    @Override
    public long count() {
        return baseNoticeRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        baseNoticeRepository.delete(primaryKey);
    }

    public List<BaseNotice> getList(BaseNoticeSearchVO searchVO) {
        return baseNoticeRepository.getList(searchVO);
    }

    public void setFilePathAndPathNameById(String filePath, String fileName, int id) {
        baseNoticeRepository.setFilePathAndPathNameById(filePath, fileName, id);
    }
}
