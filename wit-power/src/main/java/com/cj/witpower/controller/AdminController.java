package com.cj.witpower.controller;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.RemoteRequestService;
import com.cj.witcommon.aop.Log;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witpower.service.AdminService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XD on 2017/12/6.
 * 用户信息处理、角色管理、权限管理
 */

@RestController
@RequestMapping(value = "/api/v1/admin")
@Api(tags = {"注册、登录、注销、角色、权限管理业务"})
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;

    @Autowired
    private RemoteRequestService remoteRequestService;

    @Autowired
    private CloudService cloudService;





    /*=========================================== 人员管理 start ========================================================*/

    /**
     * 添加用户
     * @param //admin 实体类
     * @return
     */
//    @PostMapping("/addAdmin")
//    @ApiOperation("添加账户,此处暂时不用，没地方维护账户详情")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "adminName",value = "用户名",required = true),
//            @ApiImplicitParam(name = "adminPass",value = "密码",required = true),
//            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true),
//            @ApiImplicitParam(name = "adminType",value = "1-管理员，2-用户",required = true)
//    })
//    public ApiResult addAdmin(HttpServletRequest request,
//                              String adminName,
//                              String adminPass,
//                              Long roleId,
//                              String adminType){
//        Admin admin = new Admin();
//        admin.setAdminName(adminName);
//        admin.setAdminPass(adminPass);
//        admin.setRoleId(roleId);
//        admin.setAdminType(adminType);
//
//        ApiResult a = new ApiResult();
//        int i = 0;
//        //检查用户名是否已存在
//        if(adminService.findAdminByName(admin.getAdminName()) > 0){ //此用户名已存在
//            a.setCode(ApiCode.user_exist);
//            a.setMsg(ApiCode.user_exist_MSG);
//            return a;
//        }
//
//        AdminRole adminRole = adminService.findAdminRoleByAdminRoleId(admin.getRoleId());
//
//
//        //返回true,则继续注册
//        if(cloudService.cloudRegisterPM(request,admin,adminRole)){
//            i = adminService.addAdmin(admin);
//        }
//
//        if( i == ApiCode.SUCCESS){
//            a.setCode(ApiCode.SUCCESS);
//            a.setMsg(ApiCode.SUCCESS_MSG);
//        }else {
//            a.setCode(ApiCode.FAIL);
//            a.setMsg(ApiCode.FAIL_MSG);
//
//        }
//
//        return a;
//
//    }


