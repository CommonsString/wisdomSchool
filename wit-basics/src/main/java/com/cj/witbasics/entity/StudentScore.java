package com.cj.witbasics.entity;

import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
public class StudentScore {
    /**
     * 学生成绩信息表
     */
    private Long scoreId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 考试父ID
     */
    private Long examParentId;

    /**
     * 考试ID
     */
    private Long examId;

    /**
     * 届次
     */
    private Date thetime;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 学籍号
     */
    private String registerNumber;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学期（前端单选）
     */
    private String schoolStageId;

    /**
     * 课程ID（pk-学校课程信息表）
     */
    private Long schoolSubjectId;

    /**
     * 课程成绩
     */
    private BigDecimal score;

    /**
     * 课程总分
     */
    private String subjectTotalScore;

    /**
     * 年级排名
     */
    private Integer gradeRanking;

    /**
     * 班级排名
     */
    private Integer classRanking;

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
     * 学生成绩信息表
     * @return score_id 学生成绩信息表
     */
    public Long getScoreId() {
        return scoreId;
    }

    /**
     * 学生成绩信息表
     * @param scoreId 学生成绩信息表
     */
    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
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
     * 考试父ID
     * @return exam_parent_id 考试父ID
     */
    public Long getExamParentId() {
        return examParentId;
    }

    /**
     * 考试父ID
     * @param examParentId 考试父ID
     */
    public void setExamParentId(Long examParentId) {
        this.examParentId = examParentId;
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
     * 学籍号
     * @return register_number 学籍号
     */
    public String getRegisterNumber() {
        return registerNumber;
    }

    /**
     * 学籍号
     * @param registerNumber 学籍号
     */
    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber == null ? null : registerNumber.trim();
    }

    /**
     * 学生姓名
     * @return student_name 学生姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 学生姓名
     * @param studentName 学生姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    /**
     * 学期（前端单选）
     * @return school_stage_id 学期（前端单选）
     */
    public String getSchoolStageId() {
        return schoolStageId;
    }

    /**
     * 学期（前端单选）
     * @param schoolStageId 学期（前端单选）
     */
    public void setSchoolStageId(String schoolStageId) {
        this.schoolStageId = schoolStageId == null ? null : schoolStageId.trim();
    }

    /**
     * 课程ID（pk-学校课程信息表）
     * @return school_subject_id 课程ID（pk-学校课程信息表）
     */
    public Long getSchoolSubjectId() {
        return schoolSubjectId;
    }

    /**
     * 课程ID（pk-学校课程信息表）
     * @param schoolSubjectId 课程ID（pk-学校课程信息表）
     */
    public void setSchoolSubjectId(Long schoolSubjectId) {
        this.schoolSubjectId = schoolSubjectId;
    }

    /**
     * 课程成绩
     * @return score 课程成绩
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 课程成绩
     * @param score 课程成绩
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 课程总分
     * @return subject_total_score 课程总分
     */
    public String getSubjectTotalScore() {
        return subjectTotalScore;
    }

    /**
     * 课程总分
     * @param subjectTotalScore 课程总分
     */
    public void setSubjectTotalScore(String subjectTotalScore) {
        this.subjectTotalScore = subjectTotalScore == null ? null : subjectTotalScore.trim();
    }

    /**
     * 年级排名
     * @return grade_ranking 年级排名
     */
    public Integer getGradeRanking() {
        return gradeRanking;
    }

    /**
     * 年级排名
     * @param gradeRanking 年级排名
     */
    public void setGradeRanking(Integer gradeRanking) {
        this.gradeRanking = gradeRanking;
    }

    /**
     * 班级排名
     * @return class_ranking 班级排名
     */
    public Integer getClassRanking() {
        return classRanking;
    }

    /**
     * 班级排名
     * @param classRanking 班级排名
     */
    public void setClassRanking(Integer classRanking) {
        this.classRanking = classRanking;
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