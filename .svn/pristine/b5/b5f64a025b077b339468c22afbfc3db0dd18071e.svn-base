package com.cj.witpower.mapper;


import com.cj.witbasics.entity.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminRoleMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(AdminRole record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(AdminRole record);

    /**
     *
     * @mbggenerated
     */
    AdminRole selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AdminRole record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AdminRole record);
	
	    //查询此角色是否已存在
    public AdminRole findRoleName(AdminRole adminRole);

    //删除此角色
    public int updateRole(AdminRole adminRole);

    //查询正常使用的角色
    public List<AdminRole> findAllRole(AdminRole adminRole);

    //根据 AdminRoleId 查询角色信息
    public AdminRole findAdminRoleByAdminRoleId(Long adminRoleId);
}