//    //删除用户，将State改为0,根据ID
//    @DeleteMapping("/deleteAdmin")
//    @ApiOperation("删除用户")
//    @ApiImplicitParam(name = "id",value = "用户ID",required = true)
//    public ApiResult deleteAdmin(Long id){
//        Admin admin = new Admin();
//        admin.setId(id);
//
//
//        admin.setAdminState("0");
//        int i = adminService.updateAdmin(admin);
//
//        ApiResult a = new ApiResult();
//        if(i>0){
//            a.setCode(ApiCode.SUCCESS);
//            a.setMsg(ApiCode.SUCCESS_MSG);
//        }else {
//            a.setCode(ApiCode.error_delete_failed);
//            a.setMsg(ApiCode.error_delete_failed_MSG);
//        }
//        return a;
//    }

    /**
     * 管理员批量删除
     * 参数：LIST<adminId>
     * 返回：成功、失败
     * @param //request
     * @return
     */

    @ApiOperation("批量删除账户及教职工详情及人员-部门关系")
    @Log(name = "批量删除账户及教职工详情及人员-部门关系")
    @DeleteMapping("/updateAdminsAndInfo")
    public ApiResult updateAdminsAndInfo(
            @ApiParam(name = "adminIds",value = "账户ID集合",required = true) @RequestBody Map map){

        List<Long> adminIdList = (List)map.get("adminIds");

        int i = adminService.updateAdmins(adminIdList);

        ApiResult a = new ApiResult();
        if(i > 0){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else {
            a.setCode(ApiCode.error_delete_failed);
            a.setMsg("教职工信息"+ApiCode.error_delete_failed_MSG);
        }
        return a;
    }
    @ApiOperation("批量删除学生及详情表信息")
    @Log(name = "批量删除学生及详情表信息")
    @DeleteMapping("/updateAdminsAndStudents")
    public ApiResult updateAdminsAndStudents(
            @ApiParam(name = "adminIds",value = "账户ID集合",required = true) @RequestBody Map map){
        List<Long> adminIdList = (List)map.get("adminIds");

        int i = adminService.updateAdminsAndStudents(adminIdList);

        ApiResult a = new ApiResult();
        if(i > 0){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else {
            a.setCode(ApiCode.error_delete_failed);
            a.setMsg("学生信息"+ApiCode.error_delete_failed_MSG);
        }
        return a;
    }

    //禁用(adminState=-1)、取消禁用用户、修改用户信息

    @PutMapping("/updateAdmin")
    @ApiOperation("禁用(adminState=-1)、取消禁用用户")
    @Log(name = "锁定、取消锁定用户")
    public ApiResult updateAdmin(
            @ApiParam(name = "id",value = "用户ID",required = true) @Validated Long id,
            @ApiParam(name = "roleId",value = "用户角色ID",required = false) @Validated Long roleId,
            @ApiParam(name = "adminState",value = "-1，禁用此账号，1，恢复账号正常使用",required = false) @Validated String adminState){

        Admin admin = new Admin();
        admin.setId(id);
        admin.setRoleId(roleId);
        admin.setAdminState(adminState);


        System.out.println(admin);
        if(!(admin.getAdminState().equals("-1") || admin.getAdminState().equals("1"))){
            admin.setAdminState("-1");
        }
        admin.setUpdateTime(new Date());
        int i = adminService.updateAdmin(admin);

        ApiResult a = new ApiResult();
        if(i>0){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else {
            a.setCode(ApiCode.error_update_failed);
            a.setMsg(ApiCode.error_update_failed_MSG);
        }
        return a;
    }


    /**
     * 分页查询所有用户信息
     * @param p
     * @return
     */
    @GetMapping("/findAllAdmin")
    @ApiOperation("分页查询所有用户账户信息")
    @Log(name = "分页查询所有用户账户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "currentPage",value = "页码",required = false,dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "Integer")
//    })
    @ApiImplicitParam(name = "p",value = "分页参数，currentPage（初始页码1），pageSize（初始条数10），可为空",required = false)
    public ApiResult findAllAdmin(Pager p){

        System.out.println("p==>"+p);

        p = adminService.findAllAdmin(p);
        ApiResult a = new ApiResult();

        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(p);
        return a;
    }


    /*========================================= 人员管理 end===========================================================*/


    /*========================================= 角色管理 start ========================================================*/
    //添加角色
    @PostMapping("/addRole")
    @ApiOperation("添加角色")
    @Log(name = "添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName",value = "角色名称",required = true),
            @ApiImplicitParam(name = "type",value = "角色分类,角色分类，0：系统用户\n" +
                    "\t1: 系统管理员\n" +
                    "\t2: 学校管理员\n" +
                    "\t3: 老师\n" +
                    "\t4：学生\n" +
                    "\t5：家长\n" +
                    "\t6：其他",required = true)
    })
    public ApiResult addRole(String roleName,String type ){
        AdminRole adminRole = new AdminRole();
        adminRole.setRoleName(roleName);
        adminRole.setType(type);


        int i = adminService.addRole(adminRole);

        ApiResult a = new ApiResult();
        if(i==1){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i==0){
            a.setCode(ApiCode.error_create_failed);
            a.setMsg(ApiCode.error_create_failed_MSG);

        }else if(i==-1){
            a.setCode(ApiCode.role_exist);
            a.setMsg(ApiCode.role_exist_MSG);
        }
        return a;
    }



    //删除角色
    @DeleteMapping("/deleteRole")
    @ApiOperation("删除角色")
    @Log(name = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "角色ID",required = true)
    })
    public ApiResult deleteRole(Integer id){
        AdminRole adminRole = new AdminRole();
        adminRole.setId(id);


        //根据RoleId检查角色下是否有用户存在
        int i = adminService.updateRole(adminRole);
        ApiResult a = new ApiResult();
        if (i==0){
            a.setCode(ApiCode.error_delete_failed);
            a.setMsg(ApiCode.error_delete_failed_MSG);
        }else  if (i==1){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i==-1){
            a.setCode(ApiCode.role_admin_exist);
            a.setMsg(ApiCode.role_admin_exist_MSG);
        }
        return a;
    }

    //修改用户角色
    @PutMapping("/updateAdminRole")
    @ApiOperation("修改用户角色,此处角色ID调用接口查询")
    @Log(name = "修改用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true,dataType = "Long")
    })
    public ApiResult updateAdminRole(Long id,Long roleId){
        Admin admin = new Admin();
        admin.setId(id);
        admin.setRoleId(roleId);

        int i = adminService.updateAdmin(admin);
        ApiResult a = new ApiResult();

        if(i>0){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);

        }else {
            a.setCode(ApiCode.error_update_failed);
            a.setMsg(ApiCode.error_update_failed_MSG);
        }
        return a;
    }

    //分类查询正常使用的角色列表
    @GetMapping("/findAllRole")
    @ApiOperation("分类查询正常使用的角色列表")
    @Log(name = "分类查询正常使用的角色列表")
    @ApiImplicitParam(name = "type",value = "-1,查询所有未删除的角色， 0：系统用户 \t1: 系统管理员 \t2: 学校管理员 \t3: 老师 \t4：学生 \t5：家长 \t6：其他",required = false)
    public ApiResult findAllRole(String type){
        AdminRole adminRole = new AdminRole();
        adminRole.setType(type);

        List<AdminRole> adminRoles =  adminService.findAllRole(adminRole);
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(adminRoles);

        return a;
    }
    /*========================================= 角色管理 end ==========================================================*/


    /*========================================= 角色权限管理 start ==========================================================*/


    /**
     * map
     * @return
     * roleId 传入角色ID
     */
    //查询所有的正常使用的目录及页面及当前角色的权限ID集合
    @GetMapping("/findRoleModulars")
    @ApiOperation("角色权限管理,查询所有的正常使用的目录及页面及当前角色的权限ID集合")
    @Log(name = "查询所有的正常使用的目录及页面及当前角色的权限ID集合")
    @ApiImplicitParam(name = "roleId",value = "角色ID",required = true,dataType = "Long")
    public ApiResult findRoleModulars(Long roleId){

        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(adminService.findRoleModulars(roleId));
        return a;
    }

    /**
     *
     * @param //map
     * @return
     * roleId 要修改的角色ID
     * modulars 新的权限ID集合
     */
    //修改角色权限
    @PutMapping("/updateRoleModular")
    @ApiOperation("修改角色权限")
    @Log(name = "修改角色权限")
    @ApiImplicitParam(name = "map",value = "roleId 要修改的角色ID,modulars 新的权限ID集合",required = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true),
            @ApiImplicitParam(name = "modulars",value = "id字符串，逗号分隔",required = true)
    })
    public ApiResult updateRoleModular(Long roleId,String modulars){ //Map包括roleId modularId的集合

        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("modulars",modulars);

        int i = adminService.updateRoleModular(map);
        ApiResult a = new ApiResult();
        if(i==0){
            a.setCode(ApiCode.error_update_failed);
            a.setMsg(ApiCode.error_update_failed_MSG);
        }else {
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG+",下次登录生效");
        }
        return a;
    }

    /*========================================= 角色权限管理 end ==========================================================*/




    //登陆
    @PostMapping("/ifLogin")
    @ApiOperation("用户登录")
    @Log(name = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminName",value = "用户名",required = true),
            @ApiImplicitParam(name = "adminPass",value = "密码",required = true)
    })
    public ApiResult ifLogin(HttpServletRequest request, HttpServletResponse response,
                                     String adminName,
                                     String adminPass)
            throws IOException {
        ApiResult a = new ApiResult();
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        admin.setAdminPass(adminPass);

//        //检查用户是否已登录
//        String oldAdminName = (String) request.getSession().getAttribute("adminName");
//        if(oldAdminName != null && oldAdminName.length() > 0){
//            a.setCode(ApiCode.account_login);
//            a.setMsg(ApiCode.account_login_MSG);
//            return a;
//        }

        ApiResult apiResult = new ApiResult();

        Map map = new HashMap();
        if(cloudService.cloudLogin(admin,request)){
            map = adminService.findAdmin(request, admin);
        }

        //根据adminId查询账号类型
        Long adminRoleId = (Long)map.get("adminRoleId");
        Long adminId = (Long)map.get("adminId");
        AdminRole adminRole = adminService.findAdminRoleByAdminRoleId(adminRoleId);

        int i = (Integer) map.get("result");

        if (i == 1){

//            CookieTool.addCookie(response,"token",(String)request.getSession().getAttribute("Token"),30*60);

            Map map1 = new HashMap();
            map1.put("adminId",adminId);
            map1.put("adminRoleType",adminRole.getType());
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
            a.setData(map1);
            return a;
        }else if(i == -1 || i == 0){
            a.setCode(ApiCode.account_exist);
            a.setMsg(ApiCode.account_exist_MSG);
            return a;
        }else {
            a.setCode(ApiCode.FAIL);
            a.setMsg(ApiCode.FAIL_MSG);
            return a;
        }
    }




