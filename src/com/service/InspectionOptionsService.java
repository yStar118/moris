package com.service;

import com.common.service.BaseService;
import com.entity.InspectionOptions;
import com.repositories.InspectionOptionsRepository;
import com.searchVO.InspectionOptionsSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/30.
 * 检查项
 */
@Service //业务层组件
public class InspectionOptionsService implements BaseService<InspectionOptions, Integer> {

    @Autowired //是一种函数 完成自动装配工作。
    private InspectionOptionsRepository inspectionOptionsRepository;

    @Override //伪代码 表示重写，（不写也可以）
    public <S extends InspectionOptions> S save(S entity) {
        return inspectionOptionsRepository.save(entity);
    }

    public <S extends InspectionOptions> Iterable<S> save(List<S> entity) {
        return inspectionOptionsRepository.save(entity);
    }

    @Override
    public InspectionOptions findOne(Integer primaryKey) {
        return inspectionOptionsRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionOptions> findAll() {
        return IterableUtils.toList(inspectionOptionsRepository.findAll());
    }

    public List<InspectionOptions> getList(InspectionOptionsSearchVO searchVO) {
        return inspectionOptionsRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return inspectionOptionsRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        inspectionOptionsRepository.delete(primaryKey);
    }

    public InspectionOptions findByName(String name) {
        List<InspectionOptions> byName = inspectionOptionsRepository.findByName(name);
        if (byName != null && byName.size() > 0) {
            return byName.get(0);
        }
        return new InspectionOptions();
    }
}
