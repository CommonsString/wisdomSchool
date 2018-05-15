package com.cj.witcommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 考试模块,查询班级
 */
@ToString
@Setter
@Getter
public class BaseClass {

    private Long classId;
    private String className; //班级名称
    private int classNumber; //班号
    private String classYear; //入学时间
}
