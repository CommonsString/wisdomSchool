package com.cj.witbasics.controller;


import com.cj.witbasics.entity.SchoolBasic;
import com.cj.witbasics.service.SchoolBasicService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/schoolBasic")
@Api(tags = {"学校信息"})
public class SchoolBasicController {


    @Autowired
    private SchoolBasicService schoolBasicService;

    @Value("school_id")
    String schoolId;

    /**
     * 登录成功后完善学校信息
     * 参数：学校信息表信息
     * 返回：成功、失败
     * 时间：0.5小时
     */
    @PostMapping("/addSchoolBasic")
    @ApiImplicitParam(name = "schoolBasic",value = "schoolBasic实体类",required = true)
    @ApiOperation("新增学校基础信息")
    public ApiResult addSchoolBasic(SchoolBasic schoolBasic,HttpServletRequest request){
        if(schoolId != null){
            schoolBasic.setSchoolId(Long.parseLong(schoolId));
        }
        ApiResult apiResult = new ApiResult();

        int i = schoolBasicService.addSchoolBasic(request,schoolBasic);
        return ApiResultUtil.fastResult(new ApiResult(),i);
    }

    /**
     * 检查学校信息是否已存在
     */
    @GetMapping("/findSchoolBasic")
    @ApiOperation("查询学校基本信息是否已完善")
    public ApiResult findSchoolBasic(){

        int i = schoolBasicService.findSchoolBasic(Long.parseLong(schoolId));

        return ApiResultUtil.fastResult(new ApiResult(),i);

    }

}
