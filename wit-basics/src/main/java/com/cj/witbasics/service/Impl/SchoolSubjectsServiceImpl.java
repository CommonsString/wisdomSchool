package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.entity.SchoolSubjects;
import com.cj.witbasics.mapper.SchoolSubjectMapper;
import com.cj.witbasics.mapper.SchoolSubjectsMapper;
import com.cj.witbasics.service.SchoolSubjectsService;
import com.cj.witcommon.entity.ApiCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class SchoolSubjectsServiceImpl implements SchoolSubjectsService {

    @Autowired
    private SchoolSubjectsMapper schoolSubjectsMapper;

    @Autowired
    private SchoolSubjectMapper schoolSubjectMapper;

    @Value("${school_id}")
    String schoolId;

    @Override
    public int addSubjects(HttpSession session,SchoolSubjects schoolSubjects) {
        SchoolSubjects schoolSubjects1 = schoolSubjectsMapper.findSunbjectsBySubjectsName(schoolSubjects.getSubjectsName());
        if(schoolSubjects1 != null){
            return ApiCode.error_duplicated_data;
        }
        Long adminId = (Long) session.getAttribute("adminId");
        schoolSubjects.setSchoolId(Long.parseLong(schoolId));
        //开发、假设创建人ID为0
//        schoolSubjects.setFounderId(0l);
        schoolSubjects.setFounderId(adminId);
        schoolSubjects.setCreateTime(new Date());

        return schoolSubjectsMapper.insertSelective(schoolSubjects);
    }

    @Override
    public int deleteSubjects(HttpSession session, Long subjectsId) {
        //检查科目下是否有未删除的课程，如果有，则不删除
        List<SchoolSubject> subjectList = schoolSubjectMapper.findAllSchoolSubjectBySubjectsId(subjectsId);
        if(subjectList.size() > 0){
            return ApiCode.subjects_subject_exist;
        }




        return schoolSubjectsMapper.deleteByPrimaryKey(subjectsId);
    }

    @Override
    public int updateSubjects(HttpSession session, SchoolSubjects schoolSubjects) {
        SchoolSubjects schoolSubjects1 = schoolSubjectsMapper.findSunbjectsBySubjectsName(schoolSubjects.getSubjectsName());
        if(schoolSubjects1 != null){
            return ApiCode.error_duplicated_data;
        }
        Long adminId = (Long) session.getAttribute("adminId");
        //开发、假设操作员ID为0
//        schoolSubjects.setFounderId(0l);
        schoolSubjects.setFounderId(adminId);

        return schoolSubjectsMapper.updateByPrimaryKeySelective(schoolSubjects);
    }

    @Override
    public List<SchoolSubjects> findAllSubjects() {

        return schoolSubjectsMapper.findAllSubjectsBySchhoolId(Long.parseLong(schoolId));
    }
}
