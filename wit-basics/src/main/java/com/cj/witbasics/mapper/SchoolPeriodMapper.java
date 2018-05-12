package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witcommon.entity.PeriodUnderGrade;
import org.apache.ibatis.annotations.Param;

import java.time.Period;
import java.util.Date;
import java.util.List;

public interface SchoolPeriodMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long periodId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolPeriod record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolPeriod record);

    /**
     *
     * @mbggenerated
     */
    SchoolPeriod selectByPrimaryKey(Long periodId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolPeriod record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolPeriod record);


    /**
     * 根据学校ID返回信息
     */
//    Map<String, Object> selectInfoBySchoolId(Long schoolId);

    List<SchoolPeriod> selectInfoBySchoolId(Long schoolId);

    /**
     * 查重
     */
    int selectByPeriodName(String periodName);


    /**
     *插入学段信息并返回ID
     */
    Long insertRetuenId(SchoolPeriod period);


    /**
     * 根据学段集合ID，批量删除
     */
    int deleteBatchByPrimaryKey(@Param("list") List<Long> periodList,
                                @Param("operatorId") Long operatorId,
                                @Param("deleteTime") Date deleteTime);

    /**
     * 根据学段名返回学点ID
     */
    SchoolPeriod selectPeriodIdByPeriodName(String periodName);



    //查询所有的学段
    public List<SchoolPeriod> findAllSchoolPeriod(Long schoolId);


    //查询学段、年级信息 树形结构
    public List<PeriodUnderGrade> findAllPeriodAndGradeBySchoolId(Long schoolId);







}