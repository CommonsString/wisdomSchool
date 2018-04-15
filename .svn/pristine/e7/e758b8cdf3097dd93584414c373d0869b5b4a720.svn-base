package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.SchoolSubject;
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

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class SchoolSubjectServiceImpl implements SchoolSubjectService {

    private static final Logger log = LoggerFactory.getLogger(SchoolSubjectServiceImpl.class);

    @Autowired(required = false)
    private SchoolSubjectMapper subjectMapper;

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
    public ApiResult addSubjectInfo(SchoolSubject subject) {
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
        //创建时间
        subject.setCreateTime(new Date());
        int flag = this.subjectMapper.insertSelective(subject);
        //成功
        if(flag > 0){
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }


    /**
     * 查询科目
     */
    @Override
    @Transactional
    public Pager findSchoolSunjectInfo(Long schoolId,Pager pager) {
        //查询总条数
        int total = this.subjectMapper.selectCountByAll(schoolId, pager);
        //limit #{pager.minRow},#{pager.pageSize}
System.out.println(total);
        pager.setPageTotal(total);
        // 查询集合
        List<SchoolSubject> result = this.subjectMapper.selectByScholId(schoolId, pager);
        if(result.size() > 0){
            pager.setContent(result);
        }else{
            return null;
        }
        return pager;
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
    public boolean updataSubjectInfoDel(SchoolSubject subject) {
        //参数检查
        if(subject.getSubjectId() == null) return false;
        //返回标志
        boolean success = false;
        //删除标志
        subject.setState("0");
        subject.setDeleteTime(new Date());
        int result = this.subjectMapper.updateByPrimaryKeySelective(subject);
        if(result > 0){
            success = true;
        }
        return success;
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
}
