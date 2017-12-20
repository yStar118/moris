package com.portal;

import com.common.controller.BaseController;
import com.entity.ContingencyInfo;
import com.searchVO.ContingencyInfoSearchVO;
import com.service.ContingencyCategoryService;
import com.service.ContingencyInfoService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/25.
 * 应急预案
 */
@Controller
@RequestMapping("portal/contingency/info")
public class ContingencyInfoController extends BaseController {

    private final ContingencyInfoService contingencyInfoService;
    private final ContingencyCategoryService contingencyCategoryService;

    @Autowired
    public ContingencyInfoController(ContingencyInfoService contingencyInfoService, ContingencyCategoryService contingencyCategoryService) {
        this.contingencyInfoService = contingencyInfoService;
        this.contingencyCategoryService = contingencyCategoryService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/contingency/infoIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(ContingencyInfoSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<ContingencyInfo> list = contingencyInfoService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(Integer id) {
        ModelAndView mv = new ModelAndView("/contingency/infoSave");
        mv.addObject("categoryList", contingencyCategoryService.findAll());
        if (id == null) {
            mv.addObject("contingencyInfo", new ContingencyInfo());
        } else {
            mv.addObject("contingencyInfo", contingencyInfoService.findOne(id));
        }
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param contingencyCategory 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ContingencyInfo contingencyCategory) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (contingencyCategory.getId() == null) {
            contingencyCategory.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        contingencyCategory.setUpdateUser(user_id);
        contingencyInfoService.save(contingencyCategory);
        return "redirect:/portal/contingency/info/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public ContingencyInfo findOne(Integer id) {
        return contingencyInfoService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(Integer id) {
        contingencyInfoService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<ContingencyInfo> findAll() {
        return contingencyInfoService.findAll();
    }


    /**
     * 删除文件路径和名称
     */
    @RequestMapping(value = "delFileName", method = RequestMethod.GET)
    @ResponseBody
    public void delFileName(int id) {
        contingencyInfoService.setFilePathAndPathNameById("", "", id);
    }


    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer id) {
        ModelAndView mv = new ModelAndView("/contingency/infoDetail");
        mv.addObject("contingencyInfo", contingencyInfoService.findOne(id));
        return mv;
    }

}
