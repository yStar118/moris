package com.portal;

import com.common.vo.ErrorImportVO;
import com.common.vo.JsonResult;
import com.entity.DictionaryTag;
import com.searchVO.DictionaryTagSearchVO;
import com.service.DictionaryTagService;
import com.util.datatables.DataTablesResult;
import com.util.excel.Excel2007Util;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 高宇飞 on 2017/8/31 11:38:13
 */
@Controller
@RequestMapping("portal/dictionary/tag")
public class DictionaryTagController {

    @Autowired
    private DictionaryTagService dictionaryTagService;

    /**
     * 进入index页面，
     *
     * @return ModelAndView 页面路径和参数
     */
    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        return new ModelAndView("/dictionary/tagIndex");
    }

    /**
     * 给datatables返回数据，按照格式   详情看http://datatables.club/manual/server-side.html
     *
     * @param searchVO 查询VO
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getList(DictionaryTagSearchVO searchVO) {
        DataTablesResult result = new DataTablesResult();
        List<DictionaryTag> list = dictionaryTagService.getList(searchVO);
        result.setDraw(searchVO.getDraw());//每个字段在实体中都有注释
        result.setRecordsFiltered(searchVO.getTotal());//每个字段在实体中都有注释
        result.setRecordsTotal(searchVO.getTotal());//每个字段在实体中都有注释
        result.setData(list);//每个字段在实体中都有注释
        return result;
    }

    /**
     * 新增/修改
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public DictionaryTag save(DictionaryTag dictionaryTag) {
        return dictionaryTagService.save(dictionaryTag);
    }

    /**
     * 根据ID查询对象
     *
     * @param id id
     */
    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ResponseBody
    public DictionaryTag findOne(Integer id) {
        return dictionaryTagService.findOne(id);
    }

    /**
     * 根据id删除对象
     *
     * @param id id
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id) {
        dictionaryTagService.delete(id);
    }


    /**
     * 查询所有
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<DictionaryTag> findAll() {
        return dictionaryTagService.findAll();
    }

    /**
     * 导入
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> importDepartment(MultipartFile excel_file) {
        List<DictionaryTag> dictionaryTags = dictionaryTagService.findAll();
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
                        List<DictionaryTag> dictionaryTagList = new ArrayList<>();
                        int lastRowNum = firstSheet.getLastRowNum();
                        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
                            if (xssfRow == null) {
                                continue;
                            }
                            String tag = Excel2007Util.getCellStringValue(xssfRow, 0);
                            String tagValue = Excel2007Util.getCellStringValue(xssfRow, 1);

                            if (check_params(tag, tagValue)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，有空单元格");
                                errorImportVOS.add(errorImportVO);
                            } else if (check_repeat_params(dictionaryTags, dictionaryTagList, tag)) {
                                ErrorImportVO errorImportVO = new ErrorImportVO();
                                errorImportVO.setReason("第" + rowIndex + "行导入失败，tag重复");
                                errorImportVOS.add(errorImportVO);
                            } else {
                                DictionaryTag dictionaryTag = getImport(tag, tagValue);
                                dictionaryTagList.add(dictionaryTag);
                            }
                        }
                        saveForImport(dictionaryTagList);
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

    private boolean check_params(String tag, String tagValue) {
        return (StringUtils.isEmpty(tag) || StringUtils.isEmpty(tagValue));
    }


    private boolean check_repeat_params(List<DictionaryTag> dictionaryTags, List<DictionaryTag> dictionaryTagList, String tag) {
        if (dictionaryTagList.size() == 0) {
            for (DictionaryTag dictionaryTag : dictionaryTags) {
                if (Objects.equals(dictionaryTag.getTag(), tag)) {
                    return true;
                }
            }
            return false;
        } else {
            for (DictionaryTag dictionaryTag : dictionaryTagList) {
                if (Objects.equals(dictionaryTag.getTag(), tag)) {
                    return true;
                } else {
                    for (DictionaryTag dicTag : dictionaryTags) {
                        if (Objects.equals(dicTag.getTag(), tag)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private DictionaryTag getImport(String tag, String tagValue) {
        DictionaryTag dictionaryTag = new DictionaryTag();
        dictionaryTag.setTag(tag);
        dictionaryTag.setTagValue(tagValue);
        return dictionaryTag;
    }

    /**
     * 导入数据
     *
     * @param dictionaryTags 导入数据List
     * @return 带有错误原因VO的list
     */
    private List<DictionaryTag> saveForImport(List<DictionaryTag> dictionaryTags) {
        //todo 如果数据重复暂时不做处理
        return IterableUtils.toList(dictionaryTagService.save(dictionaryTags));
    }


}
