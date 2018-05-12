package com.cj.witbasics.controller;


import com.cj.witbasics.entity.AdminInfo;
import com.cj.witbasics.entity.DepartmentInfo;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.PersonnelManagementService;
import com.cj.witbasics.service.RemoteRequestService;

import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witcommon.entity.SynBasicInformation;
import com.cj.witcommon.utils.entity.other.Pager;

import io.swagger.annotations.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 人事管理
 */
@RestController
@RequestMapping("/api/v1/personnelManagement")
@Api(tags = {"人事管理"})
public class PersonnelManagementController {
    private static final Logger log = LoggerFactory.getLogger(PersonnelManagementController.class);
    @Autowired
    private PersonnelManagementService personnelManagementService;
    @Autowired
    RemoteRequestService remoteRequestService;

    @Autowired
    private CloudService cloudService;

    @Value("${server_port1}")
    String server_port1;

    @Value("${server_port2}")
    String server_port2;

    @Value("${server_path1}")
    String server_path1;

    @Value("${server_path2}")
    String server_path2;

    @Value("${web.upload-path}")
    String web_upload_path;

    @Value("${default_admin_pass}")
    String defaultAdminPass;


    //============================================人事信息维护 satrt======================================================================

    /**
     * 功能：根据部门和模糊姓名进行查询
     * 参数：部门，模糊姓名
     * 返回：admin_inf对象集合
     * 完成时间：2小时
     * 实现：创建AdminInf对象，通过部门和模糊姓名搜索 ，把id, admin_id 姓名，身份证号，教职工编号，出生年月，性别，手机号放入对象，返回对象结果集
     */
//    @ApiOperation("根据部门名称和姓名查询信息==暂时废弃不用")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "theDepartment",value = "部门名称",required = true),
//            @ApiImplicitParam(name = "name",value = "姓名",required = true),
//            })
//    @GetMapping("/selectByDepartAndName")
//    public ApiResult selectByDepartAndName(String theDepartment ,String name){
//        AdminInfo adminInfo=new AdminInfo();
//        adminInfo.setTheDepartment(theDepartment);
//        adminInfo.setFullName(name);
//        List<AdminInfo> adminInfos = personnelManagementService.selectByDepartAndName(adminInfo);
//        //将查询到的信息放入结果集
//        ApiResult<List<AdminInfo>> listApiResult=new ApiResult<>();
//        listApiResult.setData(adminInfos);
//        return listApiResult;
//    }


    /**
     * 功能：生成教职工编号
     * 参数：
     * 返回：一个新的教职工编号
     * 实现:通过admin_inf表查询到职工编号最大值，返回编号+1
     * 完成时间：1小时
     */
    @ApiOperation("生成教职工编号")
    @GetMapping("/createIdentifier")
    @Log(name = "人事 ==> 生成教职工编号")
    public ApiResult createIdentifier(){
        ApiResult<String> apiResult=new ApiResult<>();
        apiResult.setData(personnelManagementService.productMaxStaffNumber());
        return apiResult;
    }


    /**
     * 功能：新增教职工信息
     * 参数：admin_inf对象, 创建人id
     * 返回：成功/失败
     * 完成时间：3小时
     * 实现：同时生成账号（校验是否同步）管理员表admin 角色id不管（固定的）
     *       人事详情表admin_info    :放入人员信息，生成表单id. 管理员id 用户UUID；放入创建人id  操作员id
     *
     */
    @ApiOperation("新增教职工信息(同时生成账号)")
    @PostMapping("/addStaff")
    @Log(name = "人事 ==> 新增教职工信息(同时生成账号)")
    public ApiResult addStaff(
            @ApiParam(name = "adminInfo",value = "教职工详情,封装为adminInfo,账号分类，1-管理员，2-用户（老师、学生、家长、其他）",required = true)
            @RequestBody AdminInfo adminInfo,
            HttpServletRequest request)  {

        String adminType = adminInfo.getAdminType();

        //创建返回数据结果集
        ApiResult apiResult=new ApiResult();
        int i = 0;
        i  = personnelManagementService.createAdminInfo(adminInfo,request,adminType);
        if (i>0){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }else {
            apiResult.setCode(ApiCode.FAIL);
            apiResult.setMsg(ApiCode.FAIL_MSG);
        }
        return apiResult;
    }

