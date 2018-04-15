package com.cj.witcommon.interceptors;

import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.MemoryData;
import com.cj.witcommon.utils.json.JSONUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by XD on 2018/1/8.
 * 后端拦截器
 */
public class AdminInterceptors implements HandlerInterceptor {

    //不校验登陆的
    String[] urls = new String[] {
            "/api/v1/admin/ifLogin",//登录
            "/api/v1/admin/ifLogout"//注销

    };

    //不校验权限的
    String[] urlss = new String[]{
            "/api/v1/admin/ifLogin",//登录
            "/api/v1/admin/ifLogout",//注销
            "/api/v1/admin/loginSuccess"//查询用户所拥有的权限

    };


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println(">>>AdminInterceptors>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

//        return true;// 只有返回true才会继续向下执行，返回false取消当前请求

        String Path = request.getContextPath();  //上下文路径
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+Path+"/"; //全路径






        String requestURI = request.getRequestURI();  //请求全路径
        String path = request.getServletPath();  //请求路径
        System.out.println("===========================================================================================");
        System.out.println("Path===>"+Path);
        System.out.println("basePath===>"+basePath);
        System.out.println("requestURI===>"+requestURI);
        System.out.println("path===>"+path);
        System.out.println("===========================================================================================");
        boolean boo1 = true;  //默认校验登录
        boolean boo2 = true;  //默认校验权限

        System.out.println("============================登录、权限校验拦截器====================================");
        System.out.println(requestURI);
        System.out.println("当前请求路径================="+path);

        System.out.println("============================登录、权限校验拦截器====================================");

        try {

            //不检验登陆的url
            for (String url : urls) {
                if (url.equals(path)) {
                    boo1 = false;
                    break;

                }
            }

            //不检验权限的url
            for (String url : urlss) {
                if (url.equals(path)) {
                    boo2 = false;
                    break;

                }
            }



            HttpSession session = request.getSession();
            boolean boo4 = true;  //是否放行总开关
            boolean boo5 = true;  //单设备登录sessionId校验
            //校验登录
            if (boo1) {

                //获取session中的adminId
                Long adminId  = (Long) session.getAttribute("adminId");
                if(adminId != null && adminId >0 ){


                }

                if(adminId == null || adminId < 1 ){  //未登录
                    System.out.println("====================管理员未登录============================");
                    //没有登陆，返回状态码

                    boo4 = false;

                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null ;
                    ApiResult apiResult = new ApiResult();
                    apiResult.setCode(ApiCode.no_login);
                    apiResult.setMsg(ApiCode.no_login_MSG);
                    String str = JSONUtil.toJSONString(apiResult);
                    out = response.getWriter();
                    out.append(str);
                    out.flush();
                    out.close();

                }else if(adminId > 1){
                    //adminId存在
                    //获取sessionIDMap中的sessionId
                    String sessionId = MemoryData.getSessionIDMap().get(adminId.toString());

                    if(!request.getSession().getId().equals(sessionId)){
                        //如果sessionId不一致,用户已在其他设备登录
                        boo4 = false;
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        PrintWriter out = null ;
                        ApiResult apiResult = new ApiResult();
                        apiResult.setCode(ApiCode.account_login);
                        apiResult.setMsg(ApiCode.account_login_MSG);
                        String str = JSONUtil.toJSONString(apiResult);
                        out = response.getWriter();
                        out.append(str);
                        out.flush();
                        out.close();
                    }
                }else {

                    //已登录
                }


            }else {  //不校验登陆
                System.out.println("====================无需校验登陆============================");


            }

            if(boo2){
                //校验权限
                List<String> powerList = (List<String>) session.getAttribute("powerList");
                System.out.println("用户所拥有的请求路径===>>"+powerList);

                boolean boo3 = false;  //权限校验,默认不放行
                if(powerList != null  && powerList.size() > 0){

                    for (String s : powerList){
                        if (s.equals(path)){
                            //如果权限url集合里面有url和当前请求path匹配上了
                            boo3 = true;
                            break;
                        }
                    }

                }

                if (boo3){
                    System.out.println("===================通过权限校验===============================");

                }else {
                    System.out.println("===================无权限===============================");
                    boo4 = false;

                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null ;
                    ApiResult apiResult = new ApiResult();
                    apiResult.setCode(ApiCode.http_status_unauthorized);
                    apiResult.setMsg(ApiCode.http_status_unauthorized_MSG);
                    String str = JSONUtil.toJSONString(apiResult);
                    out = response.getWriter();
                    out.append(str);
                    out.flush();
                    out.close();
                }



            }else {
                System.out.println("====================无需校验权限============================");

            }


            if(boo4){
                return true;
            }


        } finally {
            request = null;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>AdminInterceptors>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println(">>>AdminInterceptors>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}
