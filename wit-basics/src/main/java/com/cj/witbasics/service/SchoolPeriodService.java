package com.cj.witbasics.service;

import com.cj.witbasics.entity.SchoolClass;
import com.cj.witbasics.entity.SchoolGrade;
import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.GradeInfo;
import com.cj.witcommon.entity.SchoolPeriodInfo;

import javax.xml.crypto.Data;
import java.time.Period;
import java.util.List;
import java.util.Map;

public interface SchoolPeriodService {

    //根据年级id,修改年级名称
    boolean updateSchoolGradeInfo(Long gradeId, String gradeName);

    //根据年级的ID，删除年级信息
    boolean updateSchoolGradeDel(Long gradeId);

    //根据年级实体添加信息
    boolean addSchoolGradeInfo(SchoolGrade grade);

    //根据学校ID，查找对于学段信息
    public List findSchoolPeriodInfo(Long schoolId);

    //添加学段信息
    public ApiResult addSchoolPeriodInfo(SchoolPeriodInfo period, List<String> gradeList, Long operatorId);

    //批量删除，参数为集合,根据学段ID集合
    public boolean updateBathPartInfoDel(List<Long> params, Long operatorId);

    //根据学段Id删除对于信息
    public boolean updatePartInfoDel(Long periodId, Long operatorId);

    //根据学段实体类，和年级信息修改信息
    public boolean updatePartInfo(SchoolPeriodInfo info);

    //根据学校ID，查询学段下年级信息(封装)
    List<SchoolPeriodInfo> findPeriodAndGradeInfo(Long schoolId);

    //根据学段ID，返回年级信息
    List<GradeInfo> findPeriodGradeInfo(Long schoolId, Long periodId);

    //查询所有的学段
    public List<Period> findAllSchoolPeriod(String schoolId);

    //查询学段下所有的年级
    public List<SchoolGrade> findAllGradeByPeriodId(Long periodId);

    //查询学段下所有届次信息
    public List<SchoolPeriodClassThetime> findAllClassByGradeAndPeriodId(Long periodId);

    //查询届次下所有班级
    public List<Map> findAllSchoolClassByThetime(Map map);




}
