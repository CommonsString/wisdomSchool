package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.DepartmentAdmin;
import com.cj.witbasics.entity.DepartmentInfo;

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

    /**
     * 根据adminID修改所在部门信息
     */
    public int updateDidByAdminId(DepartmentAdmin departmentAdmin);

    /**
     * 根据adminID查询信息
     */
    public DepartmentAdmin findDepartmentAdminByAdminId(Long adminId);

    /**
     * 根据adminId删除数据
     */
    public int deleteDepartmentAdminByAdminId(Long adminId);


}