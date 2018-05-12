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
 * 考试模块
 */
public class PeriodUnderGrade {

    private Long periodId;  //学段Id
    private Integer periodSystem;  //学制
    private String periodName; //学段名称
    private Long schoolId;

    List<GradeInfo> grade; //年级信息


}
