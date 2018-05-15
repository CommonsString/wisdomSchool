package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolSubjects;

import java.util.List;

public interface SchoolSubjectsMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long subjectsId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolSubjects record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolSubjects record);

    /**
     *
     * @mbggenerated
     */
    SchoolSubjects selectByPrimaryKey(Long subjectsId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolSubjects record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolSubjects record);

    //根据科目名称查询是否已存在
    public SchoolSubjects findSunbjectsBySubjectsName(String subjectsName);

    //删除科目
    public int deleteSubjects(Long subjectsId);

    //根据schoolID查询所有科目信息
    public List<SchoolSubjects> findAllSubjectsBySchhoolId(Long schoolId);
}