    /**
     * 功能：人事信息模板下载
     * 参数:
     * 返回：
     * 完成时间：1
     */
    @ApiOperation("人事信息模板下载")
    @GetMapping("/downloadTeacherTemplate")
    @Log(name = "人事 ==> 人事信息模板下载")
    public void downloadTeacherTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        personnelManagementService.downloadTeacherTemplate(request,response);

    }

    /**
     * 功能：批量导入教职工信息
     * 返回：成功/失败
     * 时间：5小时
     * 导入时同时生成账号（校验是否同步）
     *
     */
    @ApiOperation("批量导入教职工信息")
    @PostMapping("/loadingInStaffs")
    @Log(name = "人事 ==> 批量导入教职工信息")
    public ApiResult loadingInStaffs(@ApiParam(value = "教师导入（选择文件）",required = true) MultipartFile file,
                                     HttpServletRequest request) throws Exception {

        Map map = personnelManagementService.createAdminInfos(file,request);
        ApiResult apiResult=new ApiResult();

        int nums = (Integer) map.get("nums");
        List msgList = (ArrayList)map.get("msgList");
        if(nums > 0){
            apiResult.setCode(ApiCode.import_success);
            apiResult.setMsg(ApiCode.import_success_MSG+nums+"条数据");
            apiResult.setData(msgList);
        }else {
            apiResult.setCode(ApiCode.import_failed);
            apiResult.setMsg(ApiCode.import_failed_MSG);
            apiResult.setData(msgList);
        }
        return apiResult;
    }

