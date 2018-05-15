package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.ClassSubjectInfo;
import com.cj.witbasics.entity.SchoolExamParent;
import com.cj.witbasics.entity.SchoolSubject;
import com.cj.witbasics.mapper.ClassSubjectInfoMapper;
import com.cj.witbasics.mapper.SchoolSubjectMapper;
import com.cj.witbasics.service.SchoolSubjectService;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witcommon.utils.TimeToString;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witcommon.utils.excle.exportExcelUtil;
import io.swagger.annotations.Api;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SchoolSubjectServiceImpl implements SchoolSubjectService {

    private static final Logger log = LoggerFactory.getLogger(SchoolSubjectServiceImpl.class);

    @Autowired(required = false)
    private SchoolSubjectMapper subjectMapper;

    @Autowired(required = false)
    private ClassSubjectInfoMapper infoMapper;

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
     * 新增课程信息
     * @param subject
     */
    @Override
    @Transactional
    public ApiResult addSubjectInfo(HttpSession session, SchoolSubject subject) {
        //返回
        ApiResult result = new ApiResult();
        //查重
        int isCopy = this.subjectMapper.selectBySubjectName(subject.getSubjectName());
        if(isCopy > 0){ //存在
            log.error("数据重复");
            //数据已经存在
            result.setCode(ApiCode.error_duplicated_data);
            result.setMsg(ApiCode.error_duplicated_data_MSG);
            return result;
        }
        Long adminId = (Long) session.getAttribute("adminId");
        //开发，假设创建人ID为0
        subject.setFounderId(adminId);
//        subject.setFounderId(adminId);
        subject.setSchoolId(Long.parseLong(schoolId));
        //创建时间
        subject.setCreateTime(new Date());
        int flag = this.subjectMapper.insertSelective(subject);
        //成功
        if(flag > 0){
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }


    /**
     * 查询科目
     */
    @Override
    @Transactional
    public List<SchoolSubject> findSchoolSunjectInfo(Long schoolId/*,Pager pager*/) {
//        //查询总条数
//        int total = this.subjectMapper.selectCountByAll(schoolId, pager);
//        //limit #{pager.minRow},#{pager.pageSize}
//        System.out.println(total);
//        pager.setRecordTotal(total);
        // 查询集合
        List<SchoolSubject> result = this.subjectMapper.selectByScholId(schoolId/*, pager*/);
        return result;
    }


    /**
     * 修改课程信息
     */
    @Override
    @Transactional
    public boolean updateSubjectInfo(SchoolSubject subject) {
        //返回标志
        boolean success = false;
        int result = this.subjectMapper.updateByPrimaryKeySelective(subject);
        if(result > 0){
            success = true;
        }
        return success;
    }


    /**
     *删除课程信息，非物理删除
     * @param subject
     */
    @Override
    @Transactional
    public ApiResult updataSubjectInfoDel(SchoolSubject subject) {
        ApiResult result = new ApiResult();
        //参数检查
        if(subject.getSubjectId() == null){
            result.setCode(ApiCode.error_invalid_argument);
            result.setMsg(ApiCode.error_invalid_argument_MSG);
            return result;
        }
        //统计课程,有无使用
        int isCopy = this.infoMapper.selectCountBySubjectId(subject.getSubjectId());
        System.out.println("重复" + isCopy);
        if(isCopy > 0){
            log.error("数据存在使用");
            result.setCode(ApiCode.error_duplicated_data);
            result.setMsg(ApiCode.error_duplicated_data_MSG);
            return result;
        }
        //删除标志
        subject.setDeleteTime(new Date());
        subject.setState("0");
        int flag_s = this.subjectMapper.updateByPrimaryKeySelective(subject);
        System.out.println(flag_s + " 成功");
        if(flag_s > 0){
            result.setCode(ApiCode.SUCCESS);
            result.setMsg(ApiCode.SUCCESS_MSG);
        }else{
            result.setCode(ApiCode.error_delete_failed);
            result.setMsg(ApiCode.error_delete_failed_MSG);
        }
        return result;
    }


    /**
     * 停课,修改isBegin
     * @param
     * @return
     */
    @Override
    public ApiResult updataStopSubject(SchoolSubject subject) {
        ApiResult result = new ApiResult();
        //参数检查
        if(subject.getSubjectId() == null){
            result.setCode(ApiCode.INVALID_PARAM);
            result.setMsg(ApiCode.INVALID_PARAM_MSG);
            return result;
        }
        //删除标志
        subject.setIsBegin("0");
        int flag_r = this.subjectMapper.updateByPrimaryKeySelective(subject);
        if(flag_r > 0){
            result.setCode(ApiCode.SUCCESS);
            result.setMsg(ApiCode.SUCCESS_MSG);
        }
        return result;
    }

    /**
     * 选择导出数据
     */
    @Override
    @Transactional
    public void exportSubjectInfo(HttpServletResponse response, List<Long> subjectList) {
        OutputStream out = null;

        System.out.println("进入2");
        for(Long val : subjectList){
            System.out.println(val);
        }

        try {
            //获取流
            try {
                out = response.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置标题
            String[] titles = {"开课名称", "开课时间", "状态"};
            //Excel结果集
            List<List<String>> dataHandler = new ArrayList<List<String>>();
            //查询结果集
            List<SchoolSubject> data = this.subjectMapper.selectBathInfo(subjectList);
            //测试
            for(SchoolSubject s : data){
                System.out.println(s.toString());
            }

            //结果集
            List<List<String>> sumData = new ArrayList<List<String>>();
            for(SchoolSubject info : data){
                List<String> rowData  = new ArrayList<String>();
                rowData.add(info.getSubjectName());
                //时间处理
                rowData.add(TimeToString.DateToStr(info.getCreateTime()));
                //状态处理
                rowData.add("1".equals(info.getIsBegin()) ? "正在使用" : "停课");
                dataHandler.add(rowData);
            }
            //创建工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            try {
                //导出信息
                exportExcelUtil.exportExcel(workbook, 0, "开课导出信息", titles, dataHandler, out);
                workbook.write(out);
            } catch (Exception e) {
                log.error("导出失败");
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.error("无法写出文件");
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("关闭流失败");
                e.printStackTrace();
            }
        }
    }


    /**
     * 导出全部数据
     */
    @Override
    public ApiResult exportSubjectInfoAll(HttpServletResponse response) {
        //返回对象
        ApiResult result = new ApiResult();
        OutputStream out = null;
        Long schoolId = toLong();
        System.out.println("进入2");
        try {
            //获取流
            try {
                out = response.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置标题
            String[] titles = {"开课名称", "开课时间", "状态"};
            //Excel结果集
            List<List<String>> dataHandler = new ArrayList<List<String>>();
            //查询结果集
//            List<SchoolSubject> data = this.subjectMapper.selectBathInfo(subjectList);
            List<SchoolSubject> data = this.subjectMapper.selectBySchoolIdAllInfo(schoolId);

            //测试
            for(SchoolSubject s : data){
                System.out.println(s.toString());
            }

            //结果集
            List<List<String>> sumData = new ArrayList<List<String>>();
            for(SchoolSubject info : data){
                List<String> rowData  = new ArrayList<String>();
                rowData.add(info.getSubjectName());
                //时间处理
                rowData.add(TimeToString.DateToStr(info.getCreateTime()));
                //状态处理
                rowData.add("1".equals(info.getIsBegin()) ? "正在使用" : "停课");
                dataHandler.add(rowData);
            }
            //创建工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            try {
                //导出信息
                exportExcelUtil.exportExcel(workbook, 0, "开课导出信息", titles, dataHandler, out);
                workbook.write(out);
            } catch (Exception e) {
                log.error("导出失败");
                result.setCode(ApiCode.export_failed);
                result.setMsg(ApiCode.export_failed_MSG);
                e.printStackTrace();
                return result;
            }
        } catch (Exception e) {
            log.error("无法写出文件");
            result.setCode(ApiCode.export_failed);
            result.setMsg(ApiCode.export_failed_MSG);
            e.printStackTrace();
            return result;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                result.setCode(ApiCode.http_status_internal_server_error);
                result.setMsg(ApiCode.http_status_internal_server_error_MSG);
                log.error("关闭流失败");
                e.printStackTrace();
                return result;
            }
        }
        result.setCode(ApiCode.export_success);
        result.setMsg(ApiCode.export_success_MSG);
        return result;
    }


    /**
     * 设置课程(新增)
     * 以课程为基础，设置班级课程
     * @param
     * @return
     */
    @Override
    @Transactional
    public ApiResult SelectSubjectAndClassRight(Map params,
                                                HttpSession session) {
        System.out.println("进入逻辑");
        ApiResult result = new ApiResult();
        //班级ID
        List<Integer> classId = (List<Integer>)params.get("classId");
        //科目
        List<Integer> subjectId = (List<Integer>)params.get("subjectId");
        //标志
        int flag = 0;
        for(Integer id : classId){
            //去除重复
            for(Integer item : subjectId){
                Long tempId = id.longValue();
                Long tempItem = item.longValue();
                int flag_a = this.infoMapper.selectByclassByClassIdAndSubjectId(tempId, tempItem);
                //不存在,插入
                if(flag_a <= 0){
//                    subjectId.remove(item);
                    ClassSubjectInfo info = new ClassSubjectInfo();
                    info.setClassId(tempId);
                    info.setSubjectId(tempItem);
                    //插入
                    flag = this.infoMapper.insertSelective(info);
                }else{
                    flag = 1;
                }
            }
        }
        System.out.println(flag + " 标志");
//        int flag_b = this.infoMapper.insertSelectiveBath(classId, subjectId);
        if(flag > 0){
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }


    /**
     * 设置课程(删除)
     * 以课程为基础，设置班级课程
     * @param
     * @return
     */
    @Override
    @Transactional
    public ApiResult SelectSubjectAndClassLeight(Map params,
                                                 HttpSession session) {
        ApiResult result = new ApiResult();
        //班级ID
        List<Integer> classId = (List<Integer>)params.get("classId");
        //科目
        List<Integer> subjectId = (List<Integer>)params.get("subjectId");
        int flag = 0;
        for(Integer id : classId){
            //去除重复
            for(Integer item : subjectId){
                Long tempId = id.longValue();
                Long tempItem = item.longValue();
                int flag_a = this.infoMapper.selectByclassByClassIdAndSubjectId(tempId, tempItem);
                //存在,删除
                if(flag_a > 0){
//                    subjectId.remove(item);
                    ClassSubjectInfo info = new ClassSubjectInfo();
                    info.setState("0");
                    info.setClassId(tempId);
                    info.setSubjectId(tempItem);
                    //删除
                    flag = this.infoMapper.updateByClassIdAndSubjectIdDel(info);
                }else{
                    flag = 1;
                }
            }
        }
//        for(Long item : subjectId){
//            int flag_a = this.infoMapper.selectByclassByClassIdAndSubjectId(classId, item);
//            //不存在,说明是学校课程
//            if(flag_a <= 0){
//                //移除
//                subjectId.remove(item);
//            }
//        }
//        System.out.println(subjectId.size() + " 后长度");
//        //批量删除
//        int flag_b = 0;
//        for(Long item : subjectId){
//            flag_b = this.infoMapper.deleteByBatch(classId, item);
//        }
        //标志
        if(flag > 0){
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }

    @Override
    public List<SchoolSubject> findAllSubjectBySubjectsId(Long subjectsId) {
        return subjectMapper.findAllSchoolSubjectBySubjectsId(subjectsId);
    }

    @Override
    public int deleteSubject(Long subjectId) {
        //统计课程,有无使用
        int isCopy = infoMapper.selectCountBySubjectId(subjectId);
        if(isCopy > 0){
            return ApiCode.error_delete_failed;
        }
        return subjectMapper.deleteByPrimaryKey(subjectId);
    }


}
