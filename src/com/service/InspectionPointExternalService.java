package com.service;

import com.common.service.BaseService;
import com.entity.InspectionPoint;
import com.entity.InspectionPointExternal;
import com.repositories.InspectionPointExternalRepository;
import com.searchVO.InspectionPointExternalSearchVO;
import com.util.string.StringUtil;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/6.
 * 检查点关联
 */
@Service
public class InspectionPointExternalService implements BaseService<InspectionPointExternal, Integer> {

    @Autowired
    private InspectionPointExternalRepository inspectionPointExternalRepository;

    @Override
    public <S extends InspectionPointExternal> S save(S entity) {
        return inspectionPointExternalRepository.save(entity);
    }

    public <S extends InspectionPointExternal> Iterable<S> save(List<S> entity) {
        return inspectionPointExternalRepository.save(entity);
    }

    @Override
    public InspectionPointExternal findOne(Integer primaryKey) {
        return inspectionPointExternalRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionPointExternal> findAll() {
        return IterableUtils.toList(inspectionPointExternalRepository.findAll());
    }

    @Override
    public long count() {
        return inspectionPointExternalRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        inspectionPointExternalRepository.delete(primaryKey);
    }


    public void savePointExternalByPlan(List<InspectionPoint> list, String externalCode) {
        if (list.size() > 0 && StringUtil.isNotNullOrEmpty(externalCode)) {
            inspectionPointExternalRepository.deleteByExternalCode(externalCode);
            List<InspectionPointExternal> externalArrayList = new ArrayList<>();
            for (InspectionPoint inspectionPoint : list) {
                System.out.println(inspectionPoint.getCategoryName() + "---" + inspectionPoint.getAttributeName());
                InspectionPointExternal inspectionPointExternal = new InspectionPointExternal();
                BeanUtils.copyProperties(inspectionPoint, inspectionPointExternal);
                inspectionPointExternal.setExternalCode(externalCode);
                inspectionPointExternal.setPointId(inspectionPoint.getId());
                inspectionPointExternal.setPointName(inspectionPoint.getName());
                externalArrayList.add(inspectionPointExternal);
            }
            save(externalArrayList);
        }
    }

    public List<InspectionPointExternal> getList(InspectionPointExternalSearchVO searchVO) {
        return inspectionPointExternalRepository.getList(searchVO);
    }

}
