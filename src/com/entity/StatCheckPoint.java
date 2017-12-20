package com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 高宇飞 on 2017/11/6 13:24:59
 */
@Entity
@Table(name = "stat_check_point")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "in_and_out_test", procedureName = "test_pkg.in_and_out_test", parameters = {
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class),
})})
public class StatCheckPoint {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //部门编号
    private String departmentCode;
    //部门名称
    private String departmentName;
    //岗位编号
    private String jobsCode;
    //岗位名称
    private String jobsName;
    //检查点id
    private String pointId;
    //检查点名称
    private String pointName;
    //完成数量
    private Integer finishQuantity;
    //完成率
    private BigDecimal finishRate;
    //漏检数量
    private Integer leakageQuantity;
    //漏检率
    private BigDecimal leakageRate;
    //空检数量
    private Integer emptyQuantity;
    //空检率
    private BigDecimal emptyRate;
    //弱检数量
    private Integer weakQuantity;
    //弱检率
    private BigDecimal weakRate;
    //总数
    private Integer totalQuantity;
    //统计时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date statDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobsCode() {
        return jobsCode;
    }

    public void setJobsCode(String jobsCode) {
        this.jobsCode = jobsCode;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public BigDecimal getFinishRate() {
        if (finishRate != null) {
            return finishRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public void setFinishRate(BigDecimal finishRate) {
        this.finishRate = finishRate;
    }


    public BigDecimal getLeakageRate() {
        if (leakageRate != null) {
            return leakageRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public void setLeakageRate(BigDecimal leakageRate) {
        this.leakageRate = leakageRate;
    }


    public BigDecimal getEmptyRate() {
        if (emptyRate != null) {
            return emptyRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public void setEmptyRate(BigDecimal emptyRate) {
        this.emptyRate = emptyRate;
    }

    public BigDecimal getWeakRate() {
        if (weakRate != null) {
            return weakRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public void setWeakRate(BigDecimal weakRate) {
        this.weakRate = weakRate;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Integer getFinishQuantity() {
        if (finishQuantity != null) {
            return finishQuantity;
        } else {
            return 0;
        }
    }

    public void setFinishQuantity(Integer finishQuantity) {
        this.finishQuantity = finishQuantity;
    }

    public Integer getLeakageQuantity() {
        if (leakageQuantity != null) {
            return leakageQuantity;
        } else {
            return 0;
        }
    }

    public void setLeakageQuantity(Integer leakageQuantity) {
        this.leakageQuantity = leakageQuantity;
    }

    public Integer getEmptyQuantity() {
        if (emptyQuantity != null) {
            return emptyQuantity;
        } else {
            return 0;
        }
    }

    public void setEmptyQuantity(Integer emptyQuantity) {
        this.emptyQuantity = emptyQuantity;
    }

    public Integer getWeakQuantity() {
        if (weakQuantity != null) {
            return weakQuantity;
        } else {
            return 0;
        }
    }

    public void setWeakQuantity(Integer weakQuantity) {
        this.weakQuantity = weakQuantity;
    }

    public Integer getTotalQuantity() {
        if (totalQuantity != null) {
            return totalQuantity;
        } else {
            return 0;
        }
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
