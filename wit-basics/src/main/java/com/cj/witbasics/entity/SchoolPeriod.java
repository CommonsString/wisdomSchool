package com.cj.witbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolPeriod {
    /**
     * 学校学段信息表
     */
    private Long periodId;

    /**
     * 学段名称
     */
    private String periodName;

    /**
     * 学段简称
     */
    private String periodAbbreviation;

    /**
     * 入学年龄
     */
    private Integer periodAge;

    /**
     * 学制
     */
    private Integer periodSystem;

    /**
     * 使用状态,0-未使用，1-正在使用，默认为0
     */
    private String periodState;

    /**
     * 显示顺序
     */
    private Integer periodSort;

    /**
     * 备注
     */
    private String periodRemarks;

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
     * 学校（校区）ID
     */
    private Long schoolId;



}