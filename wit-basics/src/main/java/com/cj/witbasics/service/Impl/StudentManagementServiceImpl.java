package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.*;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.StudentManagementService;
import com.cj.witcommon.entity.*;
import com.cj.witcommon.utils.common.FileUtil;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witcommon.utils.excle.ImportExeclUtil;
import com.cj.witcommon.utils.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
@Transactional
public class StudentManagementServiceImpl implements StudentManagementService {


    @Autowired(required = false)
    private StudentOsaasMapper studentOsaasMapper;

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Autowired
    private CloudService cloudService;

    @Autowired
    private SchoolPeriodMapper schoolPeriodMapper;

    @Autowired
    private SchoolBasicMapper schoolBasicMapper;

    @Autowired
    private SchoolClassMapper schoolClassMapper;


    @Value("${web.upload-path}")
    String path;

    @Value("${student_staff_prefix}")
    String studentStaffPrefix;

    @Value("${school_id}")
    String schoolId;

    @Value("${default_admin_pass}")
    String defaultAdminPass;


    List<PeriodUnderGrade> periodUnderGrades = new ArrayList<>();
    List<PeriodAndThetime> periodAndThetimes = new ArrayList<>();

    @Override
    public Pager findStudentsByCondition(Pager p) {

        p.setRecordTotal(studentOsaasMapper.findStudentsByConditionTotal(p));
        p.setContent(studentOsaasMapper.findStudentsByCondition(p));

        return p;
    }

