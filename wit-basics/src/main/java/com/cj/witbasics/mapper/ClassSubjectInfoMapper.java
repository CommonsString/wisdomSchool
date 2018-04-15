package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.ClassSubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClassSubjectInfoMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long clsSubId);

    /**
     *
     * @mbggenerated
     */
    int insert(ClassSubjectInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ClassSubjectInfo record);

    /**
     *
     * @mbggenerated
     */
    ClassSubjectInfo selectByPrimaryKey(Long clsSubId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ClassSubjectInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ClassSubjectInfo record);


    //根据班级ID和学科ID修改教师ID
    int updateBySubjectIdAndAdmin(@Param("classId") Long classId,
                                  @Param("subjectId") Long subjectId,
                                  @Param("adminInfoId") Long adminInfoId);


    /**
     * 测试方法
     */
    int updateBySubjectTest(@Param("classId") Long classId, @Param("list") List list);



}