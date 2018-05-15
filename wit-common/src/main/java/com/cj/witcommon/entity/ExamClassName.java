package com.cj.witcommon.entity;

import lombok.*;

import java.util.List;

/**
 * 封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamClassName {

    private Long classId;
    private String className;
    private List<ExamSubject> examSubjects;
}
