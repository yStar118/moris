package com.repositories;

import com.entity.BaseNotice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
public interface BaseNoticeRepository extends CrudRepository<BaseNotice,String>,BaseNoticeRepositoryCustom{
}
