package com.cj.witscorefind.mapper;

import com.cj.witbasics.entity.Grade;

import java.util.List;

public interface GradeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long gradeId);

    /**
     *
     * @mbggenerated
     */
    int insert(Grade record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Grade record);

    /**
     *
     * @mbggenerated
     */
    Grade selectByPrimaryKey(Long gradeId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Grade record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Grade record);

    //批量添加档次详情信息
    public int addGrade(List<Grade> grades);
}