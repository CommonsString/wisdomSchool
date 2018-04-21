package com.cj.witpower.service.Impl;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminModular;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.MemoryData;
import com.cj.witcommon.utils.common.QueryBase;
import com.cj.witcommon.utils.entity.other.AdminModulars;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witcommon.utils.util.Md5Utils;
import com.cj.witcommon.utils.util2.MD5Util;
import com.cj.witcommon.utils.util2.SecurityUtil;
import com.cj.witpower.mapper.AdminMapper;
import com.cj.witpower.mapper.AdminModularMapper;
import com.cj.witpower.mapper.AdminRoleMapper;
import com.cj.witpower.mapper.AdminRoleModularMapper;
import com.cj.witpower.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by XD on 2017/12/8.
 */

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleModularMapper adminRoleModularMapper;

    @Autowired
    private AdminModularMapper adminModularMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    //检查用户名是否已存在
    @Override
    public Integer findAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }

    //添加管理员
    @Override
    @Transactional
    public int addAdmin(Admin admin) {

        //创建时间
        admin.setCreateTime(new Date());
        admin.setAdminPass(Md5Utils.MD5Encode(admin.getAdminPass(),"UTF-8",false));
        //添加用户
        int j = adminMapper.insertSelective(admin);
        if(j > 0){
            return ApiCode.SUCCESS;
        }else {
            return ApiCode.FAIL;

        }


    }

    //禁用、取消禁用管理员、修改用户角色
    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    //批量删除账户
    @Override
    @Transactional
    public int updateAdmins(List<Long> adminIds) {
        //修改admin/adminInfo表State为0
        int i = adminMapper.updateAdmins(adminIds);
        //删除department_admin 表 adminId相关信息
        int j = adminMapper.deleteDepartmentAdmin(adminIds);

        return i;
    }
    //批量删除账户和学生信息
    @Override
    public int updateAdminsAndStudents(List<Long> adminIds) {
        return adminMapper.updateAdminsAndStudents(adminIds);
    }

    //查询所有用户
    @Override
    public Pager findAllAdmin(Pager p) {
        //查询总条数
        p.setRecordTotal(adminMapper.findAdminSize(p));
        //查询符合条件的集合
        p.setContent(adminMapper.findAllAdmin(p));

        System.out.println("结果集==>"+p.getContent());
        return p;
    }

    //添加角色
    @Override
    public int addRole(AdminRole adminRole) {
        //查询此角色是否已存在
        AdminRole adminRole1 = adminRoleMapper.findRoleName(adminRole);
        if(adminRole1!=null){

        }else { //不存在，则添加
            adminRole.setCreateTime(new Date());
            int i = adminRoleMapper.insertSelective(adminRole);
            return i;
        }
        return -1;
    }

    //删除角色
    @Override
    public int updateRole(AdminRole adminRole) {
        //根据RoleId检查角色下是否有管理员存在
        int i = adminRoleModularMapper.findRoleModular(adminRole);
        if(i==0){ //无管理员、则删除
            int j = adminRoleMapper.updateRole(adminRole);
            return j;

        }else {  //有管理员
            return -1;
        }

    }
    //查询正常使用的角色
    @Override
    public List<AdminRole> findAllRole(AdminRole adminRole) {
        return adminRoleMapper.findAllRole(adminRole);
    }

    //查询所有的正常使用的目录及页面及当前角色的权限ID
    //树形结构封装
    @Override
    public Map findRoleModulars(Long roleId) {

        //根据roleId查询所有该角色下的权限信息
        List modular = adminRoleModularMapper.findAllAdminRoleModularModularId(roleId);

        //查询所有的可使用的权限页面信息
        AdminModulars modulars = adminModularMapper.findAllRoleModulars();


        //将目录和权限封装为列表样式
//        List<AdminModulars> catas = encapsulation(modulars);

        Map map = new HashMap();
        map.put("modular",modular);
        map.put("catas",modulars);
        return map;
    }

    //修改角色权限
    @Override
    @Transactional
    public int updateRoleModular(Map map) {


        //先删除该角色ID下的所有数据
        int i = adminRoleModularMapper.deleteRoleModular(map);
        //再新增传入的数据
        int j = adminRoleModularMapper.addRoleModular(map);
        if(i>-1&&j>0){
            return 1;
        }
        return 0;
    }

    //管理员登陆
    @Override
    public Map findAdmin(HttpServletRequest request, Admin admin) {
        //检查用户名是否已存在
        Admin admin1 = adminMapper.findAdmin(admin);
        Map map = new HashMap();


        if(admin1==null){//用户不存在
            map.put("result",-1);
            return map;
        }else {
//            String pwd = SecurityUtil.getStoreLogpwd(admin1.getId().toString(),admin.getAdminPass(),admin1.getSaltVal());
            String pwd = Md5Utils.MD5Encode(admin.getAdminPass(),"UTF-8",false);

            if(admin1.getAdminPass().equals(pwd)){
                HttpSession session = request.getSession();
                //生成Token
                String Token = MD5Util.md5Hex(session.getId()+admin1.getAdminName()+ UUID.randomUUID());

                //将用户ID、用户名、Token、角色Id绑定到session
                Long adminId = admin1.getId();

                session.setAttribute("adminId",adminId);
                session.setAttribute("adminName",admin1.getAdminName());
                session.setAttribute("roleId",admin1.getRoleId());
//                session.setAttribute("Token",Token);

                //将adiminId---sessionId放入 sessionIDMap,用于实现单设备登录
                String sessionID = request.getRequestedSessionId();
                if (!MemoryData.getSessionIDMap().containsKey(adminId)) { //不存在，首次登陆，放入Map
                    MemoryData.getSessionIDMap().put(adminId.toString(),sessionID);  //添加adminId-sessionID
                }else if( MemoryData.getSessionIDMap().containsKey(adminId) && !StringUtils.equals(sessionID, MemoryData.getSessionIDMap().get(adminId))){
                    MemoryData.getSessionIDMap().remove(adminId);  //删除adminId-sessionID
                    MemoryData.getSessionIDMap().put(adminId.toString(),sessionID);  //添加adminId-sessionID
                }


                map.put("result",1);
                map.put("adminId",admin1.getId());
                map.put("adminRoleId",admin1.getRoleId());
                return map;
            }else {
                map.put("result",0);
                return map;
            }
        }

    }

    //修改密码
    @Override
    public int updatePass(HttpServletRequest request, Map map) {
        HttpSession session = request.getSession();
        String adminName = (String)session.getAttribute("adminName");

        String oldPass = (String)map.get("oldPass");
        String newPass = (String)map.get("newPass");


        Admin admin = new Admin();
        admin.setAdminName(adminName);

        Admin admin1 = adminMapper.findAdmin(admin);
//        String oldpwd = SecurityUtil.getStoreLogpwd(admin1.getId().toString(),oldPass,admin1.getSaltVal());
        //对用户输入的旧密码加密
        oldPass = Md5Utils.MD5Encode(oldPass,"UTF-8",false);
        if(admin1.getAdminPass().equals(oldPass)){ //旧密码正确
//            String newpwd = SecurityUtil.getStoreLogpwd(admin1.getId().toString(),newPass,admin1.getSaltVal());
            //对用户输入的新密码进行加密
            newPass = Md5Utils.MD5Encode(newPass,"UTF-8",false);
            admin1.setAdminPass(newPass);
            //修改管理员密码
            int k = adminMapper.updateByPrimaryKeySelective(admin1);
            if(k>0){
                return 1;
            }else {
                return 0;
            }
        }else {
            //旧密码错误
            return -1;
        }



    }

    //修改密码，不校验旧密码
    @Override
    public int updateAdminPass(HttpServletRequest request, Long adminId, String newPass) {
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setAdminPass(Md5Utils.MD5Encode(newPass,"UTF-8",false));
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    //注销
    @Override
    public void ifLogout(HttpServletRequest request){
        Long adminId = (Long) request.getSession().getAttribute("adminId");
        //清理MemoryData中的用户数据
        MemoryData.getSessionIDMap().remove(adminId);  //删除adminId-sessionID
        //销毁此session
        request.getSession().invalidate();
    }

    //查询管理员所拥有的权限集合
    @Override
    public Map loginSuccess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long roleId = (Long)session.getAttribute("roleId");


        //用于封装返回的结果集
        Map map = new HashMap();

//        //从session中获取用户名
//        String adminName = (String)request.getSession().getAttribute("adminName");
//        Admin util2 = new Admin();
//        util2.setAdminName(adminName);

//        //用adminName查询
//        Admin admin1 = adminMapper.findAdmin(admin);

//        //根据roleId查询adminRole信息
//        AdminRole adminRole = adminRoleMapper.findAdminRoleByAdminRoleId(roleId);

        //根据roleId查询该用户拥有的权限集合
        List roleIds = adminRoleModularMapper.findAllAdminRoleModularModularId(roleId);

        //根据roleIds查询全新信息，树形结构封装
        if(roleIds.size()==0){
            map.put("catas",new ArrayList<>());
        }else {
            //查询权限页面信息
            AdminModulars modulars = adminModularMapper.findAllRoleModular(roleIds);

//            //将目录和权限封装为列表样式
//            List<AdminModulars> catas = encapsulation(modulars);

            //将权限列表封装到map
//            map.put("catas",cleanCatas(catas));  //清理无权限的目录

            //提取用户拥有的权限url添加到session
            List<String> list = new ArrayList<>();
            for (AdminModulars adminModulars : modulars.getChildren()){
                for (AdminModulars adminModulars1 : adminModulars.getChildren()){
                    list.add(adminModulars1.getPageUrl());

                }

            }
            request.getSession().setAttribute("powerList",list);
            System.out.println("权限接口==>>"+list);

            map.put("catas",modulars);
        }



        return map;
    }

    //根据adminId查询角色信息
    @Override
    public AdminRole findAdminRoleByAdminRoleId(Long adminRoleId) {
        return adminRoleMapper.findAdminRoleByAdminRoleId(adminRoleId);
    }

