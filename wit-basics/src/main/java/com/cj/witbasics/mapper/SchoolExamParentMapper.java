package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolExamParent;
import com.cj.witcommon.utils.entity.other.Pager;

import java.util.List;

public interface SchoolExamParentMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long examParentId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolExamParent record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolExamParent record);

    /**
     *
     * @mbggenerated
     */
    SchoolExamParent selectByPrimaryKey(Long examParentId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolExamParent record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolExamParent record);


    /**
     * 模糊查询考试集合
     */
    public List<SchoolExamParent> findAllSchoolExamParentByParameter(Pager p);

    /**
     * 查重,根据考试名称
     */
    int selectByExamName(String examName);
}