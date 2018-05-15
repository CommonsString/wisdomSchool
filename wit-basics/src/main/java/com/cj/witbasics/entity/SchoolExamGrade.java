package com.cj.witbasics.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "考试参数对象")
public class SchoolExamGrade {
    /**
     * 考试成绩等级表
     */
    private Long examGradeId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 考试父节点ID
     */
    @ApiModelProperty(name = "examParentId",value = "考试父节点ID",dataType = "Long")
    private Long examParentId;

    /**
     * 考试ID
     */
    @ApiModelProperty(name = "examId",value = "考试ID",dataType = "Long")
    private Long examId;

    /**
     * 学段ID
     */
    @ApiModelProperty(name = "classPeriodId",value = "学段ID",dataType = "Integer")
    private Integer classPeriodId;

    /**
     * 届次
     */
    @ApiModelProperty(name = "thetime",value = "届次",dataType = "String")
    private String thetime;

    /**
     * 班级类型ID
     */
    @ApiModelProperty(name = "classTypeId",value = "班级类型ID",dataType = "Integer")
    private Integer classTypeId;



    /**
     * 班级ID
     */
    @ApiModelProperty(name = "classId",value = "班级ID",dataType = "Long")
    private Long classId;

    /**
     * 考试课程ID
     */
    @ApiModelProperty(name = "examSubjectId",value = "考试课程ID",dataType = "Long")
    private Long examSubjectId;

    /**
     * 考试课程
     */
    @ApiModelProperty(name = "examSubject",value = "考试课程",dataType = "String")
    private String examSubject;

    /**
     * 档次设置方式，1-分数，2-名次
     */
    @ApiModelProperty(name = "gradeType",value = "档次设置方式，1-分数，2-名次",dataType = "String")
    private String gradeType;

    /**
     * 创建人ID
     */
    private Long founderId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作员ID
     */
    private Long operatorId;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 0-已删除，1-正常，默认为1
     */
    private String state;

    /**
     * 档次信息集合
     */
    @ApiModelProperty(name = "grades",value = "档次信息集合",dataType = "List")
    private List<Grade> grades;


}