package com.cj.witcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodAndThetime {

    private Long classPeriodId;
    private String classPeriod;
    private List<ClassYear> thisYear;

}
