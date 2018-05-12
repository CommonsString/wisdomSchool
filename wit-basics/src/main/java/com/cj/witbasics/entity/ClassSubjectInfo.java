package com.cj.witbasics.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassSubjectInfo {
    /**
     * 班级-科目-管理员信息(查询)
     */
    private Long clsSubId;

    /**
     * 班级信息ID
     */
    private Long classId;

    /**
     * 科目信息ID
     */
    private Long subjectId;

    /**
     * 管理员(教师)ID
     */
    private Long adminInfoId;

    private String state;
    /**
     * 班级-科目-管理员信息(查询)
     * @return cls_sub_id 班级-科目-管理员信息(查询)
     */
    public Long getClsSubId() {
        return clsSubId;
    }

    /**
     * 班级-科目-管理员信息(查询)
     * @param clsSubId 班级-科目-管理员信息(查询)
     */
    public void setClsSubId(Long clsSubId) {
        this.clsSubId = clsSubId;
    }

    /**
     * 班级信息ID
     * @return class_id 班级信息ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 班级信息ID
     * @param classId 班级信息ID
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 科目信息ID
     * @return subject_id 科目信息ID
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * 科目信息ID
     * @param subjectId 科目信息ID
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 管理员(教师)ID
     * @return admin_info_id 管理员(教师)ID
     */
    public Long getAdminInfoId() {
        return adminInfoId;
    }

    /**
     * 管理员(教师)ID
     * @param adminInfoId 管理员(教师)ID
     */
    public void setAdminInfoId(Long adminInfoId) {
        this.adminInfoId = adminInfoId;
    }
}