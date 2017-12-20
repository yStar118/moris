package com.portal;

import com.model.StatCheckBehavior;
import com.searchVO.StatCheckResultCustomSearchVO;
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
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/11 14:33:50
 * 检查行为报表
 */
@Controller
@RequestMapping("portal/stat/checkBehavior")
public class StatCheckBehaviorController {

    @Autowired
    private StatCheckResultService statCheckResultService;


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/stat/checkBehavior");
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
        List<StatCheckBehavior> list = statCheckResultService.getListForCheckBehavior(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 导出
     */
    @RequestMapping(value = "print", method = RequestMethod.GET)
    @ResponseBody
    public void print(HttpServletRequest request, HttpServletResponse response) {
        StatCheckResultCustomSearchVO searchVO = new StatCheckResultCustomSearchVO();
        searchVO.setIndex(0);
        searchVO.setLength(9999);
        statCheckResultService.export(request, response, searchVO);
    }
}
