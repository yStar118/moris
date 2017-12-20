package com.app;

import com.app.dto.ListDTO;
import com.app.views.ContingencyInfoViews;
import com.common.vo.JsonResult;
import com.entity.ContingencyInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.searchVO.ContingencyInfoSearchVO;
import com.service.ContingencyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/14.
 * 应急预案API
 */
@RestController
@RequestMapping("api/contingency/info")
public class AppContingencyInfoController extends AppBaseController {

    @Autowired
    private ContingencyInfoService contingencyInfoService;

    @JsonView(ContingencyInfoViews.GetExchangeList.class)
    @GetMapping("getList")
    public JsonResult getList(ContingencyInfoSearchVO searchVO) {
        ListDTO<ContingencyInfo> listDTO = new ListDTO<>();
        searchVO.setIndex(searchVO.getIndexParams());
        List<ContingencyInfo> contingencyInfoList = contingencyInfoService.getList(searchVO);
        listDTO.setList(contingencyInfoList);
        listDTO.setTotal(searchVO.getTotal());
        return new JsonResult(true, "请求成功", listDTO);
    }

    @JsonView(ContingencyInfoViews.GetExchange.class)
    @GetMapping("getById")
    public JsonResult getById(Integer id) {
        ContingencyInfo contingencyInfo = contingencyInfoService.findOne(id);
        return new JsonResult(true, "请求成功", contingencyInfo);
    }

}
