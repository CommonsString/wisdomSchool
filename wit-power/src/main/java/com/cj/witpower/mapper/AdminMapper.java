package com.cj.witpower.mapper;

import com.cj.witbasics.entity.Admin;
import com.cj.witcommon.utils.entity.other.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    //添加管理员
    int insertSelective(Admin record);

     //删除、取消删除管理员、修改用户角色
    public int updateAdmin(Admin admin);

    //批量删除账户
    public int updateAdmins(List<Long> adminIds);

    //批量删除 department_admin 表adminId相关信息
    public int deleteDepartmentAdmin(List<Long> adminIds);

    //批量删除账户和学生信息
    public int updateAdminsAndStudents(List<Long> adminIds);

    //查询所有用户 总条数
    public Integer findAllAdminSize(Pager p);

    //查询所有用户
    public List<Map> findAllAdmin(Pager p);

    Admin selectByPrimaryKey(Integer id);

    //修改管理员信息，修改自己密码，修改他人密码
    int updateByPrimaryKeySelective(Admin record);


    int updateByPrimaryKey(Admin record);

    //检查用户名是否已存在
    public Integer findAdminByName(String adminName);

    //管理员登陆
    public Admin findAdmin(Admin admin);


    /*********************************************************/
    /*********************************************************/

    //查询具有班主任权限的角色
    List<Map> findHasPowerForHeadmaster(@Param("vague") String vague);

    //查询具有年级主任权限的角色
    List<Map> findHasPowerForDirector(@Param("vague") String vague);


}