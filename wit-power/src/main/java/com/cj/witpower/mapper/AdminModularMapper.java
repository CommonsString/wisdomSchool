package com.cj.witpower.mapper;


import com.cj.witbasics.entity.AdminModular;
import com.cj.witcommon.utils.entity.other.AdminModulars;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminModularMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminModular record);

    int insertSelective(AdminModular record);

    AdminModular selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminModular record);

    int updateByPrimaryKey(AdminModular record);

    //查询所有目录
    public List<AdminModulars> findAllCatalog();

    //查询roleIds集合的权限列表
    public List<AdminModular> findAllRoleModular(List roleIds);

    //查询所有的权限列表
    public List<AdminModular> findAllRoleModulars();
}