package com.app;


import com.app.dto.ListDTO;
import com.app.views.DocumentViews;
import com.common.controller.BaseController;
import com.common.vo.JsonResult;
import com.entity.BaseDocument;
import com.fasterxml.jackson.annotation.JsonView;
import com.searchVO.BaseDocumentSearchVO;
import com.service.BaseDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/8/8
 * 文件文档Api
 */
@RestController
@RequestMapping("api/base/document")
public class AppBaseDocumentController extends BaseController {

    @Autowired
    private BaseDocumentService baseDocumentService;

    @JsonView(DocumentViews.GetExchangeList.class)
    @GetMapping("getList")
    public JsonResult getList(BaseDocumentSearchVO searchVO) {
        ListDTO<BaseDocument> listDTO = new ListDTO<>();
        searchVO.setIndex(searchVO.getIndexParams());
        List<BaseDocument> list = baseDocumentService.getList(searchVO);
        listDTO.setList(list);
        listDTO.setTotal(searchVO.getTotal());
        return new JsonResult(true,"请求成功",listDTO);
    }
}
