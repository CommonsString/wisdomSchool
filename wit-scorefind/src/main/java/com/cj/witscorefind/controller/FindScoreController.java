package com.cj.witscorefind.controller;


import com.cj.witbasics.entity.Grade;
import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witscorefind.service.FindScoreService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 成绩查询类
 *
 */
@Api(tags = "成绩查询")
@RestController
@RequestMapping("/api/v1/findScore")
public class FindScoreController {

    @Autowired(required = false)
    private FindScoreService findService;


    /**
     *  功能描述：不同的角色,查询不同的成绩
     *  参数：
     *  返回：
     */
    @ApiOperation("查询成绩统计信息(班主任，年级主任，各类管理员)")
    @ApiParam(value = "无参数",required = false)
    @PostMapping("/bathImportInfo")
    @Log(name = "成绩查询 ==> 查询信息")
//    @ApiImplicitParam(name = "test", value = "测试参数")
    public ApiResult findScoreForDifferentRole(HttpServletRequest request/*, String test*/){
        //返回对象
        ApiResult apiResult = new ApiResult();
//        System.out.println("进入： " + test);
        try{
            //构造对象
            apiResult = this.findService.findScoreForDifferentRole(request);
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }



    /**
     *  功能描述：添加成绩档次
     *  参数：档次信息
     *  返回：
     */
    @ApiOperation("添加成绩档次")
    @Log(name = "成绩查询 ==> 添加成绩档次")
    @PostMapping("/updateDirector")
    public ApiResult addGradeLevelInfo(HttpServletRequest request,
                                       @ApiParam(name = "info",
                                               value = "exam_grade_id:考试id,grade_name:档次名称" + ",grade_type:场次设置方式")
                                       @RequestBody Grade info){
        //操作员Id
        Long adminId = (Long) request.getSession().getAttribute("adminId");
        //设置创建人,学校ID
        ApiResult apiResult = this.findService.addGradeLevelInfo(info);
        try{
            apiResult = null;
            System.out.println(apiResult.toString());
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：查看分数
     *  参数：班级ID,科目id
     *  返回：
     */
    @ApiOperation(value = "查看分数", notes = "成功/失败")
    @Log(name = "成绩查询 ==> 查看分数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classId", value = "班级Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "subjectId", value = "科目ID", required = true, dataType = "Long")
    })
    @GetMapping("/findScoreDetils")
    public ApiResult findScoreDetils(Long classId, Long subjectId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
//            List<GradeInfo> result = this.periodService.findPeriodGradeInfo(schoolId, periodId);
            List result = this.findService.findScoreDetils(classId, subjectId);
            if(!result.isEmpty()){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, result); //数据的封装
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_search_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_search_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }

    /**
     *  功能描述：导出
     *  参数：班级ID
     *  返回：
     */
    @ApiOperation(value = "导出信息(选择导出)", notes = "成功/失败")
    @Log(name = "成绩查询 ==> 导出信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classIdList", value = "班级ID集合"),
    })
//    @PostMapping("/exportScoreInfo")
    public void exportScoreInfo(
            @RequestBody List<Long> classIdList, HttpServletResponse response){
        response.setHeader("content-Type", "application/vnd.ms-excel");
        //下载文件的默认名字
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("scoreDetails", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("进入！");
        for(Long val : classIdList){
            System.out.println(val);
        }
        this.findService.exportScoreInfo(classIdList, response);
    }




}
