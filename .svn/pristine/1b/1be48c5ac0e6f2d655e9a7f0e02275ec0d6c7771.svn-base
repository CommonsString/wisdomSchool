package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.entity.StudentScore;
import com.cj.witbasics.mapper.SchoolSubjectMapper;
import com.cj.witbasics.mapper.StudentScoreMapper;
import com.cj.witbasics.service.StudentScoreService;
import com.cj.witcommon.utils.common.StringHandler;
import com.cj.witcommon.utils.excle.ImportExeclUtil;
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

@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    //日志
    private static final Logger log = LoggerFactory.getLogger(SchoolClassServiceImpl.class);

    @Autowired(required = false)
    protected StudentScoreMapper scoreMapper;

    @Autowired
    private SchoolSubjectMapper subjectMapper;


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
    public boolean bathImportInfo(MultipartFile file, InputStream in, Long operatorId) {
//System.out.println("进入Service");
        //获取文件名字
        String fileName = file.getOriginalFilename();
        //创建工作薄
        Workbook excel = null;
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
            //创建时间
            Date time = new Date();
            //保存标题
            StudentScoreInfo title = scoreInfo.get(0);
            //分值上限处理
            boolean isRight = StringHandler.isRight(scoreInfo, title);
            //程序开始时间
            Long start = System.currentTimeMillis();
//System.out.println(isRight == true ? "存在分数违法" : "所有分数正常");
            if(!isRight){ //导入数据无错
                //暂存科目名
                List<String> subList = StringHandler.returnSubjectName(title);
                //创建时间
                Date createTime = new Date();
                //判断标志
                int success = 0;
                //操作集合
                List<StudentScore> listScore = new ArrayList<StudentScore>();
                //excle类循环
                for(int k = 1, len_info = scoreInfo.size(); k < len_info; k++){
                    //科目分数
                    List<BigDecimal> subjectScore = StringHandler.saveSubjectScore(scoreInfo.get(k));
                    //科目循环
                    for(int j = 0, len_sub = subList.size(); j < len_sub; j++){ //涉及科目ID,无科目分数
                        //科目名
                        Long subjectId = this.subjectMapper.selectBySubNameReturnId(subList.get(j));
                        //科目查重,即存在该科目成绩,无法导入
//                        System.out.println("科目ID： " + subjectId + " 学籍号" + scoreInfo.get(k).getRegisterNumber());
                        int isCopy = this.scoreMapper.selectByCountScoreId(scoreInfo.get(k).getRegisterNumber(), subjectId);
//System.out.println(isCopy > 0 ? "重复数据" : "可以插入");
                        if(isCopy > 0) return false;
//System.out.println("科目名：" + subList.get(j));
                        //创建分数对象
                        StudentScore stuScore = new StudentScore();
                        stuScore.setSchoolId(toLong());
                        //TODO:前台获取,考试ID
                        stuScore.setExamId(1L);
                        stuScore.setStudentName(scoreInfo.get(k).getStudentName());
                        stuScore.setRegisterNumber(scoreInfo.get(k).getRegisterNumber());
                        //TODO:学期前端单选
                        stuScore.setSchoolStageId("1");
//System.out.println("subList.get(j) ： " + subjectId);
                        stuScore.setSchoolSubjectId(subjectId);
                        //提取总分
                        stuScore.setScore(subjectScore.get(j));
                        //创建时间
                        stuScore.setCreateTime(createTime);
                        stuScore.setFounderId(operatorId);
                        stuScore.setOperatorId(operatorId);
                        listScore.add(stuScore);
                    }
                }
                success = this.scoreMapper.insertBathInfo(listScore);
                Long end = System.currentTimeMillis();
System.out.println("插入时间： " + (end - start));
                if(success > 0) return true;
                //程序结束时间
            }
        }
        return false;
    }
}
