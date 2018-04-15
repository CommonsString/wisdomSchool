package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolRoom {
    /**
     * 学校房间信息表
     */
    private Long roomId;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 房间类型(前端单选）
     */
    private String roomType;

    /**
     * 房间编号
     */
    private String roomCode;

    /**
     * 校区ID
     */
    private Integer campusId;

    /**
     * 所在校区(前端单选）
     */
    private String roomCampus;

    /**
     * 建筑ID
     */
    private Integer buildId;

    /**
     * 所属楼宇
     */
    private String roomBuilding;

    /**
     * 所在楼层
     */
    private String roomFloor;

    /**
     * 容纳人数
     */
    private String roomCapacity;

    /**
     * 有效座位数
     */
    private String roomSeat;

    /**
     * 考试座位数
     */
    private String roomExamSeat;

    /**
     * 是否用于教学,0-否，1-是
     */
    private String roomTeaching;

    /**
     * 责任人/房间管理员(可以选择)
     */
    private String roomAdmin;

    /**
     * 备注
     */
    private String roomRemarks;

    /**
     * 房间物品
     */
    private String roomGoods;

    /**
     * 物品编号
     */
    private String roomGoodsCode;

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
     * 学校房间信息表
     * @return room_id 学校房间信息表
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * 学校房间信息表
     * @param roomId 学校房间信息表
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
     * 房间名称
     * @return room_name 房间名称
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 房间名称
     * @param roomName 房间名称
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    /**
     * 房间类型(前端单选）
     * @return room_type 房间类型(前端单选）
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 房间类型(前端单选）
     * @param roomType 房间类型(前端单选）
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * 房间编号
     * @return room_code 房间编号
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * 房间编号
     * @param roomCode 房间编号
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * 校区ID
     * @return campus_id 校区ID
     */
    public Integer getCampusId() {
        return campusId;
    }

    /**
     * 校区ID
     * @param campusId 校区ID
     */
    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    /**
     * 所在校区(前端单选）
     * @return room_campus 所在校区(前端单选）
     */
    public String getRoomCampus() {
        return roomCampus;
    }

    /**
     * 所在校区(前端单选）
     * @param roomCampus 所在校区(前端单选）
     */
    public void setRoomCampus(String roomCampus) {
        this.roomCampus = roomCampus == null ? null : roomCampus.trim();
    }

    /**
     * 建筑ID
     * @return build_id 建筑ID
     */
    public Integer getBuildId() {
        return buildId;
    }

    /**
     * 建筑ID
     * @param buildId 建筑ID
     */
    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    /**
     * 所属楼宇
     * @return room_building 所属楼宇
     */
    public String getRoomBuilding() {
        return roomBuilding;
    }

    /**
     * 所属楼宇
     * @param roomBuilding 所属楼宇
     */
    public void setRoomBuilding(String roomBuilding) {
        this.roomBuilding = roomBuilding == null ? null : roomBuilding.trim();
    }

    /**
     * 所在楼层
     * @return room_floor 所在楼层
     */
    public String getRoomFloor() {
        return roomFloor;
    }

    /**
     * 所在楼层
     * @param roomFloor 所在楼层
     */
    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor == null ? null : roomFloor.trim();
    }

    /**
     * 容纳人数
     * @return room_capacity 容纳人数
     */
    public String getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * 容纳人数
     * @param roomCapacity 容纳人数
     */
    public void setRoomCapacity(String roomCapacity) {
        this.roomCapacity = roomCapacity == null ? null : roomCapacity.trim();
    }

    /**
     * 有效座位数
     * @return room_seat 有效座位数
     */
    public String getRoomSeat() {
        return roomSeat;
    }

    /**
     * 有效座位数
     * @param roomSeat 有效座位数
     */
    public void setRoomSeat(String roomSeat) {
        this.roomSeat = roomSeat == null ? null : roomSeat.trim();
    }

    /**
     * 考试座位数
     * @return room_exam_seat 考试座位数
     */
    public String getRoomExamSeat() {
        return roomExamSeat;
    }

    /**
     * 考试座位数
     * @param roomExamSeat 考试座位数
     */
    public void setRoomExamSeat(String roomExamSeat) {
        this.roomExamSeat = roomExamSeat == null ? null : roomExamSeat.trim();
    }

    /**
     * 是否用于教学,0-否，1-是
     * @return room_teaching 是否用于教学,0-否，1-是
     */
    public String getRoomTeaching() {
        return roomTeaching;
    }

    /**
     * 是否用于教学,0-否，1-是
     * @param roomTeaching 是否用于教学,0-否，1-是
     */
    public void setRoomTeaching(String roomTeaching) {
        this.roomTeaching = roomTeaching == null ? null : roomTeaching.trim();
    }

    /**
     * 责任人/房间管理员(可以选择)
     * @return room_admin 责任人/房间管理员(可以选择)
     */
    public String getRoomAdmin() {
        return roomAdmin;
    }

    /**
     * 责任人/房间管理员(可以选择)
     * @param roomAdmin 责任人/房间管理员(可以选择)
     */
    public void setRoomAdmin(String roomAdmin) {
        this.roomAdmin = roomAdmin == null ? null : roomAdmin.trim();
    }

    /**
     * 备注
     * @return room_remarks 备注
     */
    public String getRoomRemarks() {
        return roomRemarks;
    }

    /**
     * 备注
     * @param roomRemarks 备注
     */
    public void setRoomRemarks(String roomRemarks) {
        this.roomRemarks = roomRemarks == null ? null : roomRemarks.trim();
    }

    /**
     * 房间物品
     * @return room_goods 房间物品
     */
    public String getRoomGoods() {
        return roomGoods;
    }

    /**
     * 房间物品
     * @param roomGoods 房间物品
     */
    public void setRoomGoods(String roomGoods) {
        this.roomGoods = roomGoods == null ? null : roomGoods.trim();
    }

    /**
     * 物品编号
     * @return room_goods_code 物品编号
     */
    public String getRoomGoodsCode() {
        return roomGoodsCode;
    }

    /**
     * 物品编号
     * @param roomGoodsCode 物品编号
     */
    public void setRoomGoodsCode(String roomGoodsCode) {
        this.roomGoodsCode = roomGoodsCode == null ? null : roomGoodsCode.trim();
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