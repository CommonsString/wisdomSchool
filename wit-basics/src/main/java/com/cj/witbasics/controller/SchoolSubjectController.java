package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.mapper.SchoolSubjectMapper;
import com.cj.witbasics.service.SchoolSubjectService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witcommon.entity.SchoolPeriodInfo;
import com.cj.witcommon.utils.entity.other.Pager;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Api(tags = "课程管理")
@RestController
@RequestMapping("/api/v1/schoolsubject")
public class SchoolSubjectController {


    @Autowired
    private SchoolSubjectService subjectService;

    @Value("${school_id}")
    private String schoolId;


    /**
     * 转为Long
     * @return
     */
    private Long toLong(){
        System.out.println(this.schoolId);
        return Long.valueOf(this.schoolId);
    }



    /**
     *  功能描述：增加开课
     *  参数：课程实体
     *  返回：成功/失败
     *  时间：
     */
    @ApiOperation(value = "新增课程", notes = "成功/失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectName", value = "课程名", required = true),
            @ApiImplicitParam(name = "subjectsId", value = "科目ID", required = true)
    })
    @PostMapping("/addSubjectInfo")
    @Log(name = "课程管理 ==> 新增课程")
    public ApiResult addSubjectInfo(String subjectName, Long subjectsId, HttpServletRequest request){
        ApiResult apiResult = new ApiResult();
        try{
            //数据填充
            Long adminId = (Long)request.getSession().getAttribute("adminId");
            SchoolSubject schoolSubject = new SchoolSubject();
            schoolSubject.setSubjectsId(subjectsId);
            schoolSubject.setSubjectName(subjectName);
            schoolSubject.setOperatorId(adminId);
            apiResult = this.subjectService.addSubjectInfo(request.getSession(), schoolSubject);
System.out.println(apiResult.toString());
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
            ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：查询开课
     *  参数：课程名
     *  返回：成功/失败
     *  时间：
     */
    @ApiOperation(value = "查询课程信息", notes = "集合")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pager",value = "分页参数，初始页码1，初始条数10，可为空",required = false)
    })
    @GetMapping("/findSubjectInfo")
    public ApiResult findSchoolSunjectInfo(/*Pager pager*/){

        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            List<SchoolSubject> result = this.subjectService.findSchoolSunjectInfo(toLong()/*, pager*/);
//            List result = null;
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

    @GetMapping("/findAllSubjectBySubjectsId")
    @ApiOperation("根据科目id查询其下所有的课程")
    @Log(name = "课程 ==> 查询科目下的所有课程")
    @ApiImplicitParam(name = "subjectsId", value = "科目ID", required = true,paramType = "query")
    public ApiResult findAllSubjectBySubjectsId(
                                                Long subjectsId){

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(subjectService.findAllSubjectBySubjectsId(subjectsId));

        return apiResult;



    }


    /**
     *  功能描述：修改信息（只能修改状态）
     *  参数：课程名
     *  返回：成功/失败
     *  时间：
     */
    @ApiOperation(value = "编辑课程信息", notes = "成功/失败")

    @PutMapping("/updateSubjectInfo")
    @Log(name = "课程管理 ==> 编辑课程信息")
    public ApiResult updateSubjectInfo(@ApiParam(name = "",value = "subjectName=课程名称，subjectId=课程Id，isBegin=是否停课，state=是否删除")
                                           @RequestBody SchoolSubject schoolSubject){
        //处理参数
        schoolSubject.setSchoolId(toLong());
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            boolean result = this.subjectService.updateSubjectInfo(schoolSubject);
//            boolean result = false;
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_update_failed, ApiCode.FAIL_MSG, null);
            }
}catch (Exception e){ //异常处理
        ApiResultUtil.fastResultHandler(apiResult, false,
        ApiCode.error_update_failed, ApiCode.error_unknown_database_operation_MSG, null);
        e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：删除开课信息
     *  参数：课程名
     *  返回：成功/失败
     *  时间：
     */
//    @ApiOperation(value = "删除课程信息", notes = "成功/失败")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "subjectId", value = "开课ID", required = false, dataType = "Long"),
//    })
//    @DeleteMapping("/updataSubjectInfoDel")
//    @Log(name = "课程  ==> 删除课程（非物理删除）")
//    public ApiResult updataSubjectInfoDel(Long subjectId, HttpSession session){
//        //TODO:获取操作员ID
//        Long operatorId = (Long) session.getAttribute("adminId");
//        //返回对象
//        ApiResult apiResult = new ApiResult();
//        try{
//            //构造对象
//            SchoolSubject subject = new SchoolSubject();
//            subject.setSubjectId(subjectId);
//            subject.setOperatorId(operatorId);
//            apiResult = this.subjectService.updataSubjectInfoDel(subject);
//        }catch (Exception e){ //异常处理
//            ApiResultUtil.fastResultHandler(apiResult, false,
//                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
//            e.printStackTrace();
//        }
//        return apiResult;
//    }

    /**
     * 删除课程-物理删除
     */
    @ApiOperation("删除课程-物理删除")
    @DeleteMapping("/deleteSubject")
    @Log(name = "课程 ==> 删除课程")
    @ApiImplicitParam(name = "subjectId",value = "subjectId=课程ID",required = true)
    public ApiResult deleteSubject(Long subjectId){

        ApiResult apiResult = new ApiResult();
        int i = subjectService.deleteSubject(subjectId);
        if(i == 9){
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_delete_failed_MSG+",此课程有班级正在使用");

        }else if(i == 1){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);

        }else {
            apiResult.setCode(ApiCode.FAIL);
            apiResult.setMsg(ApiCode.FAIL_MSG);

        }


        return apiResult;
    }


    /**
     *  功能描述：停课
     *  参数：课程名
     *  返回：成功/失败
     *  时间：
     */
    @ApiOperation(value = "停课", notes = "成功/失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "开课ID", required = false, dataType = "Long"),
    })
    @DeleteMapping("/stopSubject")
    public ApiResult updataStopSubject(Long subjectId){
        //TODO:获取操作员ID
        Long operatorId = 1L;
        ApiResult apiResult = null;
        try{
            //构造对象
            SchoolSubject subject = new SchoolSubject();
            subject.setSubjectId(subjectId);
            subject.setOperatorId(operatorId);
            apiResult = this.subjectService.updataStopSubject(subject);

        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }

    /**
     *  功能描述：导出excel
     *  参数：导出路径，导出文件名
     *  时间：
     */
    @ApiOperation(value = "导出信息(选择导出)", notes = "成功/失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectList", value = "开课ID集合(选中需要导出的数据行)"),
    })
    @GetMapping("/exportSubjectInfo")
    public void exportSubjectInfo(
            @ApiParam(name = "subjectList")
            @RequestBody List<Long> subjectList, HttpServletResponse response){
        //TODO:获取导出路径
        String savePath = "D:/file/subjectInfoTable.xlsx";
        //TODO:获取输入文件名
//        String fileName = "subjectInfoTable.xlsx";
System.out.println("进入！");
        for(Long val : subjectList){
            System.out.println(val);
        }
        this.subjectService.exportSubjectInfo(response, subjectList);
    }

    /**
     *  功能描述：导出excel
     *  参数：导出路径，导出文件名
     *  时间：
     */
    @ApiOperation(value = "导出信息(全部导出)", notes = "成功/失败")
    @GetMapping("/exportSubjectAll")
    public ApiResult exportSubjectInfoAll(HttpServletResponse response){
        //TODO:获取导出路径
        //TODO:获取输入文件名
        //用什么软件打开
        response.setHeader("content-Type", "application/vnd.ms-excel");
        //下载文件的默认名字
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("subjectInfo", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("进入全部导出！");
        ApiResult apiResult = this.subjectService.exportSubjectInfoAll(response);
        return apiResult;
    }

    /**
     *  功能描述：科目为基础，选择班级
     *  参数：
     *  时间：
     */
    @ApiOperation(value = "设置课程右移(班级为基础，选择课程)", notes = "成功/失败")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "classId",value = "班级Id"),
