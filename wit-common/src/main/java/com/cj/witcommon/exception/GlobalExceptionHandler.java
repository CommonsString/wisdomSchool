package com.cj.witcommon.exception;

import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

/**
 * Created by wuwf on 17/3/31.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class.getName());
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultHandler(HttpServletRequest request, Exception e) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e);
//        modelAndView.addObject("url", request.getRequestURL());
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.http_status_method_not_allowed);
        apiResult.setMsg(ApiCode.http_status_method_not_allowed_MSG);

        return new ResponseEntity<Object>(apiResult, NOT_EXTENDED);

    }

    //可以返回自定义的错误页面
    @ExceptionHandler(Exception.class)  //这里根据报的异常可以写不同的方法，分别捕捉
    @ResponseBody
    public ApiResult jsonHandler(HttpServletRequest request, Exception e) throws Exception {

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.http_status_internal_server_error);
        apiResult.setMsg(ApiCode.http_status_internal_server_error_MSG);
        log(e, request);


        return apiResult;
    }

    private void log(Exception ex, HttpServletRequest request) {
        logger.error("************************异常开始*******************************");
//        if(getUser() != null)
//            logger.error("当前用户id是" + getUser().getUserId());
        logger.error(ex);
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }

        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }
}