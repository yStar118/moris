package com.portal;


import com.common.controller.BaseController;
import com.entity.BaseDocument;
import com.searchVO.BaseDocumentSearchVO;
import com.service.BaseDocumentService;
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
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
@Controller
@RequestMapping("portal/base/document")
public class BaseDocumentController extends BaseController {

    private final BaseDocumentService baseDocumentService;

    @Autowired
    public BaseDocumentController(BaseDocumentService baseDocumentService) {
        this.baseDocumentService = baseDocumentService;

    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/base/documentIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(BaseDocumentSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<BaseDocument> list = baseDocumentService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param baseDocument 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public BaseDocument save(BaseDocument baseDocument) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (StringUtil.isNullOrEmpty(baseDocument.getId())) {
            baseDocument.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        return baseDocumentService.save(baseDocument);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public BaseDocument findOne(String id) {
        return baseDocumentService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(String id) {
        baseDocumentService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseDocument> findAll() {
        return baseDocumentService.findAll();
    }

}