//    /**
//     * 功能：删除教职工id
//     * 参数：adminIds（已查询出的所有信息表的管理员id），操作员id
//     * 返回：成功/失败
//     * 完成时间：2小时
//     * 删除的表有：
//     admin
//     admin_info
//
//     */
//    @ApiOperation("批量删除教职工信息及账号及本门信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "adminIds",value = "教职工id",required = true)
//    })
//    @DeleteMapping("/deleteStaff")
//    public ApiResult deleteStaff(@ApiParam(name = "adminIds",value = "adminId集合",required = true)@RequestBody List<Long> adminIds,HttpServletRequest request){
//        ApiResult apiResult=new ApiResult();
//        Integer result = personnelManagementService.deleteStaff(adminIds, request);
//        return ApiResultUtil.fastResult(apiResult, result);
//    }

    /**
     * 功能：编辑信息前查询信息(通过admin_id查询人事信息详情)
     * 参数：admin_id
     * 返回：admin_info对象
     * 完成时间：
     */
    @ApiOperation("根据 adminId 查询 adminInfo 信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="adminId",value = "用户ID"),
    })
    @GetMapping("/selectAdminInfo")
    @Log(name = "人事 ==> 查询人事详情")
    public ApiResult selectAdminInfo(Long adminId){

        ApiResult<AdminInfo> apiResult=new ApiResult<>();

        AdminInfo result = personnelManagementService.selectAdminInfoById(adminId);

        ApiResultUtil.fastResult(apiResult,result);
        return apiResult;
    }

    /**
     * 功能：编辑教职工信息
     * 参数：AdminInfo对象（adminInfo 对象里必须要有 admin_id主键,admin_info_id）
     * 返回：成功/失败
     * 完成时间：
     */
    @ApiOperation("更新教职工信息，根据adminId")
    @PutMapping("/updateStaffInfo")
    @Log(name = "人事 ==> 更新教职工信息")
    public ApiResult updateStaffInfo(
            @ApiParam(name ="AdminInfo",value = "教职工详情表") @RequestBody AdminInfo adminInfo,
            HttpServletRequest request){
        ApiResult apiResult=new ApiResult();
        int i=0;



            log.info("adminInfo={}",adminInfo);
            i = personnelManagementService.updateAdminInfo(adminInfo,request);
            if(i == -1){
                apiResult.setCode(ApiCode.error_update_failed);
                apiResult.setMsg(ApiCode.error_update_failed_MSG);
            }else {
                apiResult.setCode(ApiCode.SUCCESS);
                apiResult.setMsg(ApiCode.SUCCESS_MSG);

            }
        return apiResult;
    }

    /**
     * 模糊查询能够担任部门领导的人员信息（ID/name)
     */
    @ApiOperation("模糊查询能够担任部门领导的人")
    @Log(name = "人事 ==> 模糊查询能够担任部门领导的人")
    @PostMapping("/findAllDepartmentalLeadership")
    public ApiResult findAllDepartmentalLeadership(@ApiParam(name = "p",value = "parameter=模糊查询需要的姓名",required = false)
                                                   @RequestBody Pager p){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiCode.SUCCESS);
        apiResult.setMsg(ApiCode.SUCCESS_MSG);
        apiResult.setData(personnelManagementService.findAllDepartmentalLeadership(p));

        return apiResult;

    }


    /**
     * 功能:分页、模糊（parameters.vague=姓名、教职工编号）、条件（parameters.theDepartment=部门）
     *      查询人事信息
     * 参数:Pager
     * 返回:ID、姓名、身份证号、教职工编号、出生年月、性别、手机号
     * 完成时间:
     */
    @ApiOperation("分页、模糊、条件查询教职工信息，vague=姓名、教职工编号、theDepartment=部门，封装到parameters（map）里面")
    @PostMapping("/selectAdminInfoByVague")
    @Log(name = "人事 ==> 查询教职工信息")
    public ApiResult selectAdminInfoByVague(
            @ApiParam(name = "p",value = "vague=姓名、教职工编号、theDepartment=部门，封装到 parameters（map）里面",required = false) @RequestBody Pager p){
        ApiResult apiResult=new ApiResult();
        p=personnelManagementService.selectAdminInfoByVague(p);
        return ApiResultUtil.fastResult(apiResult, p);
    }


