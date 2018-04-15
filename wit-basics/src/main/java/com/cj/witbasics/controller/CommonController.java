package com.cj.witbasics.controller;

import com.cj.witbasics.service.CloudService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.utils.common.FileUtil;
import io.swagger.annotations.Api;
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
import java.io.*;
import java.util.UUID;

import static com.cj.witcommon.utils.common.FileUtil.uploadImgBase64;
import static com.cj.witcommon.utils.http.APIHttpClient.analyzeFile;


@RestController
@RequestMapping("/api/v1/common")
@Api(tags = "公用方法")
public class CommonController {

    @Value("${web.upload-path}")
    String path;

    @Autowired
    private CloudService cloudService;

    //单文件上传
    @ApiOperation("单文件上传（file）")
    @PostMapping("/uploadFile")
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
    public void getUserInfo(HttpServletRequest request){
        cloudService.cloudGet(request);

    }
}
