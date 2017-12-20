package com.portal;

import com.common.controller.BaseController;
import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.OrganizationDepartment;
import com.searchVO.OrganizationDepartmentSearchVO;
import com.service.OrganizationDepartmentService;
import com.service.OrganizationEnterpriseService;
import com.util.datatables.DataTablesResult;
import com.util.excel.Excel2003Util;
import com.util.excel.Excel2007Util;
import com.util.string.StringUtil;
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
 * Created by 1553280431@qq.com on 2017/6/8.
 * 部门Controller
 */
@Controller
@RequestMapping("/portal/organization/department")
public class OrganizationDepartmentController extends BaseController {
    /**
     * 注入service层
     */
    private final OrganizationDepartmentService organizationDepartmentService;
    private final OrganizationEnterpriseService organizationEnterpriseService;

    @Autowired
    public OrganizationDepartmentController(OrganizationDepartmentService organizationDepartmentService,
                                            OrganizationEnterpriseService organizationEnterpriseService) {
        this.organizationDepartmentService = organizationDepartmentService;
        this.organizationEnterpriseService = organizationEnterpriseService;
    }


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/organization/departmentIndex"); //详细去看ModelAndView
        mv.addObject("departments", organizationDepartmentService.findAll());
        mv.addObject("enterprises", organizationEnterpriseService.findAll());
        return mv;
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(OrganizationDepartmentSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<OrganizationDepartment> list = organizationDepartmentService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param organizationDepartment 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public OrganizationDepartment save(OrganizationDepartment organizationDepartment) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (StringUtil.isNullOrEmpty(organizationDepartment.getId())) {
            organizationDepartment.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        organizationDepartment.setUpdateUser(user_id);
        return organizationDepartmentService.save(organizationDepartment);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public OrganizationDepartment findOne(String id) {
        return organizationDepartmentService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(String id) {
        organizationDepartmentService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationDepartment> findAll() {
        return organizationDepartmentService.findAll();
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
                        List<OrganizationDepartment> organizationDepartmentList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
                            if (hssfRow == null) {
                                continue;
                            }

                            String code = Excel2003Util.getCellStringValue(hssfRow, 0);
                            String name = Excel2003Util.getCellStringValue(hssfRow, 1);
                            String address = Excel2003Util.getCellStringValue(hssfRow, 2);
                            String category = Excel2003Util.getCellStringValue(hssfRow, 3);
                            String type = Excel2003Util.getCellStringValue(hssfRow, 4);
                            String parentName = Excel2003Util.getCellStringValue(hssfRow, 5);
                            String enterpriseCode = Excel2003Util.getCellStringValue(hssfRow, 6);

                            if (check_params(code, name, address, category, type, enterpriseCode)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(organizationDepartmentList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                String parentCode = getParentCode(organizationDepartmentList, parentName);
                                OrganizationDepartment organizationDepartment = getImport(code, name, address, category,
                                        type, parentCode, enterpriseCode, rowIndex);
                                organizationDepartmentList.add(organizationDepartment);
                            }
                        }
                        saveForImport(organizationDepartmentList);
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
                        List<OrganizationDepartment> organizationDepartmentList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }

                            String code = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String name = Excel2007Util.getCellStringValue(xssfRow, 1);
                            String address = Excel2007Util.getCellStringValue(xssfRow, 2);
                            String category = Excel2007Util.getCellStringValue(xssfRow, 3);
                            String type = Excel2007Util.getCellStringValue(xssfRow, 4);
                            String parentName = Excel2007Util.getCellStringValue(xssfRow, 5);
                            String enterpriseCode = Excel2007Util.getCellStringValue(xssfRow, 6);

                            if (check_params(code, name, address, category, type, enterpriseCode)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(organizationDepartmentList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                String parentCode = getParentCode(organizationDepartmentList, parentName);
                                OrganizationDepartment organizationDepartment = getImport(code, name, address, category,
                                        type, parentCode, enterpriseCode, rowIndex);
                                organizationDepartmentList.add(organizationDepartment);
                            }
                        }
                        saveForImport(organizationDepartmentList);
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

    private boolean check_params(String code, String name, String address, String category, String type, String enterpriseCode) {
        return (StringUtils.isEmpty(code) || StringUtils.isEmpty(name) || StringUtils.isEmpty(address) ||
                StringUtils.isEmpty(category) || StringUtils.isEmpty(type) || StringUtils.isEmpty(enterpriseCode));
    }

    private String getParentCode(List<OrganizationDepartment> list, String parentName) {
        if (list.size() == 0 || StringUtils.isEmpty(parentName)) {
            return "";
        } else {
            for (OrganizationDepartment organizationDepartment : list) {
                if (Objects.equals(organizationDepartment.getName(), parentName)) {
                    return organizationDepartment.getCode();
                }
            }
            return "";
        }
    }

    private boolean check_repeat_params(List<OrganizationDepartment> list, String code) {
        List<OrganizationDepartment> departmentList = organizationDepartmentService.findAll();
        if (list.size() == 0) {
            for (OrganizationDepartment department : departmentList) {
                if (Objects.equals(department.getCode(), code)) {
                    return true;
                }
            }
            return false;
        } else {
            for (OrganizationDepartment organizationDepartment : list) {
                if (Objects.equals(organizationDepartment.getCode(), code)) {
                    for (OrganizationDepartment department : departmentList) {
                        if (Objects.equals(department.getCode(), code)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private OrganizationDepartment getImport(String code, String name, String address, String category, String type, String parentCode, String enterpriseCode, int row_index) {
        OrganizationDepartment organizationDepartment = new OrganizationDepartment();
        organizationDepartment.setCode(code);
        organizationDepartment.setName(name);
        organizationDepartment.setAddress(address);
        organizationDepartment.setCategory(category);
        organizationDepartment.setType(type == null ? 0 : Integer.parseInt(type));
        organizationDepartment.setParentCode(parentCode);
        organizationDepartment.setEnterpriseCode(enterpriseCode);
        organizationDepartment.setCreateUser(getCurrentUser().getUser_id());
        organizationDepartment.setUpdateUser(getCurrentUser().getUser_id());
        organizationDepartment.setRow_index(row_index);
        return organizationDepartment;
    }

    /**
     * 导入数据
     *
     * @param baseDepartmentList 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<OrganizationDepartment> saveForImport(List<OrganizationDepartment> baseDepartmentList) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(organizationDepartmentService.save(baseDepartmentList));
    }

}
