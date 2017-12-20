package com.common.controller;


import com.util.session.UserSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 1553280431@qq.com on 2017/5/26.
 * 基础控制器
 */
public class BaseController {

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    public UserSession getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if (request.getSession().getAttribute("userSession") != null) {
            return (UserSession) request.getSession().getAttribute("userSession");
        } else {
            return null;
        }
    }
}
