package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.*;
import com.cj.witbasics.service.SchoolExamService;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.common.StringHandler;
import com.cj.witcommon.utils.entity.other.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SchoolExamServiceImpl implements SchoolExamService{
    private static final Logger log = LoggerFactory.getLogger(SchoolExamServiceImpl.class);

    @Autowired(required = false)
    private SchoolGradeMapper gradeMapper;

    @Autowired(required = false)
    private SchoolExamMapper examMapper;

    @Autowired(required = false)
    private SchoolClassMapper classMapper;

    @Autowired(required = false)
    private SchoolSubjectMapper subjectMapper;

    @Autowired(required = false)
    private SchoolPeriodMapper periodMapper;


    @Value("${school_id}")
    private String schoolId;

    @Autowired(required = false)
    private SchoolExamParentMapper schoolExamParentMapper;

    /**
     * 转为Long
     * @return
     */
    private Long toLong(){
        return Long.valueOf(this.schoolId);
    }



    /**
     * 查询届次
     * @param schoolId
     * @return
     */
    @Override
    public List<Map> findAllGradeName(Long schoolId) {

        List<Map> param = examMapper.findAllPeriodAndGrade(schoolId);
        for(Map map : param){
            System.out.println("学段ID" + map.get("periodId"));
            SchoolPeriod period = this.periodMapper.selectByPrimaryKey((Long)map.get("periodId"));
            map.put("periodName", period.getPeriodName());
        }
        return param;
    }



    /**
     *
     * 根据传入的信息,对应班级信息
     * 班级信息
     */
    @Override
    @Transactional
    public List findAllUnderGradeClass(List<PeriodAndGrade> param) {
        List<List<Map>> result = new ArrayList<>();
        for(PeriodAndGrade temp : param){
System.out.println(temp.toString());
            String thetime = temp.getThetime();
            thetime += "-7-1";
            System.out.println(thetime);
            List<Map> tempResult = this.classMapper.selectByByPeriodAndThetimeExam(temp.getPeriodId(), thetime);
            result.add(tempResult);
        }
        return result;
 /*       //总list
        List allList = new ArrayList();
        List<ExamClassInfo> resultList = new ArrayList<ExamClassInfo>();
        //学段年级
        for(PeriodAndGrade item : param){
            //获取学段id
            Long periodId = item.getPeriodId();
            //获取学段下的班级
            List<SchoolClassInfo> periodUnderClass = this.classMapper.selectByPeriodIdNoPager(periodId);
            //获取年级
            int gradeAge = StringHandler.getGradeAge(item.getGradeName());
            //二级筛选
//            List<ClassInfoPx> resultList = new ArrayList<ClassInfoPx>();
//            List<ExamClassInfo> resultList = new ArrayList<ExamClassInfo>();

            ExamClassInfo tem = new ExamClassInfo();
            tem.setPeriodId(periodId);
            tem.setPeriodName(item.getPeriodName());
            tem.setGradeId(item.getGradeId());
            tem.setGradeName(item.getGradeName());

            List<BaseClass> list = new ArrayList<BaseClass>();
            for(SchoolClassInfo temp : periodUnderClass){
                SchoolClassInfo classInfo = this.classMapper.findAllClassForYeah(temp, gradeAge);
                if(classInfo != null){
                    System.out.println(classInfo.toString());
                    System.out.println(tem.toString());
                    //班级信息
                    BaseClass base = new BaseClass();
                    base.setClassId(classInfo.getClassId());
                    base.setClassName(classInfo.getClassName());
                    base.setClassNumber(classInfo.getClassNumber());
                    base.setClassYear(classInfo.getClassYear());
                    list.add(base);
                    tem.setClassInfo(list);
//                    ClassInfoPx px = new ClassInfoPx();
//                    px.setClassId(classInfo.getClassId());
//                    px.setClassName(classInfo.getClassName());
//                    px.setClassNumber(classInfo.getClassNumber());
//                    px.setClassYear(classInfo.getClassYear());
//                    px.setPeriodId(periodId);
//                    px.setPeriodName(item.getPeriodName());
//                    px.setGradeId(item.getGradeId());
//                    px.setGradeName(item.getGradeName());
                    //填充
//                    resultList.add(px);
                }
            }
//            allList.add(resultList);
            resultList.add(tem);
        }*/
    }


    /**
     * 查询班级对应的科目
     */
    @Override
    @Transactional
    public List findAllSubjectInfo(Long classId) {
        List result = this.subjectMapper.findSubjectInfo(classId);
        return result;
    }


    /**
     * 查询考试名称
     * @param
     * @return
     */
    @Override
    public List<Map> findExamName(Long schoolId) {
        List<Map> result = this.examMapper.selectBySchoolId(schoolId);
        return result;
    }


    /**
     * 模糊查询,考试类别,考试对象
     * @param
     * @param vague
     * @return
     */
    @Override
    public Pager findExamOfVague(String examName, String vague, Pager pager) {
//        int total = this.examMapper.selectCountIdAndVague(examName, vague);
        List<SchoolExam> result = this.examMapper.selectByIdAndVague(examName, vague, pager);
        pager.setRecordTotal(result.size());
        pager.setContent(result);
        return pager;
    }

    @Override
    public List<SchoolExam> findAllSchoolExamByThetime(String thetime) {
        return examMapper.findAllSchoolExamByThetime(thetime);
    }

    @Override
    public List<SchoolExamParent> findAllSchoolExamParentByParameter(Pager p) {
        return schoolExamParentMapper.findAllSchoolExamParentByParameter(p);
    }

    @Override
    public List<Map> findAllSchoolExamThetimeBySchoolExamParent(Long examParentId) {
        return examMapper.findAllSchoolExamThetimeBySchoolExamParent(examParentId);
    }

    @Override
    public List<ExamClassPeriod> findAllSchoolExamClassByExamParentIdAndThetime(Map map) {
        return examMapper.findAllSchoolExamClassByExamParentIdAndThetime(map);
    }

    @Override
    public List<ExamClassPeriod> findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime(Map map) {
        return examMapper.findAllSchoolExamThetimeAndSubjectByExamParentIdAndThetime(map);
    }

    /**
     * 新增考试
     */
    @Override
//    @Transactional
    public ApiResult addSchoolExamInfo(ExamParam examInfo, Long adminId) {

        //父节点ID
        Long parentId = null;
        int flag_a = this.schoolExamParentMapper.selectByExamName(examInfo.getExamName());
        if(flag_a <= 0){
            /////////////////////////父节点////////////////////////////
            SchoolExamParent schoolExamParent = new SchoolExamParent();
            schoolExamParent.setExamName(examInfo.getExamName());
            schoolExamParent.setCreateTime(new Date());
            schoolExamParentMapper.insertSelective(schoolExamParent);
            //父节点ID
            parentId = schoolExamParent.getExamParentId();
System.out.println(parentId + "  id");
            //////////////////////////父节点////////////////////////////+
        }
        //===================================================================
        ApiResult result = new ApiResult();
        //插入集合
        List<SchoolExam> examList = new ArrayList<SchoolExam>();
        //获取班级ID,科目ID集合
        List<ExamClassSubject> listClass = examInfo.getParams();
        //创建时间
        Date time = new Date();
        //查重逻辑
        for(ExamClassSubject e : listClass){
            Integer classId = e.getClassId();
            //第二层
            List<SubjectForTea> subject = e.getSubject();
            for(SubjectForTea s : subject){
                //考试科目
                String examSubject = s.getSubjectName();
                Date start = examInfo.getExamTime();
                //根据考试科目,班级ID,查重,增加时间
                int isCopy = this.examMapper.selectCountBySubjectNameAndClassId(classId, examSubject, start);
                if(isCopy > 0){
                    //存在记录
                    log.error("数据已存在");
                    result.setCode(ApiCode.error_duplicated_data);
                    result.setMsg(ApiCode.error_duplicated_data_MSG);
                    return result;
                }

                //封装结果集合
                SchoolExam exam = new SchoolExam();
                exam.setExamTypeName(examInfo.getExamTypeName());
                exam.setExamTime(examInfo.getExamTime());
                exam.setExamName(examInfo.getExamName());
                exam.setSchoolId(toLong());
                SchoolClass info = classMapper.selectByPrimaryKey(classId.longValue());
System.out.println(info.toString());
                //设置届次
                exam.setThetime(info.getThetime());
                //设置父节点ID
                if(parentId != null) exam.setExamParentId(parentId);
                //设置考试年级
                exam.setCreateTime(time);
                exam.setFounderId(adminId);
                exam.setOperatorId(adminId);
                //数据
                exam.setExamObject(examInfo.getExamObject());
                exam.setClassId(classId);
                exam.setExamSubject(s.getSubjectName());
                examList.add(exam);
            }
        }
        //批量插入
        int success = this.examMapper.insertBatchInfoByU(examList);
//        for(SchoolExam ss : examList){
//            System.out.println(ss.toString());
//        }
        //新增考试父节点信息


        if(success > 0){
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }




}
