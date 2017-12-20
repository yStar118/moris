package com.sys.controller;

import com.sys.model.SysModule;
import com.sys.service.SysModuleService;
import com.util.backurl.BackUrlUtil;
import com.util.cache.EhCacheUtil;
import com.util.config.PubConfig;
import com.util.controller.BaseController;
import com.util.json.JsonUtil;
import com.util.redis.RedisUtil;
import com.util.string.StringUtil;
import com.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("portal/sys/module")
public class SysModuleController extends BaseController {
    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入模块维护界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/module");
        setBtnAutho(request, "SysModule");
        List<SysModule> list = sysModuleService.list();
        mv.addObject("list", list);// 把获取的记录放到mv里面
        String url = pubConfig.getDynamicServer() + "/portal/sys/module/index.do?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * 新增模块
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        ModelAndView mv = new ModelAndView();
        String ztree = sysModuleService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.setViewName("/sys/moduleAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 修改模块
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysModule sysModule = sysModuleService.get(id);
        String ztree = sysModuleService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysModule", sysModule);
        mv.setViewName("/sys/moduleUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        if (sysModule.getParent_id() == null)
            sysModule.setParent_id(1);
        sysModuleService.add(sysModule);
        return "redirect:/portal/sys/module/index.do";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        if (sysModule.getParent_id() == null)
            sysModule.setParent_id(1);
        if (sysModule.getId() == sysModule.getParent_id()) {
            return "forward:/error.do";
        } else {
            int flag = sysModuleService.update(sysModule);
            if (flag == 1)
                return "redirect:/portal/sys/module/index.do";
            else
                return "forward:/error.do";

        }
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysModuleService.delete(id);
        if (flag == 0)
            return "forward:/error.do";
        else if (flag == 2)
            return "forward:/error.do";
        else
            return "redirect:/portal/sys/module/index.do";
    }

    /**
     * 模块tree grid列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getTreeGrid")
    public void getTreeGrid(HttpServletRequest request, HttpServletResponse response) {
        String json = sysModuleService.getTreeGridJson();
        WebUtil.out(response, json);
    }

    /**
     * 清空缓存
     *
     * @param request
     * @param response
     */
    @RequestMapping("/clearCache")
    public void clearCache(HttpServletRequest request, HttpServletResponse response) {
        EhCacheUtil.removeAll("sysCache");
        WebUtil.out(response, JsonUtil.createOperaStr(true, "操作成功"));
    }

    /**
     * 清空redis
     *
     * @param request
     * @param response
     */
    @RequestMapping("/clearRedis")
    public void clearRedis(HttpServletRequest request, HttpServletResponse response) {
        RedisUtil.flushDb();
        WebUtil.out(response, JsonUtil.createOperaStr(true, "操作成功"));
    }

}
