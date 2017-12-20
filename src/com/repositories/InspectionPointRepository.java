package com.repositories;

import com.entity.InspectionPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
public interface InspectionPointRepository extends CrudRepository<InspectionPoint,String> ,InspectionPointRepositoryCustom{
}
