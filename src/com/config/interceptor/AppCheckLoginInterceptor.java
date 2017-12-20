package com.config.interceptor;

import com.app.AppBaseController;
import com.sys.model.SysUser;
import com.util.string.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 高宇飞 on 2017/8/8.
 */
public class AppCheckLoginInterceptor extends AppBaseController implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getHeader("accessToken");
        if (StringUtil.isNotNullOrEmpty(token)) {
            SysUser sysUser = getCurrentUser(request);
            if (sysUser != null) {
                return true;
            }
        }
        request.getRequestDispatcher("/oauthToken/failure.do").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
