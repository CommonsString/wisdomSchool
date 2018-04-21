package com.cj.witbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolPeriodClassThetime {
    /**
     * 学校-学段-班级-班主任-届次关系表
     */
    private Long ssctId;

    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * 学段ID
     */
    private Long periodId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 班主任ID
     */
    private Long adminId;

    /**
     * 班主任名字
     */
    private String headmaster;

    /**
     * 所属届次（2017届-毕业时间）
     */
    private Date thetime;

    /**
     * 创建人Id
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
     * 操作时间
     */
    private Date updateTime;

    /**
     * 状态，0-已删除，1-正常，默认为1
     */
    private String state;


}