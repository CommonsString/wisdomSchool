package com.cj.witcommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 课程设置
 */
@Getter
@Setter
@ToString
public class SubjectUnit {

    private Long periodId;
    private String periodName;
    private Date thetime;
    private List<SubjectDetail> classInfo;

}
