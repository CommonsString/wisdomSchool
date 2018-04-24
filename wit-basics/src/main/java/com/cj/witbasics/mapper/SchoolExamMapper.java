package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolExam;
import com.cj.witcommon.entity.ExamClassSubject;
import com.cj.witcommon.entity.ExamParam;
import com.cj.witcommon.entity.PeriodUnderGrade;
import com.cj.witcommon.utils.entity.other.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    int selectCountBySubjectNameAndClassId(@Param("classId") int classId,
                                           @Param("subjectName") String subjectName,
                                           @Param("examTime") Date examTime);

    /**
     * 批量插入
     */
    int insertBatchInfoByU(@Param("list") List<SchoolExam> list);

    /**
     * 查询考试名称
     */
    List<Map> selectBySchoolId(Long schoolId);


    /**
     * 模糊计数
     */
    int selectCountIdAndVague(@Param("examName") String examName,
                              @Param("vague") String vague);

    /**
     * 模糊查询
     */
    List<SchoolExam> selectByIdAndVague(@Param("examName") String examName,
                                        @Param("vague") String vague,
                                        @Param("pager") Pager pager);

}