package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolCurriculum {
    /**
     * 学校开设课程信息表
     */
    private Long curriculumId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 课程名称
     */
    private String curriculumName;

    /**
     * 课程简称
     */
    private String curriculumAbbreviation;

    /**
     * 所属科目(前端单选)
     */
    private String curriculumSubject;

    /**
     * 是否必修，1-必修,2-选修，默认为2
     */
    private String curriculumRequired;

    /**
     * 显示顺序
     */
    private Integer curriculumSort;

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
     * 学校开设课程信息表
     * @return curriculum_id 学校开设课程信息表
     */
    public Long getCurriculumId() {
        return curriculumId;
    }

    /**
     * 学校开设课程信息表
     * @param curriculumId 学校开设课程信息表
     */
    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
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
     * 课程名称
     * @return curriculum_name 课程名称
     */
    public String getCurriculumName() {
        return curriculumName;
    }

    /**
     * 课程名称
     * @param curriculumName 课程名称
     */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName == null ? null : curriculumName.trim();
    }

    /**
     * 课程简称
     * @return curriculum_abbreviation 课程简称
     */
    public String getCurriculumAbbreviation() {
        return curriculumAbbreviation;
    }

    /**
     * 课程简称
     * @param curriculumAbbreviation 课程简称
     */
    public void setCurriculumAbbreviation(String curriculumAbbreviation) {
        this.curriculumAbbreviation = curriculumAbbreviation == null ? null : curriculumAbbreviation.trim();
    }

    /**
     * 所属科目(前端单选)
     * @return curriculum_subject 所属科目(前端单选)
     */
    public String getCurriculumSubject() {
        return curriculumSubject;
    }

    /**
     * 所属科目(前端单选)
     * @param curriculumSubject 所属科目(前端单选)
     */
    public void setCurriculumSubject(String curriculumSubject) {
        this.curriculumSubject = curriculumSubject == null ? null : curriculumSubject.trim();
    }

    /**
     * 是否必修，1-必修,2-选修，默认为2
     * @return curriculum_required 是否必修，1-必修,2-选修，默认为2
     */
    public String getCurriculumRequired() {
        return curriculumRequired;
    }

    /**
     * 是否必修，1-必修,2-选修，默认为2
     * @param curriculumRequired 是否必修，1-必修,2-选修，默认为2
     */
    public void setCurriculumRequired(String curriculumRequired) {
        this.curriculumRequired = curriculumRequired == null ? null : curriculumRequired.trim();
    }

    /**
     * 显示顺序
     * @return curriculum_sort 显示顺序
     */
    public Integer getCurriculumSort() {
        return curriculumSort;
    }

    /**
     * 显示顺序
     * @param curriculumSort 显示顺序
     */
    public void setCurriculumSort(Integer curriculumSort) {
        this.curriculumSort = curriculumSort;
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