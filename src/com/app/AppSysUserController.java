package com.app;

import com.common.vo.JsonResult;
import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 高宇飞 on 2017/8/12.
 * 用户相关
 */
@RestController
@RequestMapping("api/user")
public class AppSysUserController extends AppBaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("userInfo")
    public JsonResult userInfo(HttpServletRequest request) {
        SysUser sysUser = getCurrentUser(request);
        if (sysUser != null) {
            return new JsonResult(true, "请求成功", sysUser);
        } else {
            return new JsonResult(false, "请求失败");
        }
    }

    @PostMapping("updatePassword")
    public JsonResult updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        SysUser sysUser = getCurrentUser(request);
        if (sysUser == null) {
            return new JsonResult(false, "登录已失效");
        }
        int flag = sysUserService.updatePass(sysUser.getId(), oldPassword, newPassword);
        if (flag == 0)
            return new JsonResult(false, "密码修改失败");
        else if (flag == 2)
            return new JsonResult(false, "原密码输入错误");
        else
            return new JsonResult(true, "密码修改成功");

    }


}

