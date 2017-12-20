package com.common.controller;

import com.common.vo.UploadResult;
import com.util.code.SerialNumUtil;
import com.util.config.PubConfig;
import com.util.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Paths;

/**
 * 文件上传controller
 *
 * @author gaoyf
 */
@Controller
@RequestMapping("/common")
public class UploadController {
    private final PubConfig pubConfig;

    @Autowired
    public UploadController(PubConfig pubConfig) {
        this.pubConfig = pubConfig;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public UploadResult upload(@RequestParam(value = "file", required = false) MultipartFile file, String file_type, HttpServletRequest request) {
        String uploadPath = pubConfig.getImageUploadPath();
        String storePath = "/" + file_type + "/" + DateUtil.getShortSystemDate() + "/";
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String newFileName = DateUtil.getShortSystemTime() + SerialNumUtil.createRandowmNum(6) + "." + suffix;
        File targetFile = new File(uploadPath + storePath, newFileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        return getjson(file, fileName, newFileName, storePath, targetFile);
    }

    private UploadResult getjson(MultipartFile file, String oldFileName, String newFileName, String storePath, File targetFile) {
        UploadResult result = new UploadResult();
        if (file.getSize() > 1024 * 1024 * 100) {
            result.setSuccess(false);
            result.setMessage("文件超过100M");
        } else {
            //保存
            try {
                file.transferTo(targetFile);
                result.setSuccess(true);
                result.setMessage("成功");
                result.setCreateFileName(oldFileName);
                result.setCreateFilePath(storePath + newFileName);
            } catch (Exception e) {
                result.setSuccess(false);
                result.setMessage("上传失败");
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping(value = "delFile", method = RequestMethod.GET)
    @ResponseBody
    public UploadResult delFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return new UploadResult(false, "没有找到路径");
        }
        String uploadPath = pubConfig.getImageUploadPath();
        File file = Paths.get(uploadPath, path).toFile();
        if (file.isFile()) {
            file.delete();
            return new UploadResult(true, "删除成功");
        } else {
            return new UploadResult(false, "找不到文件!");
        }
    }

}
