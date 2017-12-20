package com.util.session;

import com.util.string.StringUtil;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    /**
     * 功能描述:获取用户session
     *
     * @return UserSession
     * @author 高宇飞
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") != null)
            return (UserSession) request.getSession().getAttribute("userSession");
        else {
            return null;
        }
    }


    /**
     * 获取token
     * APP使用
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtil.isNotNullOrEmpty(token)) {
            return token;
        } else {
            return "";
        }
    }

}
