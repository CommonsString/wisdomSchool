package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.mapper.SchoolSubjectMapper;
import com.cj.witbasics.service.SchoolSubjectService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "开课管理")
@RestController
@RequestMapping("/api/v1/schoolsubject")
public class SchoolSubjectController {


    @Autowired(required = false)
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
    @ApiOperation(value = "新增科目", notes = "成功/失败")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "subjectName", value = "科目名称", required = false)
    })
    @PostMapping("/addSubjectInfo")
    public ApiResult addSubjectInfo(/*Long schoolId, */SchoolSubject subject){
        //TODO:session里面获取学校ID
        //TODO:获取创建人ID
        //创建人ID
        Long operatorId = 1L;
        //设置创建人,学校ID
System.out.println(subject.toString());
        subject.setOperatorId(operatorId);
        subject.setSchoolId(toLong());
        ApiResult apiResult = null;
        try{
            apiResult = this.subjectService.addSubjectInfo(subject);
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
    @ApiOperation(value = "查询开课信息(科目名)", notes = "集合")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "subjecName", value = "开课名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pager",value = "分页参数，初始页码1，初始条数10，可为空",required = false)
    })
    @GetMapping("/findSubjectInfo")
    public ApiResult findSchoolSunjectInfo(/*Long schoolId, *//*String subjecName, */Pager pager){
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            Pager result = this.subjectService.findSchoolSunjectInfo(toLong(), pager);
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



    /**
     *  功能描述：修改信息（只能修改状态）
     *  参数：课程名
     *  返回：成功/失败
     *  时间：
     */

    @ApiOperation(value = "修改开课信息(仅修改状态)", notes = "成功/失败")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "subjectId", value = "开课ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "state",value = "状态",required = false, dataType="String")
    })
    @PutMapping("/updateSubjectInfo")
    public ApiResult updateSubjectInfo(/*Long schoolId,*/ Long subjectId, String state){
        //处理参数
        SchoolSubject subject = new SchoolSubject();
        subject.setSchoolId(toLong());
        subject.setSubjectId(subjectId);
        subject.setState(state);
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            boolean result = this.subjectService.updateSubjectInfo(subject);
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
    @ApiOperation(value = "删除开课信息", notes = "成功/失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "开课ID", required = false, dataType = "Long"),
    })
    @DeleteMapping("/updataSubjectInfoDel")
    public ApiResult updataSubjectInfoDel(Long subjectId){
        //TODO:获取操作员ID
        Long operatorId = 1L;
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            //构造对象
            SchoolSubject subject = new SchoolSubject();
            subject.setSubjectId(subjectId);
            subject.setOperatorId(operatorId);
            boolean result = this.subjectService.updataSubjectInfoDel(subject);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_delete_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_delete_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
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
//        String savePath = "D:/file/subjectInfoTable.xlsx";
        //TODO:获取输入文件名
        //用什么软件打开
        response.setHeader("content-Type", "application/vnd.ms-excel");
        //下载文件的默认名字
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("bb", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("进入全部导出！");
        ApiResult apiResult = this.subjectService.exportSubjectInfoAll(response);
        return apiResult;
    }

}
