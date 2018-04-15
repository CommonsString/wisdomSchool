package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.StudentScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentScoreMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long scoreId);

    /**
     *
     * @mbggenerated
     */
    int insert(StudentScore record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(StudentScore record);

    /**
     *
     * @mbggenerated
     */
    StudentScore selectByPrimaryKey(Long scoreId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentScore record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StudentScore record);

    /**
     * 根据学籍号,科目ID查重
     */
    int selectByCountScoreId(@Param("registerNumber") String registerNumber, @Param("subjectId") Long subjectId);

    /**
     * 批量插入
     */
    int insertBathInfo(List<StudentScore> list);


}