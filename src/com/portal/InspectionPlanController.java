package com.portal;

import com.common.controller.BaseController;
import com.entity.InspectionPlan;
import com.entity.InspectionPoint;
import com.searchVO.InspectionPlanSearchVO;
import com.searchVO.InspectionPointSearchVO;
import com.service.*;
import com.util.code.SerialNumUtil;
import com.util.datatables.DataTablesResult;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/5.
 * 检查方案
 */
@Controller
@RequestMapping("portal/inspection/plan")
public class InspectionPlanController extends BaseController {

    private final InspectionPlanService inspectionPlanService;
    private final InspectionPointService inspectionPointService;
    private final OrganizationDepartmentService organizationDepartmentService;
    private final InspectionPointExternalService inspectionPointExternalService;
    private final InspectionTaskService inspectionTaskService;
    private final DictionaryPointService dictionaryPointService;

    @Autowired
    public InspectionPlanController(InspectionPlanService inspectionPlanService, DictionaryPointService dictionaryPointService,
                                    OrganizationDepartmentService organizationDepartmentService, InspectionTaskService inspectionTaskService,
                                    InspectionPointService inspectionPointService, InspectionPointExternalService inspectionPointExternalService) {
        this.inspectionPlanService = inspectionPlanService;
        this.organizationDepartmentService = organizationDepartmentService;
        this.inspectionPointExternalService = inspectionPointExternalService;
        this.inspectionPointService = inspectionPointService;
        this.inspectionTaskService = inspectionTaskService;
        this.dictionaryPointService = dictionaryPointService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(Integer type) {
        ModelAndView mv = new ModelAndView("/inspection/planIndex");
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
    public DataTablesResult getList(InspectionPlanSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionPlan> list = inspectionPlanService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(Integer type, String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("inspectionPlan", inspectionPlanService.findOne(id == null ? "0" : id));
        mv.addObject("type", (type == null ? "1" : type));
        mv.addObject("departmentList", organizationDepartmentService.findAll());
        if (type == null || type == 1) {
            mv.setViewName("/inspection/planSaveInspection");
        } else {
            mv.addObject("dictionaryPointList", dictionaryPointService.findAll());
            if (StringUtil.isNotNullOrEmpty(id)) {
                InspectionPlan inspectionPlan = inspectionPlanService.findOne(id);
                if (inspectionPlan != null) {
                    String departmentCode = inspectionPlan.getDepartmentCode();
                    if (departmentCode != null) {
                        String[] split = departmentCode.split(",");
                        List list = Arrays.asList(split);
                        mv.addObject("departmentCodeForDB", list);
                    }
                    String category = inspectionPlan.getCategory();
                    if (category != null) {
                        String[] split = category.split(",");
                        List list = Arrays.asList(split);
                        mv.addObject("categoryListForDB", list);
                    }
                    String attribute = inspectionPlan.getAttribute();
                    if (attribute != null) {
                        String[] split = attribute.split(",");
                        List list = Arrays.asList(split);
                        mv.addObject("attributeListForDB", list);
                    }
                }
            }
            mv.setViewName("/inspection/planSaveScreening");
        }
        return mv;
    }

    /**
     * 新增/修改(巡检单元)
     *
     * @param inspectionPlan 实体
     */
    @RequestMapping(value = "saveInspection", method = RequestMethod.POST)
    public String saveInspection(InspectionPlan inspectionPlan) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (StringUtil.isNullOrEmpty(inspectionPlan.getId())) {
            inspectionPlan.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
            inspectionPlan.setExternalCode(SerialNumUtil.createRandowmLetter(16));
            inspectionPlan.setIsStart(1);
        }
        InspectionPointSearchVO inspectionPointSearchVO = new InspectionPointSearchVO();
        List<String> departmentCodes = new ArrayList<>();
        departmentCodes.add(inspectionPlan.getDepartmentCode());
        inspectionPointSearchVO.setDepartmentCodes(departmentCodes);
        inspectionPointSearchVO.setJobsCode(inspectionPlan.getJobsCode());
        inspectionPointSearchVO.setIndex(0);
        inspectionPointSearchVO.setLength(9999);
        List<InspectionPoint> list = inspectionPointService.getList(inspectionPointSearchVO);
        inspectionPointExternalService.savePointExternalByPlan(list, inspectionPlan.getExternalCode());

        inspectionPlan.setUpdateUser(user_id);
        inspectionPlan.setCheckTime(InspectionPointController.getCheckTime(inspectionPlan.getCheckCycle(), inspectionPlan.getCheckFrequency()));
        InspectionPlan save = inspectionPlanService.save(inspectionPlan);
        return "redirect:/portal/inspection/plan/index.do?type=" + inspectionPlan.getType();
    }


    /**
     * 新增/修改(临时检查)
     *
     * @param inspectionPlan 实体
     */
    @RequestMapping(value = "saveScreening", method = RequestMethod.POST)
    public String saveScreening(InspectionPlan inspectionPlan) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (StringUtil.isNullOrEmpty(inspectionPlan.getId())) {
            inspectionPlan.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
            inspectionPlan.setExternalCode(SerialNumUtil.createRandowmLetter(16));
            inspectionPlan.setIsStart(1);
        }
        InspectionPointSearchVO inspectionPointSearchVO = new InspectionPointSearchVO();
        /*对查询条件（部门集合）进行处理*/
        List<String> departmentCodes = inspectionPlan.getDepartmentCodes();
        inspectionPointSearchVO.setDepartmentCodes(departmentCodes);
        StringBuilder sb = new StringBuilder();
        if (departmentCodes != null && departmentCodes.size() > 0) {
            for (String s : departmentCodes) {
                sb.append(s).append(",");
            }
            inspectionPlan.setDepartmentCode(sb.toString().substring(0, sb.toString().length() - 1));
        }
         /*对查询条件（检查对象分类集合）进行处理*/
        List<String> categoryList = inspectionPlan.getCategoryList();
        inspectionPointSearchVO.setCategoryList(categoryList);
        StringBuilder sb1 = new StringBuilder();
        if (categoryList != null && categoryList.size() > 0) {
            for (String s : categoryList) {
                sb1.append(s).append(",");
            }
            inspectionPlan.setCategory(sb1.toString().substring(0, sb1.toString().length() - 1));
        }
         /*对查询条件（检查对象属性集合）进行处理*/
        List<String> attributeList = inspectionPlan.getAttributeList();
        inspectionPointSearchVO.setAttributeList(attributeList);
        StringBuilder sb2 = new StringBuilder();
        if (attributeList != null && attributeList.size() > 0) {
            for (String s : attributeList) {
                sb2.append(s).append(",");
            }
            inspectionPlan.setAttribute(sb2.toString().substring(0, sb2.toString().length() - 1));
        }
        inspectionPointSearchVO.setIndex(0);
        inspectionPointSearchVO.setLength(9999);
        List<InspectionPoint> list = inspectionPointService.getList(inspectionPointSearchVO);
        inspectionPointExternalService.savePointExternalByPlan(list, inspectionPlan.getExternalCode());
        inspectionPlan.setUpdateUser(user_id);
        inspectionPlan.setCheckTime(InspectionPointController.getCheckTime(inspectionPlan.getCheckCycle(), inspectionPlan.getCheckFrequency()));
        inspectionPlanService.save(inspectionPlan);
        return "redirect:/portal/inspection/plan/index.do?type=" + inspectionPlan.getType();
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionPlan findOne(String id) {
        return inspectionPlanService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(String id) {
        inspectionPlanService.delete(id);

    }

    /**
     * 根据id删除对象
     */
    @RequestMapping(value = "automationTask", method = RequestMethod.GET)
    public void automationTask() {
        inspectionTaskService.updateAutomationTask();

    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionPlan> findAll() {
        return inspectionPlanService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer type, String id) {
        ModelAndView mv = new ModelAndView("/inspection/planDetail");
        mv.addObject("type", type);
        mv.addObject("inspectionPlan", inspectionPlanService.findOne(id));
        return mv;
    }
}
