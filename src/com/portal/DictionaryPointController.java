package com.portal;

import com.entity.DictionaryPoint;
import com.searchVO.DictionaryPointSearchVO;
import com.service.DictionaryPointService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/31 14:22:45
 * 字典  方案检索条件
 */
@Controller
@RequestMapping("portal/dictionary/point")
public class DictionaryPointController {

    @Autowired
    private DictionaryPointService dictionaryPointService;

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/dictionary/pointIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(DictionaryPointSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<DictionaryPoint> list = dictionaryPointService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public DictionaryPoint save(DictionaryPoint dictionaryPoint) {
        return dictionaryPointService.save(dictionaryPoint);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public DictionaryPoint findOne(Integer id) {
        return dictionaryPointService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        dictionaryPointService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<DictionaryPoint> findAll() {
        return dictionaryPointService.findAll();
    }
}
