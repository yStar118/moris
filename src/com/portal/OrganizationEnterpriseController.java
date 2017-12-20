package com.portal;

import com.common.controller.BaseController;
import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.OrganizationEnterprise;
import com.searchVO.OrganizationEnterpriseSearchVO;
import com.service.OrganizationEnterpriseService;
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

/**
 * Created by 1553280431@qq.com on 2017/6/22.
 * 企业
 */
@Controller
@RequestMapping("portal/organization/enterprise")
public class OrganizationEnterpriseController extends BaseController {

    private final OrganizationEnterpriseService organizationEnterpriseService;

    @Autowired
    public OrganizationEnterpriseController(OrganizationEnterpriseService organizationEnterpriseService) {
        this.organizationEnterpriseService = organizationEnterpriseService;
    }

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/organization/enterpriseIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(OrganizationEnterpriseSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<OrganizationEnterprise> list = organizationEnterpriseService.getList(searchVO);
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
    public OrganizationEnterprise save(OrganizationEnterprise organizationEnterprise) {
        int user_id = getCurrentUser().getUser_id();//调用父类的方法获取当前用户session
        if (organizationEnterprise.getId() == null) {
            organizationEnterprise.setCreateUser(user_id);//如果第一次数据，没有id  曾新增一个创建人
        }
        organizationEnterprise.setUpdateUser(user_id);
        return organizationEnterpriseService.save(organizationEnterprise);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public OrganizationEnterprise findOne(Integer id) {
        return organizationEnterpriseService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        organizationEnterpriseService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationEnterprise> findAll() {
        return organizationEnterpriseService.findAll();
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
                        List<OrganizationEnterprise> organizationEnterpriseList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
                            if (hssfRow == null) {
                                continue;
                            }

                            String code = Excel2003Util.getCellStringValue(hssfRow, 0);
                            String name = Excel2003Util.getCellStringValue(hssfRow, 1);
                            String address = Excel2003Util.getCellStringValue(hssfRow, 2);
                            String responsible = Excel2003Util.getCellStringValue(hssfRow, 3);
                            String contact = Excel2003Util.getCellStringValue(hssfRow, 4);
                            String remark = Excel2003Util.getCellStringValue(hssfRow, 5);

                            if (!check_import_params(code, name, address, responsible, contact, remark)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setRowIndex(rowIndex);
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                OrganizationEnterprise organizationEnterprise = getImport(code, name, address, responsible,
                                        contact, remark, rowIndex);

                                organizationEnterpriseList.add(organizationEnterprise);
                            }
                        }
                        saveForImport(organizationEnterpriseList);
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
                        List<OrganizationEnterprise> organizationEnterpriseList = new ArrayList<>();
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
                            String parentCode = Excel2007Util.getCellStringValue(xssfRow, 5);

                            if (!check_import_params(code, name, address, category, type, parentCode)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                OrganizationEnterprise organizationEnterprise = getImport(code, name, address, category,
                                        type, parentCode, rowIndex);
                                organizationEnterpriseList.add(organizationEnterprise);
                            }
                        }
                        saveForImport(organizationEnterpriseList);
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


    private boolean check_import_params(String code, String name, String address, String responsible, String contact, String remark) {
        return !(StringUtils.isEmpty(code) || StringUtils.isEmpty(name) || StringUtils.isEmpty(address) ||
                StringUtils.isEmpty(responsible) || StringUtils.isEmpty(contact) || StringUtils.isEmpty(remark));
    }

    private OrganizationEnterprise getImport(String code, String name, String address, String responsible, String contact, String remark, int rowIndex) {
        OrganizationEnterprise organizationEnterprise = new OrganizationEnterprise();
        organizationEnterprise.setCode(code);
        organizationEnterprise.setName(name);
        organizationEnterprise.setAddress(address);
        organizationEnterprise.setResponsible(responsible);
        organizationEnterprise.setContact(contact);
        organizationEnterprise.setRemark(remark);
        organizationEnterprise.setCreateUser(getCurrentUser().getUser_id());
        organizationEnterprise.setUpdateUser(getCurrentUser().getUser_id());
        organizationEnterprise.setRowIndex(rowIndex);
        return organizationEnterprise;
    }

    /**
     * 导入数据
     *
     * @param organizationEnterprises 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<OrganizationEnterprise> saveForImport(List<OrganizationEnterprise> organizationEnterprises) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(organizationEnterpriseService.save(organizationEnterprises));
    }

}
