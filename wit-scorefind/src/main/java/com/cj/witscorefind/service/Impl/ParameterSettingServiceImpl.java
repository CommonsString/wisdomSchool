package com.cj.witscorefind.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.PeriodAndThetime;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witscorefind.mapper.GradeMapper;
import com.cj.witscorefind.mapper.SchoolExamGradeMapper;
import com.cj.witscorefind.service.ParameterSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ParameterSettingServiceImpl implements ParameterSettingService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private SchoolExamGradeMapper schoolExamGradeMapper;

    @Value("${school_id}")
    String schoolId;

    @Override
    public int addExamGrade(List<SchoolExamGrade> schoolExamGrades, HttpSession session) {

        Long adminId = (Long) session.getAttribute("adminId");
        Long roleId = (Long) session.getAttribute("roleId");
        Date date = new Date();
        //校验操作权限
        if(roleId == 21){  //年级主任
            //查询此年级主任管理的学段、届次
            PeriodDirectorThetime periodDirectorThetime = schoolExamGradeMapper.findPeriodDirectorThetimeByAdminId(adminId);
            if(periodDirectorThetime != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String thetime = sdf.format(periodDirectorThetime.getThetime());
                for (SchoolExamGrade schoolExamGrade :schoolExamGrades){
                    //匹配学段ID和届次
                    if(thetime.equals(schoolExamGrade.getThetime()) && periodDirectorThetime.getPeriodId().intValue() == schoolExamGrade.getClassPeriodId()){

                    }else {
                        return ApiCode.http_status_unauthorized;
                    }
                }
            }else {
                return ApiCode.http_status_unauthorized;
            }
        }else if(roleId == 4){  //班级主任
            //查询此班级主任管理的班级
            SchoolPeriodClassThetime schoolPeriodClassThetime = schoolExamGradeMapper.findSchoolPeriodClassThetimeByAdminId(adminId);
            if(schoolPeriodClassThetime != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String thetime = sdf.format(schoolPeriodClassThetime.getThetime());

                for (SchoolExamGrade schoolExamGrade :schoolExamGrades){
                    //匹配学段ID和届次和班级ID
                    if(thetime.equals(schoolExamGrade.getThetime())
                            && schoolPeriodClassThetime.getPeriodId().intValue() == schoolExamGrade.getClassPeriodId()
                            && schoolPeriodClassThetime.getClassId() == schoolExamGrade.getClassId()){


                    }else {
                        return ApiCode.http_status_unauthorized;
                    }
                }
            }else {
                return ApiCode.http_status_unauthorized;
            }
        }else {

            //不是年级主任和班主任
            return ApiCode.http_status_unauthorized;
        }

        int i = 0;


        for (SchoolExamGrade schoolExamGrade : schoolExamGrades){
            schoolExamGrade.setFounderId(adminId);
            schoolExamGrade.setCreateTime(date);
            schoolExamGrade.setSchoolId(Long.parseLong(schoolId));

            //判断这场考试参数是否已存在，存在则更新参数设置详情

            i = schoolExamGradeMapper.insertSelective(schoolExamGrade);
            Long examGradeId = schoolExamGrade.getExamGradeId();

            for(Grade grade : schoolExamGrade.getGrades()){
                grade.setExamGradeId(examGradeId);
            }

            gradeMapper.addGrade(schoolExamGrade.getGrades());


        }
        return i;
    }

    @Override
    public Pager findScoreGrade(Pager p) {
        //查询总条数
        p.setRecordTotal(schoolExamGradeMapper.findScoreGradeTotal(p));

        //查询结果
        p.setContent(schoolExamGradeMapper.findScoreGrade(p));

        return p;
    }


}
