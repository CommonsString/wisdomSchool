package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolExam;
import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.service.SchoolExamService;
import com.cj.witcommon.entity.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 考试管理相关业务
 */
@Api(tags = "考试管理")
@RestController
@RequestMapping("/api/v1/schoolexam")
public class SchoolExamController {


    @Autowired(required = false)
    private SchoolExamService examService;


    @Value("${school_id}")
    private String schoolId;

    /**
     * 转为Long
     * @return
     */
    private Long toLong(){
        return Long.valueOf(this.schoolId);
    }


    /**
     *  功能描述：查询学校下的年级(初一，初二，高一....)
     *  参数：学校ID
     *  返回：对应结果集
     *  时间：6小时
     */
    @ApiOperation(value = "无参。查询---学段(高中...)--年级(一年级...)", notes = "返回状态")
    @GetMapping("/findGrade")
    public ApiResult findAllGradeName(){
        //获取学校Id
        Long schoolId = toLong();
        //返回对象
        ApiResult apiResult = new ApiResult();

        try{
            List<Map> result = examService.findAllGradeName(schoolId);
            if(result != null){
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
     *  功能描述：查询选择条件下的所有班级
     *  参数：学校ID，年级ID
     *  返回：班级信息
     *  时间：8小时
     */
    @ApiOperation(value = "查询班级", notes = "返回成功或失败")
    @ApiImplicitParam(name = "param", value = "参数")
    //测试post
//    @GetMapping("/findAllClass")
    @PostMapping("/findAllClass")
    public ApiResult findAllUnderGradeClass(
            @ApiParam(name = "param", value = "实体")
            @RequestBody List<PeriodAndGrade> param){
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            List result = this.examService.findAllUnderGradeClass(param);
            if(result != null){
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
     *  功能描述：查询班级对应的科目
     *  参数：根据班级Id
     *  返回：对应结果集
     *  时间：6小时
     */
    @ApiOperation(value = "查询科目", notes = "返回成功或失败")
    @ApiImplicitParam(name = "classId", value = "班级Id", required = true, dataType = "Long")
    @GetMapping("/findSubjectInfo")
    public ApiResult findAllSubjectInfo(Long classId){
        ApiResult apiResult = new ApiResult();
        try{
//            List result = this.examService.findAllUnderGradeClass(param);
            List result = this.examService.findAllSubjectInfo(classId);
            if(result != null){
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
     *  功能描述：新增考试
     *  参数：
     *  {
     *      班级ID
     *      科目列表:
     *              {
     *                  科目id
     *                  科目名称
     *              }
     *  }
     *  返回：成功/失败
     *  时间：16小时
     */
    @ApiOperation(value = "新增考试", notes = "成功/失败")
    @PostMapping("/addExamInfo")
    public ApiResult addSchoolExamInfo(
            @ApiParam(name = "examInfo", value = "最内层,只传递subjectId,subjectName")
            @RequestBody ExamParam examInfo){
        System.out.println(examInfo.toString());
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            apiResult = this.examService.addSchoolExamInfo(examInfo);
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：查询考试信息
     *  参数：学校ID，考试类别，考试对象(初一，初二.....)
     *  返回：成功/失败
     *  时间：10小时
     */
    public void findSchoolExamInfo(){

    }





}
