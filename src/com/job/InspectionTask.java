package com.job;

import com.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 高宇飞 on 2017/9/3 20:41
 */
@Component
@Lazy(false)
public class InspectionTask {

    private static Logger logger = LoggerFactory.getLogger("jobLog");
    @Autowired
    private InspectionTaskService inspectionTaskService;
    @Autowired
    private WarningNoticeService warningNoticeService;
    @Autowired
    private StatCheckPointService statCheckPointService;
    @Autowired
    private StatCheckResultService statCheckResultService;
    @Autowired
    private InspectionRectificationService inspectionRectificationService;

    /**
     * 自动生成检查任务
     * 每天凌晨0点执行
     */
    @Scheduled(cron = "0 0 23 * * ?")
    @Transactional
    public void inspectOrders() {
        logger.info("开始自动生成检查任务");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "生成检查任务");
        try {
            inspectionTaskService.updateAutomationTask();
        } catch (Exception e) {
            logger.error("生成检查任务发生错误\r\n", e);
        }
    }

    /**
     * 漏检预警
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Transactional
    public void leakageCheckAlarm() {
        logger.info("开始自动检查任务进行漏检预警");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  漏检预警");
        try {
            warningNoticeService.leakageCheckAlarm();
        } catch (Exception e) {
            logger.error("生成漏检预警发生错误\r\n", e);
        }
    }

    /**
     * 把过期任务修改为空检
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    @Transactional
    public void modifyTheEmptyInspection() {
        logger.info("开始自动把过期任务修改为空检");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "过期任务修改为空检");
        try {
            warningNoticeService.updateTheEmptyInspection();
        } catch (Exception e) {
            logger.error("过期任务修改为空检发生错误\r\n", e);
        }
    }

    /**
     * 空检报警
     */
    @Scheduled(cron = "0 5 0/4 * * ?")
    @Transactional
    public void emptyCheckTaskAlarm() {
        logger.info("开始自动检查任务进行空检报警");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "空检报警");
        try {
            warningNoticeService.emptyCheckTaskAlarm();
        } catch (Exception e) {
            logger.error("检查任务进行空检报警发生错误\r\n", e);
        }
    }

    /**
     * 每日统计空检、漏检、弱检
     */
    @Scheduled(cron = "0 10 0 * * ?")
    @Transactional
    public void statTimingTaskForBehavior() {
        logger.info("开始自动检查任务进行检查行为统计");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  每日统计空检、漏检、弱检");
        try {
            statCheckPointService.updateTimingStat();
        } catch (Exception e) {
            logger.error("检查行为统计发生错误\r\n", e);
        }
    }


    /**
     * 每日统计检查结果
     */
    @Scheduled(cron = "0 15 0 * * ?")
    @Transactional
    public void statTimingTaskForResult() {
        logger.info("开始自动检查任务进行检查结果统计");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  每日统计检查结果");
        try {
            statCheckResultService.updateTimingStat();
        } catch (Exception e) {
            logger.error("检查结果统计发生错误\r\n", e);
        }
    }

    /**
     * 每日对到期的整改任务进行报警
     */
    @Scheduled(cron = "0 20 0 * * ?")
    @Transactional
    public void alarmTask() {
        logger.info("开始自动对到期的整改任务进行报警");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  对到期的整改任务进行报警");
        try {
            inspectionRectificationService.alarmTask();
        } catch (Exception e) {
            logger.error("对到期的整改任务进行报警发生错误\r\n", e.toString());
        }
    }

}