package com.cj.witscorefind.service;

import com.cj.witbasics.entity.SchoolExam;
import com.cj.witbasics.entity.SchoolExamGrade;
import com.cj.witcommon.utils.entity.other.Pager;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ParameterSettingService {

    //添加档次设置信息
    public int addExamGrade(List<SchoolExamGrade> schoolExamGrades, HttpSession session);

    //查询考试参数设置
    public Pager findScoreGrade(Pager p);


}
