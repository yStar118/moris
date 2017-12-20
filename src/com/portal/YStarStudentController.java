package com.portal;

import com.entity.YStarStudent;

import com.searchVO.YStarStudentSearchVO;
import com.service.YStarStudentService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wyx-pc on 2017/12/18.
 * 学生类
 */
@Controller
@RequestMapping("portal/base/qstudent")//路径
public class YStarStudentController {
    @Autowired
    private YStarStudentService yStarStudentService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("base/studentIndex");
    }

    //
    @ResponseBody
    public DataTablesResult getList(YStarStudentSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<YStarStudent> list = yStarStudentService.getList(searchVO);
        result.setData(searchVO.getDraw());
        result.setRecordsFiltered(searchVO.getTotal());
        result.setRecordsTotal(searchVO.getTotal());
        result.setData(list);


        return result;
    }

    //去保存
    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(Integer id) {
        ModelAndView mv = new ModelAndView("/base/studentSave");
        if (id == null) {
            mv.addObject("student", new YStarStudent());
        } else {
            mv.addObject("student", yStarStudentService.findOne(id));
        }
        return mv;
    }

    //新增  修改
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(YStarStudent yStarStudent) {
        YStarStudent yStarStudent1 = yStarStudentService.save(yStarStudent);
        System.out.println("======" + yStarStudent1.getId());
        return "redirect:/portal/base/student/Index.do";
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<YStarStudent> findAll() {
        return yStarStudentService.findAll();
    }


}
