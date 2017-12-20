package com.portal;

import com.common.controller.BaseController;
import com.entity.BaseNotice;
import com.searchVO.BaseNoticeSearchVO;
import com.searchVO.BaseNoticeUserSearchVO;
import com.service.BaseNoticeService;
import com.service.BaseNoticeUserService;
import com.service.OrganizationDepartmentService;
import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
@Controller
@RequestMapping("portal/base/notice")
public class BaseNoticeController extends BaseController {

    private final BaseNoticeService baseNoticeService;
    private final BaseNoticeUserService baseNoticeUserService;
    private final OrganizationDepartmentService organizationDepartmentService;
    private final SysUserService sysUserService;

    @Autowired
    public BaseNoticeController(BaseNoticeService baseNoticeService, OrganizationDepartmentService
            organizationDepartmentService, SysUserService sysUserService, BaseNoticeUserService baseNoticeUserService) {
        this.baseNoticeService = baseNoticeService;
        this.organizationDepartmentService = organizationDepartmentService;
        this.sysUserService = sysUserService;
        this.baseNoticeUserService = baseNoticeUserService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(String type) {
        ModelAndView mv = new ModelAndView("/base/noticeIndex");
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(BaseNoticeSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<BaseNotice> list = baseNoticeService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增
     *
     * @param baseNotice 实体
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(BaseNotice baseNotice) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        baseNotice.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        baseNotice.setUpdateUser(user_id);
        BaseNotice notice = baseNoticeService.save(baseNotice);
        List<SysUser> sysUserList;
        if (baseNotice.getDepartmentIds() != null && baseNotice.getDepartmentIds().size() > 0) {
            sysUserList = sysUserService.listByDepartmentId(baseNotice.getDepartmentIds());
        } else {
            sysUserList = sysUserService.listAll();
        }
        baseNoticeUserService.save(sysUserList, notice.getId());
        return "redirect:/portal/base/notice/index.do?type=" + notice.getType();
    }

    /**
     * 新增
     *
     * @param baseNotice 实体
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(BaseNotice baseNotice) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        baseNotice.setUpdateUser(user_id);
        BaseNotice notice = baseNoticeService.save(baseNotice);
        return "redirect:/portal/base/notice/index.do?type=" + notice.getType();

    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("/base/noticeUpdate");
        mv.addObject("departmentList", organizationDepartmentService.findAll());
        mv.addObject("baseNotice", baseNoticeService.findOne(id));
        return mv;
    }

    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("/base/noticeDetail");
        mv.addObject("baseNotice", baseNoticeService.findOne(id));
        BaseNoticeUserSearchVO searchVO = new BaseNoticeUserSearchVO();
        searchVO.setNoticeId(id);
        mv.addObject("baseNoticeUsers", baseNoticeUserService.getListByNoticeId(searchVO));
        return mv;
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(Integer type) {
        ModelAndView mv = new ModelAndView("/base/noticeAdd");
        mv.addObject("departmentList", organizationDepartmentService.findAll());
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public BaseNotice findOne(String id) {
        return baseNoticeService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(String id) {
        baseNoticeUserService.deleteByNoticeId(id);
        baseNoticeService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseNotice> findAll() {
        return baseNoticeService.findAll();
    }


    /**
     * 删除文件路径和名称
     */
    @RequestMapping(value = "delFileName", method = RequestMethod.GET)
    @ResponseBody
    public void delFileName(int id) {
        baseNoticeService.setFilePathAndPathNameById("", "", id);
    }

}
