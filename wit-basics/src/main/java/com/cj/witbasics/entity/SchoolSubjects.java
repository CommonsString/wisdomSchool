package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolSubjects {
    /**
     * 科目表
     */
    private Long subjectsId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 科目名称
     */
    private String subjectsName;

    /**
     * 科目英文名称
     */
    private String subjectsEnglishName;

    /**
     * 显示顺序
     */
    private Integer subjectsSort;

    /**
     * 0-停课，1-正常，默认为1
     */
    private String isBegin;

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
     * 操作时间
     */
    private Date updateTime;

    /**
     * 0-已删除，1-正常，默认为1
     */
    private String state;

    /**
     * 科目表
     * @return subjects_id 科目表
     */
    public Long getSubjectsId() {
        return subjectsId;
    }

    /**
     * 科目表
     * @param subjectsId 科目表
     */
    public void setSubjectsId(Long subjectsId) {
        this.subjectsId = subjectsId;
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
     * 科目名称
     * @return subjects_name 科目名称
     */
    public String getSubjectsName() {
        return subjectsName;
    }

    /**
     * 科目名称
     * @param subjectsName 科目名称
     */
    public void setSubjectsName(String subjectsName) {
        this.subjectsName = subjectsName == null ? null : subjectsName.trim();
    }

    /**
     * 科目英文名称
     * @return subjects_english_name 科目英文名称
     */
    public String getSubjectsEnglishName() {
        return subjectsEnglishName;
    }

    /**
     * 科目英文名称
     * @param subjectsEnglishName 科目英文名称
     */
    public void setSubjectsEnglishName(String subjectsEnglishName) {
        this.subjectsEnglishName = subjectsEnglishName == null ? null : subjectsEnglishName.trim();
    }

    /**
     * 显示顺序
     * @return subjects_sort 显示顺序
     */
    public Integer getSubjectsSort() {
        return subjectsSort;
    }

    /**
     * 显示顺序
     * @param subjectsSort 显示顺序
     */
    public void setSubjectsSort(Integer subjectsSort) {
        this.subjectsSort = subjectsSort;
    }

    /**
     * 0-停课，1-正常，默认为1
     * @return is_begin 0-停课，1-正常，默认为1
     */
    public String getIsBegin() {
        return isBegin;
    }

    /**
     * 0-停课，1-正常，默认为1
     * @param isBegin 0-停课，1-正常，默认为1
     */
    public void setIsBegin(String isBegin) {
        this.isBegin = isBegin == null ? null : isBegin.trim();
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
     * 操作时间
     * @return update_time 操作时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 操作时间
     * @param updateTime 操作时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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