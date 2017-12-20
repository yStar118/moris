package com.repositories;

import com.app.dto.BaseNoticeUserDTO;
import com.entity.BaseNoticeUser;
import com.searchVO.BaseNoticeUserSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知 用户关联
 */
public interface BaseNoticeUserRepositoryCustom {

    void deleteByNoticeId(String noticeId);

    List<BaseNoticeUser> getListByNoticeId(BaseNoticeUserSearchVO searchVO);

    List<BaseNoticeUserDTO> getListByUserId(BaseNoticeUserSearchVO searchVO);
}
