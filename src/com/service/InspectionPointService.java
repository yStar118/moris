package com.service;

import com.common.service.BaseService;
import com.entity.InspectionPoint;
import com.repositories.InspectionPointRepository;
import com.searchVO.InspectionPointSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
@Service
public class InspectionPointService implements BaseService<InspectionPoint, String> {

    @Autowired
    private InspectionPointRepository inspectionPointRepository;

    @Override
    public <S extends InspectionPoint> S save(S entity) {
        return inspectionPointRepository.save(entity);
    }

    public <S extends InspectionPoint> List<S> save(List<S> entity) {
        return IterableUtils.toList(inspectionPointRepository.save(entity));
    }

    @Override
    public InspectionPoint findOne(String primaryKey) {
        return inspectionPointRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionPoint> findAll() {
        return IterableUtils.toList(inspectionPointRepository.findAll());
    }

    @Override
    public long count() {
        return inspectionPointRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        inspectionPointRepository.delete(primaryKey);
    }

    public List<InspectionPoint> getList(InspectionPointSearchVO searchVO) {
        return inspectionPointRepository.getList(searchVO);
    }

    public List<String> getCategoryName() {
        return inspectionPointRepository.getCategoryName();
    }
}
