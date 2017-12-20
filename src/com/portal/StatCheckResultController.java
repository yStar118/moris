package com.portal;

import com.entity.StatCheckResult;
import com.searchVO.StatCheckResultSearchVO;
import com.service.StatCheckResultService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/8 15:40:33
 */
@Controller
@RequestMapping("portal/stat/checkResult")
public class StatCheckResultController {

    @Autowired
    private StatCheckResultService statCheckResultService;


    /**
     * 进入index页面，
     * type    1 按岗位      2 按设备   3 按检查对象   4按检查点
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index(Integer type) {
        ModelAndView mv = new ModelAndView();
        type = type == null ? 1 : type;
        if (type == 2) {
            mv.setViewName("/stat/checkResultForEquipment");
        } else if (type == 3) {
            mv.setViewName("/stat/checkResultForObject");
        } else if (type == 4) {
            mv.setViewName("/stat/checkResultForPoint");
        } else {
            mv.setViewName("/stat/checkResultForJob");
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        Date monday = c.getTime();
        mv.addObject("startDate", monday);
        mv.addObject("endDate", new Date());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 检查结果 按照岗位分组统计
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getListForJob", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForJob(StatCheckResultSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckResult> list = statCheckResultService.getListForJob(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 检查结果 按照设备分组统计
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getListForEquipment", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForEquipment(StatCheckResultSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckResult> list = statCheckResultService.getListForEquipment(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 检查结果 按照检查对象分组统计
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getListForObject", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForObject(StatCheckResultSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckResult> list = statCheckResultService.getListForObject(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     * 检查结果 按照检查点分组统计
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getListForPoint", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getListForPoint(StatCheckResultSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<StatCheckResult> list = statCheckResultService.getListForPoint(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 导出
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "print", method = RequestMethod.GET)
    @ResponseBody
    public void print(HttpServletRequest request, HttpServletResponse response, StatCheckResultSearchVO searchVO) {
        statCheckResultService.export(request, response, searchVO);
    }
}
