package com.cj.witcommon.filter;


import com.cj.witcommon.utils.CookieTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理跨域问题
 * 和cookie
 * @author MR.ZHENG
 * @date 2016/08/08
 *
 */
@Component
@Slf4j
public class OriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
//        log.info("========================Cookie过滤器=====================");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String basePath = CookieTool.getCookieValueByName(request,"basePath");
//        System.out.println("basePath==="+basePath);
//        System.out.println("reqUrl==="+request.getRequestURL());

        if(basePath == null || basePath.trim().length()<5){
            String path = request.getContextPath();
            basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            CookieTool.addCookie(response,"basePath",basePath,30*60);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}