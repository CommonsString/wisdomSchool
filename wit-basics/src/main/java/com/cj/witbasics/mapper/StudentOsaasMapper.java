package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.StudentOsaas;
import com.cj.witcommon.utils.entity.other.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentOsaasMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long osaasId);

    /**
     *
     * 添加学生信息
     */
    int insert(StudentOsaas record);

    /*
    *根据学号查重
    * */
     int selectBySchoolNumber(String schoolNumber);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(StudentOsaas record);

    /**
     *
     * @mbggenerated
     */
    StudentOsaas selectByPrimaryKey(Long osaasId);

    /**
     *根据adminId修改学生信息
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentOsaas record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StudentOsaas record);

    /**
     * 模糊查询学生信息 总条数
     */
    public int findStudentsByConditionTotal(Pager p);

    /**
     * 模糊查询学生信息
     */
    public List<StudentOsaas> findStudentsByCondition(Pager p);

    //根据 adminId 查询 StudentOsaas
    public StudentOsaas selectStudentOsaas(Long adminId);

    //根据 学籍号 查询 adminId
    public Long findAdminIdByRegisterNumber(String registerNumber);


}