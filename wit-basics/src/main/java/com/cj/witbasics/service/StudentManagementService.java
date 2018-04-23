package com.cj.witbasics.service;

import com.cj.witbasics.entity.StudentOsaas;
import com.cj.witcommon.utils.entity.other.Pager;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 学生管理接口
 */
public interface StudentManagementService{

    //生成学号
    public String getStudentNumber();

    //新增学生信息
    public  int addStudentOsaasinfo(StudentOsaas record,HttpServletRequest request);

    //模糊查询学生信息
    public Pager findStudentsByCondition(Pager p);

    //下载学生信息模板
    public void downloadStudengTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception;

    //根据adminId查询 StudentOsaas
    public StudentOsaas selectStudentOsaas(Long adminId);

    //根据adminId 修改学生信息
    public int updateStaffInfo(StudentOsaas studentOsaas);

    //学生信息批量导入.
    public int importStucents(MultipartFile multipartFile,HttpServletRequest request) throws Exception;

}
