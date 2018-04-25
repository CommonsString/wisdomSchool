package com.cj.witbasics.service;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminInfo;
import com.cj.witbasics.entity.DepartmentInfo;
import com.cj.witcommon.utils.entity.other.Pager;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PersonnelManagementService {
    /**
     * 通过根据部门和模糊名字进行查询
     * @return AdminInfo对象集
     */
    List<AdminInfo> selectByDepartAndName(AdminInfo adminInfo);
    /**
     * 查询教职工编号最大值
     */
    String productMaxStaffNumber();
    /**
     * 创建管理员Admin
     */
    Long createAdmin(Admin admin);
    /**
     * 创建人事信息详情
     */
    Integer createAdminInfo(AdminInfo adminInfo,HttpServletRequest request,String adminType);
    /**
     * 创建人事信息详情
     */
    public Map createAdminInfos(MultipartFile multipartFile, HttpServletRequest request) throws Exception;
    /**
     * 删除教职工信息
     */
    Integer deleteStaff(List<Long> adminIds, HttpServletRequest request);
    /**
     * 修改密码
     */
    Integer updateAdminPass(Long[] adminId, String adminPass);
    /**
     * 通过adminId查询员工信息
     */
    AdminInfo selectAdminInfoById(Long adminId);
    /**
     * 修改教职工信息
     */
    Integer updateAdminInfo(AdminInfo adminInfo,HttpServletRequest request);

    /**
     * 锁定id
     */
    Integer lockingAdminInfo(Long adminId);

    /**
     * 新增部门
     */
    Integer addDepartmentInfo(DepartmentInfo departmentInfo,HttpServletRequest request);

    /**
     * 根据部门ID查找部门人员信息
     */
    Pager selectAdminInfoByDepartment(Pager p);

    /**
     * 根据部门ID查找部门人员信息
     */
    Pager selectAdminInfoAndDepartment(Pager p);

    /**
     * 根据姓名进行模糊查询
     */
    Pager selectAdminInfoByVague(Pager p);

    /**
     * 修改员工所在部门
     */
    Integer updateStaffDepartment(List<Long> adminIds,Long did, String theDepartment, HttpServletRequest request);

    /**
     * 查询部门下的信息
     */
    DepartmentInfo selectDepartmentInfoById(Long id);


    /**
     * 获取部门编号
     */
    public String getDNumber();

    /**
     * 获取所有部门信息
     */
    public List<DepartmentInfo> findAllDepartmentInfo();

    /**
     * 根据部门ID修改部门信息
     */
    public int updateDepartmentInfo(DepartmentInfo departmentInfo);

    //下载人事信息模板
    public void downloadTeacherTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 根据部门ID删除部门
     */
    public int deleteDepartmentInfo(Long id);

}
