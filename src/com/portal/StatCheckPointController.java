package com.portal;

import com.common.controller.BaseController;
import com.entity.StatCheckPoint;
import com.searchVO.StatCheckPointSearchVO;
import com.service.StatCheckPointService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/6 17:39:42
 */
@Controller
@RequestMapping("portal/stat/checkPoint")
public class StatCheckPointController extends BaseController {

    @Autowired
    private StatCheckPointService statCheckPointService;

    /**
     * 进入index页面，
     * type    1 日报表    2月报表      3年报表
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(Integer type) {
        ModelAndView mv = new ModelAndView();
        type = type == null ? 1 : type;
        if (type == 2) {
            mv.setViewName("/stat/checkPointMonth");
        } else if (type == 3) {
            mv.setViewName("/stat/checkPointYear");
        } else {
            mv.setViewName("/stat/checkPointDay");
        }
        mv.addObject("startDate", new Date());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 日统计
     *
     * @param statCheckPointSearchVO 查询VO
     */
    @RequestMapping(value = "getListForDay", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForDay(StatCheckPointSearchVO statCheckPointSearchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckPoint> list = statCheckPointService.getListForDay(statCheckPointSearchVO);
        result.setDraw(statCheckPointSearchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 月统计
     *
     * @param statCheckPointSearchVO 查询VO
     */
    @RequestMapping(value = "getListForMonth", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForMonth(StatCheckPointSearchVO statCheckPointSearchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckPoint> list = statCheckPointService.getListForMonth(statCheckPointSearchVO);
        result.setDraw(statCheckPointSearchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 年统计
     *
     * @param statCheckPointSearchVO 查询VO
     */
    @RequestMapping(value = "getListForYear", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForYear(StatCheckPointSearchVO statCheckPointSearchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckPoint> list = statCheckPointService.getListForYear(statCheckPointSearchVO);
        result.setDraw(statCheckPointSearchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(statCheckPointSearchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 导出
     *
     * @param statCheckPointSearchVO 查询VO
     */
    @RequestMapping(value = "print", method = RequestMethod.GET)
    @ResponseBody
    public void print(HttpServletRequest request, HttpServletResponse response, StatCheckPointSearchVO statCheckPointSearchVO) {
        statCheckPointService.export(request, response, statCheckPointSearchVO);
    }


}
