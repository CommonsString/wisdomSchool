package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolExamParent {
    /**
     * 考试信息父节点
     */
    private Long examParentId;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 考试信息父节点
     * @return exam_parent_id 考试信息父节点
     */
    public Long getExamParentId() {
        return examParentId;
    }

    /**
     * 考试信息父节点
     * @param examParentId 考试信息父节点
     */
    public void setExamParentId(Long examParentId) {
        this.examParentId = examParentId;
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
}