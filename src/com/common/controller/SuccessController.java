package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class SuccessController {

    @RequestMapping("success")
    public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        mv.addObject("msg", request.getParameter("msg"));
        mv.addObject("backUrl", request.getParameter("backUrl"));
        return mv;
    }
}
