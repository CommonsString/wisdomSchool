package com.cj.witscorefind.service.Impl;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminInfo;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.entity.PeriodDirectorThetime;
import com.cj.witbasics.mapper.AdminInfoMapper;
import com.cj.witbasics.mapper.ClassSubjectInfoMapper;
import com.cj.witbasics.mapper.PeriodDirectorThetimeMapper;
import com.cj.witbasics.mapper.StudentScoreMapper;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witscorefind.service.FindScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class FindScoreServiceImpl implements FindScoreService {

    private static final Logger log = LoggerFactory.getLogger(FindScoreServiceImpl.class);

    @Autowired(required = false)
    private StudentScoreMapper scoreMapper;

    @Autowired(required = false)
    private ClassSubjectInfoMapper clsInfo;

    @Autowired(required = false)
    private AdminInfoMapper adminInfoMapper;

    @Autowired(required = false)
    private PeriodDirectorThetimeMapper directorTimeMapper;

    /**
     * 不同的角色,查询不同的成绩
     * @return
     */
    @Override
    public ApiResult findScoreForDifferentRole(HttpServletRequest request) {
System.out.println("进入逻辑！");
        ApiResult result = new ApiResult();
        //获取管理员ID
        Long adminId = (Long) request.getSession().getAttribute("adminId");
        //根据管理员角色ID,查询管理员表
        //TODO:GG
//        Admin admin = null;
        Admin admin = new Admin();
        admin.setId(61L);
System.out.println(admin.toString());
        //TODO:gg
        //根据角色ID,查询角色
        AdminRole role = new AdminRole();
        role.setId(4);
        role.setType("3");
System.out.println(role.toString());
//        AdminRole role = null;
        switch (role.getType()){
            //待定
            case "0" : break;
            case "1" : break;
            case "2" : break;

            case "3" :
                result = caseThree(result, role, admin);
                break;
            default : break;
        }
        return result;
    }

    /**********************************************************************************************/
    /*******************************程序私有方法****************************************************/
    /**********************************************************************************************/
    //角色类型3 -- 包含(班主任,科目教师,年级主任)
    private ApiResult caseThree(ApiResult result, AdminRole role, Admin admin){
        //角色类型,获取角色ID
        int id = role.getId();
System.out.println("角色ID: " + id);
        if(id == 4){        //班主任
            //根据班主任ID,查询对应班科目分数统计
            Long headmasterId = admin.getId();
            List info = this.scoreMapper.selectPowerByHeadmaster(headmasterId);
            //数据的封装
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else if(id == 5){  //科目教师
            //根据ID,查询对应科目
            Long adminId = admin.getId();
            AdminInfo adminInfo = this.adminInfoMapper.selectByadminId(adminId);
            //根据id,查询
            List info = this.scoreMapper.selectPowerBySubjectTeacher(adminInfo.getAdminInfoId());
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else if(id == 21){ //年级主任
            //获取ID
            Long adminId = admin.getId();
            PeriodDirectorThetime tempInfo = this.directorTimeMapper.selectByDirectorId(adminId);
            //根据学段ID,毕业届次查询
            List info = this.scoreMapper.selectPowerByPeriodIdAndThetime(tempInfo);
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else{
            result.setCode(ApiCode.error_search_failed);
            result.setMsg(ApiCode.error_search_failed_MSG);
        }
        return result;
    }



}
