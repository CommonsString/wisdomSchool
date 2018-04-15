package com.cj.witcommon.utils.entity.other;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiReturnUpload {

    private int code;
    private String msg;
    private ToDataBeanUpload data;
    private String params;
    private boolean succeed;
}
