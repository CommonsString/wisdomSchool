package com.cj.witbasics.controller;

import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.SchoolPeriodService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.utils.common.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.Map;
import java.util.UUID;

import static com.cj.witcommon.utils.common.FileUtil.uploadImgBase64;
import static com.cj.witcommon.utils.http.APIHttpClient.analyzeFile;


@RestController
@RequestMapping("/api/v1/common")
@Api(tags = "公用方法")
public class CommonController {

    @Value("${web.upload-path}")
    String path;

    @Value("${school_id}")
    String schoolId;

    @Autowired
    private CloudService cloudService;

    @Autowired
    private SchoolPeriodService schoolPeriodService;

    //单文件上传
    @ApiOperation("上传头像（file）")
    @PostMapping("/uploadFile")
    @Log(name = "上传头像")
    public ApiResult uploadFile(HttpServletRequest request,
            @ApiParam(name = "file",value = "头像",required = true) MultipartFile file) throws Exception {
        String imgUrl1 = "";  //本地头像地址
        String imgUrl2 = "";  //云端头像地址
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        //判断文件是否为空
        if(file != null && !file.isEmpty()){
            imgUrl1 += FileUtil.uploadFile(path,file);

        }
        System.out.println("文件路径===>>"+imgUrl1);

        imgUrl2 = cloudService.cloudUpload(new File(imgUrl1),request);

        ApiResult apiResult = new ApiResult();
        if(imgUrl2.length() > 10){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setData(imgUrl2);

        }else if(imgUrl1.length() > 10){

            imgUrl1 = imgUrl1.replace(this.path,basePath+"img/");
            System.out.println("imgUrl1===============>"+imgUrl1);
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setData(imgUrl1);

        }else  {
            apiResult.setCode(ApiCode.error_pic_upload);
            apiResult.setMsg(ApiCode.error_pic_upload_MSG);
        }

        return apiResult;

    }

    @PostMapping(value="/uploadBase64")
    @ApiOperation("上传图片，base64")
    @Log(name = "上传头像")
    public ApiResult base64UpLoad(@RequestParam @ApiParam(name = "base64Data",value = "Base64格式图片",required = true) String base64Data,
                               HttpServletRequest request,
                               HttpServletResponse response) throws FileNotFoundException {
        String imgUrl1 = "";  //本地头像地址
        String imgUrl2 = "";  //云端头像地址
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

        imgUrl1 = uploadImgBase64(base64Data,path,request);

        File file = new File(imgUrl1);
        imgUrl2 = cloudService.cloudUpload(file,request);

        ApiResult apiResult = new ApiResult();
        if(imgUrl2.length() > 10){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setData(imgUrl2);

        }else if(imgUrl1.length() > 10){

            imgUrl1 = imgUrl1.replace(this.path,basePath+"img/");
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
            apiResult.setData(imgUrl1);

        }else  {
            apiResult.setCode(ApiCode.error_pic_upload);
            apiResult.setMsg(ApiCode.error_pic_upload_MSG);
        }

        return apiResult;

    }


    @GetMapping("/getUserInfo")
    @Log(name = "从云端查询用户信息")
    public void getUserInfo(HttpServletRequest request){
        cloudService.cloudGet(request);

    }

    /**
     * 查询学校的所有学段
     */
    @GetMapping("/findAllSchoolPeriod")
    @ApiOperation("查询学校的所有学段")
    @Log(name = "查询学校的所有学段")
    public ApiResult findAllSchoolPeriod(){

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(schoolPeriodService.findAllSchoolPeriod(schoolId));

        return apiResult;

    }

//    /**
//     * 查询学段下所有的年级
//     */
//    @GetMapping("/findAllGradeByPeriodId")
//    @ApiOperation("查询学段下所有的年级")
//    @Log(name = "查询学段下所有的年级")
//    @ApiImplicitParam(name = "periodId",value = "学段ID",required = true)
//    public ApiResult findAllGradeByPeriodId(String periodId){
//        ApiResult apiResult = new ApiResult();
//        apiResult.setCode(ApiCode.SUCCESS);
//        apiResult.setMsg(ApiCode.SUCCESS_MSG);
//        apiResult.setData(schoolPeriodService.findAllGradeByPeriodId(Long.parseLong(periodId)));
//
//        return apiResult;
//    }
//

    /**
     * 根据学段ID、查询所有的届次
     */
    @GetMapping("/findAllClassByGradeAndPeriodId")
    @ApiOperation("根据学段ID、查询所有的届次")
    @Log(name = "根据学段ID、查询所有的届次")
    @ApiImplicitParam(name = "periodId",value = "学段ID",required = true)
    public ApiResult findAllClassByGradeAndPeriodId(String periodId){

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(schoolPeriodService.findAllClassByGradeAndPeriodId(Long.parseLong(periodId)));
        return apiResult;

    }

    /**
     * 根据届次查询所有的班级信息
     */
    @PostMapping("/findAllSchoolClassByThetime")
    @ApiOperation("根据届次和学段ID查询所有的班级信息")
    @Log(name = "根据届次和学段ID查询所有的班级信息")
    public ApiResult findAllSchoolClassByThetime(
            @ApiParam(name = "map",value = "thetime=毕业时间-届次，periodId=学段ID")
                    @RequestBody Map map){

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(schoolPeriodService.findAllSchoolClassByThetime(map));
        return apiResult;


    }



}
