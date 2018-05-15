package com.cj.witcommon.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 课程子类
 */
@Setter
@Getter
@ToString
public class SubjectDetail {

    private Long classId;
    private String className;
    private Long subjectId;
    private String subjectName;

}
