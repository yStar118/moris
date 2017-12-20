package com.repositories;

import com.entity.ContingencyInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案
 */
public interface ContingencyInfoRepository extends CrudRepository<ContingencyInfo, Integer>, ContingencyInfoRepositoryCustom {

  /*  @SuppressWarnings("JpaQlInspection")
    @Modifying
    @Query("update contingency_info ci set ci.filePath=?1,ci.fileName=?2 where ci.id=?3")
    void setFilePathAndPathNameById(String filePath, String fileName, Integer id);*/

}
