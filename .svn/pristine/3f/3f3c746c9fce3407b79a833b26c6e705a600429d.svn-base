package com.cj.witcommon.utils.common;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by onglchen on 12/5/15.
 */
public class ServletUtil {
    public static Map getMap(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    System.out.println("参数：" + paramName + "=" + paramValue);
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}
