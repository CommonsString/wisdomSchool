package com.cj.witcommon.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AdminFilter implements Filter {

    //不校验登陆和token的
    String[] urls = new String[] {
            "/api/v1/admin/ifLogin",
            "/util2/ifLogin",
            "/util2/ifLogout",
            "/util2/to"

    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException{
        log.info("========================Admin过滤器=====================");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;



        filterChain.doFilter(req, resp);

















    }

    @Override
    public void destroy() {

    }



}