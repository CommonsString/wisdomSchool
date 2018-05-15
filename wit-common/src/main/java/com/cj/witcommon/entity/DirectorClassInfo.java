package com.cj.witcommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class DirectorClassInfo {

    private Long periodId;
    private Long directorId;
    private String fullName;
    private Date thetime;

}
