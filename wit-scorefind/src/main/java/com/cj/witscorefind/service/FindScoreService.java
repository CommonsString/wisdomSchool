package com.cj.witscorefind.service;

import com.cj.witbasics.entity.Grade;
import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witbasics.entity.SchoolPeriodClassThetime;
import com.cj.witcommon.entity.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface FindScoreService {


    /**
     * 不同的角色,查询不同的成绩
     * @return
     */
    ApiResult findScoreForDifferentRole(HttpServletRequest request);


    /**
     *修改班主任ID
     */
    boolean updateHeadmasterId(SchoolPeriodClassThetime classThetime);


    /**
     * 修改年级主任ID
     */
    boolean updateDirectorId(PeriodDirectorThetime info);

    /**
     * 档次添加
     */
    ApiResult addGradeLevelInfo(Grade info);

    /**
     * 查看分数
     */
    List<Map> findScoreDetils(Long classId, Long subjectId);

    /**
     * 成绩导出
     */
    void exportScoreInfo(List<Long> classIdList, HttpServletResponse response);



}
