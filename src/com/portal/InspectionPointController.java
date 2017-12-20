package com.portal;

import com.common.controller.BaseController;
import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.InspectionObject;
import com.entity.InspectionPoint;
import com.searchVO.InspectionPointSearchVO;
import com.service.*;
import com.util.code.SerialNumUtil;
import com.util.datatables.DataTablesResult;
import com.util.excel.Excel2007Util;
import com.util.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by 1553280431@qq.com on 2017/7/11.
 * 检查点
 */
@Controller
@RequestMapping("portal/inspection/point")
public class InspectionPointController extends BaseController {

    private final InspectionPointService inspectionPointService;
    private final InspectionOptionsService inspectionOptionsService;
    private final ContingencyInfoService contingencyInfoService;
    private final OrganizationDepartmentService organizationDepartmentService;
    private final InspectionObjectService inspectionObjectService;

    @Autowired
    public InspectionPointController(InspectionPointService inspectionPointService,
                                     InspectionOptionsService inspectionOptionsService,
                                     ContingencyInfoService contingencyInfoService,
                                     OrganizationDepartmentService organizationDepartmentService,
                                     InspectionObjectService inspectionObjectService) {
        this.inspectionPointService = inspectionPointService;
        this.inspectionOptionsService = inspectionOptionsService;
        this.contingencyInfoService = contingencyInfoService;
        this.organizationDepartmentService = organizationDepartmentService;
        this.inspectionObjectService = inspectionObjectService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("inspection/pointIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionPointSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionPoint> list = inspectionPointService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping(value = "toSave", method = RequestMethod.GET)
    public ModelAndView toSave(String id) {
        ModelAndView mv = new ModelAndView("/inspection/pointSave");
        mv.addObject("optionList", inspectionOptionsService.findAll());
        mv.addObject("departmentList", organizationDepartmentService.findAll());
        mv.addObject("contingencyInfoList", contingencyInfoService.findAll());
        mv.addObject("inspectionPoint", inspectionPointService.findOne(id == null ? "0" : id));
        return mv;
    }

    /**
     * 新增/修改
     *
     * @param inspectionPoint 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(InspectionPoint inspectionPoint) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (StringUtil.isNullOrEmpty(inspectionPoint.getId())) {
            inspectionPoint.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
            inspectionPoint.setName(inspectionPoint.getObjectName() + "-" + inspectionPoint.getCheckOptionName());
        }
        inspectionPoint.setUpdateUser(user_id);
        inspectionPoint.setIsStart(1);
        inspectionPoint.setStartDate(new Date());
        inspectionPoint.setCheckTime(getCheckTime(inspectionPoint.getCheckCycle(), inspectionPoint.getCheckFrequency()));
        inspectionPointService.save(inspectionPoint);
        return "redirect:/portal/inspection/point/index.do";
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionPoint findOne(String id) {
        return inspectionPointService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(String id) {
        inspectionPointService.delete(id);
        return true;
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionPoint> findAll() {
        return inspectionPointService.findAll();
    }

    /**
     * 根据ID查询对象
     *
     * @param id 对象id
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView("/inspection/pointDetail");
        mv.addObject("inspectionPoint", inspectionPointService.findOne(id));
        return mv;
    }

    public static String getCheckTime(String checkCycle, String checkFrequency) {
        if (checkCycle != null && checkFrequency != null) {
            long frequency = Long.parseLong(checkFrequency);
            switch (checkCycle) {
                case "年":
                    return Long.toString(365L * 60L * 24L * 60L * 1000L / frequency);
                case "月":
                    return Long.toString(30L * 60L * 24L * 60L * 1000L / frequency);
                case "周":
                    return Long.toString(7L * 60L * 24L * 60L * 1000L / frequency);
                case "日":
                    return Long.toString(60L * 24L * 60L * 1000L / frequency);
            }
        }
        return null;
    }

    /**
     * 导入
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> importDepartment(MultipartFile excel_file) {
        int user_id = getCurrentUser().getUser_id();
        if (excel_file == null || excel_file.isEmpty()) {
            return new ResponseEntity<>(new JsonResult(false, "上传失败!"), HttpStatus.BAD_REQUEST);
        }
        try {
            String fileName = excel_file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            List<ErrorImportVO> errorImportVOS = new ArrayList<>();

            switch (suffix) {
                case "xlsx": {
                    XSSFWorkbook subjectWb = new XSSFWorkbook(excel_file.getInputStream());
                    if (subjectWb.getNumberOfSheets() > 0) {
                        XSSFSheet firstSheet = subjectWb.getSheetAt(0);
                        if (firstSheet == null || firstSheet.getLastRowNum() < 1)
                            return new ResponseEntity<>(new JsonResult(false, "没有找到上传数据!"), HttpStatus.BAD_REQUEST);
                        List<InspectionPoint> inspectionPointList = new ArrayList<>();
                        List<InspectionObject> inspectionObjectList = new ArrayList<>();
                        String equipmentNameValue = null;
                        String objectNameValue = null;
                        String specificationsValue = null;
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }
                            String departmentName = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String departmentCode = Excel2007Util.getCellStringValue(xssfRow, 1);
                            String jobsName = Excel2007Util.getCellStringValue(xssfRow, 2);
                            String jobsCode = Excel2007Util.getCellStringValue(xssfRow, 3);
                            String equipmentName = Excel2007Util.getCellStringValue(xssfRow, 4);
                            String objectName = Excel2007Util.getCellStringValue(xssfRow, 5);
                            String specifications = Excel2007Util.getCellStringValue(xssfRow, 6);
                            String remark = Excel2007Util.getCellStringValue(xssfRow, 7);
                            String tag = Excel2007Util.getCellStringValue(xssfRow, 8);
                            String gps = Excel2007Util.getCellStringValue(xssfRow, 9);
                            String checkCycle = Excel2007Util.getCellStringValue(xssfRow, 10);
                            String checkFrequency = Excel2007Util.getCellStringValue(xssfRow, 11);
                            String checkInterval = Excel2007Util.getCellStringValue(xssfRow, 12);
                            String checkOptionName = Excel2007Util.getCellStringValue(xssfRow, 13);
                            String unit = Excel2007Util.getCellStringValue(xssfRow, 14);
                            String minValue = Excel2007Util.getCellStringValue(xssfRow, 15);
                            String standardValue = Excel2007Util.getCellStringValue(xssfRow, 16);
                            String bigValue = Excel2007Util.getCellStringValue(xssfRow, 17);
                            String yellowWarning = Excel2007Util.getCellStringValue(xssfRow, 18);
                            String orangeWarning = Excel2007Util.getCellStringValue(xssfRow, 19);
                            String redWarning = Excel2007Util.getCellStringValue(xssfRow, 20);
                            String isSubJudge = Excel2007Util.getCellStringValue(xssfRow, 21);
                            String subjectiveJudgment = Excel2007Util.getCellStringValue(xssfRow, 22);
                            String displayOrder = Excel2007Util.getCellStringValue(xssfRow, 23);
                            String categoryName = Excel2007Util.getCellStringValue(xssfRow, 24);
                            String attributeName = Excel2007Util.getCellStringValue(xssfRow, 25);
                            String contingencyScene = Excel2007Util.getCellStringValue(xssfRow, 27);
                            String contingencyInfoCode = Excel2007Util.getCellStringValue(xssfRow, 28);
                            String importantLevel = Excel2007Util.getCellStringValue(xssfRow, 29);
                            String dangerLevel = Excel2007Util.getCellStringValue(xssfRow, 30);
                            String governmentPolicy = Excel2007Util.getCellStringValue(xssfRow, 31);
                            String objectCode = "";
                            if (check_params(departmentCode, jobsCode, equipmentName, objectName, tag, checkOptionName, isSubJudge, displayOrder)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else {

                                if (!Objects.equals(objectName, objectNameValue) ||
                                        (Objects.equals(objectName, objectNameValue) &&
                                                !Objects.equals(equipmentName, equipmentNameValue)) ||
                                        (Objects.equals(objectName, objectNameValue) &&
                                                Objects.equals(equipmentName, equipmentNameValue) &&
                                                !Objects.equals(specifications, specificationsValue))) {
                                    InspectionObject inspectionObject = new InspectionObject();
                                    inspectionObject.setDepartmentCode(departmentCode);
                                    inspectionObject.setJobsCode(jobsCode);
                                    inspectionObject.setEquipmentName(equipmentName);
                                    inspectionObject.setCode(SerialNumUtil.createRandowmLetter(16));
                                    objectCode = inspectionObject.getCode();
                                    inspectionObject.setName(objectName);
                                    inspectionObject.setSpecifications(specifications);
                                    inspectionObject.setRemark(remark);
                                    inspectionObject.setTag(tag);
                                    inspectionObject.setGps(gps);
                                    inspectionObject.setCategoryName(categoryName);
                                    inspectionObject.setAttributeName(attributeName);
                                    inspectionObject.setIsStart(1);
                                    inspectionObject.setCreateUser(user_id);
                                    inspectionObject.setUpdateUser(user_id);
                                    objectNameValue = objectName;
                                    equipmentNameValue = equipmentName;
                                    specificationsValue = specifications;
                                    inspectionObjectList.add(inspectionObject);
                                }
                                InspectionPoint inspectionPoint = getImport(departmentCode, departmentName, jobsCode,
                                        jobsName, equipmentName, objectCode, objectName, specifications, remark, tag, gps,
                                        checkCycle, checkFrequency, checkInterval, checkOptionName, unit, minValue, standardValue,
                                        bigValue, yellowWarning, orangeWarning, redWarning, isSubJudge, subjectiveJudgment,
                                        displayOrder, categoryName, attributeName, contingencyScene, contingencyInfoCode,
                                        importantLevel, dangerLevel, governmentPolicy);
                                inspectionPointList.add(inspectionPoint);
                            }
                        }
                        saveObjectForImport(inspectionObjectList);
                        savePointForImport(inspectionPointList);
                        return ResponseEntity.ok(new JsonResult(true, "导入成功", errorImportVOS));
                    }
                    break;
                }
                default:
                    return ResponseEntity.ok(new JsonResult(true, "导入成功", errorImportVOS));
            }


        } catch (NullPointerException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new JsonResult(false, "处理失败!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean check_params(String departmentCode, String jobsCode, String equipmentName, String objectName,
                                 String tag, String checkOptionName, String isSubJudge, String displayOrder) {
        return (StringUtils.isEmpty(departmentCode) || StringUtils.isEmpty(jobsCode) || StringUtils.isEmpty(equipmentName) ||
                StringUtils.isEmpty(objectName) || StringUtils.isEmpty(tag) || StringUtils.isEmpty(checkOptionName
        ) || StringUtils.isEmpty(isSubJudge) || StringUtils.isEmpty(displayOrder));
    }


    private InspectionPoint getImport(String departmentCode, String departmentName, String jobsCode, String jobsName,
                                      String equipmentName, String objectCode, String objectName, String specifications,
                                      String remark, String tag, String gps, String checkCycle, String checkFrequency,
                                      String checkInterval, String checkOptionName, String unit, String minValue,
                                      String standardValue, String bigValue, String yellowWarning, String orangeWarning,
                                      String redWarning, String isSubJudge, String subjectiveJudgment, String displayOrder,
                                      String categoryName, String attributeName, String contingencyScene,
                                      String contingencyInfoCode, String importantLevel,
                                      String dangerLevel, String governmentPolicy) {
        InspectionPoint inspectionPoint = new InspectionPoint();
        inspectionPoint.setDepartmentCode(departmentCode);
        inspectionPoint.setDepartmentName(departmentName);
        inspectionPoint.setName(objectName + "-" + checkOptionName);
        inspectionPoint.setJobsCode(jobsCode);
        inspectionPoint.setJobsName(jobsName);
        inspectionPoint.setEquipmentName(equipmentName);
        inspectionPoint.setObjectCode(objectCode);
        inspectionPoint.setObjectName(objectName);
        inspectionPoint.setSpecifications(specifications);
        inspectionPoint.setRemark(remark);
        inspectionPoint.setTag(tag);
        inspectionPoint.setGps(gps);
        inspectionPoint.setCheckCycle(checkCycle);
        inspectionPoint.setCheckFrequency(checkFrequency);
        inspectionPoint.setCheckInterval(checkInterval);
        inspectionPoint.setCheckTime(getCheckTime(inspectionPoint.getCheckCycle(), inspectionPoint.getCheckFrequency()));
        inspectionPoint.setCheckOptionId(inspectionOptionsService.findByName(checkOptionName).getId());
        inspectionPoint.setCheckOptionName(checkOptionName);
        inspectionPoint.setUnit(unit);
        inspectionPoint.setMinValue(minValue);
        inspectionPoint.setStandardValue(standardValue);
        inspectionPoint.setBigValue(bigValue);
        inspectionPoint.setYellowWarning(yellowWarning);
        inspectionPoint.setOrangeWarning(orangeWarning);
        inspectionPoint.setRedWarning(redWarning);
        inspectionPoint.setIsSubJudge(Integer.parseInt(isSubJudge));
        inspectionPoint.setSubjectiveJudgment(StringUtil.isNotNullOrEmpty(subjectiveJudgment) ? Integer.parseInt(subjectiveJudgment) : 0);
        inspectionPoint.setDisplayOrder(Integer.parseInt(displayOrder));
        inspectionPoint.setCategoryName(categoryName);
        inspectionPoint.setAttributeName(attributeName);
        inspectionPoint.setContingencyScene(contingencyScene);
        inspectionPoint.setContingencyInfoCode(contingencyInfoCode);
        inspectionPoint.setImportantLevel(importantLevel);
        inspectionPoint.setDangerLevel(dangerLevel);
        inspectionPoint.setGovernmentPolicy(governmentPolicy);
        inspectionPoint.setIsStart(1);
        inspectionPoint.setStartDate(new Date());
        inspectionPoint.setCreateUser(getCurrentUser().getUser_id());
        inspectionPoint.setUpdateUser(getCurrentUser().getUser_id());
        return inspectionPoint;
    }

    /**
     * 导入数据
     */
    private void saveObjectForImport(List<InspectionObject> inspectionObjectList) {
        inspectionObjectService.save(inspectionObjectList);
    }

    /**
     * 导入数据
     */
    private void savePointForImport(List<InspectionPoint> inspectionPointList) {
        inspectionPointService.save(inspectionPointList);
    }

    @RequestMapping("getCategoryName")
    public List<String> getCategoryName() {
        return inspectionPointService.getCategoryName();
    }
}
