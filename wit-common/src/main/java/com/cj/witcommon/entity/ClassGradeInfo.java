package com.cj.witcommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClassGradeInfo {

    private String className; //班级名称
    private Long subjectId; //科目ID
    private String subjectName; //科目名称
    private double minScore; //最低分
    private double maxScore; //最高分
    private double avgScore; //平均分
    private int actuallyCome; //实考人数
    private int notCome; //缺考人数
    private long rank; //排名

}