//============================================人事信息维护 end======================================================================
//    /**
//     * 功能：修改密码
//     * 参数：新密码String,管理员id
//     * 返回：成功/失败
//     * 完成时间：
//     */
//    @ApiOperation("有权限的管理员修改密码==暂时无用??")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name ="adminPass",value = "新密码"),
//            @ApiImplicitParam(name="adminIds",value = "被修改密码的管理员ID数组")
//    })
//    @PostMapping("/updatePassword")
//    public ApiResult updatePassword(String adminPass,Long[] adminIds){
//        ApiResult apiResult=new ApiResult();
//        Integer result = personnelManagementService.updateAdminPass(adminIds, adminPass);
//
//        return ApiResultUtil.fastResult(apiResult, result);
//    }
//
//    /**
//     * 功能：锁定当前教职工信息
//     * 参数：admin_id(教职工id)
//     * 返回：锁定成功/失败
//     * 完成时间：
//     */
//    @ApiOperation("锁定当前教职工信息==暂时无用")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name ="adminId",value = "管理员id"),
//    })
//    @PostMapping("/lockingAdminInfo")
//    public ApiResult lockingAdminInfo(Long adminId){
//        ApiResult apiResult=new ApiResult();
//        Integer result = personnelManagementService.lockingAdminInfo(adminId);
//        return ApiResultUtil.fastResult(apiResult, result);
//    }





    //==============================================部门信息维护 start=======================================================================

    /**
     * 功能：新增部门
     * 参数：department对象,创建人id
     * 返回：成功/失败
     * 完成时间：
     */
    @ApiOperation("新增部门")
    @PostMapping("/addDepartment")
    @Log(name = "部门 ==> 新增部门")
    public ApiResult addDepartment(HttpServletRequest request,
                                   @ApiParam(name ="DepartmentInfo",value = "部门名称=dName,部门编号=dNumber，" +
                                           "有效性，0-无效，1-有效=dEffectiveness，部门排序=dSort，部门领导ID=dLeaderId，部门领导=dLeader，" +
                                           "所属部门=iddAttribute 如无则为0，学校id=schoolId",required = true)
                                           @RequestBody DepartmentInfo departmentInfo){
        System.out.println(departmentInfo.getdName());




        ApiResult apiResult=new ApiResult();

        Integer result = personnelManagementService.addDepartmentInfo(departmentInfo,request);
        return  ApiResultUtil.fastResult(apiResult, result);
    }

    /**
     * 功能：根据（部门ID、姓名、教职工编号） 分页查询部门人员信息
     * 参数：模糊条件=单个参数
     * 返回 Admin_Info对象结果集 包含：人事详情表id, 姓名、所属部门、邮箱、电话、地址
     * 完成时间：
     */
    @ApiOperation("根据部门ID，姓名 分页、条件查询 部门人员信息（部门下所有人）//currentPage（初始页码1），pageSize（初始条数10），(parameters k-v 方式存储多个参数)部门ID,姓名,可为空")
    @PostMapping("/selectAdminInfoByDepartment")
    @Log(name = "部门 ==> 查询部门人员信息")
    public ApiResult selectAdminInfoByDepartment(
            @ApiParam(name = "p",value = "分页参数",required = false) @RequestBody  Pager p){
        ApiResult apiResult =new ApiResult();
        p = personnelManagementService.selectAdminInfoByDepartment(p);

        return ApiResultUtil.fastResult(apiResult, p);
    }

    /**
     * 功能：根据（部门ID、姓名、教职工编号） 分页查询部门人员信息
     * 参数：模糊条件=单个参数
     * 返回 Admin_Info对象结果集 包含：人事详情表id, 姓名、所属部门、邮箱、电话、地址
     * 完成时间：
     */
    @ApiOperation("根据部门ID，姓名 分页、条件查询 人员信息（所有人,不管有没有部门）//currentPage（初始页码1），pageSize（初始条数10），(parameter 可为)部门ID=id,姓名=fullName,可为空")
    @PostMapping("/selectAdminInfoAndDepartment")
    @Log(name = "部门 ==> 查询人员信息")
    public ApiResult selectAdminInfoAndDepartment(
            @ApiParam(name = "p",value = "分页参数",required = false) Pager p){
        ApiResult apiResult =new ApiResult();
        p = personnelManagementService.selectAdminInfoAndDepartment(p);

        return ApiResultUtil.fastResult(apiResult, p);

    }


    /**
     * 功能：向部门里添加员工
     * 参数:admin_info_id, 当前部门名称the_department
     * 返回:成功/失败
     * 完成时间
     *
     *
     * 功能：修改职工所在部门
     * 参数：人事信息表ID admin_info_id,部门名称，操作员id
     * 返回:成功/失败
     * 完成时间:
     */
    @ApiOperation("批量修改职工所在部门")
    @PutMapping("/updateStaffDepartment")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="adminIds",value = "adminId集合"),
            @ApiImplicitParam(name = "did",value = "新部门id"),
            @ApiImplicitParam(name ="theDepartment",value = "新部门名称")

    })
    @Log(name = "部门 ==> 批量修改职工所在部门")
    public ApiResult updateStaffDepartment( @RequestBody Map map,

            HttpServletRequest request){

        List<Integer> adminIds = (List<Integer>) map.get("adminIds");
        System.out.println("adminIds====>>"+adminIds);
        Long did = new Long((Integer)map.get("did"));
        System.out.println("did====>>"+did);
        String theDepartment = (String) map.get("theDepartment");
        System.out.println("theDepartment====>>"+theDepartment);

        List<Long> longs = new ArrayList<>();
        for (Integer adminId:adminIds){
            longs.add(new Long(adminId));
        }
        ApiResult apiResult=new ApiResult();
        Integer result=personnelManagementService.updateStaffDepartment(longs,did,theDepartment,request);
        return ApiResultUtil.fastResult(apiResult,result);
    }

    /**
     * 功能：查询部门信息
     * 参数：部门名称
     * 返回：Department对象 包含：部门名称，部门编号，成立时间 ，有效性，部门排序，部门领导，所属部门
     * 完成时间:
     */
    @ApiOperation("根据部门ID查询部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "部门ID")
    })
    @GetMapping("/selectDepartmentById")
    @Log(name = "部门 ==> 根据部门ID查询部门信息")
    public ApiResult selectDepartmentByDname(Long id){
        ApiResult apiResult=new ApiResult();
        DepartmentInfo departmentInfo=personnelManagementService.selectDepartmentInfoById(id);
        return ApiResultUtil.fastResult(apiResult,departmentInfo);
    }


    /**
     * 获取部门编号
     * 参数：无
     * 返回：编号
     * 时间：0.5h
     */
    @ApiOperation("获取部门编号")
    @GetMapping("/getDNumber")
    @Log(name = "部门 ==> 获取部门编号")
    public ApiResult getDNumber(){
        return ApiResultUtil.fastResult(new ApiResult(),personnelManagementService.getDNumber());
    }

    /**
     * 获取所有部门信息
     *
     */
    @ApiOperation("获取所有部门信息")
    @GetMapping("/findAllDepartmentInfo")
    @Log(name = "部门 ==> 获取所有部门信息")
    public ApiResult findAllDepartmentInfo(){

        return ApiResultUtil.fastResult(new ApiResult(),personnelManagementService.findAllDepartmentInfo());
    }

    /**
     * 修改部门信息
     * 参数：部门ID
     * 返回：成功、失败
     * 时间：0.3
     */
    @PutMapping("/updateDepartmentInfo")
    @ApiOperation("修改部门信息")
    @Log(name = "部门 ==> 修改部门信息")
    public ApiResult updateDepartmentInfo(
            @ApiParam(name = "departmentInfo",value = "部门信息，ID必传",required = true) DepartmentInfo departmentInfo){

        ApiResult apiResult = new ApiResult();
        int i = 0;
        if(departmentInfo.getId() != null || departmentInfo.getId() > 0){

            i = personnelManagementService.updateDepartmentInfo(departmentInfo);
        }

        if(i == -1){
            apiResult.setCode(ApiCode.FAIL);
            apiResult.setMsg("部门名称重复");

        }else if(i == 0){
            apiResult.setCode(ApiCode.error_update_failed);
            apiResult.setMsg(ApiCode.error_update_failed_MSG);
        }else {
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);
        }

        return apiResult;

    }

    /**
     * 删除部门
     */
    @ApiOperation("删除部门")
    @DeleteMapping("/deleteDepartmentInfo")
    @ApiImplicitParam(name = "id",value = "部门ID",required = true)
    @Log(name = "部门 ==> 删除部门")
    public ApiResult deleteDepartmentInfo( Long id ){
        ApiResult apiResult = new ApiResult();
        int i = personnelManagementService.deleteDepartmentInfo(id);

        if(i > 0){
            apiResult.setCode(ApiCode.SUCCESS);
            apiResult.setMsg(ApiCode.SUCCESS_MSG);

        }else if( i==0 ){
            apiResult.setCode(ApiCode.error_delete_failed);
            apiResult.setMsg(ApiCode.error_delete_failed_MSG);
        }else {
            apiResult.setCode(ApiCode.FAIL);
            apiResult.setMsg("无法删除，部门下有人员");
        }


        return apiResult;

    }


    //==============================================部门信息维护 end=======================================================================



}
