package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.StudentHome;

public interface StudentHomeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long homeId);

    /**
     *
     * @mbggenerated
     */
    int insert(StudentHome record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(StudentHome record);

    /**
     *
     * @mbggenerated
     */
    StudentHome selectByPrimaryKey(Long homeId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentHome record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StudentHome record);
}