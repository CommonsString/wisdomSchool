package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolBuild {
    /**
     * 学校建筑信息表
     */
    private Long buildId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 建筑名称
     */
    private String buildName;

    /**
     * 建筑编号
     */
    private String buildCode;

    /**
     * 建筑类型（前端单选）
     */
    private String buildType;

    /**
     * 所在校区（前端单选）
     */
    private String buildCampus;

    /**
     * 建筑地址
     */
    private String buildAddress;

    /**
     * 建筑描述
     */
    private String buildDescribe;

    /**
     * 创建人ID
     */
    private Long founderId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作员ID
     */
    private Long operatorId;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 0-已删除，1-正常，默认为1
     */
    private String state;

    /**
     * 学校建筑信息表
     * @return build_id 学校建筑信息表
     */
    public Long getBuildId() {
        return buildId;
    }

    /**
     * 学校建筑信息表
     * @param buildId 学校建筑信息表
     */
    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    /**
     * 学校（校区）ID
     * @return school_id 学校（校区）ID
     */
    public Long getSchoolId() {
        return schoolId;
    }

    /**
     * 学校（校区）ID
     * @param schoolId 学校（校区）ID
     */
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 建筑名称
     * @return build_name 建筑名称
     */
    public String getBuildName() {
        return buildName;
    }

    /**
     * 建筑名称
     * @param buildName 建筑名称
     */
    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    /**
     * 建筑编号
     * @return build_code 建筑编号
     */
    public String getBuildCode() {
        return buildCode;
    }

    /**
     * 建筑编号
     * @param buildCode 建筑编号
     */
    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode == null ? null : buildCode.trim();
    }

    /**
     * 建筑类型（前端单选）
     * @return build_type 建筑类型（前端单选）
     */
    public String getBuildType() {
        return buildType;
    }

    /**
     * 建筑类型（前端单选）
     * @param buildType 建筑类型（前端单选）
     */
    public void setBuildType(String buildType) {
        this.buildType = buildType == null ? null : buildType.trim();
    }

    /**
     * 所在校区（前端单选）
     * @return build_campus 所在校区（前端单选）
     */
    public String getBuildCampus() {
        return buildCampus;
    }

    /**
     * 所在校区（前端单选）
     * @param buildCampus 所在校区（前端单选）
     */
    public void setBuildCampus(String buildCampus) {
        this.buildCampus = buildCampus == null ? null : buildCampus.trim();
    }

    /**
     * 建筑地址
     * @return build_address 建筑地址
     */
    public String getBuildAddress() {
        return buildAddress;
    }

    /**
     * 建筑地址
     * @param buildAddress 建筑地址
     */
    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress == null ? null : buildAddress.trim();
    }

    /**
     * 建筑描述
     * @return build_describe 建筑描述
     */
    public String getBuildDescribe() {
        return buildDescribe;
    }

    /**
     * 建筑描述
     * @param buildDescribe 建筑描述
     */
    public void setBuildDescribe(String buildDescribe) {
        this.buildDescribe = buildDescribe == null ? null : buildDescribe.trim();
    }

    /**
     * 创建人ID
     * @return founder_id 创建人ID
     */
    public Long getFounderId() {
        return founderId;
    }

    /**
     * 创建人ID
     * @param founderId 创建人ID
     */
    public void setFounderId(Long founderId) {
        this.founderId = founderId;
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

    /**
     * 操作员ID
     * @return operator_id 操作员ID
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作员ID
     * @param operatorId 操作员ID
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 删除时间
     * @return delete_time 删除时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 删除时间
     * @param deleteTime 删除时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * 0-已删除，1-正常，默认为1
     * @return state 0-已删除，1-正常，默认为1
     */
    public String getState() {
        return state;
    }

    /**
     * 0-已删除，1-正常，默认为1
     * @param state 0-已删除，1-正常，默认为1
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}