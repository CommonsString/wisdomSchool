package com.cj.witcommon.exception;

import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuwf on 17/4/12.
 */
@Controller
@ApiIgnore
public class FinalExceptionHandler implements ErrorController {
    Logger logger = LoggerFactory.getLogger(FinalExceptionHandler.class);
    @Override
    public String getErrorPath() {
        logger.info("********************进入自定义异常********************");
        //这个返回的视图名称不要用error，springboot默认的视图名是error,如果一定要用error,需要将error放在指定的路径下，这个日后再整理。
        return "err";
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public Object error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.http_status_not_found);
        apiResult.setMsg(ApiCode.http_status_not_found_MSG);
        return apiResult;
    }



}