package com.cj.witscorefind.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.*;
import com.cj.witcommon.entity.ApiCode;
import com.cj.witcommon.entity.ApiResult;
import com.cj.witcommon.entity.ApiResultUtil;
import com.cj.witcommon.entity.ClassGradeInfo;
import com.cj.witcommon.utils.TimeToString;
import com.cj.witcommon.utils.excle.exportExcelUtil;
import com.cj.witscorefind.service.FindScoreService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FindScoreServiceImpl implements FindScoreService {

    private static final Logger log = LoggerFactory.getLogger(FindScoreServiceImpl.class);

    @Autowired(required = false)
    private StudentScoreMapper scoreMapper;

    @Autowired(required = false)
    private ClassSubjectInfoMapper clsInfo;

    @Autowired(required = false)
    private AdminInfoMapper adminInfoMapper;

    @Autowired(required = false)
    private PeriodDirectorThetimeMapper directorTimeMapper;

    @Autowired(required = false)
    private SchoolPeriodClassThetimeMapper classThetimeMapper;

    @Autowired(required = false)
    private SchoolClassMapper classMapper;

    @Autowired(required = false)

    /**
     * 选择导出
     * @param classIdList
     * @param response
     */
    @Override
    public void exportScoreInfo(List<Long> classIdList, HttpServletResponse response) {
        OutputStream out = null;
        try {
            //获取流
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置标题
        String[] titles = {"班级", "实考人数", "缺考人数", "平均分", "最低分", "最高分", "排名"};
        //Excel结果集
        List<List<String>> dataHandler = new ArrayList<List<String>>();
        List<ClassGradeInfo> data = this.scoreMapper.selectBathInfo(classIdList);
        //测试
        for(Object s : data){
            System.out.println(s.toString());
        }
        //结果集
        List<List<String>> sumData = new ArrayList<List<String>>();
        for(ClassGradeInfo info : data){
            List<String> rowData  = new ArrayList<String>();
            rowData.add(info.getClassName());
            rowData.add(String.valueOf(info.getActuallyCome()));
            rowData.add(String.valueOf(info.getNotCome()));
            rowData.add(String.valueOf(info.getAvgScore()));
            rowData.add(String.valueOf(info.getMinScore()));
            rowData.add(String.valueOf(info.getMaxScore()));
            rowData.add(String.valueOf(info.getRank()));
            dataHandler.add(rowData);
        }
        //创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //导出信息
        try {
            exportExcelUtil.exportExcel(workbook, 0, "成绩导出信息", titles, dataHandler, out);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不同的角色,查询不同的成绩
     * @return
     */
    @Override
    public ApiResult findScoreForDifferentRole(HttpServletRequest request) {
        System.out.println("进入逻辑！");
        ApiResult result = new ApiResult();
        //获取管理员ID
//        Long adminId = (Long) request.getSession().getAttribute("adminId");
        //根据管理员角色ID,查询管理员表
        //TODO:GG
//        Admin admin = null;
        Admin admin = new Admin();
        admin.setId(61L);
        System.out.println(admin.toString());
        //TODO:gg
        //根据角色ID,查询角色
        AdminRole role = new AdminRole();
        role.setId(4);
        role.setType("3");
        System.out.println(role.toString());
//        AdminRole role = null;
        switch (role.getType()){
            //待定
            case "0" :
            case "1" :
            case "2" :
                System.out.println("各种管理员");
//                result = allAdmin();
                break;

            case "3" :
                System.out.println("班主任/科目教师/年级主任逻辑");
                result = caseThree(result, role, admin);
                break;
            default : break;
        }
        return result;
    }


    /**
     * 添加成绩档次
     * @param info
     * @return
     */
    @Override
    public ApiResult addGradeLevelInfo(Grade info) {
        ApiResult result = new ApiResult();
        //  1--为分数档次   2--为名次档次
        if("1".equals(info.getGradeType())){
            //查重,根据exam_grade_id,考试ID
            int isCopy = 0;
            if(isCopy > 0){
                //数据存在
                result.setCode(ApiCode.error_duplicated_data);
                result.setMsg(ApiCode.error_duplicated_data_MSG);
                return result;
            }
            //save保存
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else if("2".equals(info.getGradeType())){
            //查重,根据exam_grade_id,考试ID
            int isCopy = 0;
            if(isCopy > 0){
                //数据存在
                result.setCode(ApiCode.error_duplicated_data);
                result.setMsg(ApiCode.error_duplicated_data_MSG);
                return result;
            }
            //save保存
            ApiResultUtil.fastResultHandler(result, true, null, null, null);
        }else{
            ApiResultUtil.fastResultHandler(result, false, ApiCode.error_create_failed, ApiCode.FAIL_MSG, null);
        }
        return result;
    }


    /**********************************************************************************************/
    /*******************************程序私有方法****************************************************/
    /**********************************************************************************************/
    //角色类型3 -- 包含(班主任,科目教师,年级主任)
    private ApiResult caseThree(ApiResult result, AdminRole role, Admin admin){
        //角色类型,获取角色ID
        int id = role.getId();
        System.out.println("角色ID: " + id);
        if(id == 4){        //班主任
            //根据班主任ID,查询对应班科目分数统计
            Long headmasterId = admin.getId();
            SchoolPeriodClassThetime scTime = this.classThetimeMapper.selectByAdminId(headmasterId);
            //根据班主任ID,查找班级
            List info = this.scoreMapper.selectPowerByHeadmaster(scTime.getClassId());
            for(Object a : info){
                System.out.println(a.toString());
            }
            //数据的封装
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else if(id == 5){  //科目教师
            //根据ID,查询对应科目
            Long adminId = admin.getId();
            AdminInfo adminInfo = this.adminInfoMapper.selectByadminId(adminId);
            //根据科目教师查询对应科目和班级
            List<ClassSubjectInfo> subClass = this.clsInfo.selectAdminId(adminInfo.getAdminInfoId());
            //根据id,查询
            List info = this.scoreMapper.selectPowerBySubjectTeacher(subClass);
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else if(id == 21){ //年级主任
            //获取ID
            Long adminId = admin.getId();
            PeriodDirectorThetime tempInfo = this.directorTimeMapper.selectByDirectorId(adminId);
            //根据学段ID,毕业届次查询
            List info = this.scoreMapper.selectPowerByPeriodIdAndThetime(tempInfo);
            ApiResultUtil.fastResultHandler(result, true, null, null, info);
        }else{
            result.setCode(ApiCode.error_search_failed);
            result.setMsg(ApiCode.error_search_failed_MSG);
        }
        return result;
    }
    //角色类型 -- 包含(各种系统管理员)
    private ApiResult allAdmin(ApiResult result, AdminRole role, Admin admin){

        return null;
    }






    /**
     * 查看分数
     * @param classId
     * @param subjectId
     * @return
     */
    @Override
    public List<Map> findScoreDetils(Long classId, Long subjectId) {
        List<Map> result = this.scoreMapper.selectByClassIdAndSubjectId(classId, subjectId);
        return result;
    }

}
