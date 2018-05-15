package com.cj.witcommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 考试模块,班级查询
 */
@ToString
@Setter
@Getter
public class ExamClassInfo {
    private Long periodId;
    private String periodName;
    private Long gradeId;
    private String gradeName;
    List<BaseClass> classInfo;
}
