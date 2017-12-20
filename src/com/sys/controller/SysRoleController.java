package com.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.service.SysFunctionService;
import com.sys.service.SysModuleService;
import com.sys.service.SysRoleService;
import com.util.backurl.BackUrlUtil;
import com.util.config.PubConfig;
import com.util.json.JsonUtil;
import com.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.model.SysFunction;
import com.sys.model.SysModule;
import com.sys.model.SysRole;
import com.util.controller.BaseController;
import com.util.string.StringUtil;
import com.util.web.WebUtil;

@RequestMapping("portal/sys/role")
@Controller
public class SysRoleController extends BaseController {
	private final SysRoleService sysRoleService;
	private final PubConfig pubConfig;
	private final SysModuleService sysModuleService;
	private final SysFunctionService sysFunctionService;

	@Autowired
	public SysRoleController(SysRoleService sysRoleService, PubConfig pubConfig,
							 SysModuleService sysModuleService, SysFunctionService sysFunctionService) {
		this.sysRoleService = sysRoleService;
		this.pubConfig = pubConfig;
		this.sysModuleService = sysModuleService;
		this.sysFunctionService = sysFunctionService;
	}

	/**
	 * 进入角色维护界面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/role");
		setBtnAutho(request, "SysRole");
		List<SysRole> list = sysRoleService.list();
		mv.addObject("list", list);
		String url = pubConfig.getDynamicServer() + "/portal/sys/role/index.do?";
		mv.addObject("backUrl", StringUtil.encodeUrl(url));
		return mv;
	}

	/**
	 * 新增角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/roleAdd");
		List<SysModule> listModule = sysModuleService.listByType();
		mv.addObject("listModule", listModule);
		List<SysFunction> listFunction = sysFunctionService.getAll();
		mv.addObject("listFunction", listFunction);
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
		SysRole sysRole = sysRoleService.get(id);
		mv.addObject("sysRole", sysRole);
		mv.setViewName("/sys/roleUpdate");
		List<SysModule> listModule = sysModuleService.listByType();
		mv.addObject("listModule", listModule);
		List<SysFunction> listFunction = sysFunctionService.getAll();
		mv.addObject("listFunction", listFunction);
		String checkButton=sysRoleService.checkModuleAndFunction(sysRole.getId());
		mv.addObject("checkButton",checkButton);
		BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
		return mv;
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
		sysRole.setCreate_person(SessionUtil.getUserSession(request).getRealname());//创建人
		sysRole.setIs_delete(1);
		String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
		String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
		int flag = sysRoleService.add(sysRole, moduleArr, functionArr);
		if (flag == 0)
			return "forward:/error.do";
		else
			return "redirect:/portal/sys/role/index.do";
	}

	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
		String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
		String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
		int flag = sysRoleService.update(sysRole, moduleArr, functionArr);
		if (flag == 0)
			return "forward:/error.do";
		else
			return "redirect:/portal/sys/role/index.do";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
		int flag = sysRoleService.delete(id);
		if (flag == 0)
			return "forward:/error.do";
		else
			return "redirect:/portal/sys/role/index.do";
	}

	/**
	 * 角色 grid列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/listJson")
	public void listJson(HttpServletRequest request, HttpServletResponse response) {
		String json = JsonUtil.toStr(sysRoleService.list());
		WebUtil.out(response, json);
	}

	/**
	 * 角色 对应的模块列表，checkbox tree
	 * @param request
	 * @param response
	 */
	@RequestMapping("/listRoleModuleJson")
	public void listRoleModuleJson(HttpServletRequest request, HttpServletResponse response) {
		int role_id = WebUtil.getSafeInt(request.getParameter("role_id"));
		WebUtil.out(response, sysRoleService.listRoleModuleJson(role_id));
	}

	/**
	 * 修改角色对应的模块
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateRoleModule")
	public void updateRoleModule(HttpServletRequest request, HttpServletResponse response, int role_id) {
		String moduleList = WebUtil.getSafeStr(request.getParameter("moduleList"));
		String functionList = WebUtil.getSafeStr(request.getParameter("functionList"));
		int flag = sysRoleService.updateRoleModule(role_id, moduleList, functionList);
		if (flag == 0)
			WebUtil.out(response, JsonUtil.createOperaStr(false, "设置失败"));
		else if (flag == 1)
			WebUtil.out(response, JsonUtil.createOperaStr(true, "设置成功"));
	}
}
