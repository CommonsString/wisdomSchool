package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolGrade;
import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witbasics.service.SchoolPeriodService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.entity.other.Pager;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 学段管理相关业务
 * 备注：学段表中新增学校ID
 */
@Controller
@Api(tags = "学段管理")
@RestController
@RequestMapping("/api/v1/schoolperiod")
public class SchoolPeriodController {



    @Autowired(required = false)
    private SchoolPeriodService periodService;


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
     *  功能描述：根据学校ID，查询对应学段信息
     *  参数：学校ID
     *  返回：成功，返回学段集合
     *       失败，返回失败信息
     *  时间：2 小时
     *  未完成
     */
    @ApiOperation(value = "查询学段信息(渲染)", notes = "成功/失败")
    @Log(name = "查询学段信息")
//    @ApiImplicitParam(name = "schoolId", value = "学校(校区)ID", required = true,dataType = "Long")
    @GetMapping("/findInfo")
    public ApiResult findSchoolPeriodInfo(/*Long schoolId*/){
System.out.println("schoolId============>>"+schoolId);
        //TODO:session中获取学校id,就无需传参数
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            List<SchoolPeriodInfo> result = this.periodService.findPeriodAndGradeInfo(toLong());
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
     *  功能描述：年级的名称的修改
     *  参数：年级ID，新的名称
     *  返回：成功/失败
     */
    @ApiOperation(value = "修改年级", notes = "成功/失败")
    @Log(name = "修改年级")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gradeId", value = "年级ID", required = true, dataType = "Long"),
         @ApiImplicitParam(name = "gradeName", value = "新的年级名称", required = true, dataType = "String"),
    })
//    @PutMapping("updateGradeInfo")
    public ApiResult updateSchoolGradeInfo(Long gradeId, String gradeName){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.periodService.updateSchoolGradeInfo(gradeId, gradeName);
            if(result){
                apiResult.setCode(ApiCode.SUCCESS); //状态码
                apiResult.setMsg(ApiCode.SUCCESS_MSG);
            }else{
                apiResult.setCode(ApiCode.error_delete_failed);
                apiResult.setMsg(ApiCode.FAIL_MSG);
            }
        }catch (Exception e){ //异常处理
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_unknown_database_operation_MSG);
            e.printStackTrace();
        }
        return apiResult;
    }

    /**
     *  功能描述：年级的的删除
     *  参数：年级ID
     *  返回：成功/失败
     */
    @ApiOperation(value = "年级删除", notes = "成功/失败")
    @Log(name = "年级删除")
    @ApiImplicitParam(name = "gradeId", value = "年级ID", required = true, dataType = "Long")
//    @DeleteMapping("updateGradeDel")
    public ApiResult updateSchoolGradeDel(Long gradeId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.periodService.updateSchoolGradeDel(gradeId);
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
     *  功能描述：年级的添加
     *  参数：学校ID，学段ID，年级名称，使用状态
     *  返回：新增成功或失败
     *  时间：5 小时
     */
    @ApiOperation(value = "年级添加", notes = "成功/失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "periodId", value = "学段id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "gradeName", value = "年级名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "使用状态", required = false, dataType = "int"),
    })
