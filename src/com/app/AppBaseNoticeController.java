package com.app;


import com.app.dto.BaseNoticeUserDTO;
import com.app.dto.ListDTO;
import com.common.vo.JsonResult;
import com.searchVO.BaseNoticeUserSearchVO;
import com.service.BaseNoticeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/8/8
 * 文件文档Api
 */
@RestController
@RequestMapping("api/base/notice")
public class AppBaseNoticeController extends AppBaseController {

    @Autowired
    private BaseNoticeUserService baseNoticeUserService;

    @GetMapping("getListByUser")
    public JsonResult getListByUser(HttpServletRequest request, BaseNoticeUserSearchVO searchVO) {
        ListDTO<BaseNoticeUserDTO> listDTO = new ListDTO<>();
        searchVO.setUserId(getCurrentUser(request).getId());
        searchVO.setIndex(searchVO.getIndexParams());
        List<BaseNoticeUserDTO> list = baseNoticeUserService.getListByUserId(searchVO);
        listDTO.setList(list);
        listDTO.setTotal(searchVO.getTotal());
        return new JsonResult(true, "请求成功", listDTO);
    }

    @PostMapping("readNotice")
    public JsonResult readNotice(Integer id) {
        baseNoticeUserService.updateReadDateById(id);
        return new JsonResult(true, "请求成功",new ArrayList<>());
    }


}
