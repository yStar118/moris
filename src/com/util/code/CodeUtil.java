package com.util.code;

import com.util.date.DateUtil;


/**
 * 公共代码生成
 *
 * @author gaoyf
 */
public class CodeUtil {

    /**
     * 生成 yyyyMMdd123456格式字符串
     */
    public static String createRefundCode() {
        return DateUtil.getShortSystemDate() + SerialNumUtil.createRandowmNum(6);
    }


    /**
     * 生成表id
     *
     * @return 生成表id
     */
    public String generateOrderNumber() {
        return DateUtil.getShortSystemTime() + RandomCodeUtil.createRandomCode(18);
    }
}
