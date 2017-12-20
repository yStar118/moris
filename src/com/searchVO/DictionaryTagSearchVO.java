package com.searchVO;

/**
 * Created by 高宇飞 on 2017/8/31 11:28:30
 * 字典- tag
 */
public class DictionaryTagSearchVO extends CommonSearchVO {

    private String tag;

    public String getTag() {
        return tag;
    }

    public String getTagParam() {
        return "%" + tag + "%";
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
