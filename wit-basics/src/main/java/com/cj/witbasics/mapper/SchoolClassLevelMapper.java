package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolClassLevel;

import java.util.List;

public interface SchoolClassLevelMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer classLevelId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolClassLevel record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolClassLevel record);

    /**
     *
     * @mbggenerated
     */
    SchoolClassLevel selectByPrimaryKey(Integer classLevelId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolClassLevel record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolClassLevel record);

    //新增班级层次,查重
    int selectByClassLevel(String classLevelName);

    //返回班级层次
    List<SchoolClassLevel> sselectLevelBySchoolId(Long schoolId);

    //批量增加信息
    int addBathClassLevel(List<SchoolClassLevel> list);
}