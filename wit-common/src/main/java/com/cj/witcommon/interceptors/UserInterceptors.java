package com.cj.witcommon.interceptors;

import com.cj.witcommon.utils.common.Status;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by XD on 2018/1/8.
 * 前端拦截器
 */
public class UserInterceptors implements HandlerInterceptor {

    //不拦截的请求
    String[] urls = new String[] {
            "/bx/login.html",
            "/bx/index.html"

    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>UserInterceptors>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

//        return true;// 只有返回true才会继续向下执行，返回false取消当前请求

        String Path = request.getContextPath();  //上下文路径
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+Path+"/"; //全路径

        String requestURI = request.getRequestURI();
        String path = request.getServletPath();
        boolean boo = true;
        System.out.println("============================前端拦截器====================================");
        System.out.println(requestURI);
        System.out.println(path);

        System.out.println("============================前端拦截器====================================");

        try {
            //不校验登陆的
            for (String url : urls) {
                if (url.equals(path)) {
                    boo = false;
                }
                break;

            }

            if (boo){
                //说明处在编辑的页面
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("userName");
                if(username!=null){
                    //登陆成功的用户
                    return true;
                }else{
                    System.out.println("====================用户未登录============================");
                    //没有登陆，转向登陆界面
//                    request.getRequestDispatcher(request.getContextPath()+Status.LOGIN+Status.Suffix).forward(request,response);
                    response.sendRedirect(Path+ Status.Static+Status.LOGIN+Status.Suffix);
                    return false;
                }
            }else {
                System.out.println("====================跳过登陆验证============================");
                //跳过验证
                return true;
            }


        }finally {
            request = null;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>UserInterceptors>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>UserInterceptors>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