    @Override
    public void downloadStudengTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //文件名
        String fileName ="student.xls";
        new FileUtil().download(request,response,path,fileName);

    }

    //根据adminId查询 StudentOsaas
    @Override
    public StudentOsaas selectStudentOsaas(Long adminId) {

        return studentOsaasMapper.selectStudentOsaas(adminId);
    }
    //根据adminId  修改学生信息
    @Override
    public int updateStaffInfo(StudentOsaas studentOsaas) {
        return studentOsaasMapper.updateByPrimaryKeySelective(studentOsaas);
    }



    //学生信息批量导入
    @Override
    public Map importStucents(MultipartFile multipartFile,HttpServletRequest request) throws Exception {

        String fileName = multipartFile.getOriginalFilename();
        InputStream in = multipartFile.getInputStream();




        Workbook wb = ImportExeclUtil.chooseWorkbook(fileName, in);
        int num = wb.getNumberOfSheets();
        System.out.println("sheet数量==>>"+num);

        //接收多个sheet中的数据
        List<StudentOsaas> osaasList = new ArrayList<>();


        for (int i = 0;i<num ;i++) {

            //读取对象列表的信息
            StudentOsaas studentOsaas = new StudentOsaas();

            //第二行开始，到倒数第一行结束（总数减去0行）
            List<StudentOsaas> readDateListT = ImportExeclUtil.readDateListT(wb,studentOsaas , 2, 0,i);
//            System.out.println(readDateListT);


            osaasList.addAll(readDateListT);
        }



        //查询本校区 学校、学段、年级、班级 的 ID、名称
//        Map map0 = schoolBasicMapper.findAllSchoolBasic(Long.parseLong(schoolId));

        //查询学段、年级信息 树形结构
        periodUnderGrades = schoolPeriodMapper.findAllPeriodAndGradeBySchoolId(Long.parseLong(schoolId));

        //查询学段、届次、班级信息 树形结构
        periodAndThetimes = schoolClassMapper.findAllPeriodAndThetimeAndClassBySchoolId(Long.parseLong(schoolId));






        System.out.println("===================================================");
        System.out.println("osaasList.szie()============================"+osaasList.size());
        int nums = 0;  //学生信息导入成功数量
        List msgList = new ArrayList();
        Map map = new HashMap();
        for (StudentOsaas studentOsaas : osaasList) {
            System.out.println(studentOsaas);

            //学生信息导入对象装换及信息处理

            //调用添加一个学生对象接口
            int i = addStudentOsaasinfo(studentOsaas,request);

            if(i == 1){
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理成功 ");
                nums += i;
            }else if(i == ApiCode.period_error){
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理失败 "+ApiCode.period_error_MSG);

            }else if(i == ApiCode.grade_error){
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理失败 "+ApiCode.grade_error_MSG);

            }else if(i == ApiCode.thetime_error){
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理失败 "+ApiCode.thetime_error_MSG);

            }else if(i == ApiCode.class_error){
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理失败 "+ApiCode.class_error_MSG);
            }else {
                msgList.add(studentOsaas.getFullName()+":("+studentOsaas.getRegisterNumber()+")处理失败 ");
            }

        }

        map.put("nums",nums);
        map.put("msgList",msgList);

        return map;
    }


    //生成学号
    @Override
    public String getStudentNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return studentStaffPrefix+schoolId+sdf.format(new Date());
    }

    /*
     * 添加一个学生对象信息
     * */
    @Override
    @Transactional
    public  int addStudentOsaasinfo(StudentOsaas studentOsaas,HttpServletRequest request) {
//        //数据库中此学号的数量
//        int i = studentOsaasMapper.selectBySchoolNumber(studentOsaas.getSchoolNumber());

        //检查 学段、年级、班级 是否存在
        int periodSystem = 0;

        //检查学段是否存在
        for(PeriodUnderGrade periodUnderGrade : periodUnderGrades){
            if(periodUnderGrade.getPeriodName().equals(studentOsaas.getPeriodIdName())){  //学段匹配
                studentOsaas.setPeriodId(periodUnderGrade.getPeriodId());  //设置学段ID
                periodSystem = periodUnderGrade.getPeriodSystem();  //设置此学生的学制

                //检查年级是否存在
                for (GradeInfo gradeInfo: periodUnderGrade.getGrade()){

                    if(gradeInfo.getGradeName().equals(studentOsaas.getGradeIdName())){  //年级匹配
                        studentOsaas.setGradeId(gradeInfo.getGradeId());  //设置年级ID

                        break;

                    }

                }

                break;
            }
        }
        if(studentOsaas.getPeriodId() == null){
            //学段不存在
            return ApiCode.period_error;
        }
        if(studentOsaas.getGradeId() == null){
            //年级不存在
            return ApiCode.grade_error;

        }

        //年级存在的话，匹配当前年级-届次

        //获取当前年份
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.YEAR);

        //获取当前月份
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH)+1;

        //设置当前学生届次
        String thetime = null;  //届次等于当前年+学制

        if(month>8){  //如果当前大于8月（9,10，11,12月）

            switch (studentOsaas.getGradeIdName()){
                case "1年级": thetime = year+periodSystem-0+"";break;
                case "一年级": thetime = year+periodSystem-0+"";break;
                case "2年级": thetime = year+periodSystem-1+"";break;
                case "二年级": thetime = year+periodSystem-1+"";break;
                case "3年级": thetime = year+periodSystem-2+"";break;
                case "三年级": thetime = year+periodSystem-2+"";break;
                case "4年级": thetime = year+periodSystem-3+"";break;
                case "四年级": thetime = year+periodSystem-3+"";break;
                case "5年级": thetime = year+periodSystem-4+"";break;
                case "五年级": thetime = year+periodSystem-4+"";break;
                case "6年级": thetime = year+periodSystem-5+"";break;
                case "六年级": thetime = year+periodSystem-5+"";break;
            }

        }else {  //如果当前月份为1,2,3,4,5,6,7,8月
            switch (studentOsaas.getGradeIdName()){
                case "1年级": thetime = year+periodSystem-1+"";break;
                case "一年级": thetime = year+periodSystem-1+"";break;
                case "2年级": thetime = year+periodSystem-2+"";break;
                case "二年级": thetime = year+periodSystem-2+"";break;
                case "3年级": thetime = year+periodSystem-3+"";break;
                case "三年级": thetime = year+periodSystem-3+"";break;
                case "4年级": thetime = year+periodSystem-4+"";break;
                case "四年级": thetime = year+periodSystem-4+"";break;
                case "5年级": thetime = year+periodSystem-5+"";break;
                case "五年级": thetime = year+periodSystem-5+"";break;
                case "6年级": thetime = year+periodSystem-6+"";break;
                case "六年级": thetime = year+periodSystem-6+"";break;
            }
        }

        if(thetime != null && thetime != ""){
            for(PeriodAndThetime periodAndThetime : periodAndThetimes){
                if(periodAndThetime.getClassPeriod().equals(studentOsaas.getPeriodIdName())){  //匹配学段
                    for (ClassYear classYear : periodAndThetime.getThisYear()){
                        if(classYear.getThetiem().equals(thetime)){
                            studentOsaas.setBelongToYear(thetime);
                            for (ClassInfo classInfo : classYear.getClassInfo()){
                                if(classInfo.getClassName().equals(studentOsaas.getClassIdName())){   //匹配班级
                                    studentOsaas.setClassId(classInfo.getClassId());  //设置班级ID
                                    break;
                                }
                            }
                            break;

                        }
                    }

                    break;

                }

            }

        }

        if(studentOsaas.getBelongToYear() == null){
            //届次不存在
            return ApiCode.thetime_error;
        }

        if(studentOsaas.getClassId() == null){

            //班级不存在
            return ApiCode.class_error;
        }




        //根据学籍号 查询 adminId
        Long adminId0 = studentOsaasMapper.findAdminIdByRegisterNumber(studentOsaas.getRegisterNumber());
        //如果信息已存在，则更新
        if(adminId0!=null && adminId0>0){
            studentOsaas.setAdminId(adminId0);
            int i = studentOsaasMapper.updateByPrimaryKeySelective(studentOsaas);
            return i;

        }
        //学生信息是否保存成功
        int j = 0;


        //添加账号
        Admin admin = new Admin();
        admin.setRoleId(6l);
        if(studentOsaas.getRegisterNumber() != null && studentOsaas.getRegisterNumber().length() > 0){
//            admin.setAdminName(studentOsaas.getSchoolNumber());  //取学号做账号
//        }else {
            admin.setAdminName(studentOsaas.getRegisterNumber());  //取学籍号做账号
        }

        admin.setAdminPass(Md5Utils.MD5Encode(defaultAdminPass,"UTF-8",false));
        admin.setAdminType("2");
        admin.setCreateTime(new Date());
        Long adminId = 0l;

        //根据角色ID查询角色信息
        AdminRole adminRole = adminInfoMapper.findAdminRoleByAdminRoleId(admin.getRoleId());
        //根据学段ID查询学段信息
        SchoolPeriod schoolPeriod = schoolPeriodMapper.selectByPrimaryKey(studentOsaas.getPeriodId());




        //TODO:云端创建学生账号
        if(cloudService.cloudRegisterSM(request,admin,adminRole,schoolPeriod)){
            Long newUserId = (Long) request.getSession().getAttribute("newUserId");
            if(newUserId != null && newUserId > 0){
                //设置adminId为云端注册的ID
                admin.setId(newUserId);
            }

            //本地创建学生账号
            adminInfoMapper.insertAdminSelective(admin);

            adminId = admin.getId();
            //设置adminID
            studentOsaas.setAdminId(adminId);
            String newUserUUID = (String) request.getSession().getAttribute("newUserUUID");
            //设置用户uuid为云端注册的uuid
            if(newUserUUID != null && newUserUUID.length() > 0){
                studentOsaas.setUserUuid(newUserUUID);
            }else {  //根据角色类型生成uuid
                studentOsaas.setUserUuid(SLIDUtil.newUUID(adminRole.getType()));
            }

            SynBasicInformation synBasicInformation = new SynBasicInformation();
            synBasicInformation.setName(adminInfoMapper.findAdminNameByadminId(studentOsaas.getAdminId()));  //用户名
            synBasicInformation.setNickName(studentOsaas.getUserNike());  //昵称
            synBasicInformation.setEnglishName(studentOsaas.getEnglishName());  //英文名
            synBasicInformation.setBirthday(studentOsaas.getBirthDate());  //生日
            synBasicInformation.setSex(studentOsaas.getGender());  //性别
            synBasicInformation.setAvatar(studentOsaas.getUserHead());  //头像


            //TODO:云端同步学生信息
            if(cloudService.cloudSynchronization(request,synBasicInformation)){
                j = studentOsaasMapper.insertSelective(studentOsaas);
            }

        }

        return j;






    }


}