package com.cj.witbasics.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "档次设置详情")
public class Grade {
    /**
     * 档次信息表
     */
    private Long gradeId;

    /**
     * （pk-考试等级信息表)
     */
    private Long examGradeId;

    /**
     * 档次名称
     */
    @ApiModelProperty(name = "gradeName",value = "档次名称",dataType = "String")
    private String gradeName;

    /**
     * 档次类型，1-分数，2-名次
     */
    @ApiModelProperty(name = "gradeType",value = "档次类型，1-分数，2-名次",dataType = "String")
    private String gradeType;

    /**
     * 分数、名次最大值（分数不包含、满分时除外，名次包含）
     */
    @ApiModelProperty(name = "numMax",value = "分数、名次最大值（分数不包含、满分时除外，名次包含）",dataType = "Integer")
    private Integer numMax;

    /**
     * 分数、名次最小值（包含）
     */
    @ApiModelProperty(name = "numMin",value = "分数、名次最小值（包含）",dataType = "Integer")
    private Integer numMin;


}