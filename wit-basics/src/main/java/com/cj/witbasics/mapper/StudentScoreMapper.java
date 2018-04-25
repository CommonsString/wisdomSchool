package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.ClassSubjectInfo;
import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witbasics.entity.StudentScore;
import com.cj.witcommon.entity.ClassGradeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 成绩查询,班主任权限
     */
    List<ClassGradeInfo> selectPowerByHeadmaster(Long classId);

    /**
     * 成绩查询,科目教师权限
     * @param
     * @return
     */
    List<ClassGradeInfo> selectPowerBySubjectTeacher(@Param("list") List<ClassSubjectInfo> list);


    /**
     *根据学段ID,毕业届次查询
     */
    List<ClassGradeInfo> selectPowerByPeriodIdAndThetime(@Param("info") PeriodDirectorThetime info);

    /**
     * 分数查询
     */
    List<Map> selectByClassIdAndSubjectId(@Param("classId") Long classId,
                                          @Param("subjectId") Long subjectId);

    List<ClassGradeInfo> selectBathInfo(@Param("list") List<Long> classIdList);

}