package com.cj.witbasics.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminRole implements Serializable {
    /**
     * 管理员角色表
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色分类，0：系统用户 	1: 系统管理员 	2: 学校管理员 	3: 老师 	4：学生 	5：家长 	6：其他
     */
    private String type;

    /**
     * 状态（1-正常，0-已删除）
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 管理员角色表
     * @return id 管理员角色表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 管理员角色表
     * @param id 管理员角色表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 角色分类，0：系统用户 	1: 系统管理员 	2: 学校管理员 	3: 老师 	4：学生 	5：家长 	6：其他
     * @return type 角色分类，0：系统用户 	1: 系统管理员 	2: 学校管理员 	3: 老师 	4：学生 	5：家长 	6：其他
     */
    public String getType() {
        return type;
    }

    /**
     * 角色分类，0：系统用户 	1: 系统管理员 	2: 学校管理员 	3: 老师 	4：学生 	5：家长 	6：其他
     * @param type 角色分类，0：系统用户 	1: 系统管理员 	2: 学校管理员 	3: 老师 	4：学生 	5：家长 	6：其他
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 状态（1-正常，0-已删除）
     * @return state 状态（1-正常，0-已删除）
     */
    public String getState() {
        return state;
    }

    /**
     * 状态（1-正常，0-已删除）
     * @param state 状态（1-正常，0-已删除）
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}