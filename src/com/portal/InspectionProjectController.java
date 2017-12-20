package com.portal;

import com.entity.InspectionTask;
import com.searchVO.InspectionTaskSearchVO;
import com.service.InspectionTaskService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查计划
 */
@Controller
@RequestMapping("/portal/inspection/project")
public class InspectionProjectController {

    @Autowired
    private InspectionTaskService inspectionTaskService;

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(Integer type) {
        ModelAndView mv = new ModelAndView("/inspection/projectIndex");
        mv.addObject("type", type == null ? 1 : type);
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionTaskSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionTask> list = inspectionTaskService.getListByProject(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionTask findOne(String id) {
        return inspectionTaskService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(String id) {
        inspectionTaskService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionTask> findAll() {
        return inspectionTaskService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView("/inspection/projectDetail");
        mv.addObject("inspectionScene", inspectionTaskService.findOne(id));
        return mv;
    }


}
