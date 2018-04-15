package com.cj.witbasics.entity;

import java.util.Date;

public class StudentPunishment {
    /**
     * 学生惩罚信息表
     */
    private Long punishmentId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学籍号
     */
    private String registerNumber;

    /**
     * 处分类型（前端单选）
     */
    private String punishmentType;

    /**
     * 处分日期
     */
    private Date punishmentDate;

    /**
     * 处分文号
     */
    private String punishmentNumber;

    /**
     * 处分撤销日期
     */
    private Date punishmentRevokeDate;

    /**
     * 处分撤销文号
     */
    private Date punishmentRevokeNumber;

    /**
     * 处分说明
     */
    private String punishmentExplain;

    /**
     * 附件(存附件地址)
     */
    private String punishmentEnclosure;

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
     * 学生惩罚信息表
     * @return punishment_id 学生惩罚信息表
     */
    public Long getPunishmentId() {
        return punishmentId;
    }

    /**
     * 学生惩罚信息表
     * @param punishmentId 学生惩罚信息表
     */
    public void setPunishmentId(Long punishmentId) {
        this.punishmentId = punishmentId;
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
     * 处分类型（前端单选）
     * @return punishment_type 处分类型（前端单选）
     */
    public String getPunishmentType() {
        return punishmentType;
    }

    /**
     * 处分类型（前端单选）
     * @param punishmentType 处分类型（前端单选）
     */
    public void setPunishmentType(String punishmentType) {
        this.punishmentType = punishmentType == null ? null : punishmentType.trim();
    }

    /**
     * 处分日期
     * @return punishment_date 处分日期
     */
    public Date getPunishmentDate() {
        return punishmentDate;
    }

    /**
     * 处分日期
     * @param punishmentDate 处分日期
     */
    public void setPunishmentDate(Date punishmentDate) {
        this.punishmentDate = punishmentDate;
    }

    /**
     * 处分文号
     * @return punishment_number 处分文号
     */
    public String getPunishmentNumber() {
        return punishmentNumber;
    }

    /**
     * 处分文号
     * @param punishmentNumber 处分文号
     */
    public void setPunishmentNumber(String punishmentNumber) {
        this.punishmentNumber = punishmentNumber == null ? null : punishmentNumber.trim();
    }

    /**
     * 处分撤销日期
     * @return punishment_revoke_date 处分撤销日期
     */
    public Date getPunishmentRevokeDate() {
        return punishmentRevokeDate;
    }

    /**
     * 处分撤销日期
     * @param punishmentRevokeDate 处分撤销日期
     */
    public void setPunishmentRevokeDate(Date punishmentRevokeDate) {
        this.punishmentRevokeDate = punishmentRevokeDate;
    }

    /**
     * 处分撤销文号
     * @return punishment_revoke_number 处分撤销文号
     */
    public Date getPunishmentRevokeNumber() {
        return punishmentRevokeNumber;
    }

    /**
     * 处分撤销文号
     * @param punishmentRevokeNumber 处分撤销文号
     */
    public void setPunishmentRevokeNumber(Date punishmentRevokeNumber) {
        this.punishmentRevokeNumber = punishmentRevokeNumber;
    }

    /**
     * 处分说明
     * @return punishment_explain 处分说明
     */
    public String getPunishmentExplain() {
        return punishmentExplain;
    }

    /**
     * 处分说明
     * @param punishmentExplain 处分说明
     */
    public void setPunishmentExplain(String punishmentExplain) {
        this.punishmentExplain = punishmentExplain == null ? null : punishmentExplain.trim();
    }

    /**
     * 附件(存附件地址)
     * @return punishment_enclosure 附件(存附件地址)
     */
    public String getPunishmentEnclosure() {
        return punishmentEnclosure;
    }

    /**
     * 附件(存附件地址)
     * @param punishmentEnclosure 附件(存附件地址)
     */
    public void setPunishmentEnclosure(String punishmentEnclosure) {
        this.punishmentEnclosure = punishmentEnclosure == null ? null : punishmentEnclosure.trim();
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