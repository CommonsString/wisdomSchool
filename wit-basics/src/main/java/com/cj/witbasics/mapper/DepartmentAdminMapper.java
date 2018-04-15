package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.DepartmentAdmin;

public interface DepartmentAdminMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(DepartmentAdmin record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(DepartmentAdmin record);

    /**
     *
     * @mbggenerated
     */
    DepartmentAdmin selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DepartmentAdmin record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DepartmentAdmin record);

    /**
     * 根据部门ID查询部门下成员数量
     */
    public int findPersonnelById(Long id);
}