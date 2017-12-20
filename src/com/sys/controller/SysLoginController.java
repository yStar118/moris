package com.sys.controller;

import com.sys.model.SysUser;
import com.sys.model.SysUserLogin;
import com.sys.service.SysLoginService;
import com.sys.service.SysUserService;
import com.util.json.JsonUtil;
import com.util.session.SessionUtil;
import com.util.session.UserSession;
import com.util.string.StringUtil;
import com.util.verifycode.VerifyCodeUtil;
import com.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RequestMapping("/portal")
@Controller
public class SysLoginController {
    private final SysUserService sysUserService;
    private final SysLoginService userLoginService;

    @Autowired
    public SysLoginController(SysUserService sysUserService, SysLoginService userLoginService) {
        this.sysUserService = sysUserService;
        this.userLoginService = userLoginService;
    }

    /**
     * 进入系统登录界面
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        return mv;
    }

    /**
     * 登录校验
     */
    @RequestMapping("/checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String verifyCode) {
        //		// 校验账号和密码是否都为空
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "用户名或密码错误"));
            return;
        }

        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null || !sysUserService.checkPass(sysUser, password)) {
            userLoginService.addVerifyCodeNum(username);
            String json = "{\"success\":" + false + ",\"msgText\":\"用户名或密码错误\"}";
            WebUtil.out(response, json);
        } else {
            if (sysUser.getStatus() == 2 || sysUser.getStatus() == 9) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "该用户已锁定"));
            } else if (sysUser.getStatus() == 0) {
                WebUtil.out(response, JsonUtil.createOperaStr(false, "正在审核中"));
            } else {
                String ip = StringUtil.getIp(request);
                UserSession userSession = new UserSession();
                userSession.setUser_id(sysUser.getId());
                userSession.setUser_name(sysUser.getUsername());
                userSession.setUser_id(sysUser.getId());
                userSession.setUser_ip(ip);
                userSession.setRole_id(sysUser.getRole_id());
                userSession.setRole_name(sysUser.getRole_name());// 角色
                userSession.setRealname(sysUser.getRealname());
                request.getSession().setAttribute("userSession", userSession);

                request.getSession().setMaxInactiveInterval(1000 * 60 * 30);// 设置过期时间30分钟
                // 插入登录日志
                SysUserLogin sysUserLogin = new SysUserLogin();
                sysUserLogin.setUser_id(sysUser.getId());
                sysUserLogin.setLogin_date(new Date());
                sysUserLogin.setLogin_ip(ip);
                sysUserLogin.setTerminal(WebUtil.getSafeStr(request.getParameter("terminal")));
                sysUserLogin.setExplorerType(WebUtil.getSafeStr(request.getParameter("explorerType")));
                sysUserLogin.setExplorerVersion(WebUtil.getSafeStr(request.getParameter("explorerVersion")));
                userLoginService.add(sysUserLogin);
                WebUtil.out(response, JsonUtil.createOperaStr(true, "登录成功"));
            }
        }
    }

    /**
     * 生成图形验证码
     *
     * @param request
     * @param response
     * @param username
     * @throws Exception
     */
    @RequestMapping(value = "/generateVerifyCode.do")
    public void generateVerifyCode(HttpServletRequest request, HttpServletResponse response, String username) throws Exception {
        String randomCode = userLoginService.generateVerifyCode(username);
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        VerifyCodeUtil vCode = new VerifyCodeUtil(120, 40, 4, 100, randomCode);
        vCode.write(response.getOutputStream());
    }

    /**
     * 进入首页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        UserSession userSession = SessionUtil.getUserSession(request);
        if (userSession != null) {
            mv.setViewName("/index");
        } else {
            mv.setViewName("redirect:/portal/login.do");
        }
        return mv;
    }

    /**
     * 生成menu
     *
     * @param request
     * @param response
     */
    @RequestMapping("/createMenu")
    public void createMenu(HttpServletRequest request, HttpServletResponse response) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        String json = sysUserService.createMenu(userSession.getUser_id());
        WebUtil.out(response, json);
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // request.getSession().removeAttribute("userSession");
        request.getSession().invalidate();
        return "forward:/portal/login.do";
    }

    /**
     * 修改个人信息，登录页面
     */
    @RequestMapping("/updateInfo")
    public void updateInfo(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        String oldPass = WebUtil.getSafeStr(request.getParameter("oldPass"));
        String newPass = WebUtil.getSafeStr(request.getParameter("newPass"));
        int flag = sysUserService.updateInfo(sysUser, oldPass, newPass);
        if (flag == 0)
            WebUtil.out(response, JsonUtil.createOperaStr(false, "修改失败"));
        else if (flag == 1)
            WebUtil.out(response, JsonUtil.createOperaStr(true, "修改成功"));
        else if (flag == 2)
            WebUtil.out(response, JsonUtil.createOperaStr(false, "原密码输入错误"));
    }

    /**
     * 进入修改密码界面
     *
     * @param request
     * @param response
     * @param sysUser
     */
    @RequestMapping("/toUpdatePass")
    public ModelAndView toUpdatePass(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/updatePass");
        mv.addObject("backUrl", "index.do");
        return mv;
    }

    /**
     * 修改个人信息，登录页面
     */
    @RequestMapping("/updatePass")
    public String updatePass(HttpServletRequest request) {
        String oldPass = WebUtil.getSafeStr(request.getParameter("oldPass"));
        String newPass = WebUtil.getSafeStr(request.getParameter("newPass"));
        UserSession userSession = SessionUtil.getUserSession(request);
        assert userSession != null;
        int flag = sysUserService.updatePass(userSession.getUser_id(), oldPass, newPass);
        if (flag == 0)
            return "forward:/error.do?msg=" + StringUtil.encodeUrl("密码修改失败");
        else if (flag == 2)
            return "forward:/error.do?msg=" + StringUtil.encodeUrl("原密码输入错误");
        else
            return "forward:/success.do?msg=" + StringUtil.encodeUrl("密码修改成功");
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getUserInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        UserSession userSession = SessionUtil.getUserSession(request);
        // 获取用户信息
        assert userSession != null;
        SysUser sysUser = sysUserService.get(userSession.getUser_id());
        WebUtil.out(response, JsonUtil.toStr(sysUser));
    }

    /**
     * 根据地址获取选中菜单
     *
     * @param request
     * @param response
     */
    @RequestMapping("/checkMenuChecked")
    public void checkMenuChecked(HttpServletRequest request, HttpServletResponse response) {
        UserSession userSession = SessionUtil.getUserSession(request);
        // 获取用户信息
        assert userSession != null;
        SysUser sysUser = sysUserService.get(userSession.getUser_id());
        WebUtil.out(response, JsonUtil.toStr(sysUser));
    }

    /**
     * 进入系统注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/register");
        return mv;
    }


}
