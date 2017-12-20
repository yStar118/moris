package com.repositories;

import com.entity.InspectionPointExternal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 高宇飞 on 2017/8/6.
 * 检查点关联
 */
public interface InspectionPointExternalRepository extends CrudRepository<InspectionPointExternal, Integer>, InspectionPointExternalRepositoryCustom {

    @Transactional
    @SuppressWarnings("JpaQlInspection")
    @Modifying
    @Query(value = "DELETE FROM inspection_point_external WHERE externalCode = :externalCode", nativeQuery = true)
    int deleteByExternalCode(@Param("externalCode") String externalCode);
}
