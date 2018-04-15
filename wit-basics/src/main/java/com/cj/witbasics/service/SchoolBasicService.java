package com.cj.witbasics.service;

import com.cj.witbasics.entity.SchoolBasic;

import javax.servlet.http.HttpServletRequest;

/**
 * 学校基础信息
 */
public interface SchoolBasicService {

    /**
     * 新增
     * @param schoolBasic
     * @return
     */
    public int addSchoolBasic(HttpServletRequest request, SchoolBasic schoolBasic);


    /**
     * 检查学校基础信息是否已完善
     */
    public int findSchoolBasic(Long schoolId);


}
