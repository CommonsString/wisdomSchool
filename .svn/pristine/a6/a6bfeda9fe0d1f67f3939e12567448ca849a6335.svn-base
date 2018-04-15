package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.SchoolBasic;
import com.cj.witbasics.mapper.SchoolBasicMapper;
import com.cj.witbasics.service.SchoolBasicService;
import com.cj.witcommon.entity.ApiCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Service
public class SchoolBasicServiceImpl implements SchoolBasicService {

    @Autowired
    private SchoolBasicMapper schoolBasicMapper;


    @Override
    public int addSchoolBasic(HttpServletRequest request,SchoolBasic schoolBasic) {
        Long founderId = (Long)request.getSession().getAttribute("adminId");
        schoolBasic.setFounderId(founderId);
        schoolBasic.setCreateTime(new Date());
        int i = schoolBasicMapper.insertSelective(schoolBasic);
        if(i>0){
            //插入成功
            return ApiCode.SUCCESS;

        }else {
            //新增失败
            return ApiCode.error_create_failed;
        }
    }

    @Override
    public int findSchoolBasic(Long schoolId) {
        //根据schoolId查询学校信息
        SchoolBasic schoolBasic = schoolBasicMapper.finsSchoolBasic(schoolId);

        if(schoolBasic != null){
            return ApiCode.SUCCESS;
        }else {
            return ApiCode.FAIL;
        }
    }
}
