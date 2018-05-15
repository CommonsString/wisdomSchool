package com.cj.witcommon.utils.excle;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ScoreModelTwoInfo {

    @IsNeeded
    private String className; //班级名称
    @IsNeeded
    private String registerNumber; //学籍号
    @IsNeeded
    private String classNumber; //班内学号, 学生信息表 srudent_osaas
    @IsNeeded
    private String studentName; //学生名字

    ///////////////////////////////////////////////
    @IsNeeded
    private String scoreTotal; //总分
    @IsNeeded
    private String totalGradeRank; //总分年级排名
    @IsNeeded
    private String totalClassRank; //总分班级排名

    @IsNeeded
    private String scoreChinese; //语文总分
    @IsNeeded
    private String chineseGradeRank; //语文年级排名
    @IsNeeded
    private String chineseClassRank; //语文班级排名

    @IsNeeded
    private String scoreMatch; //数学总分
    @IsNeeded
    private String matchGradeRank; //数学总分年级排名
    @IsNeeded
    private String matchClassRank; //数学总分班级排名

    @IsNeeded
    private String scoreEnglish; //英语总分
    @IsNeeded
    private String englishGradeRank; //英语总分年级排名
    @IsNeeded
    private String englishClassRank; //英语总分班级排名

    @IsNeeded
    private String scorePhysical; //物理总分
    @IsNeeded
    private String physicalGradeRank; //物理总分年级排名
    @IsNeeded
    private String physicalClassRank; //物理总分班级排名

    @IsNeeded
    private String scoreChemistry; //化学总分
    @IsNeeded
    private String chemistryGradeRank; //化学总分年级排名
    @IsNeeded
    private String chemistryClassRank; //化学总分班级排名

    @IsNeeded
    private String scoreHistory; //历史总分
    @IsNeeded
    private String historyGradeRank; //历史总分年级排名
    @IsNeeded
    private String historyClassRank; //历史总分班级排名


    @IsNeeded
    private String scoreBbs; //政治总分
    @IsNeeded
    private String bbsGradeRank; //政治总分年级排名
    @IsNeeded
    private String bbsClassRank; //政治总分班级排名

}
