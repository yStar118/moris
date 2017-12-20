package com.portal;

import com.common.controller.BaseController;
import com.entity.ContingencyScene;
import com.searchVO.ContingencySceneSearchVO;
import com.service.ContingencySceneService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/8.
 * 现场处置方案
 */
@Controller
@RequestMapping("portal/contingency/scene")
public class ContingencySceneController extends BaseController {

    private final ContingencySceneService contingencySceneService;

    @Autowired
    public ContingencySceneController(ContingencySceneService contingencySceneService) {
        this.contingencySceneService = contingencySceneService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/contingency/sceneIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(ContingencySceneSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<ContingencyScene> list = contingencySceneService.getList(searchVO);
        for (ContingencyScene contingencyScene : list) {
            contingencyScene.setContentTxt(contingencyScene.getReplaceContent());
        }
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(Integer id) {
        ModelAndView mv = new ModelAndView("/contingency/sceneSave");
        if (id == null) {
            mv.addObject("contingencyScene", new ContingencyScene());
        } else {
            mv.addObject("contingencyScene", contingencySceneService.findOne(id));
        }
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param contingencyScene 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ContingencyScene contingencyScene) {
        int user_id = getCurrentUser().getUser_id();
        if (contingencyScene.getId() == null) {
            contingencyScene.setCreateUser(user_id);
        }
        contingencyScene.setUpdateUser(user_id);
        contingencySceneService.save(contingencyScene);
        return "redirect:/portal/contingency/scene/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public ContingencyScene findOne(Integer id) {
        return contingencySceneService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(Integer id) {
        contingencySceneService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<ContingencyScene> findAll() {
        return contingencySceneService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer id) {
        ModelAndView mv = new ModelAndView("/contingency/sceneDetail");
        mv.addObject("contingencyScene", contingencySceneService.findOne(id));
        return mv;
    }
}
