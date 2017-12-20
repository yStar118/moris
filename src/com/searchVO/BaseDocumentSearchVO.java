package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/6/20.
 * 文件文档
 */
public class BaseDocumentSearchVO extends CommonSearchVO {

    private String fileName;
    private String category;

    public String getFileName() {
        return fileName;
    }

    /**
     * 这里拼接模糊查询的字段值
     *
     * @return 字段值
     */
    public String getFileNameParam() {
        return "%" + fileName + "%";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
