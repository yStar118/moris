package com.portal;

import com.model.StatCheckResultCustom;
import com.searchVO.StatCheckResultCustomSearchVO;
import com.service.StatCheckResultCustomService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/11 14:33:50
 * 自定义报表
 */
@Controller
@RequestMapping("portal/stat/checkResultCustom")
public class StatCheckResultCustomController {

    @Autowired
    private StatCheckResultCustomService statCheckResultCustomService;


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/stat/checkResultCustom");
        mv.addObject("startDate", new Date());
        mv.addObject("endDate", new Date());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 检查结果 按照岗位分组统计
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(StatCheckResultCustomSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckResultCustom> list = statCheckResultCustomService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

}
