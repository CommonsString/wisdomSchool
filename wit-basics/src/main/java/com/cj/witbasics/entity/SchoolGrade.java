package com.cj.witbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolGrade {
    /**
     * 学段年级信息表
     */
    private Long gradeId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 学段ID
     */
    private Long periodId;

    /**
     * 年级名称
     */
    private String gradeName;


    /**
     * 0-已删除，1-正常，默认为1
     */
    private String state;


}