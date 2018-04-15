package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.SchoolClass;
import com.cj.witbasics.entity.SchoolGrade;
import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witbasics.mapper.SchoolClassMapper;
import com.cj.witbasics.mapper.SchoolGradeMapper;
import com.cj.witbasics.mapper.SchoolPeriodMapper;
import com.cj.witbasics.service.SchoolPeriodService;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.common.StringHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SchoolPeriodServiceImpl implements SchoolPeriodService {


    @Autowired(required = false)
    private SchoolClassMapper  classMapper;

    @Autowired(required = false)
    private SchoolPeriodMapper schoolPeriodMapper;

    @Autowired(required = false)
    private SchoolGradeMapper schoolGradeMapper;

    @Value("${school_id}")
    private String schoolId;


    /**
     * 转为Long
     * @return
     */
    private Long toLong(){
        System.out.println(this.schoolId);
        return Long.valueOf(this.schoolId);
    }

    /**
     * 根据年级id,修改年级名称
     */
    @Override
    @Transactional
    public boolean updateSchoolGradeInfo(Long gradeId, String gradeName) {
        //构造对象
        SchoolGrade grade = new SchoolGrade();
        grade.setGradeId(gradeId);
        grade.setGradeName(gradeName);
        int result = this.schoolGradeMapper.updateByPrimaryKeySelective(grade);
        //成功返回
        if(result > 0) return true;
        //失败
        return false;
    }

    /**
     * 根据年级ID，删除年级信息，非物理删除
     */
    @Override
    @Transactional
    public boolean updateSchoolGradeDel(Long gradeId) {
        int result = this.schoolGradeMapper.deleteByGradeId(gradeId); //修改state状态
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据年级信息实体添加信息
     */
    @Override
    @Transactional
    public boolean addSchoolGradeInfo(SchoolGrade grade) {
System.out.println(grade.toString());
        //根据年级名称查重
        int flag = this.schoolGradeMapper.selectByGradeName(grade.getGradeName(), grade.getSchoolId(), grade.getPeriodId());
System.out.println(flag + " 年级名称是否存在！");
        if(flag <= 0){
            int result = this.schoolGradeMapper.insertSelective(grade);
            if(result > 0){
                return true;
            }else{
                return false;
            }
        }else{
            throw new RuntimeException("数据已存在！");
        }
    }

    /**
     *  根据学校ID返回对于信息
     */
    @Override
    @Transactional
    public List findSchoolPeriodInfo(Long schoolId) {

        List result = this.schoolPeriodMapper.selectInfoBySchoolId(schoolId);

        System.out.println(result.size() + " list大小");
        System.out.println("进入");
        if(!result.isEmpty()){
            return result;
        }else{
            throw new RuntimeException();
        }
    }



    /**
     * 在班级信息界面
     * 根据学校ID，查询学段下年级信息(封装)
     */
    @Override
    @Transactional
    public List<SchoolPeriodInfo> findPeriodAndGradeInfo(Long schoolId) {

        //根据学校ID，先查询学校下的学段：
        List<SchoolPeriod> periodList = this.schoolPeriodMapper.selectInfoBySchoolId(schoolId);
        List<SchoolPeriodInfo> result = new ArrayList<SchoolPeriodInfo>();
System.out.println(periodList.size() + "  : 学段集合");
        for(SchoolPeriod p : periodList){
            Long periodId = p.getPeriodId();
            //构建对象
            SchoolPeriodInfo info = new SchoolPeriodInfo();
            info.setPeriodId(periodId);
            info.setSchoolId(schoolId);
            info.setPeriodName(p.getPeriodName());
            info.setState(p.getState());
            info.setPeriodAge(p.getPeriodAge());
            info.setPeriodSystem(p.getPeriodSystem());
            //根据学段ID和学校ID,查询年级信息
            List<GradeInfo> gradeInfo = this.schoolGradeMapper.selectBySchoolIdAndPeriodId(schoolId, periodId);
            //填充年级信息
            info.setGradeList(gradeInfo);
            result.add(info);
        }

        return result;
    }

    /**
     * 根据学段ID,年级信息
     */
    @Override
    public List<GradeInfo> findPeriodGradeInfo(Long schoolId, Long periodId) {
        List<GradeInfo> gradeInfo = this.schoolGradeMapper.selectBySchoolIdAndPeriodId(schoolId, periodId);
        return gradeInfo;
    }

    /**
     * 新增学段信息
     */
    @Override
    @Transactional  //开启事务
    public ApiResult addSchoolPeriodInfo(SchoolPeriodInfo period, List<String> gradeList, Long operatorId) {
        ApiResult result = new ApiResult();
        int flag = this.schoolPeriodMapper.selectByPeriodName(period.getPeriodName());
        if(flag <= 0){ //不存在，构造
            SchoolPeriod item = new SchoolPeriod();
            item.setPeriodName(period.getPeriodName());
            item.setSchoolId(period.getSchoolId());
            item.setPeriodAge(period.getPeriodAge());
            item.setPeriodSystem(period.getPeriodSystem());
            item.setState(period.getState());
            item.setFounderId(operatorId);
            item.setCreateTime(new Date());
            int flag_i = this.schoolPeriodMapper.insertSelective(item);
            //学段ID
            Long periodId = item.getPeriodId();
            int flag_g = 0;
            for(String gradeName : gradeList){
                SchoolGrade grade = new SchoolGrade();
                grade.setGradeName(gradeName);
                grade.setSchoolId(item.getSchoolId());
                grade.setPeriodId(periodId);
                flag_g = this.schoolGradeMapper.insertSelective(grade);
            }
            if(flag_i  > 0 && flag_g > 0){
                ApiResultUtil.fastResultHandler(result, true, null, null, null);
            }else{
                ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
            }
        }else{
            //数据已经存在
            result.setCode(ApiCode.error_duplicated_data);
            result.setMsg(ApiCode.error_duplicated_data_MSG);
        }
        return result;
    }


    /**
     * 根据学段ID删除信息
     */
    @Override
    @Transactional //开启事务
    public boolean updatePartInfoDel(Long periodId, Long operatorId) {
        //根据学段ID，查询班级，存在不删除，不存在删除
        int result = this.classMapper.selectCountPeriodId(periodId);

System.out.println(result + "结果");
        if(result <= 0){ //不存在
            SchoolPeriod period = new SchoolPeriod(); //学段对象构建
            period.setPeriodId(periodId);
            period.setOperatorId(operatorId); //设置操作原员D
            period.setDeleteTime(new Date()); //设置删除时间
            period.setState("0"); //已删除
            int periodInfo = this.schoolPeriodMapper.updateByPrimaryKeySelective(period); //删除学段信息表
System.out.println("执行");
            SchoolGrade grade = new SchoolGrade(); //年级信息
            grade.setPeriodId(periodId);
            grade.setState("0");
            int gradeInfo = this.schoolGradeMapper.deleteByPeriodId(grade); //删除学段信息表对应的年级信息

            if(periodInfo <= 0 && gradeInfo <= 0){
                return false;
            }else{
                return true;
            }
        }else{
            throw new RuntimeException("学段下存在班级，无法删除。");
        }
    }


    /**
     * 根据学段ID，删除信息  批量删除
     */
    @Override
    @Transactional //开启事务
    public boolean updateBathPartInfoDel(List<Long> periodList, Long operatorId) {
        boolean flag = false; //学段下，不存在班级
        for (Long index : periodList){ //存在一个，则无法批量删除
            int result = this.classMapper.selectCountPeriodId(index);
System.out.println("存在： " + result);
            if(result > 0) { //学段下存在班级
                flag = true;
                break;
            }
        }
        if(!flag){ //不存在班级
System.out.println("进入");
            Date deleteTime = new Date(); //创建删除时间
            int periodInfo = this.schoolPeriodMapper.deleteBatchByPrimaryKey(periodList, operatorId, deleteTime);//删除school_period表信息
            int gradeInfo = this.schoolGradeMapper.deleteBatchByPeriodId(periodList);
            if(periodInfo <= 0 && gradeInfo <= 0){
                return false;
            }else{
                return true;
            }
        }else{ //存在班级
            return false;
        }
    }

    /**
     * 根据学段ID,年级ID
     * 修改年级名称
     */
    @Override
    @Transactional
    public boolean updatePartInfo(SchoolPeriodInfo info) {

        List<SchoolGrade> gradeList = new ArrayList<SchoolGrade>();
        //学段ID
        Long periodId = info.getPeriodId();
        //遍历年级信息
        for(GradeInfo gradeInfo : info.getGradeList()){
            SchoolGrade grade = new SchoolGrade();
            grade.setPeriodId(periodId);
            grade.setGradeId(gradeInfo.getGradeId());
            grade.setSchoolId(toLong());
            grade.setGradeName(gradeInfo.getGradeName());
            gradeList.add(grade);
        }
        int success = this.schoolGradeMapper.updateBatchInfo(gradeList);
        if(success > 0)  return true;
        return false;
    }

}
