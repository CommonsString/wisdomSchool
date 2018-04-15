package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.AdminInfo;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.entity.DepartmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentInfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(DepartmentInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(DepartmentInfo record);

    /**
     *
     * @mbggenerated
     */
    DepartmentInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DepartmentInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DepartmentInfo record);

    /**
     * 根据部ID字获取部门信息
     */
    DepartmentInfo selectDepartmentInfoById(Long id);

    /**
     * 获取所有部门信息
     */
    public List<DepartmentInfo> getAllDepartmentInfo();

    /**
     * 检查部门名称是否已存在
     */
    public DepartmentInfo findDepartmentInfoByName(DepartmentInfo departmentInfo);






}