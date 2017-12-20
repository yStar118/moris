package com.service;

import com.common.service.BaseService;
import com.entity.InspectionRectification;
import com.repositories.InspectionRectificationRepository;
import com.searchVO.InspectionRectificationSearchVO;
import com.sms.SmsParam;
import com.sys.dao.SysUserDao;
import com.sys.model.SysUser;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/24 20:56:20
 * 整改计划
 */
@Service
public class InspectionRectificationService implements BaseService<InspectionRectification, String> {

    private static Logger logger = LoggerFactory.getLogger("jobLog");
    @Autowired
    private InspectionRectificationRepository inspectionRectificationRepository;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public <S extends InspectionRectification> S save(S entity) {
        return inspectionRectificationRepository.save(entity);
    }

    @Override
    public InspectionRectification findOne(String primaryKey) {
        InspectionRectification rectification = inspectionRectificationRepository.findOne(primaryKey);
        if (rectification != null) {
            if (rectification.getRectificationUserId() != null) {
                SysUser sysUser = sysUserDao.get(rectification.getRectificationUserId());
                if (sysUser != null) {
                    rectification.setRectificationUserName(sysUser.getRealname());
                }
            }
        }
        return rectification;
    }

    @Override
    public List<InspectionRectification> findAll() {
        return IterableUtils.toList(inspectionRectificationRepository.findAll());
    }

    @Override
    public long count() {
        return inspectionRectificationRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        inspectionRectificationRepository.delete(primaryKey);
    }


    public List<InspectionRectification> getList(InspectionRectificationSearchVO searchVO) {
        return inspectionRectificationRepository.getList(searchVO);
    }

    public void updateRectification(String id, String rectificationImg) {
        inspectionRectificationRepository.updateRectification(id, rectificationImg);
    }

    /**
     * 定时任务   整改任务到期时报警
     */
    public void alarmTask() {
        //查询 到期的整改任务
        List<InspectionRectification> inspectionRectificationList = inspectionRectificationRepository.findByLastDate();
        if (inspectionRectificationList != null && inspectionRectificationList.size() > 0) {
            for (InspectionRectification inspectionRectification : inspectionRectificationList) {
                SmsParam smsParam = new SmsParam();
                smsParam.setDepartmentName(inspectionRectification.getDepartmentName());
                smsParam.setJobsName(inspectionRectification.getJobsName());
                smsParam.setContent("有整改任务到期还未完成整改，请及时处理");
                logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                        ":发送短信：" + inspectionRectification.getTelephone()
                        + ",内容是：" + "有整改任务到期还未完成整改，请及时处理");
                try {
                   // AliyunTXSms.sendSms(inspectionRectification.getTelephone(), JsonUtil.toStr(smsParam));
                }catch (Exception e){
                    logger.info("发送整改短信出现异常"+e.toString());
                }
            }
        }
    }
}
