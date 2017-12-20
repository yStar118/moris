package com.app;

import com.app.dto.InspectionTaskDTO;
import com.app.dto.ListDTO;
import com.app.dto.SysUserDTO;
import com.common.vo.JsonResult;
import com.entity.InspectionTask;
import com.entity.OrganizationDepartment;
import com.service.InspectionTaskService;
import com.service.OrganizationDepartmentService;
import com.sys.model.SysUser;
import com.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/17 16:47:12
 * 任务
 */
@RestController
@RequestMapping("api/inspection/task")
public class AppInspectionTaskController extends AppBaseController {

    @Autowired
    private OrganizationDepartmentService organizationDepartmentService;
    @Autowired
    private InspectionTaskService inspectionTaskService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取未分配的任务列表
     *
     * @param request 请求
     * @return JsonResult
     */
    @GetMapping("getProjectList")
    public JsonResult getProjectList(HttpServletRequest request) {
        SysUser currentUser = getCurrentUser(request);
        if (currentUser != null) {
            String departmentId = currentUser.getDepartmentId().toString();
            OrganizationDepartment one = organizationDepartmentService.findOne(departmentId);
            ListDTO<InspectionTaskDTO> objectListDTO = new ListDTO<>();
            if (one != null) {
                List<InspectionTaskDTO> inspectionTaskDTOS = inspectionTaskService.getListByProjectForApi(one.getCode());

                if (inspectionTaskDTOS != null && inspectionTaskDTOS.size() > 0) {
                    objectListDTO.setTotal(inspectionTaskDTOS.size());
                    objectListDTO.setList(inspectionTaskDTOS);
                    return new JsonResult(true, "请求成功", objectListDTO);
                } else {
                    return new JsonResult(true, "暂时没有任务", objectListDTO);
                }

            } else {
                return new JsonResult(true, "暂时没有任务", objectListDTO);
            }
        } else {
            return new JsonResult(false, "登录已失效");
        }
    }

    /**
     * 获取普通职工列表
     */
    @GetMapping("getUserListByTasks")
    public JsonResult getUserListByTask(HttpServletRequest request) {
        SysUser sysUser = getCurrentUser(request);
        if (sysUser != null) {
            //查找
            List<SysUserDTO> sysUserList = sysUserService.searchByDepartmentIdAndRoleId(sysUser.getDepartmentId(), 5);
            return new JsonResult(true, "请求成功", sysUserList);
        } else {
            return new JsonResult(false, "登录已失效");
        }
    }

    /**
     * 获取历史记录
     */
    @GetMapping("getHistoryTask")
    public JsonResult getHistoryTask(HttpServletRequest request) {
        SysUser sysUser = getCurrentUser(request);
        if (sysUser != null) {
            List<InspectionTask> historyTaskList = inspectionTaskService.getHistoryTask(sysUser.getId());
            return new JsonResult(true, "请求成功", historyTaskList);
        } else {
            return new JsonResult(false, "登录已失效");
        }
    }

    @GetMapping("getTaskSubById")
    public JsonResult getTaskSubById(HttpServletRequest request, String taskId) {
        SysUser sysUser = getCurrentUser(request);
        if (sysUser != null) {
            List<InspectionTask> historyTaskList = inspectionTaskService.getHistoryTask(sysUser.getId());
            return new JsonResult(true, "请求成功", historyTaskList);
        } else {
            return new JsonResult(false, "登录已失效");
        }
    }

    /**
     * 分配任务
     */
    @PostMapping("allotmentTask")
    public JsonResult allotmentTask(@RequestBody List<InspectionTask> list) {
        List<InspectionTask> save = inspectionTaskService.save(list);
        if (save != null && save.size() > 0) {
            return new JsonResult(true, "分配任务成功", new ArrayList<>());
        } else {
            return new JsonResult(false, "分配任务失败");
        }
    }

    /**
     * 同步任务 ---下载最新任务
     */
    @GetMapping("synchronizingTask")
    public JsonResult synchronizingTask(HttpServletRequest request) {
        List<InspectionTaskDTO> inspectionTaskList = inspectionTaskService.getListForAppByUserId(getCurrentUser(request).getId());
        return new JsonResult(true, "获取最新任务成功", inspectionTaskList);
    }

    /**
     * 同步任务 ---上传任务
     */
    @PostMapping("uploadTask")
    public JsonResult uploadTask(@RequestBody List<InspectionTask> inspectionTaskList) {
        boolean b = inspectionTaskService.saveUploadTask(inspectionTaskList);
        return new JsonResult(b, "上传成功", new ArrayList<>());
    }

    /**
     * 确认任务
     */
    @PostMapping("confirmTask")
    public JsonResult confirmTask(String id) {
        inspectionTaskService.saveConfirmTask(2, id);
        return new JsonResult(true, "确认成功", new ArrayList<>());
    }

    /**
     * 重置任务
     */
    @PostMapping("withdrawTask")
    public JsonResult withdrawTask(String id) {
        inspectionTaskService.saveConfirmTask(0, id);
        return new JsonResult(true, "撤回成功", new ArrayList<>());
    }
}
