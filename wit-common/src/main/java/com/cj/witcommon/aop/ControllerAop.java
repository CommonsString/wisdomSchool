package com.cj.witcommon.aop;

import com.cj.witcommon.entity.ApiResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * function 系统访问数据信息增强工具类-aop
 * Created by duyuxiang on 2017/9/22.
 * version v1.0
 */
@SuppressWarnings("ALL")
@Component
@Aspect
public class ControllerAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //匹配com.cj.tangtuan.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.cj.*.controller.*..*(..))")
    public void executeService() {
    }

//    @Before("executeService()")
//    public void doAfter(JoinPoint pjp) {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String queryString = request.getQueryString();
//        String ip = GetIPUtil.getIP(request);
//        this.logger.info("\r\n ip:{}  请求信息: \r\n url:{} \r\n method:{} \r\n params:{} \r\n   ", ip, url, method, queryString);
//    }

    long startTime = 0l;
    @Before("executeService()")
    public void doBefore(JoinPoint joinPoint){

        startTime = System.currentTimeMillis();

        // 接收到请求，记录请求内容
        logger.info("收到请求:==========================================================================================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");



        // 记录下请求内容
        logger.info("请求时间 : " + sdf.format(startTime));
        logger.info("请求 URL : " + request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("IP  地址 : " + request.getRemoteAddr());
        logger.info("请求参数 : " + request.getQueryString());
        logger.info("执行方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        logger.info("执行参数 : " + Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println("请求参数==>> "+paraName+": "+request.getParameter(paraName));
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    // log.info("进入日志系统————————————" + request.getLocalAddr());
                    String description = getControllerMethodDescription(joinPoint);
                    logger.info("方法作用 : " + description);

                    //写入实体类
                    //写入数据库
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @AfterReturning(value = "executeService()",returning = "obj")
    public void  doAfterReturning(JoinPoint joinPoint,Object obj){
        ApiResult apiResult = (ApiResult) obj;
        // 处理完请求，返回内容
        logger.info("执行完成:==========================================================================================");
        logger.info("返回信息: "+obj);
        logger.info("执行结果: "+apiResult.getMsg());
        long endTime = System.currentTimeMillis();
        logger.info("执行耗时: "+(endTime-startTime) + "毫秒");


    }

    // 通过反射获取参入的参数
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        String description = "";

        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();

                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(Log.class).name();
                    break;
                }
            }
        }
        return description;
    }



}
