package com.service;

import com.common.service.BaseService;
import com.entity.InspectionPlan;
import com.repositories.InspectionPlanRepository;
import com.repositories.InspectionPointExternalRepository;
import com.searchVO.InspectionPlanSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/5.
 */
@Service
public class InspectionPlanService implements BaseService<InspectionPlan, String> {

    @Autowired
    private InspectionPlanRepository inspectionPlanRepository;
    @Autowired
    private InspectionPointExternalRepository inspectionPointExternalRepository;

    @Override
    public <S extends InspectionPlan> S save(S entity) {
        return inspectionPlanRepository.save(entity);
    }

    @Override
    public InspectionPlan findOne(String primaryKey) {
        return inspectionPlanRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionPlan> findAll() {
        return IterableUtils.toList(inspectionPlanRepository.findAll());
    }

    public List<InspectionPlan> getList(InspectionPlanSearchVO searchVO) {
        return inspectionPlanRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return inspectionPlanRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        InspectionPlan inspectionPlan = inspectionPlanRepository.findOne(primaryKey);
        inspectionPlanRepository.delete(primaryKey);
        inspectionPointExternalRepository.deleteByExternalCode(inspectionPlan.getExternalCode());
    }
    public void updateLastDateById(String lastDate,String id) {
        inspectionPlanRepository.setLastDateById(lastDate, id);
    }

}
