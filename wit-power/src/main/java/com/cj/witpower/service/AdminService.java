package com.cj.witpower.service;


import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witcommon.utils.common.QueryBase;
import com.cj.witcommon.utils.entity.other.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by XD on 2017/12/8.
 */
public interface AdminService {

    //检查用户名是否已存在
    public Integer findAdminByName(String adminName);

    //添加管理员
    public int addAdmin(Admin admin);

    //禁用、取消禁用管理员、修改用户角色
    public int updateAdmin(Admin admin);

    //批量删除账户及人员信息
    public int updateAdmins(List<Long> adminIds);

    //批量删除账户和学生信息
    public int updateAdminsAndStudents(List<Long> adminIds);

    //查询所有管理员
    public Pager findAllAdmin(Pager p);


    //添加角色
    public int addRole(AdminRole adminRole);

    //删除角色
    public int updateRole(AdminRole adminRole);

    //查询正常使用的角色
    public List<AdminRole> findAllRole(AdminRole adminRole);

    //查询所有的正常使用的目录及页面及当前角色的权限ID
    public Map findRoleModulars(Long roleId);

    //根据角色ID修改角色权限
    public int updateRoleModular(Map map);


    //管理员登陆
    public Map findAdmin(HttpServletRequest request, Admin admin);

    //修改自己密码
    public int updatePass(HttpServletRequest request, Map map);

    //修改密码，不校验旧密码
    public int updateAdminPass(HttpServletRequest request,Long adminId,String newPass);

    //注销
    public void ifLogout(HttpServletRequest request);

    //主页查询
    public Map loginSuccess(Admin admin, HttpServletRequest request);

    //根据 AdminRoleId 查询角色信息
    public AdminRole findAdminRoleByAdminRoleId(Long adminRoleId);






}
