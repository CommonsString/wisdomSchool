package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolClass;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

public interface SchoolPeriodClassThetimeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long ssctId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolPeriodClassThetime record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolPeriodClassThetime record);

    /**
     *
     * @mbggenerated
     */
    SchoolPeriodClassThetime selectByPrimaryKey(Long ssctId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolPeriodClassThetime record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolPeriodClassThetime record);


    //根据学段ID、所属届次 查询符合条件的所有班级
    public List<SchoolPeriodClassThetime> findAllClassByGradeAndPeriodId(Long periodId);

    //查询届次下所有班级
    public List<Map> findAllSchoolClassByThetime(Map map);

    //班主任ID,获取信息
    SchoolPeriodClassThetime selectByAdminId(Long headmasterId);

    //修改班主任
    int updateByClassIdKeySelective(SchoolPeriodClassThetime record);

    //清除班主任
    int updateByClassId(SchoolPeriodClassThetime theTime);

}