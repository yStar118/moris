package com.app.token;

import com.common.vo.JsonResult;
import com.sms.AliyunTXSms;
import com.sms.SmsParam;
import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import com.util.code.RandomCodeUtil;
import com.util.json.JsonUtil;
import com.util.redis.RedisKeyUtil;
import com.util.redis.RedisUtil;
import com.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 高宇飞 on 2017/8/8.
 */
@RestController
@RequestMapping("oauthToken/")
public class TokenControllerApi {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @param request  请求
     * @param userName 用户名
     * @param password 密码
     */
    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(HttpServletRequest request, String userName, String password) {

        if (StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(password)) {
            return new JsonResult(false, "用户名或者密码未填写");
        } else {
            SysUser sysUser = sysUserService.getByUsername(userName);
            if (sysUser != null) {
                if (sysUser.getStatus() == 1) {
                    if (sysUserService.checkPass(sysUser, password)) {
                        String token = RandomCodeUtil.createRandomCode(32);
                        RedisUtil.set(RedisKeyUtil.APP_USER_TOKEN + "-" + token, sysUser.getUsername());
                        sysUser.setToken(token);
                        return new JsonResult(true, "登录成功", sysUser);
                    } else {
                        return new JsonResult(false, "密码错误");
                    }
                } else {
                    return new JsonResult(false, "用户被锁定");
                }
            } else {
                return new JsonResult(false, "用户不存在");
            }
        }
    }

    @RequestMapping("failure")
    public JsonResult failure() {
        return new JsonResult(false, "登录信息失效");
    }


    @RequestMapping("sendDuanXin")
    public void sendDuanXin() {
        SmsParam smsParam = new SmsParam();
        smsParam.setDepartmentName("111");
        smsParam.setJobsName("111");
        smsParam.setContent("111");
        AliyunTXSms.sendSms("18735953992", JsonUtil.toStr(smsParam));
    }
}
