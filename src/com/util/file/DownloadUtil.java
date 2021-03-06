package com.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadUtil {
    private static Logger logger = LoggerFactory.getLogger("sysLog");

    /**
     * 文件系在
     *
     * @param filePath 文件全路径
     * @param fileName 下载保存的文件名
     */
    public static void download(HttpServletResponse response, String filePath, String fileName, String encode) {
        response.setContentType("text/html;charset=" + encode);
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        String downLoadPath = filePath;
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);// new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void download(HttpServletResponse response, String filePath, String fileName) {
        DownloadUtil.download(response, filePath, fileName, "UTF-8");
    }
}
