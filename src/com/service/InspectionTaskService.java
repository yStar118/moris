package com.service;

import com.app.dto.InspectionTaskDTO;
import com.common.service.BaseService;
import com.common.vo.TaskDateVO;
import com.entity.*;
import com.model.InspectionTaskAlarm;
import com.repositories.*;
import com.searchVO.InspectionPointExternalSearchVO;
import com.searchVO.InspectionTaskSearchVO;
import com.sms.SmsParam;
import com.sys.dao.SysUserDao;
import com.sys.model.SysUser;
import com.util.config.PubConfig;
import com.util.json.JsonUtil;
import com.util.string.StringUtil;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 高宇飞 on 2017/8/10.
 * 检查任务
 */
@Service
public class InspectionTaskService implements BaseService<InspectionTask, String> {

    private static Logger logger = LoggerFactory.getLogger("jobLog");
    @Autowired
    private InspectionTaskRepository inspectionTaskRepository;
    @Autowired
    private InspectionPlanRepository inspectionPlanRepository;
    @Autowired
    private InspectionPointExternalRepository inspectionPointExternalRepository;
    @Autowired
    private InspectionTaskSubRepository inspectionTaskSubRepository;
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private InspectionRectificationRepository inspectionRectificationRepository;
    @Autowired
    private BaseNoticeUserService baseNoticeUserService;
    @Autowired
    private BaseNoticeRepository baseNoticeRepository;

    @Override
    public <S extends InspectionTask> S save(S entity) {
        return inspectionTaskRepository.save(entity);
    }

    @Override
    public InspectionTask findOne(String primaryKey) {
        return inspectionTaskRepository.findOne(primaryKey);
    }

    @Override
    public List<InspectionTask> findAll() {
        return IterableUtils.toList(inspectionTaskRepository.findAll());
    }

    /**
     * 获取检查计划列表
     *
     * @param searchVO 检查条件
     * @return 检查计划
     */
    public List<InspectionTask> getListByTask(InspectionTaskSearchVO searchVO) {
        return inspectionTaskRepository.getListByTask(searchVO);
    }

    /**
     * 获取检查任务列表
     *
     * @param searchVO 检查条件
     * @return 检查任务
     */
    public List<InspectionTask> getListByProject(InspectionTaskSearchVO searchVO) {
        return inspectionTaskRepository.getListByProject(searchVO);
    }

    /**
     * 获取未分配的任务列表
     *
     * @param departmentCode 部门编号
     * @return 检查任务DTO
     */
    public List<InspectionTaskDTO> getListByProjectForApi(String departmentCode) {
        return inspectionTaskRepository.getListByProjectForApi(departmentCode);
    }

    @Override
    public long count() {
        return inspectionTaskRepository.count();
    }

    @Override
    public void delete(String primaryKey) {
        inspectionTaskRepository.delete(primaryKey);
    }

