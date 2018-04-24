package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.PeriodDirectorThetime;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PeriodDirectorThetimeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long sdtId);

    /**
     *
     * @mbggenerated
     */
    int insert(PeriodDirectorThetime record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(PeriodDirectorThetime record);

    /**
     *
     * @mbggenerated
     */
    PeriodDirectorThetime selectByPrimaryKey(Long sdtId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PeriodDirectorThetime record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PeriodDirectorThetime record);

    /**
     *  根据管理员ID,获取年级主任表信息
     * @return
     */
    PeriodDirectorThetime selectByDirectorId(@Param("directorId") Long directorId);

    /**
     * 查重
     */
    PeriodDirectorThetime selectByCountDirectorId(@Param("directorId") Long directorId, @Param("thetime") Date theTime);

    /**
     * 清空年级主任
     */
//    int updateByDirectorIdDel(Long directorId);

    /**
     * 年级查重
     */
    int selectCountInfo(@Param("info") PeriodDirectorThetime info);


    //清空年级主任
    int updateByDirectorId(Long directorId);

}