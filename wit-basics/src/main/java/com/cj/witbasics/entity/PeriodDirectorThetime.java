package com.cj.witbasics.entity;

import lombok.ToString;

import java.util.Date;

@ToString
public class PeriodDirectorThetime {
    /**
     * 学段-年级主任-班级届次 关联表
     */
    private Long sdtId;

    /**
     * 学段ID
     */
    private Long periodId;

    /**
     * 年级主任ID
     */
    private Long directorId;

    /**
     * 班级届次=年级主任管理的届次（哪个年级的年级主任）
     */
    private Date thetime;

    /**
     * 创建人Id
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
     * 状态，0-已删除，1-正常，默认为1
     */
    private String state;

    /**
     * 学段-年级主任-班级届次 关联表
     * @return sdt_id 学段-年级主任-班级届次 关联表
     */
    public Long getSdtId() {
        return sdtId;
    }

    /**
     * 学段-年级主任-班级届次 关联表
     * @param sdtId 学段-年级主任-班级届次 关联表
     */
    public void setSdtId(Long sdtId) {
        this.sdtId = sdtId;
    }

    /**
     * 学段ID
     * @return period_id 学段ID
     */
    public Long getPeriodId() {
        return periodId;
    }

    /**
     * 学段ID
     * @param periodId 学段ID
     */
    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    /**
     * 年级主任ID
     * @return director_id 年级主任ID
     */
    public Long getDirectorId() {
        return directorId;
    }

    /**
     * 年级主任ID
     * @param directorId 年级主任ID
     */
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    /**
     * 班级届次=年级主任管理的届次（哪个年级的年级主任）
     * @return thetime 班级届次=年级主任管理的届次（哪个年级的年级主任）
     */
    public Date getThetime() {
        return thetime;
    }

    /**
     * 班级届次=年级主任管理的届次（哪个年级的年级主任）
     * @param thetime 班级届次=年级主任管理的届次（哪个年级的年级主任）
     */
    public void setThetime(Date thetime) {
        this.thetime = thetime;
    }

    /**
     * 创建人Id
     * @return founder_id 创建人Id
     */
    public Long getFounderId() {
        return founderId;
    }

    /**
     * 创建人Id
     * @param founderId 创建人Id
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
     * 状态，0-已删除，1-正常，默认为1
     * @return state 状态，0-已删除，1-正常，默认为1
     */
    public String getState() {
        return state;
    }

    /**
     * 状态，0-已删除，1-正常，默认为1
     * @param state 状态，0-已删除，1-正常，默认为1
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}