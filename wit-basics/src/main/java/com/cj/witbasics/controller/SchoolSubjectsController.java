package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolSubjects;
import com.cj.witbasics.service.SchoolSubjectsService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@Api(tags = "科目管理")
public class SchoolSubjectsController {

    @Autowired
    private SchoolSubjectsService schoolSubjectsService;

    /**
     * 新增科目
     */
    @PostMapping("/addSubjects")
    @ApiOperation("新增科目")
    @Log(name = "科目 ==> 新增科目")
    public ApiResult addSubjects(@ApiParam(name = "schoolSubjects",value = "subjectsName=科目名称必填",required = true)
                                     @RequestBody SchoolSubjects schoolSubjects,
                                 HttpServletRequest request){
        int i = schoolSubjectsService.addSubjects(request.getSession(),schoolSubjects);

        ApiResult apiResult = new ApiResult();

        if(i == 1){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i == 15){
            apiResult.setCode(ApiCode.error_duplicated_data);
            apiResult.setMsg(ApiCode.error_duplicated_data_MSG);

        }else {
            apiResult.setCode(ApiCode.error_create_failed);
            apiResult.setMsg(ApiCode.error_create_failed_MSG);
        }
        return apiResult;
    }

    /**
     * 删除科目
     */
    @DeleteMapping("/deleteSubjects")
    @ApiOperation("删除科目")
    @ApiImplicitParam(name = "subjectsId",value = "科目ID",required = true)
    @Log(name = "科目 ==> 删除科目")
    public ApiResult deleteSubjects(HttpServletRequest request,Long subjectsId){

        int i = schoolSubjectsService.deleteSubjects(request.getSession(),subjectsId);

        ApiResult apiResult = new ApiResult();
        if(i == 1){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i == 1015){
            apiResult.setCode(ApiCode.subjects_subject_exist);
            apiResult.setMsg(ApiCode.subjects_subject_exist_MSG);

        }else {
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_delete_failed_MSG);
        }
        return apiResult;

    }

    /**
     * 修改科目
     */
    @PutMapping("/updateSubjects")
    @ApiOperation("修改科目信息")
    @Log(name = "科目 ==> 修改科目信息")
    public ApiResult updateSubjects(HttpServletRequest request,
                                    @ApiParam(name = "schoolSubjects",value = "subjectsName=科目名称,subjectsId=科目ID",required = true)
                                    @RequestBody SchoolSubjects schoolSubjects){

        int i = schoolSubjectsService.updateSubjects(request.getSession(),schoolSubjects);
        ApiResult apiResult = new ApiResult();
        if(i == 1){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i == 15){
            apiResult.setCode(ApiCode.error_duplicated_data);
            apiResult.setMsg(ApiCode.error_duplicated_data_MSG);

        }else {
            apiResult.setCode(ApiCode.error_update_not_allowed);
            apiResult.setMsg(ApiCode.error_update_not_allowed_MSG);
        }
        return apiResult;
    }

    /**
     * 查询科目信息
     */
    @GetMapping("/findAllSchoolSubjects")
    @ApiOperation("查询所有科目信息")
    @Log(name = "科目 ==> 查询所有科目信息")
    public ApiResult findAllSchoolSubjects(){
        List<SchoolSubjects> list = schoolSubjectsService.findAllSubjects();
        ApiResult apiResult = new ApiResult();
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setData(list);
        return apiResult;

    }
}
