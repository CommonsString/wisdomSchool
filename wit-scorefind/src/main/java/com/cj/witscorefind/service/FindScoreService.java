package com.cj.witscorefind.service;

import com.cj.witcommon.entity.ApiResult;

import javax.servlet.http.HttpServletRequest;

public interface FindScoreService {


    /**
     * 不同的角色,查询不同的成绩
     * @return
     */
    ApiResult findScoreForDifferentRole(HttpServletRequest request);



}