//    //用户修改密码
//    @PutMapping("/updatePass")
//    @ApiOperation("用户修改自己的密码")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "oldPass",value = "旧密码",required = true),
//            @ApiImplicitParam(name = "newPass",value = "新密码",required = true)
//    })
//    public ApiResult updatePass(String oldPass,String newPass, HttpServletRequest request){
//
//        Map map = new HashMap();
//        map.put("oldPass",oldPass);
//        map.put("newPass",newPass);
//
//        int i = adminService.updatePass(request,map);
//
//        ApiResult a = new ApiResult();
//
//        if(i==1){
//            a.setCode(ApiCode.SUCCESS);
//            a.setMsg(ApiCode.SUCCESS_MSG);
//        }else if(i==0){
//            a.setCode(ApiCode.error_update_failed);
//            a.setMsg(ApiCode.error_update_failed_MSG);
//        }else if (i==-1){
//            a.setCode(ApiCode.pass_exist);
//            a.setMsg(ApiCode.pass_exist_MSG);
//        }else {
//            a.setCode(ApiCode.FAIL);
//            a.setMsg(ApiCode.FAIL_MSG);
//        }
//        return a;
//    }

    /**
     * 修改密码，不校验旧密码
     * 参数：被修改密码的ID、新密码
     * 返回：成功、失败
     *
     * @param request
     * @return
     */
    @PutMapping("updateAdminPass")
    @ApiOperation("修改密码，不校验旧密码")
    @Log(name = "修改密码，不校验旧密码")
    public ApiResult updateAdminPass(HttpServletRequest request,
                                  @ApiParam(name = "adminId",value = "账号ID",required = true) Long adminId,
                                  @ApiParam(name = "newPass",value = "新密码",required = true) String newPass){

        int i = adminService.updateAdminPass(request,adminId,newPass);

        ApiResult a = new ApiResult();

        if(i==1){
            a.setCode(ApiCode.SUCCESS);
            a.setMsg(ApiCode.SUCCESS_MSG);
        }else if(i==0){
            a.setCode(ApiCode.error_update_failed);
            a.setMsg(ApiCode.error_update_failed_MSG);
        }else {
            a.setCode(ApiCode.FAIL);
            a.setMsg(ApiCode.FAIL_MSG);
        }
        return a;
    }



    //登陆成功后查询权限列表
    @GetMapping("/loginSuccess")
    @ApiOperation("登陆成功后查询权限列表")
    @Log(name = "登陆成功后查询权限列表")
    public ApiResult loginSuccess(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin = new Admin();
        String adminName = (String) session.getAttribute("adminName");
        admin.setAdminName(adminName);
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        a.setData(adminService.loginSuccess(admin,request));
        return a;
    }

    //用户注销
    @DeleteMapping("/ifLogout")
    @ApiOperation("用户注销")
    @Log(name = "注销")
    public ApiResult ifLogout(HttpServletRequest request){

        adminService.ifLogout(request);
        ApiResult a = new ApiResult();
        a.setCode(ApiCode.SUCCESS);
        a.setMsg(ApiCode.SUCCESS_MSG);
        return a;
    }
}
