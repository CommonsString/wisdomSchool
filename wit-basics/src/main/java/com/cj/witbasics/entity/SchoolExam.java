package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolExam {
    /**
     * 学校考试详细信息表
     */
    private Long examId;

    /**
     * 考试父节点ID
     */
    private Long examParentId;

    /**
     * 届次
     */
    private Date thetime;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 考试对象ID
     */
    private Long examObjectId;

    /**
     * 考试对象名称
     */
    private String examObject;

    /**
     * 考试类别（暂时不用）
     */
    private String examType;

    /**
     * 考试类别名称
     */
    private String examTypeName;

    /**
     * 考试时间
     */
    private Date examTime;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 考试科目ID
     */
    private Long examSubjectId;

    /**
     * 考试科目名称
     */
    private String examSubject;

    /**
     * 考试班级ID
     */
    private Integer classId;

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
     * 学校考试详细信息表
     * @return exam_id 学校考试详细信息表
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * 学校考试详细信息表
     * @param examId 学校考试详细信息表
     */
    public void setExamId(Long examId) {
        this.examId = examId;
    }

    /**
     * 考试父节点ID
     * @return exam_parent_id 考试父节点ID
     */
    public Long getExamParentId() {
        return examParentId;
    }

    /**
     * 考试父节点ID
     * @param examParentId 考试父节点ID
     */
    public void setExamParentId(Long examParentId) {
        this.examParentId = examParentId;
    }

    /**
     * 届次
     * @return thetime 届次
     */
    public Date getThetime() {
        return thetime;
    }

    /**
     * 届次
     * @param thetime 届次
     */
    public void setThetime(Date thetime) {
        this.thetime = thetime;
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
     * 考试对象ID
     * @return exam_object_id 考试对象ID
     */
    public Long getExamObjectId() {
        return examObjectId;
    }

    /**
     * 考试对象ID
     * @param examObjectId 考试对象ID
     */
    public void setExamObjectId(Long examObjectId) {
        this.examObjectId = examObjectId;
    }

    /**
     * 考试对象名称
     * @return exam_object 考试对象名称
     */
    public String getExamObject() {
        return examObject;
    }

    /**
     * 考试对象名称
     * @param examObject 考试对象名称
     */
    public void setExamObject(String examObject) {
        this.examObject = examObject == null ? null : examObject.trim();
    }

    /**
     * 考试类别（暂时不用）
     * @return exam_type 考试类别（暂时不用）
     */
    public String getExamType() {
        return examType;
    }

    /**
     * 考试类别（暂时不用）
     * @param examType 考试类别（暂时不用）
     */
    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    /**
     * 考试类别名称
     * @return exam_type_name 考试类别名称
     */
    public String getExamTypeName() {
        return examTypeName;
    }

    /**
     * 考试类别名称
     * @param examTypeName 考试类别名称
     */
    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName == null ? null : examTypeName.trim();
    }

    /**
     * 考试时间
     * @return exam_time 考试时间
     */
    public Date getExamTime() {
        return examTime;
    }

    /**
     * 考试时间
     * @param examTime 考试时间
     */
    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    /**
     * 考试名称
     * @return exam_name 考试名称
     */
    public String getExamName() {
        return examName;
    }

    /**
     * 考试名称
     * @param examName 考试名称
     */
    public void setExamName(String examName) {
        this.examName = examName == null ? null : examName.trim();
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
     * 考试科目名称
     * @return exam_subject 考试科目名称
     */
    public String getExamSubject() {
        return examSubject;
    }

    /**
     * 考试科目名称
     * @param examSubject 考试科目名称
     */
    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject == null ? null : examSubject.trim();
    }

    /**
     * 考试班级ID
     * @return class_id 考试班级ID
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 考试班级ID
     * @param classId 考试班级ID
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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