    /**
     * 根据方案开始时间和时间间隔自动生成任务()
     */
    public void updateAutomationTask() {
        //获取所有的方案列表
        List<InspectionPlan> planList = IterableUtils.toList(inspectionPlanRepository.findByIsStart(1));
        if (planList != null && planList.size() > 0) {
            //循环方案
            for (InspectionPlan inspectionPlan : planList) {
                //根据方案id获取最新的任务
                List<InspectionTask> taskSearchList = inspectionTaskRepository.findByPlanId(inspectionPlan.getId());
                //如果方案没有对应的任务，则首次新增任务
                if (taskSearchList == null || taskSearchList.size() == 0) {
                    //new检查点关联表的查询条件
                    InspectionPointExternalSearchVO inspectionPointExternalSearchVO = new InspectionPointExternalSearchVO();
                    //根据方案id 获取方案实体
                    InspectionPlan planRepositoryOne = inspectionPlanRepository.findOne(inspectionPlan.getId());
                    //判断方案实体不为空
                    if (planRepositoryOne != null) {
                        //查询条件中set关联表编号
                        inspectionPointExternalSearchVO.setExternalCode(planRepositoryOne.getExternalCode());
                        //查询条件中set下标
                        inspectionPointExternalSearchVO.setIndex(0);
                        //查询条件中set长度
                        inspectionPointExternalSearchVO.setLength(99999);
                    } else {
                        //该方案id查询不到实体 --跳出此次循环
                        continue;
                    }
                    //根据查询条件获取所有该方案的关联检查点
                    List<InspectionPointExternal> pointExternalList = inspectionPointExternalRepository.getList(inspectionPointExternalSearchVO);
                    //判断该方案的关联检查点列表是否为空
                    if (pointExternalList != null && pointExternalList.size() > 0) {
                        //创建检查任务的时间返回VO
                        List<TaskDateVO> taskDateVOList = new ArrayList<>();
                        //定义一个常量 来终止while循环
                        boolean result = true;
                        //方案的最后更新时间就是任务的开始时间
                        long startTime = inspectionPlan.getStartDate().getTime();
                        Date startDateVO = inspectionPlan.getStartDate();
                        while (result) {
                            //方案检查间隔时间 转换为long类型
                            long changeDate = Long.parseLong(inspectionPlan.getCheckTime());
                            //根据方案的最后执行时间+方案间隔时间 获得 任务的结束时间
                            Date endDate = new Date(startTime + changeDate);
                            //添加一个正确的时间结果
                            taskDateVOList.add(new TaskDateVO(true, startDateVO, endDate));
                            startDateVO = endDate;
                            startTime += changeDate;
                            //获取后天的时间戳
                            long compareTime = getCompare(new Date()).getTime();
                            //只添加最近两天的任务，所以判断任务结束时间是否大于后天00:00:01
                            if (endDate.getTime() > compareTime) {
                                if (taskDateVOList.size() > 1) {
                                    taskDateVOList.remove(taskDateVOList.size() - 1);
                                }
                                //如果任务结束时间大于后天00:00:01 则停止生成时间集合
                                //根据方案id修改最后执行时间
                                // TODO: 2017/8/11 暂时不做修改最后时间的处理
                               /* String lastDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
                                inspectionPlanService.updateLastDateById(lastDate, inspectionPlan.getId());*/
                                //添加一个错误的时间结果  用来在接下来的循环中判断是否终止生成任务
                                taskDateVOList.add(new TaskDateVO(false));
                                //赋值result为false 终止循环
                                result = false;
                            }
                        }
                        ArrayList<InspectionTaskSub> taskSubList = new ArrayList<>();
                        //循环时间集合  生成多个任务
                        for (TaskDateVO taskDateVO : taskDateVOList) {
                            //判断时间结果集是否有时间
                            if (taskDateVO.isSuccess()) {
                                //创建检查任务实体
                                InspectionTask inspectionTask = new InspectionTask();
                                //利用工具栏bean复制
                                BeanUtils.copyProperties(planRepositoryOne, inspectionTask);
                                inspectionTask.setPlanId(planRepositoryOne.getId());
                                //格式化任务开始时间为字符串
                                String startDate = new SimpleDateFormat("yyyy-MM-dd").format(taskDateVO.getStartDate());
                                //重新拼接任务名称  格式为时间+任务名称
                                inspectionTask.setName(inspectionTask.getName() + startDate);
                                //赋值任务开始时间
                                inspectionTask.setStartDate(taskDateVO.getStartDate());
                                //赋值任务结束时间
                                inspectionTask.setEndDate(taskDateVO.getEndDate());
                                inspectionTask.setStatus(0);
                                //新增一个任务
                                InspectionTask task = inspectionTaskRepository.save(inspectionTask);

                                //循环关联检查点
                                for (InspectionPointExternal inspectionPointExternal : pointExternalList) {
                                    //创建检查任务子表实体
                                    InspectionTaskSub inspectionTaskSub = new InspectionTaskSub();
                                    //利用工具栏bean复制
                                    BeanUtils.copyProperties(inspectionPointExternal, inspectionTaskSub);
                                    //赋值子表   任务id
                                    inspectionTaskSub.setTaskId(task.getId());
                                    taskSubList.add(inspectionTaskSub);
                                }
                            }
                        }
                        inspectionTaskSubRepository.save(taskSubList);
                    }
                    //该方案没有检查点 -- 跳出此次循环

                } else {
                    //如果方案有对应的任务，则判断任务结束时间是否超过明天（判断是否可以新增任务）
                    //获取最新的任务实体
                    InspectionTask inspectionTask = taskSearchList.get(0);
                    //获取最新任务的结束时间
                    Date endDate = inspectionTask.getEndDate();
                    //获取最新任务的结束时间的时间戳进行比较
                    long endTime = endDate.getTime();
                    //获取后天凌晨00:00:01 的时间戳
                    long compareTime = getCompare(new Date()).getTime();
                    //判断是否可以新增任务
                    if (endTime < compareTime) {
                        //new检查点关联表的查询条件
                        InspectionPointExternalSearchVO inspectionPointExternalSearchVO = new InspectionPointExternalSearchVO();
                        //根据方案id 获取方案实体
                        InspectionPlan planRepositoryOne = inspectionPlanRepository.findOne(inspectionPlan.getId());
                        //判断方案实体不为空
                        if (planRepositoryOne != null) {
                            //查询条件中set关联表编号
                            inspectionPointExternalSearchVO.setExternalCode(planRepositoryOne.getExternalCode());
                            //查询条件中set下标
                            inspectionPointExternalSearchVO.setIndex(0);
                            //查询条件中set长度
                            inspectionPointExternalSearchVO.setLength(99999);
                        } else {
                            //该方案id查询不到实体 --跳出此次循环
                            continue;
                        }
                        //根据查询条件获取所有该方案的关联检查点
                        List<InspectionPointExternal> pointExternalList = inspectionPointExternalRepository.getList(inspectionPointExternalSearchVO);
                        //判断该方案的关联检查点列表是否为空
                        if (pointExternalList != null && pointExternalList.size() > 0) {
                            //创建检查任务的时间返回VO
                            List<TaskDateVO> taskDateVOList = new ArrayList<>();
                            //定义一个常量 来终止while循环
                            boolean result = true;
                            while (result) {
                                //方案检查间隔时间 转换为long类型
                                long changeDate = Long.parseLong(inspectionPlan.getCheckTime());
                                //方案的最后更新时间就是任务的开始时间
                                Date startDate = endDate;
                                //根据方案的最后执行时间+方案间隔时间 获得 任务的结束时间
                                endDate = new Date(endDate.getTime() + changeDate);
                                //添加一个正确的时间结果
                                taskDateVOList.add(new TaskDateVO(true, startDate, endDate));
                                //只添加最近两天的任务，所以判断任务结束时间是否大于后天00:00:01
                                if (endDate.getTime() > compareTime) {
                                    if (taskDateVOList.size() > 1) {
                                        taskDateVOList.remove(taskDateVOList.size() - 1);
                                    }
                                    //如果任务结束时间大于后天00:00:01 则停止生成时间集合
                                    //根据方案id修改最后执行时间
                                    // TODO: 2017/8/11 暂时不做修改最后时间的处理
                               /* String lastDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
                                inspectionPlanService.updateLastDateById(lastDate, inspectionPlan.getId());*/
                                    //添加一个错误的时间结果  用来在接下来的循环中判断是否终止生成任务
                                    taskDateVOList.add(new TaskDateVO(false));
                                    //赋值result为false 终止循环
                                    result = false;
                                }
                            }
                            ArrayList<InspectionTaskSub> taskSubList = new ArrayList<>();
                            //循环时间集合  生成多个任务
                            for (TaskDateVO taskDateVO : taskDateVOList) {
                                //判断时间结果集是否有时间
                                if (taskDateVO.isSuccess()) {
                                    //创建检查任务实体
                                    inspectionTask = new InspectionTask();
                                    //利用工具栏bean复制
                                    BeanUtils.copyProperties(planRepositoryOne, inspectionTask);
                                    inspectionTask.setPlanId(planRepositoryOne.getId());
                                    //格式化任务开始时间为字符串
                                    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(taskDateVO.getStartDate());
                                    //重新拼接任务名称  格式为时间+任务名称
                                    inspectionTask.setName(inspectionTask.getName() + startDate);
                                    //赋值任务开始时间
                                    inspectionTask.setStartDate(taskDateVO.getStartDate());
                                    //赋值任务结束时间
                                    inspectionTask.setEndDate(taskDateVO.getEndDate());
                                    inspectionTask.setStatus(0);
                                    //新增一个任务
                                    InspectionTask task = inspectionTaskRepository.save(inspectionTask);
                                    //循环关联检查点
                                    for (InspectionPointExternal inspectionPointExternal : pointExternalList) {
                                        //创建检查任务子表实体
                                        InspectionTaskSub inspectionTaskSub = new InspectionTaskSub();
                                        //利用工具栏bean复制
                                        BeanUtils.copyProperties(inspectionPointExternal, inspectionTaskSub);
                                        //赋值子表   任务id
                                        inspectionTaskSub.setTaskId(task.getId());
                                        taskSubList.add(inspectionTaskSub);
                                    }
                                }
                            }
                            inspectionTaskSubRepository.save(taskSubList);
                        }
                    }
                }
            }
        }
        //记录日志
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "生成任务成功");
    }

    /**
     * 获取时间
     *
     * @param now 现在的时间
     * @return 格式化的时间
     */
    private Date getCompare(Date now) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, 2);  //设置为后二天
        Date dAfter = calendar.getTime();   //得到后二天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dAfter);    //格式化后二天
        defaultStartDate = defaultStartDate + " 00:00:00";
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        try {
            return sdf.parse(defaultStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取历史任务
     *
     * @param userId 用户id
     * @return 该用户的历史任务
     */
    public List<InspectionTask> getHistoryTask(int userId) {
        Integer saveTaskDayNumber = pubConfig.getSaveTaskDayNumber();
        saveTaskDayNumber = saveTaskDayNumber != 0 ? saveTaskDayNumber - 1 : 2;
        List<InspectionTask> historyTask = inspectionTaskRepository.getHistoryTask(saveTaskDayNumber, userId);
        SysUser sysUser = sysUserDao.get(userId);
        List<InspectionTask> taskDTOS = new ArrayList<>();
        if (historyTask != null && historyTask.size() > 0) {
            for (InspectionTask inspectionTask : historyTask) {
                List<InspectionTaskSub> byTaskId = inspectionTaskSubRepository.findByTaskId(inspectionTask.getId());
                inspectionTask.setTaskSubList(byTaskId == null ? new ArrayList<>() : byTaskId);
                inspectionTask.setCheckUserName(sysUser.getRealname());
                taskDTOS.add(inspectionTask);
            }
            return taskDTOS;
        }
        return new ArrayList<>();
    }

    public List<InspectionTask> save(List<InspectionTask> list) {
        return IterableUtils.toList(inspectionTaskRepository.save(list));
    }

    /**
     * 根据用户id获取任务列表
     *
     * @param userId 用户id
     * @return 检查任务
     */
    public List<InspectionTaskDTO> getListForAppByUserId(int userId) {
        List<InspectionTask> byCheckUserId = inspectionTaskRepository.findByCheckUserId(userId);
        SysUser sysUser = sysUserDao.get(userId);
        List<InspectionTaskDTO> taskDTOS = new ArrayList<>();
        if (byCheckUserId != null && byCheckUserId.size() > 0) {
            for (InspectionTask inspectionTask : byCheckUserId) {
                InspectionTaskDTO inspectionTaskDTO = new InspectionTaskDTO();
                List<InspectionTaskSub> byTaskId = inspectionTaskSubRepository.findByTaskId(inspectionTask.getId());
                BeanUtils.copyProperties(inspectionTask, inspectionTaskDTO);
                inspectionTaskDTO.setTaskSubList(byTaskId == null ? new ArrayList<>() : byTaskId);
                inspectionTaskDTO.setCheckUserName(sysUser.getRealname());
                taskDTOS.add(inspectionTaskDTO);
            }
            return taskDTOS;
        }
        return new ArrayList<>();
    }

    /**
     * 获取检查结果列表
     *
     * @param searchVO 查询VO
     * @return 检查结果列表
     */
    public List<InspectionTask> getResultList(InspectionTaskSearchVO searchVO) {
        return inspectionTaskRepository.getResultList(searchVO);
    }

    /**
     * 上传任务
     *
     * @param inspectionTaskList 上传的任务集合
     * @return true 成功  false  失败
     */
    public boolean saveUploadTask(List<InspectionTask> inspectionTaskList) {
        List<InspectionTaskSub> taskSubArrayList = new ArrayList<>();
        List<InspectionRectification> inspectionRectifications = new ArrayList<>();
        for (InspectionTask inspectionTask : inspectionTaskList) {
            if (inspectionTask.getStatus() != null && (inspectionTask.getStatus() == 3 || inspectionTask.getStatus() == 4)) {
                inspectionTask.setUploadDate(new Date());
            }
            if (inspectionTask.getTaskSubList() != null && inspectionTask.getTaskSubList().size() > 0) {
                taskSubArrayList.addAll(inspectionTask.getTaskSubList());
            }
        }
        inspectionTaskRepository.save(inspectionTaskList);
        if (taskSubArrayList.size() > 0) {

            for (InspectionTaskSub inspectionTaskSub : taskSubArrayList) {
                //判断检查结果参数和检查结果不为空，进行谎报误判嫌疑报警
                if (inspectionTaskSub.getCheckResultValue() != null && inspectionTaskSub.getCheckResult() != null) {
                    //根据检查点id获取最近的检查结果
                    InspectionTaskAlarm byPointIdForAlarm = inspectionTaskSubRepository.findByPointIdForAlarm(inspectionTaskSub.getPointId());
                    if (byPointIdForAlarm != null) {
                        if (inspectionTaskSub.getCheckResult() == 2) {
                            inspectionTaskSub.setCheckResult(0);
                        }
                        if (byPointIdForAlarm.getCheckResult() == 2) {
                            byPointIdForAlarm.setCheckResult(0);
                        }
                        //判断是否相等  如果不等，就需要报警
                        if (!Objects.equals(inspectionTaskSub.getCheckResult(), byPointIdForAlarm.getCheckResult())) {
                            //通过部门编号和角色id查找用户
                            List<SysUser> sysUserList = sysUserDao.searchByDepartmentCodeAndRoleId(byPointIdForAlarm.getDepartmentCode(), 9);
                            if (sysUserList != null && sysUserList.size() > 0) {
                                String content = "任务名称：" + byPointIdForAlarm.getTaskName() + byPointIdForAlarm.getStartDate()
                                        + "到" + byPointIdForAlarm.getEndDate() + ", 设备：" + byPointIdForAlarm.getEquipmentName()
                                        + " ,检查对象：" + byPointIdForAlarm.getObjectName() + " ,检查点：" + byPointIdForAlarm.getPointName() +
                                        "检查结果不同，进行误判与谎报报警。";
                                SmsParam smsParam = new SmsParam();
                                smsParam.setDepartmentName(inspectionTaskSub.getDepartmentName());
                                smsParam.setJobsName(inspectionTaskSub.getJobsName());
                                smsParam.setContent(content);
                                for (SysUser user : sysUserList) {
                                    System.out.println(user.getTelephone() + "发送短信：" + JsonUtil.toStr(smsParam));
                                    try {
                                        //   AliyunTXSms.sendSms(user.getTelephone(), JsonUtil.toStr(smsParam));
                                    } catch (Exception e) {
                                        logger.info("发送误判与谎报报警短信出现异常" + e.toString());
                                    }
                                }
                            }
                        }
                    }
                }

                String fileBase64 = inspectionTaskSub.getFilePath();
                if (StringUtil.isNotNullOrEmpty(fileBase64) && inspectionTaskSub.getCheckResult() == 1) {
                    //base64 转换图片 并返回路径
                    String filePath = saveFile(fileBase64);
                    inspectionTaskSub.setFilePath(filePath);
                    StringBuilder content = new StringBuilder();
                    InspectionRectification inspectionRectification = new InspectionRectification();
                    inspectionRectification.setName("整改" + inspectionTaskSub.getPointName() + "异常");
                    inspectionRectification.setRemark(inspectionTaskSub.getRemark());
                    inspectionRectification.setPointId(inspectionTaskSub.getPointId());
                    inspectionRectification.setPointName(inspectionTaskSub.getPointName());
                    inspectionRectification.setDepartmentCode(inspectionTaskSub.getDepartmentCode());
                    inspectionRectification.setDepartmentName(inspectionTaskSub.getDepartmentName());
                    inspectionRectification.setJobsCode(inspectionTaskSub.getJobsCode());
                    inspectionRectification.setJobsName(inspectionTaskSub.getJobsName());
                    inspectionRectification.setEquipmentName(inspectionTaskSub.getEquipmentName());
                    inspectionRectification.setObjectName(inspectionTaskSub.getObjectName());
                    inspectionRectification.setAbnormalityImg(filePath);
                    inspectionRectification.setStatus(0);
                    inspectionRectifications.add(inspectionRectification);
                    content.append(inspectionTaskSub.getDepartmentName()).append(":")
                            .append(inspectionTaskSub.getJobsName())
                            .append("的检查点")
                            .append(inspectionTaskSub.getPointName())
                            .append("出现异常，异常数值为")
                            .append(inspectionTaskSub.getCheckResultValue())
                            .append("。检查人：")
                            .append(inspectionTaskSub.getCheckUserName())
                            .append("。检查时间")
                            .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inspectionTaskSub.getCheckDate()));
                    //通过部门编号和角色id查找用户
                    List<SysUser> sysUserList = sysUserDao.searchByDepartmentCodeAndRoleId(inspectionTaskSub.getDepartmentCode(), 9);
                    if (sysUserList != null && sysUserList.size() > 0) {
                        BaseNotice baseNotice = new BaseNotice();
                        baseNotice.setCreateUser(1);//系统管理员
                        baseNotice.setUpdateUser(1);
                        baseNotice.setType(1);
                        baseNotice.setIsConfirm(0);
                        baseNotice.setName(inspectionTaskSub.getJobsName() +
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inspectionTaskSub.getCheckDate())
                                + "巡检异常");
                        baseNotice.setContent(content.toString());
                        BaseNotice save = baseNoticeRepository.save(baseNotice);
                        baseNoticeUserService.save(sysUserList, save.getId());
                        SmsParam smsParam = new SmsParam();
                        smsParam.setDepartmentName(inspectionTaskSub.getDepartmentName());
                        smsParam.setJobsName(inspectionTaskSub.getJobsName());
                        smsParam.setContent(content.toString());
                        for (SysUser user : sysUserList) {
                            logger.info(user.getTelephone() + "发送短信：" + JsonUtil.toStr(smsParam));
                            try {
                                // AliyunTXSms.sendSms(user.getTelephone(), JsonUtil.toStr(smsParam));
                            } catch (Exception e) {
                                logger.info("发送巡检异常短信出现异常" + e.toString());
                            }
                        }
                    }
                }
            }
            inspectionTaskSubRepository.save(taskSubArrayList);
            if (inspectionRectifications.size() > 0) {
                inspectionRectificationRepository.save(inspectionRectifications);
            }
        }
        return true;
    }

    /**
     * 修改任务状态
     *
     * @param status 状态值
     * @param id     任务id
     */
    public void saveConfirmTask(int status, String id) {
        inspectionTaskRepository.setStatusById(status, id);
    }

    /**
     * 保存图片
     *
     * @param imgStr 图片base64字节码
     * @return 图片路径
     */
    public String saveFile(String imgStr) {
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String uploadPath = pubConfig.getImageUploadPath();
            Path file_path = Paths.get("/rectificationImg/", DateFormatUtils.format(new Date(), "yyyy-MM/"));
            String suffix = "jpg";
            // 生成jpeg图片
            String imgFilePath = DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "-" +
                    RandomStringUtils.randomAlphanumeric(8) + "." + suffix;

            file_path = Paths.get(file_path.toString());
            File targetFile;
            File file = new File(uploadPath);
            if (!file.exists()) {
                return "配置upload_base_path的路径错误!";
            }
            targetFile = new File(Paths.get(uploadPath, file_path.toString()).toString());
            if (targetFile.exists() || targetFile.mkdirs()) {
                targetFile = new File(Paths.get(uploadPath, file_path.toString()).toString(), imgFilePath);
                OutputStream out = new FileOutputStream(targetFile);
                out.write(b);
                out.flush();
                out.close();
                String file_path_str = file_path.toString().replace("\\", "/");//将statics\\upload\\路径转换为statics/upload
                return Paths.get(file_path_str, imgFilePath).toString().replace("\\", "/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
