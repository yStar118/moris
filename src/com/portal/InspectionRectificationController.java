package com.portal;

import com.entity.InspectionRectification;
import com.searchVO.InspectionRectificationSearchVO;
import com.service.InspectionRectificationService;
import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/24 21:07:11
 * 整改任务
 */
@Controller
@RequestMapping("/portal/inspection/rectification")
public class InspectionRectificationController {

    @Autowired
    private InspectionRectificationService inspectionRectificationService;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/inspection/rectificationIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionRectificationSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionRectification> list = inspectionRectificationService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(String id) {
        InspectionRectification rectification = inspectionRectificationService.findOne(id);
        ModelAndView mv = new ModelAndView();
        List<SysUser> userList = sysUserService.listAll();
        mv.addObject("userList", userList);
        mv.addObject("rectification", rectification);
        mv.setViewName("/inspection/rectificationSave");
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param inspectionRectification 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(InspectionRectification inspectionRectification) {
        if (inspectionRectification.getRectificationUserId() != null) {
            SysUser sysUser = sysUserService.get(inspectionRectification.getRectificationUserId());
            if (sysUser != null) {
                inspectionRectification.setRectificationEnterpriseId(sysUser.getEnterpriseId());
                inspectionRectification.setRectificationDepartmentId(sysUser.getDepartmentId());
                inspectionRectification.setRectificationJobsId(sysUser.getJobsId());
                inspectionRectificationService.save(inspectionRectification);
            }
        }
        return "redirect:/portal/inspection/rectification/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionRectification findOne(String id) {
        return inspectionRectificationService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(String id) {
        inspectionRectificationService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionRectification> findAll() {
        return inspectionRectificationService.findAll();
    }


    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView("/inspection/rectificationDetail");
        mv.addObject("rectification", inspectionRectificationService.findOne(id));
        return mv;
    }
}
