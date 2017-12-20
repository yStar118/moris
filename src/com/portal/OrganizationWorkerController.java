package com.portal;

import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.OrganizationDepartment;
import com.entity.OrganizationEnterprise;
import com.entity.OrganizationJobs;
import com.service.OrganizationDepartmentService;
import com.service.OrganizationEnterpriseService;
import com.service.OrganizationJobsService;
import com.sys.model.SysUser;
import com.sys.service.SysRoleService;
import com.sys.service.SysUserService;
import com.sys.vo.SysUserSearchVO;
import com.util.code.SerialNumUtil;
import com.util.datatables.DataTablesResult;
import com.util.encrypt.EncryptUtil;
import com.util.excel.Excel2007Util;
import com.util.session.SessionUtil;
import com.util.session.UserSession;
import org.apache.commons.collections4.IterableUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 高宇飞 on 2017/7/25.
 */
@Controller
@RequestMapping("portal/organization/worker")
public class OrganizationWorkerController {

    private final SysUserService sysUserService;
    private final OrganizationEnterpriseService organizationEnterpriseService;
    private final OrganizationDepartmentService organizationDepartmentService;
    private final OrganizationJobsService organizationJobsService;
    private final SysRoleService sysRoleService;

    @Autowired
    public OrganizationWorkerController(SysUserService sysUserService, OrganizationEnterpriseService
            organizationEnterpriseService, OrganizationDepartmentService organizationDepartmentService,
                                        OrganizationJobsService organizationJobsService, SysRoleService sysRoleService) {
        this.sysUserService = sysUserService;
        this.organizationEnterpriseService = organizationEnterpriseService;
        this.organizationDepartmentService = organizationDepartmentService;
        this.organizationJobsService = organizationJobsService;
        this.sysRoleService = sysRoleService;
    }


    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/organization/workerIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(SysUserSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<SysUser> list = sysUserService.list(searchVO, searchVO.getPageParams(), searchVO.getLength());
        int i = sysUserService.listCount(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(i);//每个字段在实体中都有注释
        result.setRecordsTotal(i);//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    @RequestMapping("toSave")
    public ModelAndView toSave(Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null || id == 0) {
            mv.setViewName("/organization/workerSave");
            mv.addObject("enterpriseList", organizationEnterpriseService.findAll());
            mv.addObject("departmentList", organizationDepartmentService.findAll());
            mv.addObject("jobsList", organizationJobsService.findAll());
            mv.addObject("listRole", sysRoleService.list());// 角色列表
            mv.addObject("worker", new SysUser());
            return mv;
        } else {
            mv.setViewName("/organization/workerSave");
            mv.addObject("enterpriseList", organizationEnterpriseService.findAll());
            mv.addObject("departmentList", organizationDepartmentService.findAll());
            mv.addObject("jobsList", organizationJobsService.findAll());
            mv.addObject("listRole", sysRoleService.list());// 角色列表
            mv.addObject("worker", sysUserService.get(id));
            return mv;
        }
    }

    /**
     * 新增/修改
     *
     * @param sysUser 实体
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(SysUser sysUser, HttpServletRequest request) {
        if (sysUser.getId() <= 0) {
            sysUser.setUsername(sysUser.getTelephone());
            UserSession userSession = SessionUtil.getUserSession(request);
            assert userSession != null;
            sysUser.setCreate_person(userSession.getRealname());
            sysUserService.add(sysUser);
            return "redirect:/portal/organization/worker/index.do";
        } else {
            sysUserService.update(sysUser);
            return "redirect:/portal/organization/worker/index.do";
        }
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public SysUser findOne(Integer id) {
        return sysUserService.get(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        sysUserService.delete(id);
    }

    /**
     * 导入
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> importDepartment(MultipartFile excel_file, HttpServletRequest request) {
        List<SysUser> userList = sysUserService.listAll();
        List<OrganizationEnterprise> enterpriseList = organizationEnterpriseService.findAll();
        List<OrganizationDepartment> departmentList = organizationDepartmentService.findAll();
        List<OrganizationJobs> jobsList = organizationJobsService.findAll();
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
                        List<SysUser> sysUserList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }

                            String realname = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String sex = Excel2007Util.getCellStringValue(xssfRow, 1);
                            String enterpriseCode = Excel2007Util.getCellStringValue(xssfRow, 2);
                            String departmentCode = Excel2007Util.getCellStringValue(xssfRow, 3);
                            String jobsCode = Excel2007Util.getCellStringValue(xssfRow, 4);
                            String telephone = Excel2007Util.getCellStringValue(xssfRow, 5);
                            String email = Excel2007Util.getCellStringValue(xssfRow, 6);
                            String role_id = Excel2007Util.getCellStringValue(xssfRow, 7);

                            if (check_params(realname, sex, enterpriseCode, departmentCode, jobsCode, telephone, role_id)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(sysUserList, userList, telephone)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，用户手机号重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                Integer enterpriseId = getEnterpriseId(enterpriseList, enterpriseCode);
                                Integer departmenId = getDepartmenId(departmentList, departmentCode);
                                Integer jobsId = getJobsId(jobsList, jobsCode);
                                SysUser sysUser = getImport(realname, sex, enterpriseId, departmenId, jobsId, telephone, email, role_id, rowIndex);
                                sysUser.setUsername(sysUser.getTelephone());
                                UserSession userSession = SessionUtil.getUserSession(request);
                                assert userSession != null;
                                sysUser.setCreate_person(userSession.getRealname());
                                sysUserList.add(sysUser);
                            }
                        }
                        saveForImport(sysUserList);
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

    private boolean check_params(String realname, String sex, String enterpriseCode, String departmentCode, String jobsCode,
                                 String telephone, String role_id) {
        return (StringUtils.isEmpty(realname) || StringUtils.isEmpty(sex) || StringUtils.isEmpty(enterpriseCode) || StringUtils.isEmpty(jobsCode) ||
                StringUtils.isEmpty(departmentCode) || StringUtils.isEmpty(telephone) || StringUtils.isEmpty(role_id));
    }

    private Integer getEnterpriseId(List<OrganizationEnterprise> enterpriseList, String enterpriseCode) {
        if (enterpriseList.size() == 0 || StringUtils.isEmpty(enterpriseCode)) {
            return null;
        } else {
            for (OrganizationEnterprise organizationEnterprise : enterpriseList) {
                if (Objects.equals(organizationEnterprise.getCode(), enterpriseCode)) {
                    return organizationEnterprise.getId();
                }
            }
            return null;
        }
    }

    private Integer getDepartmenId(List<OrganizationDepartment> departmentList, String departmentCode) {
        if (departmentList.size() == 0 || StringUtils.isEmpty(departmentCode)) {
            return null;
        } else {
            for (OrganizationDepartment department : departmentList) {
                if (Objects.equals(department.getCode(), departmentCode)) {
                    return Integer.parseInt(department.getId());
                }
            }
            return null;
        }
    }

    private Integer getJobsId(List<OrganizationJobs> jobsList, String jobsCode) {
        if (jobsList.size() == 0 || StringUtils.isEmpty(jobsCode)) {
            return null;
        } else {
            for (OrganizationJobs jobs : jobsList) {
                if (Objects.equals(jobs.getCode(), jobsCode)) {
                    return jobs.getId();
                }
            }
            return null;
        }
    }

    private boolean check_repeat_params(List<SysUser> importList, List<SysUser> userList, String telephone) {
        if (importList.size() == 0) {
            for (SysUser sysUser : userList) {
                if (Objects.equals(sysUser.getUsername(), telephone)) {
                    return true;
                }
            }
            return false;
        } else {
            for (SysUser sysUser : importList) {
                if (Objects.equals(sysUser.getTelephone(), telephone)) {
                    for (SysUser user : userList) {
                        if (Objects.equals(user.getTelephone(), telephone)) {
                            return true;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private SysUser getImport(String realname, String sex, Integer enterpriseId, Integer departmentId, Integer jobsId,
                              String telephone, String email, String role_id, int row_index) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(telephone);
        sysUser.setTelephone(telephone);
        sysUser.setRealname(realname);
        sysUser.setSex(sex);
        sysUser.setEnterpriseId(enterpriseId);
        sysUser.setDepartmentId(departmentId);
        sysUser.setJobsId(jobsId);
        sysUser.setEmail(email);
        sysUser.setRole_id(Integer.parseInt(role_id));
        String password = "123456";
        String randomcode = SerialNumUtil.createRandowmNum(6);
        String md5Pass = EncryptUtil.getMd5(password + "-" + randomcode);
        sysUser.setPassword(md5Pass);
        sysUser.setRandomcode(randomcode);
        sysUser.setRowIndex(row_index);
        return sysUser;
    }

    /**
     * 导入数据
     *
     * @param sysUserList 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<SysUser> saveForImport(List<SysUser> sysUserList) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(sysUserService.save(sysUserList));
    }
}