//            @ApiImplicitParam(name = "subjectId", value = "课程Id集合")

    })
    @Log(name = "课程管理 ==> 设置课程右移(增加)")
    @PostMapping("/SelectSubjectRight")
    public ApiResult SelectSubjectAndClassRight(/*@RequestBody List<Long> classId, @RequestBody List<Long> subjectId,*/
            @RequestBody Map params,
            HttpServletRequest request){
        //TODO:获取操作员ID
        Long operatorId = 1L;
        ApiResult apiResult = new ApiResult();
//        System.out.println(params.isEmpty());
//        List<Integer> a = (List<Integer>)params.get("classId");
//        for(Integer in : a){
//            System.out.println(in);
//        }

        try{
            //构造对象
            apiResult = this.subjectService.SelectSubjectAndClassRight(params, request.getSession());
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：科目为基础，选择班级
     *  参数：
     *  时间：
     */
    @ApiOperation(value = "设置课程左移(班级为基础，移除课程)", notes = "成功/失败")
    @PostMapping("/SelectSubjectLeight")
    public ApiResult SelectSubjectAndClassLeight(@RequestBody Map params,
                                                 HttpServletRequest request){
        //TODO:获取操作员ID
        Long operatorId = 1L;
        ApiResult apiResult = new ApiResult();
        try{
            apiResult = this.subjectService.SelectSubjectAndClassLeight(params, request.getSession());
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }






}
