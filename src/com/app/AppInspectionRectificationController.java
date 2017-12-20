package com.app;

import com.common.vo.JsonResult;
import com.searchVO.InspectionRectificationSearchVO;
import com.service.InspectionRectificationService;
import com.service.InspectionTaskService;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 高宇飞 on 2017/9/22 16:29:53
 * 整改任务api
 */
@RestController
@RequestMapping("api/rectification")
public class AppInspectionRectificationController extends AppBaseController {

    @Autowired
    private InspectionRectificationService inspectionRectificationService;
    @Autowired
    private InspectionTaskService inspectionTaskService;


    @GetMapping(value = "getListByUser")
    public JsonResult getListByUser(HttpServletRequest request, InspectionRectificationSearchVO searchVO) {
        searchVO.setRectificationUserId(getCurrentUser(request).getId());
        return new JsonResult(true, "请求成功", inspectionRectificationService.getList(searchVO));
    }

    @GetMapping(value = "findOneById")
    public JsonResult findOneById(String id) {
        return new JsonResult(true, "请求成功", inspectionRectificationService.findOne(id));
    }


    @PostMapping("updateRectification")
    public JsonResult updateRectification(String id, String rectificationImg) {
        if (StringUtil.isNotNullOrEmpty(rectificationImg)) {
            rectificationImg = inspectionTaskService.saveFile(rectificationImg);
            inspectionRectificationService.updateRectification(id, rectificationImg);
            return new JsonResult(true, "请求成功");
        }
        return new JsonResult(false, "整改失败，图片不能为空");
    }
}
