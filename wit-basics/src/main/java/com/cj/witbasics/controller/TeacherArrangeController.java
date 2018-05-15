package com.cj.witbasics.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 科目管理
 */
@Api(tags = "班主任安排")
@RestController
@RequestMapping("/api/v1/TeacherArrangement")
public class TeacherArrangeController {

    /**
     *  功能描述：查找教师(查看该班主任安排)
     *  参数: 姓名
     *  返回：成功/失败
     *  时间：
     */
    public void findTeacherArrangment(){

    }

    /**
     *  功能描述：查找教师所有安排(查看该班主任安排)
     *  参数: 姓名
     *  返回：成功/失败
     *  时间：
     */
    public void findTeacherArrangmentAll(){

    }


    /**
     *  功能描述：查询学校下的所有学段和年级
     *  参数: schoolId
     *  返回：成功/失败
     *  时间：
     */
    public void findPeriodGradeInfo(){

    }

    /**
     *  功能描述：查询学校下的所有学段和年级
     *  参数: schoolId
     *  返回：成功/失败
     *  时间：
     */
    public void findClassInfoForSome(){

    }


}
