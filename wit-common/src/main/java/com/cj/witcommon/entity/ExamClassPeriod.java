package com.cj.witcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamClassPeriod {

    private Long classPeriodId;
    private String classPeriod;
    private String classTypeId;
    private String classType;
    private String thetime;
    private List<ExamClassName> examClassNames;
    private List<ExamSubject> examSubjects;
}
