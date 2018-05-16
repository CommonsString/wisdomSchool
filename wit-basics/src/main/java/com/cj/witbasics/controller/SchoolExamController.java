package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolExam;
import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.service.SchoolExamService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.entity.other.Pager;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
     *  功能描述：查询届次
     *  参数：学校ID
     *  返回：对应结果集
     *  时间：6小时
     */
    @ApiOperation(value = "查询届次(考试)", notes = "返回状态")
    @Log(name = "考试查询 ===> 查询借此")
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
    @Log(name = "查询班级")
    @ApiImplicitParam(name = "param", value = "参数")
    @PostMapping("/findAllClass")
    public ApiResult findAllUnderGradeClass(
            @ApiParam(name = "param", value = "实体")
            @RequestBody List<PeriodAndGrade> param){
        //返回对象
        ApiResult apiResult = new ApiResult();
        try{
            List result = this.examService.findAllUnderGradeClass(param);
            if(!result.isEmpty()){
                ApiResultUtil.fastResultHandler(apiResult, true, null, null, result); //数据的封装
            }else{
                ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.error_search_failed, ApiCode.FAIL_MSG, result);
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
    @Log(name = "查询科目")
    @ApiImplicitParam(name = "classId", value = "班级Id", required = true)
//    @GetMapping("/findSubjectInfo")
    @PostMapping("/findSubjectInfo")
    public ApiResult findAllSubjectInfo(@RequestBody List<Long> classId){
        ApiResult apiResult = new ApiResult();
        try{
System.out.println(classId.size() + "　　尺寸 ：");
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
    @Log(name = "新增考试")
    @PostMapping("/addExamInfo")
    public ApiResult addSchoolExamInfo(
            @ApiParam(name = "examInfo", value = "params（subjectId=课程ID,subjectName=课程名）")
            @RequestBody ExamParam examInfo,
            HttpSession session){
        ApiResult apiResult = new ApiResult(); //返回对象
        Long adminId = (Long) session.getAttribute("adminId");
        try{
            apiResult = this.examService.addSchoolExamInfo(examInfo, adminId);
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    /**
     *  功能描述：查询考试名称
     *  参数：
     *  返回：成功/失败
     *  时间：10小时
     */
    @ApiOperation(value = "查询考试名称", notes = "返回成功或失败")
    @Log(name = "查询考试名称")
    @GetMapping("/findExamName")
    public ApiResult findExamName(){
        ApiResult apiResult = new ApiResult();
        try{
            List<Map> result = this.examService.findExamName(toLong());
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
     *  功能描述：查询考试信息,模糊条件
     *  参数：学校ID，考试类别，考试对象(初一，初二.....)
     *  返回：成功/失败
     *  时间：10小时
     */
    @ApiOperation(value = "查询考试", notes = "返回成功或失败")
    @Log(name = "查询考试(模糊)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examName", value = "考试名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "vague", value = "模糊条件", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pager",value = "分页参数，初始页码1，初始条数10，可为空",required = false)
    })
    @GetMapping("/findExamOfVague")
    public ApiResult findExamOfVague(String examName, String vague, Pager pager){
        ApiResult apiResult = new ApiResult();
        try{
            Pager result = this.examService.findExamOfVague(examName, vague, pager);
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
     * ======================================================================================================
     * 查询考试集合
     */
//    @ApiOperation("根据届次查询考试集合")
//    @GetMapping("/findAllSchoolExamByThetime")
//    @Log(name = "参数设置  ==> 根据届次查询考试集合")
//    @ApiImplicitParam(name = "thetime",value = "thetime=毕业时间-届次",required = true)
//    public ApiResult findAllSchoolExamByThetime(String thetime){
//
//        ApiResult apiResult = new ApiResult();
//        apiResult.setCode(ApiCode.SUCCESS);
//        apiResult.setMsg(ApiCode.SUCCESS_MSG);
//        apiResult.setData(examService.findAllSchoolExamByThetime(thetime));
//
//        return apiResult;
//
//    }

    /**
     * 模糊查询考试集合
     */
    @PostMapping("/findAllSchoolExamParentByParameter")
    @ApiOperation("查询考试信息（模糊查询）" +
            "{\n" +
            "  \"parameter\": \"考\"\n" +
            "}")
    @Log(name = "考试  ==> 查询考试信息（模糊查询->考试名称）")
    public ApiResult findAllSchoolExamParentByParameter(@ApiParam(name = "p",value = "parameter=考试名称",required = false)
                                                         @RequestBody Pager p){

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(examService.findAllSchoolExamParentByParameter(p));
        return apiResult;

    }

    /**
     * 根据考试父节点ID查询此次考试所有 学段-届次
     */
    @GetMapping("/findAllSchoolExamThetimeBySchoolExamParent")
    @ApiOperation("查询考试届次（考试父节点ID->学段、届次、班级类型）")
    @Log(name = "考试  ==> 查询考试届次（考试父节点ID->学段、届次、班级类型）")
    @ApiImplicitParam(name = "examParentId",value = "examParentId=考试父节点ID",required = true)
    public ApiResult findAllSchoolExamThetimeBySchoolExamParent(Long examParentId){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(examService.findAllSchoolExamThetimeBySchoolExamParent(examParentId));
        return apiResult;
    }

    /**
     * 根据考试 父节点ID 和 考试届次 查询此次考试的所有班级及课程信息
     */
    @GetMapping("/findAllSchoolExamClassAndSubject")
    @ApiOperation("查询考试信息（考试父节点ID-届次-学段ID-班级类型ID->课程）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examParentId", value = "examParentId=考试父节点ID", required = true),
            @ApiImplicitParam(name = "thetime", value = "thetime=届次", required = true),
            @ApiImplicitParam(name = "classPeriodId", value = "classPeriodId=学段ID", required = true),
            @ApiImplicitParam(name = "classTypeId", value = "classTypeId=班级类型ID", required = true)
    })
    @Log(name = "考试  ==> 查询考试信息（考试父节点ID-届次-班级ID->课程）")
    public ApiResult findAllSchoolExamClassAndSubject(Long examParentId,String thetime,Long classPeriodId,Integer classTypeId){
        Map map = new HashMap();
        map.put("examParentId",examParentId);
        map.put("thetime",thetime);
        map.put("classPeriodId",classPeriodId);
        map.put("classTypeId",classTypeId);
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(examService.findAllSchoolExamClassByExamParentIdAndThetime(map));
        return apiResult;
    }


    /**
     * 根据考试 父节点ID 和 考试届次 查询此次考试的所有班级的课程信息
     */
    @GetMapping("/findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime")
    @ApiOperation("查询考试信息（考试父节点ID-届次-学段ID-班级类型ID->课程）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examParentId", value = "examParentId=考试父节点ID", required = true),
            @ApiImplicitParam(name = "thetime", value = "thetime=届次", required = true),
            @ApiImplicitParam(name = "classPeriodId", value = "classPeriodId=学段ID", required = true),
            @ApiImplicitParam(name = "classTypeId", value = "classTypeId=班级类型ID", required = true)
    })
    @Log(name = "考试  ==> 查询考试信息（考试父节点ID-届次-学段ID-班级类型ID->课程）")
    public ApiResult findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime(Long examParentId,String thetime,Long classPeriodId,Integer classTypeId){
        Map map = new HashMap();
        map.put("examParentId",examParentId);
        map.put("thetime",thetime);
        map.put("classPeriodId",classPeriodId);
        map.put("classTypeId",classTypeId);
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(examService.findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime(map));

        return apiResult;
    }


}
