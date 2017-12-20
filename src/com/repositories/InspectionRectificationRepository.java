package com.repositories;

import com.entity.InspectionRectification;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 高宇飞 on 2017/8/24 14:11:30
 */
public interface InspectionRectificationRepository extends CrudRepository<InspectionRectification,String> ,InspectionRectificationRepositoryCustom {
}
