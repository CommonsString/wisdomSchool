package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolExam;
import com.cj.witcommon.entity.ExamClassSubject;
import com.cj.witcommon.entity.ExamParam;
import com.cj.witcommon.entity.PeriodUnderGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchoolExamMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long examId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolExam record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolExam record);

    /**
     *
     * @mbggenerated
     */
    SchoolExam selectByPrimaryKey(Long examId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolExam record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolExam record);

    /**
     *
     */
    List<PeriodUnderGrade> selectBySchoolIdForExam(Long schoolId);

    /**
     * 联查学校里所有的年级、班级
     * @param schoolId
     * @return
     */
    List<Map> findAllPeriodAndGrade(Long schoolId);


    /**
     * 根据班级id,科目名,查重记录
     */
    int selectCountBySubjectNameAndClassId(@Param("classId") int classId, @Param("subjectName") String subjectName);

    /**
     * 批量插入
     */
    int insertBatchInfoByU(@Param("list") List<SchoolExam> list);


}