//    @PostMapping("addGradeInfo")
    public ApiResult addSchoolGradeInfo(SchoolGrade grade){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.periodService.addSchoolGradeInfo(grade);
            if(result){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
            }
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：新增学段信息
     *  参数：学段信息实体类
     *  返回：新增成功或失败
     *  时间：2 小时 应该没有年级名称
     */
    @ApiOperation(value = "学段/年级添加", notes = "返回状态")
    @PostMapping("/addPeriodInfo")
    public ApiResult addSchoolPeriodInfo(
        @ApiParam(name = "period",value = "periodName:学段名称---," +
                "periodAge:入学年龄---,periodSystem:学制---,state:状态,---addGradeName:年级名称列表",required = true)
        @RequestBody SchoolPeriodInfo period) {
        ApiResult apiResult = new ApiResult(); //返回对象
System.out.println(period.toString());
        try{
            //TODO:获取创建人信息：创建人ID， 创建时间
            Long operatorId = 11L;
            Long schoolId = toLong();
            period.setSchoolId(schoolId);
            apiResult = this.periodService.addSchoolPeriodInfo(period, period.getAddGradeName(), operatorId);
System.out.println(apiResult.toString());
        }catch (Exception e){ //异常处理
            apiResult.setCode(ApiCode.error_create_failed);
            apiResult.setMsg(ApiCode.error_unknown_database_operation_MSG);
            e.printStackTrace();
        }
        return apiResult;
    }





    /**
     *  功能描述：根据学段信息Id，删除对应信息
     *  参数：学段信息表Id
     *  返回：删除成功或失败
     *  时间：2 小时
     */
    @ApiOperation(value = "删除学段", notes = "返回状态")
    @ApiImplicitParam(name = "periodId", value = "学段信息表Id", required = true, dataType = "Long")
    @DeleteMapping("/updatePeriodInfoDel")
    public ApiResult updateSchoolPeriodInfoDel(HttpServletRequest request, Long periodId){
        ApiResult apiResult = new ApiResult(); //返回对象
        //TODO:获取操作员ID
        Long operatorId = 1L;
//        Long operatorId = (Long)request.getSession().getAttribute("operatorId");
        try{
            boolean result = this.periodService.updatePartInfoDel(periodId, operatorId);
            if(result){
                apiResult.setCode(ApiCode.SUCCESS); //状态码
                apiResult.setMsg(ApiCode.SUCCESS_MSG);
            }else{
                apiResult.setCode(ApiCode.error_delete_failed);
                apiResult.setMsg(ApiCode.FAIL_MSG);
            }
        }catch (Exception e){ //异常处理
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_unknown_database_operation_MSG);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：根据学段信息Id列表，删除对应信息(批量删除)
     *  参数：学段信息表 id 集合 List
     *  返回：删除成功或失败
     *  时间：(1, 3) 小时
     */
    @ApiOperation(value = "根据学段信息Id列表(List)(批量删除)", notes = "返回状态")
    @ApiImplicitParam(name = "periodList", value = "学段信息表Id集合", required = true, dataType = "List")
    @DeleteMapping("/updateBathPartInfoDel")
    public ApiResult updateBathPartInfoDel(HttpServletRequest request,
                                           /*@ApiParam(name = "periodList", value = "学段信息表Id集合", required = true)
                                                   @RequestParam(value = "periodList")*/
                                                   List<Long> periodList){
        ApiResult apiResult = new ApiResult(); //返回对象
        //测试数据
        //TODO:获取操作员ID
//        Long operatorId = (Long)request.getSession().getAttribute("operatorId");
System.out.println("数据： " + periodList);
        Long operatorId = 1L;
        try{
            boolean result = this.periodService.updateBathPartInfoDel(periodList, operatorId);
            if(result){
                apiResult.setCode(ApiCode.SUCCESS); //状态码
                apiResult.setMsg(ApiCode.SUCCESS_MSG);
            }else{
                apiResult.setCode(ApiCode.error_delete_failed);
                apiResult.setMsg(ApiCode.FAIL_MSG);
            }
        }catch (Exception e){ //异常处理
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_unknown_database_operation_MSG);
            e.printStackTrace();
        }
        return apiResult;
    }

    /**
     *  功能描述：修改学段状态(字段Id)
     *  参数：学段信息实体对象
     *  返回：修改成功或失败
     *  时间：2
     */
    @ApiOperation(value = "修改学段下的年级名称", notes = "返回状态")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "periodId", value = "学段Id", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "gradeId", value = "年级ID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "gradeName", value = "年级名称(新的名字)", required = true, dataType = "String")
//    })
    @PutMapping("/updatePartInfo")
    public ApiResult updatePartInfo(
            @ApiParam(name = "info", value = "periodId:学段ID, gradeList:年级信息{gradeId:年级ID, gradeName:年级名称}")
            @RequestBody SchoolPeriodInfo info){
        ApiResult apiResult = new ApiResult(); //返回对象
        System.out.println(info.toString());
        try{
            //periodId, gradeId, gradeName
            boolean result = this.periodService.updatePartInfo(info);
//            boolean result = false;
            if(result){
                apiResult.setCode(ApiCode.SUCCESS); //状态码
                apiResult.setMsg(ApiCode.SUCCESS_MSG);
            }else{
                apiResult.setCode(ApiCode.error_delete_failed);
                apiResult.setMsg(ApiCode.FAIL_MSG);
            }
        }catch (Exception e){ //异常处理
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_unknown_database_operation_MSG);
            e.printStackTrace();
        }
        return apiResult;
    }







}
