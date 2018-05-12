package com.cj.witcommon.utils.excle;


import lombok.*;

/**
 * 成绩导入组合类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentScoreInfo {

    @IsNeeded
    private String className; //班级名称
//
//    @IsNeeded
//    private String examNumber; //考号
    @IsNeeded
    private String registerNumber; //学籍号
//    @IsNeeded
//    private String schoolNumber; //校内学号  srudent_osaas
//    @IsNeeded
//    private String classNumber; //班内学号, 学生信息表 srudent_osaas
    @IsNeeded
    private String studentName; //学生名字
//    @IsNeeded
//    private String name;
    @IsNeeded
    private String chineseScore; //语文成绩
    @IsNeeded
    private String mathScore; //数学成绩
    @IsNeeded
    private String englishScore; //英语成绩
    @IsNeeded
    private String physicalScore; //物理成绩
    @IsNeeded
    private String chemistryScore; //化学成绩
    @IsNeeded
    private String biologicalScore; //生物成绩
}
