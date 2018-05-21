package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.*;
import com.cj.witbasics.service.StudentScoreService;
import com.cj.witcommon.utils.TimeToString;
import com.cj.witcommon.utils.common.StringHandler;
import com.cj.witcommon.utils.excle.ImportExeclUtil;
import com.cj.witcommon.utils.excle.ScoreModelTwoInfo;
import com.cj.witcommon.utils.excle.StudentScoreInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    //日志
    private static final Logger log = LoggerFactory.getLogger(SchoolClassServiceImpl.class);

    @Autowired(required = false)
    protected StudentScoreMapper scoreMapper;

    @Autowired(required = false)
    private SchoolSubjectMapper subjectMapper;

    @Autowired(required = false)
    private StudentOsaasMapper osaasMapper;

    @Autowired(required = false)
    private SchoolExamMapper examMapper;

    @Autowired(required = false)
    private SchoolExamParentMapper examPMapper;

    @Value("${school_id}")
    private String schoolId;



    /**
     * 转为Long
     * @return
     */
    private Long toLong(){
        return Long.valueOf(this.schoolId);
    }




    /**
     * 导入成绩信息
     */
    @Override
    @Transactional
    public boolean bathImportInfo(MultipartFile file, InputStream in, Map params, Long operatorId, Integer modelNumber) {
        if(modelNumber == 1){
            //模版一
            return modelOne(file, in, params, operatorId);
        }else if(modelNumber == 2){
            //模版二
            return modelTwo(file, in, params, operatorId);
        }else{

        }
        return false;
    }

    ////////////////////////////////////模版一 /////////////////////////////////////
    private boolean  modelOne(MultipartFile file, InputStream in, Map params, Long operatorId){
        //获取文件名字
        String fileName = file.getOriginalFilename();
        //创建工作薄
        Workbook excel = null;
        //届次
        Date tempThetime = TimeToString.StrToDate2((String) params.get("thetime"));
        //父节点
        System.out.println("届次：  " + tempThetime);
        try {
            excel = ImportExeclUtil.chooseWorkbook(fileName, in);
        } catch (IOException e) {
            log.error("读取文件失败");
            e.printStackTrace();
            return false;
        }
        int sheets = excel.getNumberOfSheets();
        for(int i = 0; i < sheets; i++){
            //创建导入实体
            StudentScoreInfo score = new StudentScoreInfo();
            List<StudentScoreInfo> scoreInfo = null;
            try {
                scoreInfo = ImportExeclUtil.readDateListT(excel, score, 1, 0, i);
            } catch (Exception e) {
                log.error("读取数据失败");
                e.printStackTrace();
                return false;
            }
            System.out.println(scoreInfo.toString());
            //创建时间
            Date time = new Date();
            //保存标题
            StudentScoreInfo title = scoreInfo.get(0);
            //分值上限处理
//            boolean isRight = StringHandler.isRight(scoreInfo, title);
            //程序开始时间
            Long start = System.currentTimeMillis();
//System.out.println(isRight == true ? "存在分数违法" : "所有分数正常");
            //暂存科目名
            List<String> subList = StringHandler.returnSubjectName(title);
            System.out.println(subList);
            //创建时间
            Date createTime = new Date();
            //判断标志
            int successAdd = 0;
            int successUpdate = 0;
            //插入集合
            List<StudentScore> listScoreAdd = new ArrayList<StudentScore>();
            //更新集合
            List<StudentScore> listScoreUpdate = new ArrayList<StudentScore>();
            //excle类循环
            for(int k = 1, len_info = scoreInfo.size(); k < len_info; k++){
                //科目分数
                List<BigDecimal> subjectScore = StringHandler.saveSubjectScore(scoreInfo.get(k));
                //科目循环
                for(int j = 0, len_sub = subList.size(); j < len_sub; j++){ //涉及科目ID,无科目分数
                    //科目名
                    Long subjectId = this.subjectMapper.selectBySubNameReturnId(subList.get(j));
System.out.println("科目ID " + subjectId + "科目名： " + subList.get(j));
                    //科目查重,即存在该科目成绩,无法导入
//                        System.out.println("科目ID： " + subjectId + " 学籍号" + scoreInfo.get(k).getRegisterNumber());
                    int isCopy = this.scoreMapper.selectByCountScoreId(scoreInfo.get(k).getRegisterNumber(), subjectId);
                    StudentOsaas info = this.osaasMapper.selectByRegisterNumber(scoreInfo.get(k).getRegisterNumber());
                    SchoolExam exam = this.examMapper.selectByParentIdAndSubjectName((Long)params.get("examParentId")
                        , subList.get(j), info.getClassId());
                    if(exam == null) return false;
//System.out.println(isCopy > 0 ? "重复数据" : "可以插入");
                    if(isCopy > 0){   //更新
                        //创建分数对象
                        StudentScore stuScore = new StudentScore();
                        stuScore.setSchoolId(toLong());
                        //TODO:前台获取,考试ID
                        stuScore.setExamId(exam.getExamId());
                        stuScore.setExamParentId((Long)params.get("examParentId"));
                        stuScore.setStudentName(scoreInfo.get(k).getStudentName());
                        stuScore.setRegisterNumber(scoreInfo.get(k).getRegisterNumber());
                        //TODO:学期前端单选
                        stuScore.setSchoolStageId((String)params.get("schoolStageId"));
                        stuScore.setSchoolSubjectId(subjectId);
                        //提取总分
                        stuScore.setScore(subjectScore.get(j));
                        //创建时间
                        stuScore.setCreateTime(createTime);
                        stuScore.setFounderId(operatorId);
                        //班级ID
//                        stuScore.setClassId((Long)params.get("classId"));
                        stuScore.setClassId(info.getClassId());
                        //设置届次
                        stuScore.setThetime(tempThetime);
                        stuScore.setOperatorId(operatorId);
                        successUpdate = this.scoreMapper.updateByPrimaryBySome(stuScore);
System.out.println(stuScore.toString());
                    }else{   //插入
                        //创建分数对象
                        StudentScore stuScore = new StudentScore();
                        stuScore.setSchoolId(toLong());
                        //TODO:前台获取,考试ID
//                        stuScore.setExamId((Long)params.get("eaxmId"));
                        if(exam != null) stuScore.setExamId(exam.getExamId());
//                        SchoolExamParent parent = this.examPMapper.selectByPrimaryKey((Long)params.get("examParentId"));
                        stuScore.setExamParentId((Long)params.get("examParentId"));
                        stuScore.setStudentName(scoreInfo.get(k).getStudentName());
                        stuScore.setRegisterNumber(scoreInfo.get(k).getRegisterNumber());
                        //TODO:学期前端单选
                        stuScore.setSchoolStageId((String)params.get("schoolStageId"));
//System.out.println("subList.get(j) ： " + subjectId);
                        stuScore.setSchoolSubjectId(subjectId);
                        //提取总分
                        stuScore.setScore(subjectScore.get(j));
                        //创建时间
                        stuScore.setCreateTime(createTime);
                        stuScore.setFounderId(operatorId);

                        //班级ID
//                        stuScore.setClassId((Long)params.get("classId"));
                        stuScore.setClassId(info.getClassId());
                        //设置届次
                        stuScore.setThetime(tempThetime);
                        stuScore.setFounderId(operatorId);
                        listScoreAdd.add(stuScore);
                    }
                }
            }

            //考试父节点
            //更新
//            if(!listScoreUpdate.isEmpty()){
//                successUpdate = this.scoreMapper.updateBatchInfo(listScoreUpdate);
//            }
//            //插入
            if(!listScoreAdd.isEmpty()){
                successAdd = this.scoreMapper.insertBathInfo(listScoreAdd);
            }
            if(successUpdate > 0 || successAdd > 0) return true;
        }

        return false;
    }
    ////////////////////////////////////模版一 /////////////////////////////////////


    ////////////////////////////////////模版二 /////////////////////////////////////
    private boolean  modelTwo(MultipartFile file, InputStream in, Map params, Long operatorId){
        //获取文件名字
        String fileName = file.getOriginalFilename();
        //创建工作薄
        Workbook excel = null;
        //届次
        Date tempThetime = TimeToString.StrToDate2((String) params.get("thetime"));
        //父节点
        System.out.println("届次：  " + tempThetime);
        try {
            excel = ImportExeclUtil.chooseWorkbook(fileName, in);
        } catch (IOException e) {
            log.error("读取文件失败");
            e.printStackTrace();
            return false;
        }
        int sheets = excel.getNumberOfSheets();

        for(int i = 0; i < sheets; i++){
            //创建导入实体
            ScoreModelTwoInfo score = new ScoreModelTwoInfo();
            List<ScoreModelTwoInfo> scoreInfo = null;
            try {
                scoreInfo = ImportExeclUtil.readDateListT(excel, score, 1, 0, i);
            } catch (Exception e) {
                log.error("读取数据失败");
                e.printStackTrace();
                return false;
            }

            //判断标志
            int successAdd = 0;
            int successUpdate = 0;
            //标题
            ScoreModelTwoInfo title = scoreInfo.get(0);
            //科目名字
            List<String> subjectName = StringHandler.returnSubjectName2(title);
            //插入集合
            List<StudentScore> listScoreAdd = new ArrayList<StudentScore>();
            for(int j = 2; j < scoreInfo.size(); j++){
                System.out.println(scoreInfo.get(j).toString());
                List<BigDecimal> subjectScore = StringHandler.saveSubjectScore2(scoreInfo.get(j));
                for(int k = 0; k < subjectName.size(); k++){
                    //科目名
                    Long subjectId = this.subjectMapper.selectBySubNameReturnId(subjectName.get(k));
                    System.out.println("信息：" + scoreInfo.get(j).toString());
                    StudentOsaas info = this.osaasMapper.selectByRegisterNumber(scoreInfo.get(j).getRegisterNumber());
                    System.out.println("学生信息：" + info.toString());
                    System.out.println("参数：" + (Long)params.get("examParentId") + "  " + subjectName.get(k) + "  " + info.getClassId());
                    SchoolExam exam = this.examMapper.selectByParentIdAndSubjectName((Long)params.get("examParentId")
                            , subjectName.get(k), info.getClassId());
                    int isCopy = this.scoreMapper.selectByCountScoreId(scoreInfo.get(j).getRegisterNumber(), subjectId);
                    if(exam == null) return false;
                    if(isCopy > 0){ //更新
                        //创建分数对象
                        StudentScore stuScore = new StudentScore();
                        stuScore.setSchoolId(toLong());
                        stuScore.setExamParentId((Long)params.get("examParentId"));
                        stuScore.setStudentName(scoreInfo.get(j).getStudentName());
                        stuScore.setRegisterNumber(scoreInfo.get(j).getRegisterNumber());
                        stuScore.setSchoolStageId((String)params.get("schoolStageId"));
                        stuScore.setSchoolSubjectId(subjectId);
                        stuScore.setExamId(exam.getExamId());
                        //提取总分
                        System.out.println("科目ID " + subjectId + "科目名： " + subjectName.get(k) + "科目分数： " + subjectScore.get(k));
                        stuScore.setScore(subjectScore.get(k));
                        //创建时间
                        stuScore.setCreateTime(new Date());
                        stuScore.setFounderId(operatorId);
                        System.out.println("班级ID：" + (Long)params.get("classId"));
                        //班级ID
                        stuScore.setClassId(info.getClassId());
                        //设置届次
                        stuScore.setThetime(tempThetime);
                        stuScore.setOperatorId(operatorId);
                        successUpdate = this.scoreMapper.updateByPrimaryBySome(stuScore);
                    }else{ //插入
                        //创建分数对象
                        StudentScore stuScore = new StudentScore();
                        stuScore.setSchoolId(toLong());
                        //考试ID
                        stuScore.setExamId(exam.getExamId());
                        stuScore.setStudentName(scoreInfo.get(j).getStudentName());
                        stuScore.setRegisterNumber(scoreInfo.get(j).getRegisterNumber());
                        stuScore.setSchoolStageId((String)params.get("schoolStageId"));
                        stuScore.setSchoolSubjectId(subjectId);
                        //提取总分
                        System.out.println("科目ID " + subjectId + "科目名： " + subjectName.get(k) + "科目分数： " + subjectScore.get(k));
                        stuScore.setScore(subjectScore.get(k));
                        //创建时间
                        stuScore.setCreateTime(new Date());
                        stuScore.setFounderId(operatorId);
                        //班级ID
                        System.out.println("班级ID：" + (Long)params.get("classId"));
                        stuScore.setClassId(info.getClassId());
                        //设置届次
                        stuScore.setThetime(tempThetime);
                        stuScore.setOperatorId(operatorId);
                        System.out.println(stuScore.toString());
                        listScoreAdd.add(stuScore);
                    }
                }
            }

            if(!listScoreAdd.isEmpty()){
                successAdd = this.scoreMapper.insertBathInfo(listScoreAdd);
            }
            if(successUpdate > 0 || successAdd > 0) return true;
         }
        return false;
    }
    ////////////////////////////////////模版二 /////////////////////////////////////


}
