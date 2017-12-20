package com.service;

import com.common.service.BaseService;
import com.entity.InspectionObject;
import com.repositories.InspectionObjectRepository;
import com.searchVO.InspectionObjectSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
@Service
public class InspectionObjectService implements BaseService<InspectionObject, Integer> {

    @Autowired
    private InspectionObjectRepository inspectionObjectRepository;

    @Override
    public <S extends InspectionObject> S save(S entity) {
        return inspectionObjectRepository.save(entity);
    }

    public <S extends InspectionObject> List<S> save(List<S> entity) {
        return IterableUtils.toList(inspectionObjectRepository.save(entity));
    }
    @Override
    public InspectionObject findOne(Integer primaryKey) {
        return inspectionObjectRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionObject> findAll() {
        return IterableUtils.toList(inspectionObjectRepository.findAll());
    }

    public List<InspectionObject> getList(InspectionObjectSearchVO searchVO) {
        return inspectionObjectRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return inspectionObjectRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        inspectionObjectRepository.delete(primaryKey);
    }

    public InspectionObject findByCode(String code) {
        List<InspectionObject> list = inspectionObjectRepository.findByCode(code);
        if (list.size() > 0) {
            return list.get(0);
        }
        return new InspectionObject();
    }

    public List<InspectionObject> findByJobsCode(String jobsCode) {
        return inspectionObjectRepository.findByJobsCode(jobsCode);
    }

    public List<InspectionObject> findByJobsCodeGroupByEquipmentName(String jobsCode) {
        return inspectionObjectRepository.findByJobsCodeGroupByEquipmentName(jobsCode);
    }

    public List<InspectionObject> findByEquipmentNameAndJobsCode(String equipmentName,String jobsCode ) {
        return inspectionObjectRepository.findByEquipmentNameAndJobsCode(equipmentName,jobsCode);
    }
}
