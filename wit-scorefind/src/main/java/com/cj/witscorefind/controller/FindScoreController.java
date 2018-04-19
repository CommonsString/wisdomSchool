package com.cj.witscorefind.controller;


import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witscorefind.service.FindScoreService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 成绩查询类
 *
 */
@Api(tags = "成绩查询类")
@RestController
@RequestMapping("/api/v1/findScore")
public class FindScoreController {

    @Autowired
    private FindScoreService findService;


    /**
     *  功能描述：不同的角色,查询不同的成绩
     *  参数：
     *  返回：
     */
    @ApiOperation("查询信息(权限)")
    @ApiParam(value = "无参数",required = false)
    @PostMapping("/bathImportInfo")
    public ApiResult findScoreForDifferentRole(HttpServletRequest request){
        Long operatorId = 1L;
        //返回对象
        ApiResult apiResult = null;
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
     *  功能描述：修改班主任ID
     *  参数：班级ID, 班主任ID
     *  返回：
     */
    @ApiOperation("修改班主任")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "classId", value = "班级ID"),
        @ApiImplicitParam(name = "headmasterId", value = "班主任ID")
    })
    @PutMapping("/updateHeadmaster")
    public void updateHeadmasterId(Long classId, Long headmasterId){

    }

    /**
     *  功能描述：修改年级主任
     *  参数：班级ID, 年级主任ID
     *  返回：
     */
    @ApiOperation("修改年级主任")
    @PutMapping("/updateDirector")
    public void updateDirectorId(@ApiParam(name = "info", value = "参数")
                                             PeriodDirectorThetime info){

    }




}
