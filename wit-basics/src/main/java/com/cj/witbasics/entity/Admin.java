package com.cj.witbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    /**
     * 管理员基础表
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 管理员账号
     */
    private String adminName;

    /**
     * 管理员密码
     */
    private String adminPass;

    /**
     * 密码安全码
     */
    private String saltVal;

    /**
     * 管理员分类，1-普通管理员,2-用户（老师、学生、家长）
     */
    private String adminType;

    /**
     * 状态，1-正常，0-禁用（删除），-1-停用
     */
    private String adminState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;


}