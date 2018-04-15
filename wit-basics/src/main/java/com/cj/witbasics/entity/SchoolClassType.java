package com.cj.witbasics.entity;

import lombok.ToString;

@ToString
public class SchoolClassType {
    /**
     * 班级类型ID
     */
    private Integer classTypeId;

    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * 班级类型名称
     */
    private String classtypeName;


    /**
     * 是否可编辑
     */
    private String isEdit;


    /**
     * 是否可编辑
     * @return isEdit 班级类型名称
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * 是否可编辑
     * @param isEdit
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit == null ? null : isEdit.trim();
    }


    /**
     * 班级类型ID
     * @return class_type_id 班级类型ID
     */
    public Integer getClassTypeId() {
        return classTypeId;
    }

    /**
     * 班级类型ID
     * @param classTypeId 班级类型ID
     */
    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    /**
     * 学校ID
     * @return school_id 学校ID
     */
    public Long getSchoolId() {
        return schoolId;
    }

    /**
     * 学校ID
     * @param schoolId 学校ID
     */
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 班级类型名称
     * @return classtype_name 班级类型名称
     */
    public String getClasstypeName() {
        return classtypeName;
    }

    /**
     * 班级类型名称
     * @param classtypeName 班级类型名称
     */
    public void setClasstypeName(String classtypeName) {
        this.classtypeName = classtypeName == null ? null : classtypeName.trim();
    }
}