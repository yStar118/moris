package com.portal;

import com.common.controller.BaseController;
import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.OrganizationJobs;
import com.searchVO.OrganizationJobsSearchVO;
import com.service.OrganizationJobsService;
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
 * Created by 1553280431@qq.com on 2017/6/24.
 * 岗位
 */
@Controller
@RequestMapping("portal/organization/jobs")
public class OrganizationJobsController extends BaseController {

    private final OrganizationJobsService organizationJobsService;

    @Autowired
    public OrganizationJobsController(OrganizationJobsService organizationJobsService) {
        this.organizationJobsService = organizationJobsService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/organization/jobsIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(OrganizationJobsSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<OrganizationJobs> list = organizationJobsService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     *
     * @param organizationEnterprise 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public OrganizationJobs save(OrganizationJobs organizationEnterprise) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (organizationEnterprise.getId() == null) {
            organizationEnterprise.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        organizationEnterprise.setUpdateUser(user_id);
        return organizationJobsService.save(organizationEnterprise);
    }

    /**
     * 根据部门编号查询岗位
     *
     * @param searchVO 实体
     */
    @RequestMapping(value = "findByDepartmentCode", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationJobs> findByDepartmentCode(OrganizationJobsSearchVO searchVO) {
        searchVO.setIndex(0);
        searchVO.setLength(9999);
        return organizationJobsService.getList(searchVO);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public OrganizationJobs findOne(Integer id) {
        return organizationJobsService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        organizationJobsService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationJobs> findAll() {
        return organizationJobsService.findAll();
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
                        List<OrganizationJobs> organizationJobsList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
                            if (hssfRow == null) {
                                continue;
                            }

                            String code = Excel2003Util.getCellStringValue(hssfRow, 0);
                            String name = Excel2003Util.getCellStringValue(hssfRow, 1);
                            String enterpriseCode = Excel2003Util.getCellStringValue(hssfRow, 2);
                            String departmentCode = Excel2003Util.getCellStringValue(hssfRow, 3);

                            if (!check_import_params(code, name, enterpriseCode, departmentCode)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(organizationJobsList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                OrganizationJobs organizationJobs = getImport(code, name, enterpriseCode, departmentCode, rowIndex);
                                organizationJobsList.add(organizationJobs);
                            }
                        }
                        saveForImport(organizationJobsList);
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
                        List<OrganizationJobs> organizationJobsList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }
                            String code = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String name = Excel2007Util.getCellStringValue(xssfRow, 1);
                            String enterpriseCode = Excel2007Util.getCellStringValue(xssfRow, 2);
                            String departmentCode = Excel2007Util.getCellStringValue(xssfRow, 3);

                            if (!check_import_params(code, name, enterpriseCode, departmentCode)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(organizationJobsList, code)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，编号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                OrganizationJobs organizationJobs = getImport(code, name, enterpriseCode, departmentCode, rowIndex);
                                organizationJobsList.add(organizationJobs);
                            }
                        }
                        saveForImport(organizationJobsList);
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


    private boolean check_import_params(String code, String name, String enterpriseCode, String departmentCode) {
        return !(StringUtils.isEmpty(code) || StringUtils.isEmpty(name) || StringUtils.isEmpty(enterpriseCode) ||
                StringUtils.isEmpty(departmentCode));
    }

    private boolean check_repeat_params(List<OrganizationJobs> list, String code) {
        List<OrganizationJobs> jobsList = organizationJobsService.findAll();
        if (list.size() == 0) {
            for (OrganizationJobs jobs : jobsList) {
                if (Objects.equals(jobs.getCode(), code)) {
                    return true;
                }
            }
            return false;
        } else {
            for (OrganizationJobs organizationJobs : list) {
                if (Objects.equals(organizationJobs.getCode(), code)) {
                    for (OrganizationJobs jobs : jobsList) {
                        if (Objects.equals(jobs.getCode(), code)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private OrganizationJobs getImport(String code, String name, String enterpriseCode, String departmentCode, int rowIndex) {
        OrganizationJobs organizationJobs = new OrganizationJobs();
        organizationJobs.setCode(code);
        organizationJobs.setName(name);
        organizationJobs.setEnterpriseCode(enterpriseCode);
        organizationJobs.setDepartmentCode(departmentCode);
        organizationJobs.setCreateUser(getCurrentUser().getUser_id());
        organizationJobs.setUpdateUser(getCurrentUser().getUser_id());
        organizationJobs.setRowIndex(rowIndex);
        return organizationJobs;
    }

    /**
     * 导入数据
     *
     * @param organizationJobs 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<OrganizationJobs> saveForImport(List<OrganizationJobs> organizationJobs) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(organizationJobsService.save(organizationJobs));
    }

    @RequestMapping("findByDepartmentCode")
    @ResponseBody
    public List<OrganizationJobs> findByDepartmentCode(String departmentCode) {
        return organizationJobsService.findByDepartmentCode(departmentCode);
    }
}
