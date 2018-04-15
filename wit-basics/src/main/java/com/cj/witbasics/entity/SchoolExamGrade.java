package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolExamGrade {
    /**
     * 考试成绩等级表
     */
    private Long examGradeId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 考试ID
     */
    private Long examId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 考试科目ID
     */
    private Long examSubjectId;

    /**
     * 档次设置方式，1-分数，2-名次
     */
    private String gradeType;

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
     * 考试成绩等级表
     * @return exam_grade_id 考试成绩等级表
     */
    public Long getExamGradeId() {
        return examGradeId;
    }

    /**
     * 考试成绩等级表
     * @param examGradeId 考试成绩等级表
     */
    public void setExamGradeId(Long examGradeId) {
        this.examGradeId = examGradeId;
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
     * 考试ID
     * @return exam_id 考试ID
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * 考试ID
     * @param examId 考试ID
     */
    public void setExamId(Long examId) {
        this.examId = examId;
    }

    /**
     * 班级ID
     * @return class_id 班级ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 班级ID
     * @param classId 班级ID
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 考试科目ID
     * @return exam_subject_id 考试科目ID
     */
    public Long getExamSubjectId() {
        return examSubjectId;
    }

    /**
     * 考试科目ID
     * @param examSubjectId 考试科目ID
     */
    public void setExamSubjectId(Long examSubjectId) {
        this.examSubjectId = examSubjectId;
    }

    /**
     * 档次设置方式，1-分数，2-名次
     * @return grade_type 档次设置方式，1-分数，2-名次
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 档次设置方式，1-分数，2-名次
     * @param gradeType 档次设置方式，1-分数，2-名次
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
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