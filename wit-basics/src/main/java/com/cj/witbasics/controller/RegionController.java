package com.cj.witbasics.controller;

import com.cj.witbasics.service.RegionService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.utils.entity.other.Region;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by XD on 2017/12/1.
 * 地区信息查询
 */
@RestController
@RequestMapping("/api/v1/region")
@Api(tags = {"地区查询业务相关接口"})
//@ApiIgnore
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * 查询所有的地区，按格式封装
     * @return
     */
    @GetMapping("/region")
    @ApiOperation("查询所有地区信息，树形结构封装")
    public ApiResult findRegion(){
        List<Region> initCityPicker = regionService.findAllProvinces();

        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(initCityPicker);
        return a;
    }

    //查询还没有省级代理的省。type=3，查询所有没有代理的省，type=33,查询所有的省
    @GetMapping("/findProvinces")
    @ApiOperation("查询还没有省级代理的省。type=3，查询所有没有代理的省，type=33,查询所有的省")
    @ApiImplicitParam(name = "type",value = "Integer",required = true)
    public ApiResult findProvinces(Integer type){
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(regionService.findProvinces(type));
        return a;
    }

    //查询还没有市级代理的市。type=4，查询所有省下面没有代理的市，type=44,查询所有的省下面的市
    @GetMapping("/findCity")
    @ApiOperation("查询还没有市级代理的市。type=4，查询所有省下面没有代理的市，type=44,查询所有的省下面的市")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceid",value = "String",required = true),
            @ApiImplicitParam(name = "type",value = "Integer",required = true)
    })
    public ApiResult findCity(String provinceid,Integer type){
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(regionService.findCity(provinceid,type));

        return a;
    }

    //查询还没有区县级代理的区县。type=5，查询所有市下面没有代理的区县，type=55,查询所有的市下面的区县
    @GetMapping("/findArea")
    @ApiOperation("查询还没有区县级代理的区县。type=5，查询所有市下面没有代理的区县，type=55,查询所有的市下面的区县")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityid",value = "String",required = true),
            @ApiImplicitParam(name = "type",value = "Integer",required = true)
    })
    public ApiResult findArea(String cityid,Integer type){
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(regionService.findArea(cityid,type));

        return a;
    }
}
