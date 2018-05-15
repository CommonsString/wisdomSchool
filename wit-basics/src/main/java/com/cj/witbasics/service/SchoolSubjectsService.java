package com.cj.witbasics.service;

import com.cj.witbasics.entity.SchoolSubjects;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SchoolSubjectsService {

    /**
     * 添加科目
     * @param schoolSubjects
     * @return
     */
    public int addSubjects(HttpSession session, SchoolSubjects schoolSubjects);

    /**
     * 删除科目
     */
    public int deleteSubjects(HttpSession session, Long subjectsId);

    /**
     * 修改科目
     */
    public int updateSubjects(HttpSession session, SchoolSubjects schoolSubjects);

    /**
     * 查询科目
     */
    public List<SchoolSubjects> findAllSubjects();
}
