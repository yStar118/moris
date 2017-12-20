package com.portal;

import com.common.controller.BaseController;
import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.InspectionOptions;
import com.searchVO.InspectionOptionsSearchVO;
import com.service.InspectionOptionsService;
import com.util.datatables.DataTablesResult;
import com.util.excel.Excel2003Util;
import com.util.excel.Excel2007Util;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.util.List;
import java.util.Objects;

/**
 * Created by 1553280431@qq.com on 2017/6/30.
 * 检查项
 */
@Controller
@RequestMapping("portal/inspection/options")
public class InspectionOptionsController extends BaseController {

    private final InspectionOptionsService inspectionOptionsService;

    @Autowired
    public InspectionOptionsController(InspectionOptionsService inspectionOptionsService) {
        this.inspectionOptionsService = inspectionOptionsService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/inspection/optionsIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(InspectionOptionsSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<InspectionOptions> list = inspectionOptionsService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param inspectionOptions 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public InspectionOptions save(InspectionOptions inspectionOptions) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (inspectionOptions.getId() != null) {
            inspectionOptions.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        inspectionOptions.setUpdateUser(user_id);
        return inspectionOptionsService.save(inspectionOptions);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public InspectionOptions findOne(Integer id) {
        return inspectionOptionsService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        inspectionOptionsService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectionOptions> findAll() {
        return inspectionOptionsService.findAll();
    }

    /**
     * 导入
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> importDepartment(MultipartFile excel_file) {
        if (excel_file == null || excel_file.isEmpty()) {
            return new ResponseEntity<>(new JsonResult(false, "上传失败!"), HttpStatus.BAD_REQUEST);
        }
        try {
            String fileName = excel_file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            List<ErrorImportVO> errorImportVOS = new ArrayList<>();

            switch (suffix) {
                case "xls": {
                    HSSFWorkbook subjectWb = new HSSFWorkbook(excel_file.getInputStream());
                    if (subjectWb.getNumberOfSheets() > 0) {
                        HSSFSheet firstSheet = subjectWb.getSheetAt(0);
                        if (firstSheet == null || firstSheet.getLastRowNum() < 1)
                            return new ResponseEntity<>(new JsonResult(false, "没有找到上传数据!"), HttpStatus.BAD_REQUEST);
                        List<InspectionOptions> inspectionOptionsList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
                            if (hssfRow == null) {
                                continue;
                            }

                            String code = Excel2003Util.getCellStringValue(hssfRow, 0);
                            String name = Excel2003Util.getCellStringValue(hssfRow, 1);
                            String content = Excel2003Util.getCellStringValue(hssfRow, 2);
                            String method = Excel2003Util.getCellStringValue(hssfRow, 3);
                            String remark = Excel2003Util.getCellStringValue(hssfRow, 4);
                            String isSubJudge = Excel2003Util.getCellStringValue(hssfRow, 5);
                            String subJudgeStandard = Excel2003Util.getCellStringValue(hssfRow, 6);
                            String meaning = Excel2003Util.getCellStringValue(hssfRow, 7);
                            String type = Excel2003Util.getCellStringValue(hssfRow, 8);
                            String isStart = Excel2003Util.getCellStringValue(hssfRow, 11);

                            if (check_params(code, name, content, method, remark, isSubJudge, meaning, type, isStart)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(inspectionOptionsList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                InspectionOptions inspectionOption = getImport(code, name, content, method, remark,
                                        isSubJudge, subJudgeStandard, meaning, type, isStart, rowIndex);
                                inspectionOptionsList.add(inspectionOption);
                            }
                        }
                        saveForImport(inspectionOptionsList);
                        return ResponseEntity.ok(new JsonResult(true, "导入成功", errorImportVOS));
                    }
                    break;
                }
                case "xlsx": {
                    XSSFWorkbook subjectWb = new XSSFWorkbook(excel_file.getInputStream());
                    if (subjectWb.getNumberOfSheets() > 0) {
                        XSSFSheet firstSheet = subjectWb.getSheetAt(0);
                        if (firstSheet == null || firstSheet.getLastRowNum() < 1)
                            return new ResponseEntity<>(new JsonResult(false, "没有找到上传数据!"), HttpStatus.BAD_REQUEST);
                        List<InspectionOptions> inspectionOptionsList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }
                            String code = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String name = Excel2007Util.getCellStringValue(xssfRow, 1);
                            String content = Excel2007Util.getCellStringValue(xssfRow, 2);
                            String method = Excel2007Util.getCellStringValue(xssfRow, 3);
                            String remark = Excel2007Util.getCellStringValue(xssfRow, 4);
                            String isSubJudge = Excel2007Util.getCellStringValue(xssfRow, 5);
                            String subJudgeStandard = Excel2007Util.getCellStringValue(xssfRow, 6);
                            String meaning = Excel2007Util.getCellStringValue(xssfRow, 7);
                            String type = Excel2007Util.getCellStringValue(xssfRow, 8);
                            String isStart = Excel2007Util.getCellStringValue(xssfRow, 11);


                            if (check_params(code, name, content, method, remark, isSubJudge, meaning, type, isStart)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(inspectionOptionsList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                InspectionOptions inspectionOption = getImport(code, name, content, method, remark,
                                        isSubJudge, subJudgeStandard, meaning, type, isStart, rowIndex);
                                inspectionOptionsList.add(inspectionOption);
                            }
                        }
                        saveForImport(inspectionOptionsList);
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

    private boolean check_params(String code, String name, String content, String method, String remark,
                                 String isSubJudge, String meaning, String type, String isStart) {
        return (StringUtils.isEmpty(code) || StringUtils.isEmpty(name) || StringUtils.isEmpty(content) ||
                StringUtils.isEmpty(method) || StringUtils.isEmpty(remark) || StringUtils.isEmpty(isSubJudge)
                || StringUtils.isEmpty(meaning) || StringUtils.isEmpty(type) || StringUtils.isEmpty(isStart));
    }


    private boolean check_repeat_params(List<InspectionOptions> list, String code) {
        List<InspectionOptions> inspectionOptionsList = inspectionOptionsService.findAll();
        if (list.size() == 0) {
            for (InspectionOptions inspectionOption : inspectionOptionsList) {
                if (Objects.equals(inspectionOption.getCode(), code)) {
                    return true;
                }
            }
            return false;
        } else {
            for (InspectionOptions inspectionOption : list) {
                if (Objects.equals(inspectionOption.getCode(), code)) {
                    for (InspectionOptions inspectionOptions : inspectionOptionsList) {
                        if (Objects.equals(inspectionOptions.getCode(), code)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private InspectionOptions getImport(String code, String name, String content, String method, String remark,
                                        String isSubJudge, String subJudgeStandard, String meaning, String type,
                                        String isStart, int row_index) {
        InspectionOptions inspectionOption = new InspectionOptions();
        inspectionOption.setCode(code);
        inspectionOption.setName(name);
        inspectionOption.setContent(content);
        inspectionOption.setMethod(method);
        inspectionOption.setRemark(remark);
        inspectionOption.setIsSubJudge(isSubJudge == null ? 0 : Integer.parseInt(isSubJudge));
        inspectionOption.setSubJudgeStandard(subJudgeStandard);
        inspectionOption.setMeaning(meaning);
        inspectionOption.setType(type == null ? 0 : Integer.parseInt(type));
        inspectionOption.setIsStart(isStart == null ? 0 : Integer.parseInt(isStart));
        inspectionOption.setCreateUser(getCurrentUser().getUser_id());
        inspectionOption.setUpdateUser(getCurrentUser().getUser_id());
        inspectionOption.setRow_index(row_index);
        return inspectionOption;
    }

    /**
     * 导入数据
     *
     * @param inspectionOptionsList 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<InspectionOptions> saveForImport(List<InspectionOptions> inspectionOptionsList) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(inspectionOptionsService.save(inspectionOptionsList));
    }
}
