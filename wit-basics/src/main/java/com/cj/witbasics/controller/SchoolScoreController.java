package com.cj.witbasics.controller;


import com.cj.witbasics.service.StudentScoreService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witcommon.utils.common.FileUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 班级信息相关业务
 */
@Api(tags = "成绩导入")
@RestController
@RequestMapping("/api/v1/schoolscore")
public class SchoolScoreController {

    @Autowired(required = false)
    private StudentScoreService scoreService;

    @Value("${web.upload-path}")
    private String path; //文件下载路径，配置文件

    /**
     *  功能描述：模版下载
     *  参数：response,request
     *  返回：对应结果集
     *  时间：5小时
     */
    public void downLoadExcel(HttpServletResponse response, HttpServletRequest request){
        //TODO:获取文件名
        //TODO:判断文件后缀
        String fileName = "chengji.xls";
        try {
            new FileUtil().download(request, response, this.path, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  功能描述：excel导入
     *  参数：
     *  返回：对应结果集
     *  时间：5小时
     */
    @ApiOperation("导入成绩信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolStageId", value = "学期(前端单选)"),
            @ApiImplicitParam(name = "examId", value = "考试ID")
    })
    @PostMapping("/bathImportInfo")
    public ApiResult bathImportScoreInfo(MultipartFile file, String schoolStageId, Long examId){
        //TODO:获取操作人ID
        Long operatorId = 11L;
        //返回对象
        ApiResult apiResult = new ApiResult();
        //TODO:校验文件是否为空
        if(file == null) {
            ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.FAIL, ApiCode.FAIL_MSG, null); //处理失败
            return apiResult;
        }
        try{
            //获取文件流
            InputStream in = file.getInputStream();
            boolean result = this.scoreService.bathImportInfo(file, in, operatorId);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.FAIL, ApiCode.FAIL_MSG, null); //处理失败
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.FAIL, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }






}
