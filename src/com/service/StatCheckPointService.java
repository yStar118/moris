package com.service;

import com.common.service.BaseService;
import com.entity.StatCheckPoint;
import com.repositories.StatCheckPointRepository;
import com.searchVO.StatCheckPointSearchVO;
import com.util.excel.Excel2007Util;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高宇飞 on 2017/11/6 16:58:33
 * 检查行为报表
 */
@Service
public class StatCheckPointService implements BaseService<StatCheckPoint, Integer> {

    @Autowired
    private StatCheckPointRepository statCheckPointRepository;

    @Override
    public <S extends StatCheckPoint> S save(S entity) {
        return statCheckPointRepository.save(entity);
    }

    @Override
    public StatCheckPoint findOne(Integer primaryKey) {
        return statCheckPointRepository.findOne(primaryKey);
    }

    @Override
    public List<StatCheckPoint> findAll() {
        return IterableUtils.toList(statCheckPointRepository.findAll());
    }

    public void updateTimingStat() {
        List<StatCheckPoint> listForJob = statCheckPointRepository.getListForJob();
        if (listForJob != null && listForJob.size() > 0) {
            statCheckPointRepository.save(listForJob);
        }
    }

    @Override
    public long count() {
        return statCheckPointRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        statCheckPointRepository.delete(primaryKey);
    }

    public List<StatCheckPoint> getListForDay(StatCheckPointSearchVO searchVO) {
        return statCheckPointRepository.getListForDay(searchVO);
    }

    public List<StatCheckPoint> getListForMonth(StatCheckPointSearchVO searchVO) {
        return statCheckPointRepository.getListForMonth(searchVO);
    }


    public List<StatCheckPoint> getListForYear(StatCheckPointSearchVO searchVO) {
        return statCheckPointRepository.getListForYear(searchVO);
    }

    /**
     * 导出
     */
    public void export(HttpServletRequest request, HttpServletResponse response, StatCheckPointSearchVO searchVO) {
        List<StatCheckPoint> list = new ArrayList<>();
        String title = "";
        if (searchVO.getType() != null && searchVO.getType() == 1) {
            list = statCheckPointRepository.getListForDay(searchVO);
            title = new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStatStartDate())
                    + "至" + new SimpleDateFormat("yyyy-MM-dd").format(searchVO.getStatEndDate())
                    + "检查行为日统计报表";
        }
        if (searchVO.getType() != null && searchVO.getType() == 2) {
            list = statCheckPointRepository.getListForMonth(searchVO);
            title = searchVO.getYear() + "年" + searchVO.getMonth() + "月" + "检查行为月统计报表";
        }
        if (searchVO.getType() != null && searchVO.getType() == 3) {
            list = statCheckPointRepository.getListForYear(searchVO);
            title = searchVO.getYear() + "年" + "检查行为年统计报表";
        }
        String[][] data = new String[list.size()][13];
        String srcFile = request.getServletContext().getRealPath("/upload/model") + File.separator + "statCheckPointDay.xls";
        int i = 0;
        for (StatCheckPoint statCheckPoint : list) {
            data[i][0] = String.valueOf(i + 1);// 序号
            data[i][1] = statCheckPoint.getDepartmentName();// 部门名称
            data[i][2] = statCheckPoint.getJobsName();// 岗位名称
            data[i][3] = statCheckPoint.getPointName();// 检查点名称
            data[i][4] = statCheckPoint.getFinishQuantity() != null ? statCheckPoint.getFinishQuantity().toString() : "0";// 完成数量
            data[i][5] = statCheckPoint.getFinishRate().toString();// 完成率
            data[i][6] = statCheckPoint.getLeakageQuantity() != null ? statCheckPoint.getLeakageQuantity().toString() : "0";// 漏检数量
            data[i][7] = statCheckPoint.getLeakageRate().toString();// 漏检率
            data[i][8] = statCheckPoint.getEmptyQuantity() != null ? statCheckPoint.getEmptyQuantity().toString() : "0";// 空检数量
            data[i][9] = statCheckPoint.getEmptyRate().toString();// 空检率
            data[i][10] = statCheckPoint.getWeakQuantity() != null ? statCheckPoint.getWeakQuantity().toString() : "0";// 弱检数量
            data[i][11] = statCheckPoint.getWeakRate() != null ? statCheckPoint.getWeakRate().toString() : "";// 弱检率
            data[i][12] = statCheckPoint.getTotalQuantity() != null ? statCheckPoint.getTotalQuantity().toString() : "0";// 总数
            i++;
        }
        Excel2007Util.writeExcel(data, srcFile, title, response, 1, 300);
    }


}
