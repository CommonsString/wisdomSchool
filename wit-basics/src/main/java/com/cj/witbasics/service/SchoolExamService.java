package com.cj.witbasics.service;


import com.cj.witbasics.entity.SchoolExam;
import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ExamParam;
import com.cj.witcommon.entity.PeriodAndGrade;

import java.util.List;
import java.util.Map;

public interface SchoolExamService {


    /**
     * 查询学校下的年级
     */
    List<Map> findAllGradeName(Long schoolId);


    /**
     * 返回班级信息
     */
    List findAllUnderGradeClass(List<PeriodAndGrade> param);


    /**
     * 返回班级下的科目
     */
    List findAllSubjectInfo(Long classId);


    /**
     * 新增考试
     */
    ApiResult addSchoolExamInfo(ExamParam examInfo);


}
