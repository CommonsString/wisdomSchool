package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witcommon.utils.entity.other.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolSubjectMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long subjectId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolSubject record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolSubject record);

    /**
     *
     * @mbggenerated
     */
    SchoolSubject selectByPrimaryKey(Long subjectId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolSubject record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolSubject record);

    /**
     * 根据科目名，查重
     */
    int selectBySubjectName(String subjectName);

    /**
     * 根据学校ID,科目名称,计数
     */
    int selectCountByAll(@Param("schoolId") Long schoolId, @Param("pager") Pager pager);

    /**
     * 根据学校ID,科目名称,模糊查询
     */
    List<SchoolSubject> selectByScholId(@Param("schoolId") Long schoolId, @Param("pager") Pager pager);

    /**
     * 批量查询,根据科目ID
     */
   List<SchoolSubject> selectBathInfo(List<Long> list);

    /**
     * 返回班级对应的科目
     */
    List findSubjectInfo(Long classId);


    /**
     * 根据科目名,返回科目ID
     */
    Long selectBySubNameReturnId(String subjectName);

    /**
     * 返回所有开课信息
     */
    List<SchoolSubject> selectBySchoolIdAllInfo(Long schoolId);

    /**
     * 根据科目ID查询课程信息
     */
    public List<SchoolSubject> findAllSchoolSubjectBySubjectsId(Long subjectsId);


}