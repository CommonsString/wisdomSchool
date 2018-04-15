package com.cj.witscorefind.controller;


import com.cj.witbasics.entity.SchoolExamGrade;
import com.cj.witcommon.entity.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/scorefind")
public class ScoreFindController {

    /**
     * 档次设置(班主任设置有班级ID,年级主任无需班级ID)
     * 班主任设置本班
     * 年级主任设置
     */
    public ApiResult setScoreGrade(HttpServletRequest request, SchoolExamGrade schoolExamGrade){
        ApiResult apiResult = new ApiResult();







        return apiResult;

    }


    /**
     * 查询年级下班级考试统计信息
     */

    /**
     * 查询班级考试详情
     */

}
