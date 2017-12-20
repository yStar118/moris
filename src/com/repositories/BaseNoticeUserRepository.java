package com.repositories;

import com.entity.BaseNoticeUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知 用户关联
 */
public interface BaseNoticeUserRepository extends CrudRepository<BaseNoticeUser, Integer>, BaseNoticeUserRepositoryCustom {

    @Transactional
    @Modifying
    @Query(value = "UPDATE base_notice_user SET readDate=:readDate,isRead=:isRead WHERE id=:id", nativeQuery = true)
    void setReadDateById(@Param("readDate") String readDate, @Param("isRead") int isRead, @Param("id") int id);
}
