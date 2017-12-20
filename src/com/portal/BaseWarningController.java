package com.portal;

import com.common.controller.BaseController;
import com.entity.BaseWarning;
import com.entity.OrganizationDepartment;
import com.searchVO.BaseWarningSearchVO;
import com.service.BaseWarningService;
import com.service.OrganizationDepartmentService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/9/1 22:43
 */
@Controller
@RequestMapping("portal/base/warning")
public class BaseWarningController extends BaseController {

    @Autowired
    private BaseWarningService baseWarningService;
    @Autowired
    private OrganizationDepartmentService organizationDepartmentService;

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("base/warningIndex");
        List<OrganizationDepartment> departmentList = organizationDepartmentService.findAll();
        mv.addObject("departmentList", departmentList);
        return mv;
    }

    @RequestMapping("getList")
    @ResponseBody
    public DataTablesResult getList(BaseWarningSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<BaseWarning> list = baseWarningService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param baseWarning 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public BaseWarning save(BaseWarning baseWarning) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        baseWarning.setUpdateUser(user_id);
        return baseWarningService.save(baseWarning);
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public BaseWarning findOne(Integer id) {
        return baseWarningService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(Integer id) {
        baseWarningService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseWarning> findAll() {
        return baseWarningService.findAll();
    }
}
