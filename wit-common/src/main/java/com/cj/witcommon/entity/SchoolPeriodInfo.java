package com.cj.witcommon.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
/**
 * 组合类：
 * 学段管理模块，查询封装类
 */
public class SchoolPeriodInfo {

    private Long periodId; //学段ID
    private Long SchoolId; //学校iD
    private String periodName; //学段名称：高中
    private int periodAge; //入学年龄
    private int periodSystem; //学制
    private String state; //状态
    private String periodRemarks; //备注

    private List<String> addGradeName;  //新增年级模块
    private List<GradeInfo> gradeList; //年级名称集合：高一，高二，高三


}
