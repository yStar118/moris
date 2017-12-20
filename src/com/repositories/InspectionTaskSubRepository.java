package com.repositories;

import com.entity.InspectionTaskSub;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 高宇飞 on 2017/8/11.
 */
public interface InspectionTaskSubRepository extends CrudRepository<InspectionTaskSub, Integer>, InspectionTaskSubRepositoryCustom {

    @Transactional
    @Modifying
    @Query(value = "UPDATE inspection_task_sub its SET its.checkBehavior=?1 WHERE its.id=?2 ", nativeQuery = true)
    int setCheckBehaviorById(int checkBehavior, String id);


}
