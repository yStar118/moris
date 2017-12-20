package com.portal;

import com.entity.BaseStudent;
import com.searchVO.BaseStudentSearchVO;
import com.service.BaseStudentService;
import com.util.datatables.DataTablesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/7/17.
 * 学生
 */
@Controller
@RequestMapping("portal/base/student")
public class BaseStudentController {

    @Autowired
    private BaseStudentService baseStudentService;


    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("base/studentIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    //requestMapping是一个用来处理请求地址映射的注解。用于类上，表示类中的所有
    //响应请求的方法都是以该地址作为父路径 value:制定请求的实际地址 method:制定请的method类型
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    //ResponseBody 将内容或对象作为HTTP响应正文，并调用适合HttpMessageConverter的Adapter转换对象，写入输出流。
    @ResponseBody
    public DataTablesResult getList(BaseStudentSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<BaseStudent> list = baseStudentService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(Integer id) {
        ModelAndView mv = new ModelAndView("/base/studentSave");
        if (id == null) {
            mv.addObject("student", new BaseStudent());
        } else {
            mv.addObject("student", baseStudentService.findOne(id));
        }
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param baseStudent 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(BaseStudent baseStudent) {
        baseStudentService.save(baseStudent);
        return "redirect:/portal/base/student/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public BaseStudent findOne(Integer id) {
        return baseStudentService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(Integer id) {
        baseStudentService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseStudent> findAll() {
        return baseStudentService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer id) {
        ModelAndView mv = new ModelAndView("/base/studentDetail");
        mv.addObject("student", baseStudentService.findOne(id));
        return mv;
    }
}
