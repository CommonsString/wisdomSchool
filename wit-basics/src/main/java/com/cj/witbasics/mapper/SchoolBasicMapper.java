package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolBasic;

import java.util.Map;

public interface SchoolBasicMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long schoolId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolBasic record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolBasic record);

    /**
     *
     * @mbggenerated
     */
    SchoolBasic selectByPrimaryKey(Long schoolId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolBasic record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolBasic record);


    /**
     * 查询学校信息是否已完善
     */
    public SchoolBasic finsSchoolBasic(Long schoolId);

    /**
     * 查询本校区 学校、学段、年级、班级 的 ID、名称
     */
    public Map findAllSchoolBasic(Long schoolId);
}