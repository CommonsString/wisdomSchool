package com.cj.witbasics.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SchoolSubject {
    /**
     * 学校课程表
     */
    private Long subjectId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 科目表ID
     */
    private Long subjectsId;

    /**
     * 课程名称
     */
    private String subjectName;

    /**
     * 课程英文名称
     */
    private String subjectEnglishName;

    /**
     * 是否必修,0-否，1-是，默认为0
     */
    private String subjectRequired;

    /**
     * 成绩单是否显示，0-否，1-是，默认为0
     */
    private String subjectReport;

    /**
     * 显示顺序
     */
    private Integer subjectSort;

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
     * 0-停课，1-正常，默认为1
     */
    private String isBegin;

    /**
     * 学校课程表
     * @return subject_id 学校课程表
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * 学校课程表
     * @param subjectId 学校课程表
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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
     * 科目表ID
     * @return subjects_id 科目表ID
     */
    public Long getSubjectsId() {
        return subjectsId;
    }

    /**
     * 科目表ID
     * @param subjectsId 科目表ID
     */
    public void setSubjectsId(Long subjectsId) {
        this.subjectsId = subjectsId;
    }

    /**
     * 课程名称
     * @return subject_name 课程名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 课程名称
     * @param subjectName 课程名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 课程英文名称
     * @return subject_english_name 课程英文名称
     */
    public String getSubjectEnglishName() {
        return subjectEnglishName;
    }

    /**
     * 课程英文名称
     * @param subjectEnglishName 课程英文名称
     */
    public void setSubjectEnglishName(String subjectEnglishName) {
        this.subjectEnglishName = subjectEnglishName == null ? null : subjectEnglishName.trim();
    }

    /**
     * 是否必修,0-否，1-是，默认为0
     * @return subject_required 是否必修,0-否，1-是，默认为0
     */
    public String getSubjectRequired() {
        return subjectRequired;
    }

    /**
     * 是否必修,0-否，1-是，默认为0
     * @param subjectRequired 是否必修,0-否，1-是，默认为0
     */
    public void setSubjectRequired(String subjectRequired) {
        this.subjectRequired = subjectRequired == null ? null : subjectRequired.trim();
    }

    /**
     * 成绩单是否显示，0-否，1-是，默认为0
     * @return subject_report 成绩单是否显示，0-否，1-是，默认为0
     */
    public String getSubjectReport() {
        return subjectReport;
    }

    /**
     * 成绩单是否显示，0-否，1-是，默认为0
     * @param subjectReport 成绩单是否显示，0-否，1-是，默认为0
     */
    public void setSubjectReport(String subjectReport) {
        this.subjectReport = subjectReport == null ? null : subjectReport.trim();
    }

    /**
     * 显示顺序
     * @return subject_sort 显示顺序
     */
    public Integer getSubjectSort() {
        return subjectSort;
    }

    /**
     * 显示顺序
     * @param subjectSort 显示顺序
     */
    public void setSubjectSort(Integer subjectSort) {
        this.subjectSort = subjectSort;
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
}