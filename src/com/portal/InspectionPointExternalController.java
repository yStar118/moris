package com.portal;

import com.entity.InspectionPlan;
import com.entity.InspectionPointExternal;
import com.searchVO.InspectionPointExternalSearchVO;
import com.service.InspectionPlanService;
import com.service.InspectionPointExternalService;
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
 * Created by 高宇飞 on 2017/8/6.
 * \检查点关联
 */
@Controller
@RequestMapping("portal/inspection/pointExternal")
public class InspectionPointExternalController {

    private final InspectionPointExternalService inspectionPointExternalService;
    private final InspectionPlanService inspectionPlanService;

    @Autowired
    public InspectionPointExternalController(InspectionPointExternalService inspectionPointExternalService,
                                             InspectionPlanService inspectionPlanService) {
        this.inspectionPointExternalService = inspectionPointExternalService;
        this.inspectionPlanService = inspectionPlanService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/inspection/pointExternalIndex");
        mv.addObject("inspectionPlanList", inspectionPlanService.findAll());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionPointExternalSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        if (StringUtil.isNotNullOrEmpty(searchVO.getPlanId())) {
            InspectionPlan inspectionPlan = inspectionPlanService.findOne(searchVO.getPlanId());
            if (inspectionPlan != null) {
                searchVO.setExternalCode(inspectionPlan.getExternalCode());
            }

        }
        List<InspectionPointExternal> list = inspectionPointExternalService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(Integer id) {
        ModelAndView mv = new ModelAndView("/inspection/pointExternalSave");
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param inspectionPointExternal 实体
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(InspectionPointExternal inspectionPointExternal) {
        inspectionPointExternalService.save(inspectionPointExternal);
        return "redirect:/portal/inspection/pointExternal/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionPointExternal findOne(Integer id) {
        return inspectionPointExternalService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(Integer id) {
        inspectionPointExternalService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionPointExternal> findAll() {
        return inspectionPointExternalService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer id) {
        ModelAndView mv = new ModelAndView("/inspection/pointExternalDetail");
        mv.addObject("inspectionScene", inspectionPointExternalService.findOne(id));
        return mv;
    }
}
