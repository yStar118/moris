package com.service;

import com.app.dto.AppStatcheckResultDTO;
import com.common.service.BaseService;
import com.entity.StatCheckResult;
import com.model.StatCheckBehavior;
import com.repositories.StatCheckResultRepository;
import com.searchVO.StatCheckResultCustomSearchVO;
import com.searchVO.StatCheckResultSearchVO;
import com.util.excel.Excel2007Util;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/8 14:33:49
 * 检查结果报表
 */
@Service
public class StatCheckResultService implements BaseService<StatCheckResult, Integer> {

    @Autowired
    private StatCheckResultRepository statCheckResultRepository;

    @Override
    public <S extends StatCheckResult> S save(S entity) {
        return statCheckResultRepository.save(entity);
    }

    @Override
    public StatCheckResult findOne(Integer primaryKey) {
        return statCheckResultRepository.findOne(primaryKey);
    }

    @Override
    public List<StatCheckResult> findAll() {
        return IterableUtils.toList(statCheckResultRepository.findAll());
    }

    @Override
    public long count() {
        return statCheckResultRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        statCheckResultRepository.delete(primaryKey);
    }

    public void updateTimingStat() {
        List<StatCheckResult> listForJob = statCheckResultRepository.getListForJob();
        if (listForJob != null && listForJob.size() > 0) {
            statCheckResultRepository.save(listForJob);
        }
    }

    public List<StatCheckResult> getListForJob(StatCheckResultSearchVO searchVO) {
        return statCheckResultRepository.getListForJobs(searchVO);
    }

    public List<StatCheckResult> getListForEquipment(StatCheckResultSearchVO searchVO) {
        return statCheckResultRepository.getListForEquipment(searchVO);
    }

    public List<StatCheckResult> getListForObject(StatCheckResultSearchVO searchVO) {
        return statCheckResultRepository.getListForObject(searchVO);
    }

    public List<StatCheckResult> getListForPoint(StatCheckResultSearchVO searchVO) {
        return statCheckResultRepository.getListForPoint(searchVO);
    }

