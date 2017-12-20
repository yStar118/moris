package com.portal;

import com.entity.InspectionTaskSub;
import com.searchVO.InspectionTaskSubSearchVO;
import com.service.InspectionTaskSubService;
import com.util.datatables.DataTablesResult;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/19 17:44:32
 */
@Controller
@RequestMapping("/portal/inspection/taskSub")
public class InspectionTaskSubController {

    @Autowired
    private InspectionTaskSubService inspectionTaskSubService;


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(Integer type, String taskId, Integer checkResult) {
        ModelAndView mv = new ModelAndView("/inspection/taskSubIndex");
        mv.addObject("type", type == null ? 1 : type);
        mv.addObject("checkResult", checkResult);
        if (StringUtil.isNotNullOrEmpty(taskId)) {
            mv.addObject("taskId", taskId);
        }
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionTaskSubSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionTaskSub> list = inspectionTaskSubService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 谎报
     */
    @RequestMapping(value = "lieAboutTask", method = RequestMethod.GET)
    @ResponseBody
    public Integer lieAboutTask(String id) {
        return inspectionTaskSubService.setCheckBehaviorById(1, id);
    }


    /**
     * 误判
     */
    @RequestMapping(value = "badJudgmentTask", method = RequestMethod.GET)
    @ResponseBody
    public Integer badJudgmentTask(String id) {
        return inspectionTaskSubService.setCheckBehaviorById(2, id);
    }
}
