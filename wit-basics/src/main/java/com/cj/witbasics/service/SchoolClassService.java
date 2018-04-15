package com.cj.witbasics.service;

import com.cj.witbasics.entity.SchoolClass;
import com.cj.witcommon.utils.entity.other.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 处理学校信息
 */
public interface SchoolClassService {

    //根据学校ID，返回层次/类型/学段
    List findSchoolLevelTypePeriod(Long schoolId);

    //返回班级类型
    List findSchoolLevelType(Long schoolId);

    //返回班级层次
    List findSchoolLevel(Long schoolId);

    public boolean bathImportInfo(MultipartFile file, InputStream in, Long operatorId);

    //添加班级类型
    public boolean addClassType(Long schoolId, List<String> classType);

    //更新班级类型
    public boolean updateClassType(int classTypeId, String classTypeName);

    //删除班级类型
    public boolean updateClassTypeDel(int classTypeId);


    //添加班级层次
    public boolean addClassLevel(Long schoolId, List<String> classLevel);

    //修改班级层次
    public boolean updateClassLevel(int classLevelId, String classTypeName);

    //根据ID，删除班级层次
    public boolean updateClassLevelDel(Integer classLevelId);

    //修改班级信息
    public boolean updateSchoolClassInfo(SchoolClass schoolClass, Long chineseId, Long englishId, Long mathId,
                                         String chineseTea, String englishTea, String mathTea);

    //模糊查询，根据输入条件，查询对应班级信息(班级名称，班级好，班主任名字)
    public Pager findSchoolClassInfo(Long periodId, Long gradeId, String vague, Pager pager);

}
