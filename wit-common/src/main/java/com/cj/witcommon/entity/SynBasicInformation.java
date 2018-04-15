package com.cj.witcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SynBasicInformation {
    private String name; //用户名
    private String nickName; //昵称
    private String englishName; //英文名
    private Date birthday; //yyyy-MM-dd
    private String sex; //1.男，2，女
    private String avatar; //头像
}
