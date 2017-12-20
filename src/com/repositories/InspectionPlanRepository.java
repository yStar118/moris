package com.repositories;

import com.entity.InspectionPlan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/5.
 * 检查方案
 */
public interface InspectionPlanRepository extends CrudRepository<InspectionPlan, String>, InspectionPlanRepositoryCustom {

    @SuppressWarnings("JpaQlInspection")
    @Modifying
    @Query(value = "UPDATE inspection_plan ip SET ip.lastDate = ?1 WHERE ip.id=?2", nativeQuery = true)
    int setLastDateById(String lastDate, String id);

    List<InspectionPlan> findByIsStart(int isStart);
}
