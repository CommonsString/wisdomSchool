package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolExam;
import com.cj.witcommon.entity.ExamClassPeriod;
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


    /**
     * ===================================================================
     * 根据届次查询考试集合
     */

    public List<SchoolExam> findAllSchoolExamByThetime(String thetime);

    /**
     * 根据考试父节点ID查询此次考试所有的届次
     */
    public List<Map> findAllSchoolExamThetimeBySchoolExamParent(Long examParentId);

    /**
     * 根据考试父节点ID和考试届次和学段ID 查询此次考试的所有班级及课程信息
     */
    public List<ExamClassPeriod> findAllSchoolExamClassByExamParentIdAndThetime(Map map);

    /**
     * 根据考试父节点ID和考试届次和学段ID 查询此次考试的所有班级及课程信息
     */
    public List<ExamClassPeriod> findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime(Map map);




}