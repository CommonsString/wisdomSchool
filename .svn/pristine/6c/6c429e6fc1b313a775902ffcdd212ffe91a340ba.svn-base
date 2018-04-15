package com.cj.witcommon.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class UserFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//            HttpServletRequest request = (HttpServletRequest) srequest;
//            //打印请求Url
//            System.out.println("this is MyFilter,url :" + request.getRequestURI());
//            filterChain.doFilter(srequest, sresponse);
        log.info("========================前端过滤器=====================");

        // 将请求转换成HttpServletRequest 请求
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
        HttpSession session = req.getSession(true);

        String username = (String) session.getAttribute("username");
        if (username == null || "".equals(username)) {
            resp.setHeader("Cache-Control", "no-store");
            resp.setDateHeader("Expires", 0);
            resp.setHeader("Prama", "no-cache");
            //此处设置了访问静态资源类
            resp.sendRedirect(basePath+"/index.html");
        } else {
            // Filter 只是链式处理，请求依然转发到目的地址。
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}