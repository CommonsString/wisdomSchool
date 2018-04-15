package com.cj.witcommon.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
/**
 * 组合类：
 * SchoolPeriodInfo的子类：
 * 用于封装年级信息
 */
public class GradeInfo {

    private Long gradeId; //年级ID
    private String gradeName; //年级名称

}
