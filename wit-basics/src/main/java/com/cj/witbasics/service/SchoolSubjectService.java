package com.cj.witbasics.service;


import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.utils.entity.other.Pager;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SchoolSubjectService {

    /**
     * 新增课程信息
     */
    ApiResult addSubjectInfo(SchoolSubject subject);


    /**
     * 查询所有课程
     */
    Pager findSchoolSunjectInfo(Long schoolId, Pager pager);

    /**
     * 修改课程信息
     */
    boolean updateSubjectInfo(SchoolSubject subject);

    /**
     * 删除课程信息，非物理删除
     */
    ApiResult updataSubjectInfoDel(SchoolSubject subject);


    /**
     * 选择导出数据
     */
    void exportSubjectInfo(HttpServletResponse response, List<Long> subjectList);

    /**
     * 全部导出
     */
    ApiResult exportSubjectInfoAll(HttpServletResponse response);

    /**
     * 停课
     */
    ApiResult updataStopSubject(SchoolSubject subject);

}
