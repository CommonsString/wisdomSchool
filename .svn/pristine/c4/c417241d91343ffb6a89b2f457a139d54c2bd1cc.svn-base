package com.cj.witbasics.service;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witcommon.entity.SynBasicInformation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 云端业务
 */
public interface CloudService {

    /**
     * 登录
     * 返回true表示无需校验或校验成功
     */
    public boolean cloudLogin(Admin admin, HttpServletRequest request);

    /**
     * 注册（登陆成功的账号新增账号）
     * 返回true表示无需校验或校验成功
     */
    /**
     * 云端注册(管理员、单个)
     * @return
     */
    public boolean cloudRegisterPM(HttpServletRequest request, Admin admin, AdminRole adminRole);



    /**
     * 云端注册(学生、单个)
     * @return
     */
    public boolean cloudRegisterSM(HttpServletRequest request, Admin admin, AdminRole adminRole,SchoolPeriod schoolPeriod);




    /**
     * 文件上传
     * 返回true表示无需校验或校验成功
     *
     */
    public String cloudUpload(File file, HttpServletRequest request);

    /**
     * 信息同步(修改)
     * 返回true表示无需校验或校验成功
     */
    public boolean cloudSynchronization(HttpServletRequest request, SynBasicInformation synBasicInformation);

    /**
     * 获取用户基础信息
     */
    public Object cloudGet(HttpServletRequest request);



}
