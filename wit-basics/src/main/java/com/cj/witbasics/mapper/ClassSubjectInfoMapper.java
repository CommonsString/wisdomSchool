package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.ClassSubjectInfo;
import com.cj.witbasics.entity.SchoolSubject;
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


    /**
     * 查找课程是否再使用
     */
    int selectCountBySubjectId(Long subjectId);


    /**
     * 根据教师ID,科目ID
     */
    List<ClassSubjectInfo> selectAdminId(Long teaId);
    ////////////////////////////////////////////////////

    /**
     * 查重
     */
    int selectByclassByClassIdAndSubjectId(@Param("classId") Long classId, @Param("subjectId") Long subjectId);

    /**
     * //批量增加
     */
    int insertSelectiveBath(@Param("classId") Long classId, @Param("list") List<Long> subjectId);

    /**
     * 删除，批量
     */
    int deleteByBatch(@Param("classId") Long classId, @Param("subjectId") Long subjectId);

    /**
     * 根据科目ID查询所有的课程
     */
    public List<SchoolSubject> findAllSubjectBySubjectsId(Long subjecstId);
	
	

    /**
     * 删除
     */
    int updateByClassIdAndSubjectIdDel(@Param("info") ClassSubjectInfo info);

}