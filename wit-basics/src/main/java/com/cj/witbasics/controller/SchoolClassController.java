package com.cj.witbasics.controller;

import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witbasics.entity.SchoolClass;
import com.cj.witbasics.entity.SchoolClassType;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;
import com.cj.witbasics.service.SchoolClassService;
import com.cj.witbasics.service.SchoolPeriodService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.TimeToString;
import com.cj.witcommon.utils.common.FileUtil;
import com.cj.witcommon.utils.common.StringHandler;
import com.cj.witcommon.utils.entity.other.Pager;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 班级信息相关业务
 */
@Api(tags = "班级信息管理")
@RestController
@RequestMapping("/api/v1/schoolclass")
public class SchoolClassController {

    private static final Logger log = LoggerFactory.getLogger(SchoolClassController.class);

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private SchoolPeriodService periodService;

    @Value("${web.upload-path}")
    private String path; //文件下载路径，配置文件

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
     *  功能描述：模版下载(excel)
     *  参数：HttpServletResponse response
     *  返回：指定路径内的Excel模版文件
     *  时间：2
     */
    @ApiOperation(value = "模版下载", notes = "返回指定路径内的Excel模版文件")
    @Log(name = "班级导入模版下载")
//    @ApiImplicitParam(name = "response", value = "响应流")
    @GetMapping("/downLoadExcel")
    public void downLoadExcel(HttpServletResponse response, HttpServletRequest request){
        //TODO:获取文件名
        //TODO:判断文件后缀
        String fileName = "schoolClass.xls";
        try {
            new FileUtil().download(request, response, this.path, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  功能描述：Excel导入班级信息(上传文件，文件解析，数据的插入)
     *  参数：Excel文件
     *  返回：导入成功或失败
     *  时间：8
     */
    @ApiOperation("导入班级信息")
    @Log(name = "导入班级信息")
    @ApiParam(value = "班级信息导入（选择文件）",required = true)
    @PostMapping("/bathImportInfo")
    public ApiResult bathImportInfo(MultipartFile file){

        //TODO:获取操作人ID
        Long operatorId = 11L;
        //返回对象
        ApiResult apiResult = new ApiResult();
        //TODO:校验文件是否为空
        if(file == null) {
            ApiResultUtil.fastResultHandler(apiResult, false, ApiCode.FAIL, ApiCode.FAIL_MSG, null); //处理失败
            return apiResult;
        }
        try{
            //获取文件流
            InputStream in = file.getInputStream();
            apiResult = this.schoolClassService.bathImportInfo(file, in, operatorId);
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.FAIL, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }

    /**
     *  功能描述：新增班级类型
     *  参数：学校ID， 班级Id，类型名称
     *  返回：新增成功或失败
     *  时间：2 小时
     */
    @ApiOperation(value = "添加班级类型", notes = "返回成功或失败")
    @Log(name = "添加班级类型")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "classTypeName", value = "班级类型名称", dataType = "String")
            @ApiImplicitParam(name = "classType", value = "班级类型名称")
    })
    @PostMapping("/addClassType")
    public ApiResult addClassType(/*Long schoolId, */@RequestBody List<String> classType){
        //TODO:session里面获取学校ID
        /*String classTypeName*/
        Long schoolId = toLong();
        System.out.println(schoolId + " 学校ID");
//        for(String type : classType){
//            System.out.println(type);
//        }
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            //根据班级类型名称，查重，Service层判断,返回状态
            boolean result = this.schoolClassService.addClassType(schoolId, classType);
//            boolean result = false;
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
     *  功能描述：修改班级类型名称
     *  参数：班级类型ID, 新的班级类型名称
     *  返回：修改成功或失败
     *  时间：2小时
     */
    @ApiOperation(value = "修改班级类型名称", notes = "返回成功或失败")
    @Log(name = "修改班级类型名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classTypeId", value = "班级类型Id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "classTypeName", value = "班级类型名称", required = true, dataType = "String"),
    })
    @PutMapping("/updateClassType")
    public ApiResult updateClassType(Integer classTypeId, String classTypeName){

        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.schoolClassService.updateClassType(classTypeId, classTypeName);
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
     *  功能描述：删除班级类型
     *  参数：班级类型ID
     *  返回：修改成功或失败
     *  时间：2小时
     */
    @ApiOperation(value = "删除班级类型", notes = "返回成功或失败")
    @ApiImplicitParam(name = "classTypeId", value = "班级类型Id", required = true)
    @Log(name = "删除班级类型")
    @DeleteMapping ("/updateClassTypeDel")
    public ApiResult updateClassTypeDel(Integer classTypeId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            System.out.println(classTypeId + "   班级类型");
            boolean result = this.schoolClassService.updateClassTypeDel(classTypeId);
//            boolean result = false;
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
     *  功能描述：新增班级层次
     *  参数：学校ID， 班级Id，类型名称
     *  返回：新增成功或失败
     *  时间：2 小时
     */
    @ApiOperation(value = "添加班级层次", notes = "返回成功或失败")
    @Log(name = "添加班级层次")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "schoolId", value = "学校Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "classLevel", value = "班级层次名")
    })
    @PostMapping("/addClassLevel")
    public ApiResult addClassLevel(/*Long schoolId,*/@RequestBody List<String> classLevel){
        //TODO:从session里面取出学校ID
        ApiResult apiResult = new ApiResult(); //返回对象
        Long schoolId = toLong();
        try{
            boolean result = this.schoolClassService.addClassLevel(schoolId, classLevel);
//            boolean result = false;
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
     *  功能描述：修改班级层次
     *  参数： 班级层次Id, 新的班级层次名
     *  返回：修改成功或失败
     *  时间：2小时
     */
    @ApiOperation(value = "修改班级层次", notes = "返回成功或失败")
    @Log(name = "修改班级层次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classLevelId", value = "班级层次Id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "classlevelName", value = "新的班级层次名", required = true, dataType = "String")
    })
    @PutMapping("/updateClassLevel")
    public ApiResult updateClassLevel(Integer classLevelId, String classlevelName){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.schoolClassService.updateClassLevel(classLevelId, classlevelName);
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
     *  功能描述：删除班级层次
     *  参数： 班级层次ID
     *  返回：删除成功或失败
     *  时间：2小时
     */
    @ApiOperation(value = "删除班级层次", notes = "返回成功或失败")
    @Log(name = "删除班级层次")
    @ApiImplicitParam(name = "classLevelId", value = "班级层次ID", required = true, dataType = "int")
    @DeleteMapping("/deleteClassLevel")
    public ApiResult updateClassLevelDel(Integer classLevelId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.schoolClassService.updateClassLevelDel(classLevelId);
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
     *  功能描述：修改班级信息
     *  参数：班级信息实体对象
     *  返回：修改成功或失败
     *  时间：2小时
     *  未完成
     */
    @ApiOperation(value = "修改班级信息", notes = "返回成功或失败")
    @Log(name = "修改班级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classId", value = "班级ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "className", value = "班级名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "classHeadmaster", value = "班主任名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "classNumber", value = "班号", required = false, dataType = "int"),
            @ApiImplicitParam(name = "classType", value = "班级类型", required = false, dataType = "String"),
            @ApiImplicitParam(name = "classLevel", value = "班级层次", required = false, dataType = "String"),

            @ApiImplicitParam(name = "chineseId", value = "语文科目ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "chineseTea", value = "教师名称/教师编号", required = false, dataType = "String"),

            @ApiImplicitParam(name = "englishId", value = "英语科目ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "englishTea", value = "教师名称/教师编号", required = false, dataType = "String"),

            @ApiImplicitParam(name = "mathId", value = "数学科目ID", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "mathTea", value = "教师名称/教师编号", required = false, dataType = "String")
    })
//    @PutMapping("/updateClassInfo")
    public ApiResult updateSchoolClassInfo(/*@ApiParam(name = "schoolClass", value = "班级信息实体类", required = true) @RequestBody */
            /*@RequestParam(name = "schoolClass",value = "班级实体" )*/
            SchoolClass schoolClass,
            Long chineseId, Long englishId, Long mathId,
            String chineseTea, String englishTea, String mathTea){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.schoolClassService.updateSchoolClassInfo( schoolClass, chineseId, englishId, mathId,
                    chineseTea, englishTea, mathTea);
            System.out.println(result + "  控制层结果");
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
     *  功能描述：根据输入条件，查询对应班级信息（模糊条件查询）
     *  参数：班级名称，班主任名，班级号
     *  返回：班级信息集合
     *  时间：(2, 3) 小时
     *  //未完成，SQL语句
     */
    @ApiOperation(value = "学段-年级-模糊条件--->查询班级信息", notes = "班级信息集合")
    @Log(name = "学段-年级-模糊条件--->查询班级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "periodId", value = "学段Id", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "gradeId", value = "年级Id", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "vague", value = "模糊条件", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pager",value = "分页参数，初始页码1，初始条数10，可为空",required = false)
    })
//    @GetMapping("/findClassInfo")
    public ApiResult findSchoolClassInfo(Long periodId, Long gradeId, String vague, Pager pager){
        ApiResult apiResult = new ApiResult(); //返回对象
        //String className, String classHeadmaster, Integer classNumber
        try{
            Pager result = this.schoolClassService.findSchoolClassInfo(periodId, gradeId, vague, pager); //处理
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
     *  功能描述：根据输入条件，查询对应班级信息（模糊条件查询）
     *  参数：班级名称，班主任名，班级号
     *  返回：班级信息集合
     *  时间：(2, 3) 小时
     *  //未完成，SQL语句
     */
    @ApiOperation(value = "学段-届次-模糊条件--->查询班级信息", notes = "班级信息集合")
    @Log(name = "学段-届次-模糊条件---> 查询班级信息")
    @PostMapping("/findSchoolClassInfoUBW")
    public ApiResult findSchoolClassInfoUBW(@ApiParam(name = "p",value = "查询条件，parameters(periodId=学段Id,thetime=届次,vague=模糊条件) {\n" +
            "\n" +
            "  \"parameters\": {\"periodId\":9,\"thetime\":2019,\"vague\":\"2\"}\n" +
            "\n" +
            "}",required = true)
                                            @RequestBody Pager p){


        ApiResult apiResult = new ApiResult(); //返回对象
        //String className, String classHeadmaster, Integer classNumber
        try{
            Map map = p.getParameters();
            Integer periodId = null;
            String thetime = null;
            String vague = null;
            if(map.containsKey("periodId")){
                periodId = (Integer) map.get("periodId");
            }
            if (map.containsKey("thetime")){
                thetime = (Integer)map.get("thetime")+"";
                thetime += "-7-1";
                map.put("thetime", thetime);
                System.out.println(thetime + " 届次");
            }
            if(map.containsKey("vague")){
                vague = (String) map.get("vague");
            }
            Pager result = this.schoolClassService.findSchoolClassInfoUBW(periodId, thetime, vague, p); //处理
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
     *  功能描述：根据学段，返回学段下年级信息
     *  参数：学校ID
     *  返回：修改成功或失败
     *  时间：2
     */
    @ApiOperation(value = "查询学段下的年级信息", notes = "成功/失败")
    @Log(name = "学查询学段下的年级信息")
    @ApiImplicitParam(name = "periodId", value = "学段ID", required = true, dataType = "Long")
    @GetMapping("/findGradeInfo")
    public ApiResult findSchoolGradeInfo(Long periodId){
        //TODO:schoolIds
        Long schoolId = toLong();
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            System.out.println("进入" + "学段ID " + periodId);
            List<GradeInfo> result = this.periodService.findPeriodGradeInfo(schoolId, periodId);
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
     *  功能描述：根据学校ID，返回该学校下的班级层次，班级类型，学段
     *  参数：学校ID
     *  时间：2
     */
    @ApiOperation(value = "返回学段/班级类型/班级层次", notes = "成功/失败")
    @Log(name = "返回学段/班级类型/班级层次")
    @ApiImplicitParam(name = "schoolId", value = "学校(校区)ID", required = true, dataType = "Long")
//    @GetMapping("findLevelTypePeriod")
    public ApiResult findSchoolLevelTypePeriod(Long schoolId){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            List result = this.schoolClassService.findSchoolLevelTypePeriod(schoolId);
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
     *  功能描述：返回班级类型
     *  参数：学校ID
     *  时间：2
     */
    @ApiOperation(value = "返回班级类型", notes = "成功/失败")
//    @ApiImplicitParam(name = "schoolId", value = "学校(校区)ID", required = true, dataType = "Long")
    @GetMapping("/findType")
    public ApiResult findSchoolType(/*Long schoolId*/){
        Long schoolId = toLong();
        System.out.println(schoolId);
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            List result = this.schoolClassService.findSchoolLevelType(schoolId);
            System.out.println(result.size());
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
     *  功能描述：返回班级层次
     *  参数：学校ID
     *  时间：2
     */
    @ApiOperation(value = "返回班级层次", notes = "成功/失败")
//    @ApiImplicitParam(name = "schoolId", value = "无参数", required = false, dataType = "Long")
    @GetMapping("/findLevel")
    public ApiResult findSchoolLevel(/*Long schoolId*/){
        Long schoolId = toLong();
        System.out.println(schoolId);
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            List result = this.schoolClassService.findSchoolLevel(schoolId);
            System.out.println(result.size());
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



    /**************************************************************************/




    @ApiOperation(value = "返回届次", notes = "成功/失败")
    @ApiImplicitParam(name = "periodId", value = "学段ID", required = true, dataType = "Long")
    @Log(name = "班级管理 ==> 返回届次")
    @GetMapping("/findTheTime")
    public ApiResult findTheTime(Long periodId){
        ApiResult apiResult = new ApiResult(); //返回对象
        List result = this.schoolClassService.findTheTime(periodId);
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setData(ApiCode.SUCCESS_MSG);
        apiResult.setData(result);
        return apiResult;
    }




    /**
     *  功能描述：修改班主任ID
     *  参数：班级ID, 班主任ID
     *  返回：
     */
    @ApiOperation("编辑班主任")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classId", value = "班级ID", required = true),
            @ApiImplicitParam(name = "adminId", value = "班主任ID" , required = true),
            @ApiImplicitParam(name = "fullName", value = "班主任名字", required = true)
    })
    @Log(name = "编辑班主任")
    @PutMapping("/updateHeadmaster")
    public ApiResult updateHeadmasterId(Long classId, Long adminId, String fullName, HttpServletRequest request){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
//            Long operatorId_ = (Long) request.getSession().getAttribute("adminId");
            Long operatorId_ = 1L;
            SchoolPeriodClassThetime classThetime = new SchoolPeriodClassThetime();
//            //填充
            classThetime.setOperatorId(operatorId_);
            classThetime.setClassId(classId);
            classThetime.setAdminId(adminId);
            classThetime.setHeadmaster(fullName);
            System.out.println(fullName);
            boolean result = this.schoolClassService.updateHeadmasterId(classThetime);
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
     *  功能描述：修改年级主任
     *  参数：班级ID, 年级主任ID
     *  返回：
     */
    @ApiOperation("编辑年级主任")
    @PutMapping("/updateDirector")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "directorId", value = "年级主任ID", required = true),
            @ApiImplicitParam(name = "periodId", value = "学段ID" , required = true),
            @ApiImplicitParam(name = "theTime", value = "届次", required = false)
    })
    @Log(name = "编辑年级主任")
    public ApiResult updateDirectorId(Long directorId, Long periodId, String theTime, HttpServletRequest request){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
//            Long adminId = (Long) request.getSession().getAttribute("adminId");
            Long adminId = 1L;
            //填充
            PeriodDirectorThetime param = new PeriodDirectorThetime();
            param.setPeriodId(periodId);
            param.setDirectorId(directorId);
            //日期转换
            theTime += "-6-1";
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(theTime);
            param.setThetime(date);
            System.out.println(param.toString());
            boolean result = this.schoolClassService.updateDirectorId(param, adminId);
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
     *  功能描述：查询无班主任的班班级
     *  参数：无
     *  返回：
     */
    @ApiOperation(value = "返回没有班主任的班级", notes = "成功/失败")
//    @ApiImplicitParam(name = "schoolId", value = "无参数", required = false, dataType = "Long")
    @Log(name = "返回没有班主任的班级")
    @GetMapping("/findClassNoHeadmaster")
    public ApiResult findClassNoHeadmaster(){
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
//            List result = this.schoolClassService.findSchoolLevelTypePeriod(schoolId);
            List result = this.schoolClassService.findClassNoHeadmaster();
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
     *  功能描述：清空年级主任
     *  参数：无
     *  返回：
     */
    @ApiOperation(value = "清空年级主任", notes = "返回成功或失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "directorId", value = "年级主任ID", required = true),
            @ApiImplicitParam(name = "periodId", value = "学段ID" , required = true),
            @ApiImplicitParam(name = "theTime", value = "届次", required = false)
    })
    @Log(name = "清空年级主任")
    @DeleteMapping ("/updateDirectorDel")
    public ApiResult updateDirector(Long directorId, Long periodId, String theTime){
        ApiResult apiResult = new ApiResult(); //返回对象
        //            Long adminId = (Long) request.getSession().getAttribute("adminId");
        Long adminId = 1L;
        //填充
        try{
            //日期转换
            theTime += "-7-1";
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(theTime);
            boolean result = this.schoolClassService.updateDirector(directorId, adminId, periodId, date);
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
     *  功能描述：清空年级主任
     *  参数：无
     *  返回：
     */
    @ApiOperation(value = "清空班主任", notes = "返回成功或失败")
    @ApiImplicitParam(name = "classId", value = "班级Id", required = true)
    @Log(name = "清空班主任")
    @DeleteMapping ("/updateHeadmasterDel")
    public ApiResult updateHeadmaster(Long classId){
        ApiResult apiResult = new ApiResult();//返回对象
        //            Long adminId = (Long) request.getSession().getAttribute("adminId");
        Long adminId = 1L;
        try{
            boolean result = this.schoolClassService.updateHeadmaster(classId, adminId);
            System.out.println(result + " haha ");
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


    /***********************************************************************************************/
    /**************************************新增逻辑**************************************************/
    /***********************************************************************************************/

    @ApiOperation("查询角色为班主任,并显示麾下是否有班级")
    @Log(name = "查询角色为班主任")
    @GetMapping("/findIsHeadmaster")
    @ApiImplicitParam(name = "vague",value = "班主任名字/编号",required = false)
    public ApiResult findHasPowerForHeadmaster(String vague, Pager pager){

        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        Pager pager2 = this.schoolClassService.findHasPowerForHeadmaster(vague, pager);
        a.setData(pager2);
        return a;
    }

    @ApiOperation("查询角色为年级主任,并显示是否分管年级")
    @Log(name = "查询角色为年级主任")
    @GetMapping("/findIsDirector")
    @ApiImplicitParam(name = "vague",value = "年级主任名字/编号",required = false)
    public ApiResult findHasPowerForDirector(String vague, Pager pager){
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        Pager p = this.schoolClassService.findHasPowerForDirector(vague, pager);
        a.setData(p);
        return a;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation(value = "添加班级", notes = "返回成功或失败")
    @Log(name = "添加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "className", value = "班级名称", required = true),
            @ApiImplicitParam(name = "classAbbreviation", value = "班级简称"),
            @ApiImplicitParam(name = "classNumber", value = "班级号"),
            @ApiImplicitParam(name = "classCampus", value = "校区"),
            @ApiImplicitParam(name = "classTypeId", value = "班级类型ID"),
            @ApiImplicitParam(name = "classLevelId", value = "班级层次ID"),
            @ApiImplicitParam(name = "classYear", value = "入学年度"),
            @ApiImplicitParam(name = "classPeriodId", value = "学段Id")
    })
    @PostMapping("/addClassInfo")
    public ApiResult addClassInfo(SchoolClass info, HttpServletRequest request){
        //TODO:session里面获取学校ID
        Long schoolId = toLong();
        info.setSchoolId(schoolId);
        Long adminId = (Long)request.getSession().getAttribute("adminId");
        info.setOperatorId(adminId);
        info.setFounderId(adminId);
        //入学年度
        System.out.println(info.toString());
        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            apiResult = this.schoolClassService.addClassInfo(info);
            return apiResult;
        }catch (Exception e){ //异常处理
            ApiResultUtil.fastResultHandler(apiResult, false,
                    ApiCode.error_create_failed, ApiCode.error_unknown_database_operation_MSG, null);
            e.printStackTrace();
        }
        return apiResult;
    }


    @ApiOperation(value = "删除班级", notes = "返回成功或失败")
    @Log(name = "删除班级")
    @ApiImplicitParam(name = "classId", value = "班级ID", required = true)
    @DeleteMapping("/updateClassInfoDel")
    public ApiResult updateClassInfoDel(Long classId, HttpServletRequest request){
        ApiResult apiResult = new ApiResult(); //返回对象
        Long adminId = (Long)request.getSession().getAttribute("adminId");
        try{
            boolean result = this.schoolClassService.updateClassInfoDel(classId, adminId);
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
     *  功能描述：修改班级信息
     *  参数：班级类型ID, 新的班级类型名称
     *  返回：修改成功或失败
     *  时间：2小时
     */
    @ApiOperation(value = "修改班级信息", notes = "返回成功或失败")
    @Log(name = "修改班级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classId", value = "班级Id", required = true),
            @ApiImplicitParam(name = "className", value = "班级名称"),
            @ApiImplicitParam(name = "classAbbreviation", value = "班级简称"),
            @ApiImplicitParam(name = "classNumber", value = "班级号"),
            @ApiImplicitParam(name = "classCampus", value = "校区"),
            @ApiImplicitParam(name = "classTypeId", value = "班级类型ID"),
            @ApiImplicitParam(name = "classLevelId", value = "班级层次ID"),
            @ApiImplicitParam(name = "classYear", value = "入学年度"),
            @ApiImplicitParam(name = "classPeriodId", value = "学段Id")
    })
    @PutMapping("/updateClassInfo")
    public ApiResult updateClassInfo(SchoolClass schoolInfo){

        ApiResult apiResult = new ApiResult(); //返回对象
        try{
            boolean result = this.schoolClassService.updateClassInfo(schoolInfo);
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
}
