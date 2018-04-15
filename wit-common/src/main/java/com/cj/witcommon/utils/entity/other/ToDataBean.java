package com.cj.witcommon.utils.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDataBean {


    private Long id;
    private String name;
    private String userKey;
    private Date time;
    private String token;
    private String uuid;
    private Integer schoolId;
    private String schoolName;
    private String fullName;
    private String adminId;
    private String adminName;
    private String adminRole;
}
