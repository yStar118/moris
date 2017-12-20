package com.app;

import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import com.util.redis.RedisKeyUtil;
import com.util.redis.RedisUtil;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 高宇飞 on 2017/8/12.
 */
public class AppBaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    public SysUser getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader("accessToken");
        if (token != null) {
            String userName = RedisUtil.get(RedisKeyUtil.APP_USER_TOKEN + "-" + token);
            if (StringUtil.isNotNullOrEmpty(userName)) {
                return sysUserService.getByUsername(userName);
            } else {
                return null;
            }
        }
        return null;
    }
}
