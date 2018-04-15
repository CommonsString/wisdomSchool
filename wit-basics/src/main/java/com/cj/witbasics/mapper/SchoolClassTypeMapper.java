package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolClassType;

import java.util.List;

public interface SchoolClassTypeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer classTypeId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolClassType record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolClassType record);

    /**
     *
     * @mbggenerated
     */
    SchoolClassType selectByPrimaryKey(Integer classTypeId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolClassType record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolClassType record);

    //新增班级类型
    int selectByClassType(String classtypeName);

    //返回班级类型列表
    List<SchoolClassType> selectTypeBySchoolId(Long schoolId);

    //查重
    int selectByClassTypeName(String classTypeName);

    //批量增加班级类型
    int addBathClassType(List<SchoolClassType> list);

}