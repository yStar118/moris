package com.util.http;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** Request相关帮助方法
 * Created by Bodil on 2016/6/6.
 */
public class RequestUtil {

    /**
     * 获取Request的参数，并将其"Key=Value&Key=Value"的格式返回
     * @param request 请求
     * @return "Key=Value&Key=Value"格式的字符串
     */
    public static String getOperaParams(HttpServletRequest request) {
        StringBuilder parameters = new StringBuilder();// 定义所有参数值
        Map<String, String[]> map = request.getParameterMap();
        // /取得所有参数值，用&号组装起来
        Object[] obj = null;
        obj = map.keySet().toArray();
        for (Object anObj : obj) {
            parameters.append(anObj.toString()).append("=").append(request.getParameter(anObj.toString())).append("&");
        }
        return parameters.toString();
    }
}
