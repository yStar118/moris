package com.app;

import com.app.dto.AppStatcheckResultDTO;
import com.app.dto.ListDTO;
import com.common.vo.JsonResult;
import com.model.StatCheckBehavior;
import com.searchVO.StatCheckResultCustomSearchVO;
import com.service.StatCheckResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/13 01:30:37
 * 统计报表api
 */
@RestController
@RequestMapping("api/stat")
public class AppStatcheckResultController {

    @Autowired
    private StatCheckResultService statCheckResultService;

    @RequestMapping("checkResult")
    public JsonResult getCheckResultList(Integer type) {
        ListDTO<AppStatcheckResultDTO> objectListDTO = new ListDTO<>();
        List<AppStatcheckResultDTO> list = statCheckResultService.getListForAppByWeek(type);
        int size = list.size();
        if (size > 0) {
            objectListDTO.setTotal(size);
            objectListDTO.setList(list);
            return new JsonResult(true, "请求成功", objectListDTO);
        } else {
            return new JsonResult(true, "暂时没有统计数据", objectListDTO);
        }
    }

    @RequestMapping("checkResultForMonth")
    public JsonResult checkResultForMonth(Integer type) {
        ListDTO<AppStatcheckResultDTO> objectListDTO = new ListDTO<>();
        List<AppStatcheckResultDTO> appStatcheckResultDTOList = statCheckResultService.getListForAppByMonth(type);
        int size = appStatcheckResultDTOList.size();
        objectListDTO.setTotal(size);
        objectListDTO.setList(appStatcheckResultDTOList);
        return new JsonResult(true, "请求成功", objectListDTO);
    }


    @RequestMapping("CheckBehavior")
    public JsonResult CheckBehavior() {
        ListDTO<StatCheckBehavior> objectListDTO = new ListDTO<>();
        StatCheckResultCustomSearchVO searchVO = new StatCheckResultCustomSearchVO();
        searchVO.setIndex(0);
        searchVO.setLength(9999);
        List<StatCheckBehavior> appStatcheckResultDTOList = statCheckResultService.getListForCheckBehavior(searchVO);
        int size = appStatcheckResultDTOList.size();
        objectListDTO.setTotal(size);
        objectListDTO.setList(appStatcheckResultDTOList);
        return new JsonResult(true, "请求成功", objectListDTO);
    }


}
