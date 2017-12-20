package com.repositories;

import com.entity.InspectionObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
public interface InspectionObjectRepository extends CrudRepository<InspectionObject, Integer>, InspectionObjectRepositoryCustom {

    List<InspectionObject> findByCode(String code);

    List<InspectionObject> findByJobsCode(String jobsCode);

    List<InspectionObject> findByEquipmentNameAndJobsCode(String equipmentName, String jobsCode);
}
