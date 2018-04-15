package com.cj.witbasics.entity;

public class DepartmentAdmin {
    /**
     * 部门——教职工——中间表
     */
    private Integer id;

    /**
     * 部门ID
     */
    private Integer dId;

    /**
     * 教职工ID
     */
    private Long adminId;

    /**
     * 部门——教职工——中间表
     * @return id 部门——教职工——中间表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 部门——教职工——中间表
     * @param id 部门——教职工——中间表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 部门ID
     * @return d_id 部门ID
     */
    public Integer getdId() {
        return dId;
    }

    /**
     * 部门ID
     * @param dId 部门ID
     */
    public void setdId(Integer dId) {
        this.dId = dId;
    }

    /**
     * 教职工ID
     * @return admin_id 教职工ID
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * 教职工ID
     * @param adminId 教职工ID
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}