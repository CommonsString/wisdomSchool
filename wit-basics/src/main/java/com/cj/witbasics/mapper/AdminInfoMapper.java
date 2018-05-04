package com.cj.witbasics.mapper;



import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminInfo;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witcommon.utils.entity.other.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminInfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long adminInfoId);

    /**
     *
     * @mbggenerated
     */
    int insert(AdminInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(AdminInfo record);

    /**
     *
     * @mbggenerated
     */
    AdminInfo selectByPrimaryKey(Long adminInfoId);

    /**
     * 根据adminId查询adminIdInfo信息
     */
    public AdminInfo selectByadminId(Long adminId);


    /**
     *根据adminId修改adminInfo信息
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AdminInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AdminInfo record);

    /**
     *通过根据部门和模糊名字进行查询
     */
    List<AdminInfo> selectByDepartAndName(AdminInfo adminInfo);

    /**
     * 查询教职工编号最大值
     */
    Long selectMaxStaffNumber();
    /**
     * 新增admin信息
     */
    Long insertAdminSelective(Admin admin);
    /**
     * 删除教职工信息
     */
    int deleteByAdminId(HashMap map);
    int deleteByAdminInfoId(HashMap map);
    /**
     * 修改密码
     */
    int updateAdminPass(HashMap hashMap);

    /**
     * 设置更新时间
     */
    Integer updateAdminTime(HashMap hashMap);

    /**
     * 锁定管理员账号
     */
    Integer lockingAdminByAdminId(HashMap hashMap);
    /**
     * 根据部门ID、姓名 分页、条件查询部门人员信息
     */
    List selectAdminInfoByDepartment(Pager p);

    /**
     * 根据部门ID、姓名 分页、条件查询部门人员信息总条数
     */
    int selectAdminInfoByDepartmentTotal(Pager p);

    /**
     * 根据部门ID、姓名 分页、条件查询部门人员信息
     */
    List selectAdminInfoAndDepartment(Pager p);

    /**
     * 根据部门ID、姓名 分页、条件查询部门人员信息 总条数
     */
    int selectAdminInfoAndDepartmentTotal(Pager p);

    /**
     * 分页、模糊（姓名、教职工编号）、条件（部门） 查询人事信息（ID、姓名、身份证号、教职工编号、出生年月、性别、手机号）
     */
    List selectAdminInfoByVague(Pager p);

    /**
     * 分页、模糊（姓名、教职工编号）、条件（部门） 查询人事信息（ID、姓名、身份证号、教职工编号、出生年月、性别、手机号） 总条数
     */
    int selectAdminInfoByVagueTotal(Pager p);

    /**
     * 根据姓名模糊查询没有担任部门领导的人 总条数
     */
    public Integer findAllDepartmentalLeadershipTotal(Pager p);


    /**
     * 根据姓名模糊查询没有担任部门领导的人
     */
    public List<Map> findAllDepartmentalLeadership(Pager p);



    /**
     * 更新员工所在部门
     */
    int updateStaffDepartment(Map map);


    /**
     * 根据 教师编号 查询对应的教师ID
     */
    Long selectByAdminInfoId(String staffNum);

    //根据 AdminRoleId 查询角色信息
    public AdminRole findAdminRoleByAdminRoleId(Long adminRoleId);

    /**
     * 根据adminId查询adminName信息
     */
    public String findAdminNameByadminId(Long adminId);

    /**
     * 根据 教职工编号 查询 adminId 信息
     */
    public Long findAdminIdByStaffNum(String staffNumber);


}