package com.repositories;

import com.entity.InspectionRectification;
import com.searchVO.InspectionRectificationSearchVO;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/24 14:12:00
 */
public interface InspectionRectificationRepositoryCustom {

    List<InspectionRectification> getList(InspectionRectificationSearchVO searchVO);

    void updateRectification(String id, String rectificationImg);

    List<InspectionRectification> findByLastDate();

}
