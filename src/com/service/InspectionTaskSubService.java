package com.service;

import com.entity.InspectionTaskSub;
import com.repositories.InspectionTaskSubRepository;
import com.searchVO.InspectionTaskSubSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/18 02:03:41
 */
@Service
public class InspectionTaskSubService {

    @Autowired
    private InspectionTaskSubRepository inspectionTaskSubRepository;

    public List<InspectionTaskSub> getTaskSubByTaskId(String taskId) {
        return inspectionTaskSubRepository.findByTaskId(taskId);
    }

    public List<InspectionTaskSub> getList(InspectionTaskSubSearchVO searchVO) {
        return inspectionTaskSubRepository.getList(searchVO);
    }

    public int setCheckBehaviorById(int checkBehavior, String id) {
        int i = inspectionTaskSubRepository.setCheckBehaviorById(checkBehavior, id);
        return i;
    }
}
