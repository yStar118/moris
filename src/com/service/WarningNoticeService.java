package com.service;

import com.entity.InspectionTask;
import com.repositories.InspectionTaskRepository;
import com.sms.SmsParam;
import com.sys.dao.SysUserDao;
import com.sys.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/10/25 13:33:04
 * 预警报警通知
 */
@Service
public class WarningNoticeService {

    private static Logger logger = LoggerFactory.getLogger("jobLog");
    @Autowired
    private InspectionTaskRepository inspectionTaskRepository;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 漏检报警
     */
    public void leakageCheckAlarm() {
        //获取即将到开始时间没有分配的任务
        List<InspectionTask> allotmentTask = inspectionTaskRepository.findByNotAllotmentTask();
        for (InspectionTask inspectionTask : allotmentTask) {
            List<SysUser> sysUsers = sysUserDao.searchByDepartmentCodeAndRoleId(inspectionTask.getDepartmentCode(), 9);
            if (sysUsers != null && sysUsers.size() > 0) {
                for (SysUser user : sysUsers) {
                    SmsParam smsParam = new SmsParam();
                    smsParam.setDepartmentName(inspectionTask.getDepartmentName());
                    smsParam.setJobsName(inspectionTask.getJobsName());
                    smsParam.setContent("系统检查还有任务没有分配可能会造成漏检，请进入系统进行查看。");
                    try {
                       // AliyunTXSms.sendSms(user.getTelephone(), JsonUtil.toStr(smsParam));
                    } catch (Exception e) {
                        logger.info("发送漏检短信出现异常" + e.toString());
                    }
                }
            }
        }
    }

    public void updateTheEmptyInspection() {
        inspectionTaskRepository.setStatus();
    }

    /**
     * 空检报警
     */
    public void emptyCheckTaskAlarm() {
        //获取即将到开始时间没有分配的任务
        List<InspectionTask> allotmentTask = inspectionTaskRepository.findByEmptyCheckTask();
        if (allotmentTask != null && allotmentTask.size() > 0) {
            for (InspectionTask inspectionTask : allotmentTask) {
                List<SysUser> sysUsers = sysUserDao.searchByDepartmentCodeAndRoleId(inspectionTask.getDepartmentCode(), 9);
                if (sysUsers != null && sysUsers.size() > 0) {
                    for (SysUser user : sysUsers) {
                        SmsParam smsParam = new SmsParam();
                        smsParam.setDepartmentName(inspectionTask.getDepartmentName());
                        smsParam.setJobsName(inspectionTask.getJobsName());
                        smsParam.setContent("系统检查有任务存在空检，请进入系统进行查看。");
                        try {
                           // AliyunTXSms.sendSms(user.getTelephone(), JsonUtil.toStr(smsParam));
                        } catch (Exception e) {
                            logger.info("发送空检短信出现异常" + e.toString());
                        }
                    }
                }
            }
        }
    }
}
