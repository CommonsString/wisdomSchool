package com.cj.witcommon.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieTool {
    /**
     * 添加cookie
     *
     * @param response
     * @param name Key
     * @param value Value
     * @param maxAge 有效时间
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {

        //            // 1.创建Cookie对象
//            Cookie cookie1 = new Cookie("token", (String)request.getSession().getAttribute("Token"));
//            // 2.配置Cookie对象
//            cookie1.setComment("Web Host Name");    // Cookie描述
//            cookie1.setMaxAge(30*60);            // Cookie有效时间30分
//            //cookie1.setPath("/");                 // Cookie有效路径
//
//            // 3.通过response对象将Cookie写入浏览器，当然需要解决中文乱码问题，否则会抛出异常
//            // java.lang.IllegalArgumentException: Control character in cookie value, consider BASE64 encoding your value
//            response.addCookie(cookie1);

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");       // Cookie有效路径
        cookie.setMaxAge(maxAge);  // Cookie有效时间(S)
//        cookie.setDomain(".jsoft.me");     // cookie作用域
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.addCookie(cookie);
    }

    /**
     * 检索所有Cookie封装到Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> readCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过Key获取Value
     *
     * @param request
     * @param name Key
     * @return Value
     */
    public static String getCookieValueByName(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            String cookieValue = (String) cookieMap.get(name);
            return cookieValue;
        } else {
            return null;
        }
    }

}