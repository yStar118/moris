package com.portal;

import com.common.controller.BaseController;
import com.entity.InspectionObject;
import com.searchVO.InspectionObjectSearchVO;
import com.service.InspectionObjectService;
import com.service.OrganizationDepartmentService;
import com.service.OrganizationJobsService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
@Controller
@RequestMapping("portal/inspection/object")
public class InspectionObjectController extends BaseController {

    private final InspectionObjectService inspectionObjectService;
    private final OrganizationDepartmentService organizationDepartmentService;
    private final OrganizationJobsService organizationJobsService;

    @Autowired
    public InspectionObjectController(InspectionObjectService inspectionObjectService,
                                      OrganizationDepartmentService organizationDepartmentService,
                                      OrganizationJobsService organizationJobsService) {
        this.inspectionObjectService = inspectionObjectService;
        this.organizationDepartmentService = organizationDepartmentService;
        this.organizationJobsService = organizationJobsService;
    }


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/inspection/objectIndex");
        mv.addObject("departmentList", organizationDepartmentService.findAll());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionObjectSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionObject> list = inspectionObjectService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param inspectionObject 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public InspectionObject save(InspectionObject inspectionObject) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (inspectionObject.getId() == null) {
            inspectionObject.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        inspectionObject.setUpdateUser(user_id);
        return inspectionObjectService.save(inspectionObject);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionObject findOne(Integer id) {
        InspectionObject inspectionObject = inspectionObjectService.findOne(id);
        inspectionObject.setDepartmentName(organizationDepartmentService.findOneByCode(inspectionObject.getDepartmentCode()).getName());
        inspectionObject.setJobsName(organizationJobsService.findOneByCode(inspectionObject.getJobsCode()).getName());
        return inspectionObject;
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        inspectionObjectService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionObject> findAll() {
        return inspectionObjectService.findAll();
    }

    /**
     * 根据名称查询id
     */
    @RequestMapping(value = "findByCode", method = RequestMethod.GET)
    @ResponseBody
    public InspectionObject findByCode(String code) {
        InspectionObject inspectionObject = inspectionObjectService.findByCode(code);
        inspectionObject.setDepartmentName(organizationDepartmentService.findOneByCode(inspectionObject.getDepartmentCode()).getName());
        inspectionObject.setJobsName(organizationJobsService.findOneByCode(inspectionObject.getJobsCode()).getName());
        return inspectionObject;
    }


    @RequestMapping("findByJobsCode")
    @ResponseBody
    public List<InspectionObject> findByJobsCode(String jobsCode) {
        return inspectionObjectService.findByJobsCode(jobsCode);
    }

    @RequestMapping("findByJobsCodeGroupByEquipmentName")
    @ResponseBody
    public List<InspectionObject> findByJobsCodeGroupByEquipmentName(String jobsCode) {
        return inspectionObjectService.findByJobsCodeGroupByEquipmentName(jobsCode);
    }

    @RequestMapping("findByEquipmentName")
    @ResponseBody
    public List<InspectionObject> findByEquipmentNameAndJobsCode(String equipmentName,String jobsCode ) {
        return inspectionObjectService.findByEquipmentNameAndJobsCode(equipmentName,jobsCode);
    }
}