    public void export(HttpServletRequest request, HttpServletResponse response, StatCheckResultSearchVO searchVO) {
        List<StatCheckResult> list;
        String title = "";
        String[][] data = new String[0][];
        String srcFile = null;
        if (searchVO.getType() != null) {
            if (searchVO.getType() == 1) {
                list = statCheckResultRepository.getListForJobs(searchVO);
                title = new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStartDate())
                        + "至" + new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getEndDate())
                        + "检查结果岗位统计报表";
                data = new String[list.size()][6];
                srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckResultJobs.xls";
                int i = 0;
                for (StatCheckResult statCheckResult : list) {
                    data[i][0] = String.valueOf(i + 1);// 序号
                    data[i][1] = statCheckResult.getDepartmentName();// 部门名称
                    data[i][2] = statCheckResult.getJobsName();// 岗位名称
                    data[i][3] = statCheckResult.getCorrectQuantity() != null ? statCheckResult.getCorrectQuantity().toString() : "0";//正常点次
                    data[i][4] = statCheckResult.getCorrectRate() != null ? statCheckResult.getCorrectRate().toString() : "";// 正常率
                    data[i][5] = statCheckResult.getTotalQuantity() != null ? statCheckResult.getTotalQuantity().toString() : "0";// 总点次
                    i++;
                }
            }
            if (searchVO.getType() == 2) {
                list = statCheckResultRepository.getListForEquipment(searchVO);
                title = new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStartDate())
                        + "至" + new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getEndDate())
                        + "检查结果设备统计报表";
                data = new String[list.size()][7];
                srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckResultEquipment.xls";
                int i = 0;
                for (StatCheckResult statCheckResult : list) {
                    data[i][0] = String.valueOf(i + 1);// 序号
                    data[i][1] = statCheckResult.getDepartmentName();// 部门名称
                    data[i][2] = statCheckResult.getJobsName();// 岗位名称
                    data[i][3] = statCheckResult.getEquipmentName();// 设备名称
                    data[i][4] = statCheckResult.getCorrectQuantity() != null ? statCheckResult.getCorrectQuantity().toString() : "0";//正常点次
                    data[i][5] = statCheckResult.getCorrectRate() != null ? statCheckResult.getCorrectRate().toString() : "";// 正常率
                    data[i][6] = statCheckResult.getTotalQuantity() != null ? statCheckResult.getTotalQuantity().toString() : "0";// 总点次
                    i++;
                }
            }
            if (searchVO.getType() == 3) {
                list = statCheckResultRepository.getListForObject(searchVO);
                title = new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStartDate())
                        + "至" + new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getEndDate())
                        + "检查结果检查对象统计报表";
                data = new String[list.size()][8];
                srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckResultObject.xls";
                int i = 0;
                for (StatCheckResult statCheckResult : list) {
                    data[i][0] = String.valueOf(i + 1);// 序号
                    data[i][1] = statCheckResult.getDepartmentName();// 部门名称
                    data[i][2] = statCheckResult.getJobsName();// 岗位名称
                    data[i][3] = statCheckResult.getEquipmentName();// 设备名称
                    data[i][4] = statCheckResult.getObjectName();// 检查对象
                    data[i][5] = statCheckResult.getCorrectQuantity() != null ? statCheckResult.getCorrectQuantity().toString() : "0";//正常点次
                    data[i][6] = statCheckResult.getCorrectRate() != null ? statCheckResult.getCorrectRate().toString() : "";// 正常率
                    data[i][7] = statCheckResult.getTotalQuantity() != null ? statCheckResult.getTotalQuantity().toString() : "0";// 总点次
                    i++;
                }
            }
            if (searchVO.getType() == 4) {
                list = statCheckResultRepository.getListForPoint(searchVO);
                title = new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStartDate())
                        + "至" + new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getEndDate())
                        + "检查结果检查点统计报表";
                data = new String[list.size()][9];
                srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckResultPoint.xls";
                int i = 0;
                for (StatCheckResult statCheckResult : list) {
                    data[i][0] = String.valueOf(i + 1);// 序号
                    data[i][1] = statCheckResult.getDepartmentName();// 部门名称
                    data[i][2] = statCheckResult.getJobsName();// 岗位名称
                    data[i][3] = statCheckResult.getEquipmentName();// 设备名称
                    data[i][4] = statCheckResult.getObjectName();// 检查对象名称
                    data[i][5] = statCheckResult.getPointName();// 检查点名称
                    data[i][6] = statCheckResult.getCorrectQuantity() != null ? statCheckResult.getCorrectQuantity().toString() : "0";//正常点次
                    data[i][7] = statCheckResult.getCorrectRate() != null ? statCheckResult.getCorrectRate().toString() : "";// 正常率
                    data[i][8] = statCheckResult.getTotalQuantity() != null ? statCheckResult.getTotalQuantity().toString() : "0";// 总点次
                    i++;
                }
            }
            Excel2007Util.writeExcel(data, srcFile, title, response, 1, 300);
        }
    }

    /**
     * 获取检结果统计报表  APP   周报表
     *
     * @return 结果
     */
    public List<AppStatcheckResultDTO> getListForAppByWeek(Integer type) {
        return statCheckResultRepository.getListForAppByWeek(type);
    }

    /**
     * 获取检结果统计报表  APP   月报表
     *
     * @return 结果
     */
    public List<AppStatcheckResultDTO> getListForAppByMonth(Integer type) {
        return statCheckResultRepository.getListForAppByMonth(type);
    }

    /**
     * 获取检查行为统计报表
     *
     * @return 结果
     */
    public List<StatCheckBehavior> getListForCheckBehavior(StatCheckResultCustomSearchVO searchVO) {
        return statCheckResultRepository.getListForCheckBehavior(searchVO);
    }


    /**
     * 导出
     */
    public void export(HttpServletRequest request, HttpServletResponse response, StatCheckResultCustomSearchVO searchVO) {
        String title = "检查行为日统计报表";
        List<StatCheckBehavior> list = statCheckResultRepository.getListForCheckBehavior(searchVO);
        String[][] data = new String[list.size()][6];
        String srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckBehavior.xls";
        int i = 0;
        for (StatCheckBehavior statCheckBehavior : list) {
            data[i][0] = String.valueOf(i + 1);// 序号
            data[i][1] = statCheckBehavior.getDepartmentName();// 部门名称
            data[i][2] = statCheckBehavior.getJobsName();// 岗位名称
            data[i][3] = statCheckBehavior.getUserName();// 职工姓名
            data[i][4] = statCheckBehavior.getLieAboutCount() != 0 ? String.valueOf(statCheckBehavior.getLieAboutCount()) : "0";// 谎报次数
            data[i][5] = statCheckBehavior.getBadJudgmentCount() != 0 ? String.valueOf(statCheckBehavior.getBadJudgmentCount()) : "0";// 误判次数
            i++;
        }
        Excel2007Util.writeExcel(data, srcFile, title, response, 1, 300);
    }
}
