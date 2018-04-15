package com.cj.witbasics.entity;

import java.util.Date;

public class SchoolBasic {
    /**
     * 学校基本信息表
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校英文名称
     */
    private String schoolEnglishName;

    /**
     * 学校地址
     */
    private String schoolAddress;

    /**
     * 邮编
     */
    private String schoolZipCode;

    /**
     * 电话
     */
    private String schoolPhone;

    /**
     * 传真
     */
    private String schoolFax;

    /**
     * 邮箱
     */
    private String schoolEMail;

    /**
     * 网址
     */
    private String schoolWebUrl;

    /**
     * 地区编号
     */
    private String areaId;

    /**
     * 市编号
     */
    private String cityId;

    /**
     * 省编号
     */
    private String provinceId;

    /**
     * 第一学期开学时间
     */
    private Date schoolOneOpenTime;

    /**
     * 第二学期开学时间
     */
    private Date schoolTwoOpenTime;

    /**
     * 每个学期周次
     */
    private String schoolCycleSecond;

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
     * 学校基本信息表
     * @return school_id 学校基本信息表
     */
    public Long getSchoolId() {
        return schoolId;
    }

    /**
     * 学校基本信息表
     * @param schoolId 学校基本信息表
     */
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 学校名称
     * @return school_name 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 学校名称
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    /**
     * 学校英文名称
     * @return school_english_name 学校英文名称
     */
    public String getSchoolEnglishName() {
        return schoolEnglishName;
    }

    /**
     * 学校英文名称
     * @param schoolEnglishName 学校英文名称
     */
    public void setSchoolEnglishName(String schoolEnglishName) {
        this.schoolEnglishName = schoolEnglishName == null ? null : schoolEnglishName.trim();
    }

    /**
     * 学校地址
     * @return school_address 学校地址
     */
    public String getSchoolAddress() {
        return schoolAddress;
    }

    /**
     * 学校地址
     * @param schoolAddress 学校地址
     */
    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress == null ? null : schoolAddress.trim();
    }

    /**
     * 邮编
     * @return school_zip_code 邮编
     */
    public String getSchoolZipCode() {
        return schoolZipCode;
    }

    /**
     * 邮编
     * @param schoolZipCode 邮编
     */
    public void setSchoolZipCode(String schoolZipCode) {
        this.schoolZipCode = schoolZipCode == null ? null : schoolZipCode.trim();
    }

    /**
     * 电话
     * @return school_phone 电话
     */
    public String getSchoolPhone() {
        return schoolPhone;
    }

    /**
     * 电话
     * @param schoolPhone 电话
     */
    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone == null ? null : schoolPhone.trim();
    }

    /**
     * 传真
     * @return school_fax 传真
     */
    public String getSchoolFax() {
        return schoolFax;
    }

    /**
     * 传真
     * @param schoolFax 传真
     */
    public void setSchoolFax(String schoolFax) {
        this.schoolFax = schoolFax == null ? null : schoolFax.trim();
    }

    /**
     * 邮箱
     * @return school_e_mail 邮箱
     */
    public String getSchoolEMail() {
        return schoolEMail;
    }

    /**
     * 邮箱
     * @param schoolEMail 邮箱
     */
    public void setSchoolEMail(String schoolEMail) {
        this.schoolEMail = schoolEMail == null ? null : schoolEMail.trim();
    }

    /**
     * 网址
     * @return school_web_url 网址
     */
    public String getSchoolWebUrl() {
        return schoolWebUrl;
    }

    /**
     * 网址
     * @param schoolWebUrl 网址
     */
    public void setSchoolWebUrl(String schoolWebUrl) {
        this.schoolWebUrl = schoolWebUrl == null ? null : schoolWebUrl.trim();
    }

    /**
     * 地区编号
     * @return area_id 地区编号
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 地区编号
     * @param areaId 地区编号
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * 市编号
     * @return city_id 市编号
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 市编号
     * @param cityId 市编号
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 省编号
     * @return province_id 省编号
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 省编号
     * @param provinceId 省编号
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    /**
     * 第一学期开学时间
     * @return school_one_open_time 第一学期开学时间
     */
    public Date getSchoolOneOpenTime() {
        return schoolOneOpenTime;
    }

    /**
     * 第一学期开学时间
     * @param schoolOneOpenTime 第一学期开学时间
     */
    public void setSchoolOneOpenTime(Date schoolOneOpenTime) {
        this.schoolOneOpenTime = schoolOneOpenTime;
    }

    /**
     * 第二学期开学时间
     * @return school_two_open_time 第二学期开学时间
     */
    public Date getSchoolTwoOpenTime() {
        return schoolTwoOpenTime;
    }

    /**
     * 第二学期开学时间
     * @param schoolTwoOpenTime 第二学期开学时间
     */
    public void setSchoolTwoOpenTime(Date schoolTwoOpenTime) {
        this.schoolTwoOpenTime = schoolTwoOpenTime;
    }

    /**
     * 每个学期周次
     * @return school_cycle_second 每个学期周次
     */
    public String getSchoolCycleSecond() {
        return schoolCycleSecond;
    }

    /**
     * 每个学期周次
     * @param schoolCycleSecond 每个学期周次
     */
    public void setSchoolCycleSecond(String schoolCycleSecond) {
        this.schoolCycleSecond = schoolCycleSecond == null ? null : schoolCycleSecond.trim();
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