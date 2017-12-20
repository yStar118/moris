package com.repositories;

import com.entity.InspectionTask;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
public interface InspectionTaskRepository extends CrudRepository<InspectionTask, String>, InspectionTaskRepositoryCustom {

    @Query(value = "SELECT * FROM inspection_task WHERE  endDate =(SELECT max(endDate) FROM inspection_task " +
            "WHERE planId = :planId) AND planId = :planId LIMIT 0,1", nativeQuery = true)
    List<InspectionTask> findByPlanId(@Param("planId") String planId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE inspection_task it SET it.status=?1 WHERE it.id=?2 ", nativeQuery = true)
    int setStatusById(int status, String id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE inspection_task SET `status`= 4 WHERE endDate <=NOW() AND `status` NOT IN (3,4)", nativeQuery = true)
    int setStatus();
}
