package com.cj.witcommon.utils.excle;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExportSubjectInfo {

    private String subjectName; //开课名称
    private Date createTime; //开课时间
    private String state; //状态 1：正在使用  0：未使用

    //返回信息处理
    public String getState(){
        return this.state == "1" ? "正在使用" : "停课";
    }

}
