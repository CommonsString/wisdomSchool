package com.cj.witpower.mapper;


import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.entity.AdminRoleModular;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminRoleModularMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleModular record);

    int insertSelective(AdminRoleModular record);

    AdminRoleModular selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRoleModular record);

    int updateByPrimaryKey(AdminRoleModular record);

    //根据角色ID查询管理员角色资源列表ID
    public List findAllAdminRoleModularModularId(Long roleId);

    //根据RoleId检查角色下是否有管理员存在
    public int findRoleModular(AdminRole adminRole);


    //先删除该角色ID下的所有数据
    public int deleteRoleModular(Map map);

    //再新增传入的数据
    public int addRoleModular(Map map);
}