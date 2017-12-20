package com.app.views;

/**
 * Created by 高宇飞 on 2017/8/14.
 * 应急预案View
 */
public class ContingencyInfoViews {
    /**
     * 只能看到部分信息 的列表
     * */
    public interface GetExchangeList extends BaseViews.ForListDTO {
    }

    /**
     * 只能看到部分信息
     * */
    public interface GetExchange extends GetExchangeList {
    }
}