//    //将目录和权限封装为列表样式
//    public List<AdminModulars> encapsulation(List<AdminModular> modulars){
//        List<AdminModulars> catas = adminModularMapper.findAllCatalog();
//
////        System.out.println("catas====>"+catas);
//        for (AdminModulars adminModulars : catas){  //第一层，循环标题栏
//
//
//            for(AdminModulars adminModulars1 : (AdminModulars) adminModulars.getChildren()){  //第二层，循环模块
//
//                for (AdminModular adminModular : modulars){  //第三层，循环比对请求的父级目录ID
//                    List list = new ArrayList<>();
//
//                    if(adminModular.getParentId().equals(adminModulars1.getId())){ //如果请求的父ID等于该模块的ID
//                        //将请求添加到该模块的 children 里面
//                        list.add(adminModular);
//
//                    }
////                    if(adminModulars.getId().equals(adminModular.getParentId())){
////                        list.add(adminModular);
////                    }
//                    adminModulars1.setChildren(list);
//                }
//            }
//
//
//
//        }
//        return catas;
//    }

    //清理无权限的目录
    public static List<AdminModulars> cleanCatas(List<AdminModulars> catas){

        Iterator<AdminModulars> it =  catas.iterator();
        while (it.hasNext()){
            AdminModulars modulars = it.next();
            if(modulars.getChildren().size()==0){
                it.remove();
            }
        }
        return catas;
    }



    /***************************************************/
    /**************************************************/
    /**
     * 查询具有班主任权限的角色
     * @return
     */
    @Override
    public List<Map> findHasPowerForHeadmaster(String vague) {
        List<Map> result = this.adminMapper.findHasPowerForHeadmaster(vague);
        return result;
    }

    /**
     * 查询具有年级主任权限的角色
     * @return
     */
    @Override
    public List<Map> findHasPowerForDirector(String vague) {
        List<Map> result = this.adminMapper.findHasPowerForDirector(vague);
        return result;
    }
}


