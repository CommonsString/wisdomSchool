package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.AdminInfoMapper;
import com.cj.witbasics.mapper.SchoolBasicMapper;
import com.cj.witbasics.mapper.SchoolPeriodMapper;
import com.cj.witbasics.mapper.StudentOsaasMapper;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.StudentManagementService;
import com.cj.witcommon.entity.SLIDUtil;
import com.cj.witcommon.entity.SynBasicInformation;
import com.cj.witcommon.utils.common.FileUtil;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witcommon.utils.excle.ImportExeclUtil;
import com.cj.witcommon.utils.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
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


    @Value("${web.upload-path}")
    String path;

    @Value("${student_staff_prefix}")
    String studentStaffPrefix;

    @Value("${default_admin_pass}")
    String defaultAdminPass;

    @Override
    public List<StudentOsaas> findStudentsByCondition(Pager p) {

        return studentOsaasMapper.findStudentsByCondition(p);
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
    @Transactional
    public int importStucents(MultipartFile multipartFile,HttpServletRequest request) throws Exception {

        String fileName = multipartFile.getOriginalFilename();
        InputStream in = multipartFile.getInputStream();



//        Workbook workbook = WorkbookFactory.create(in);
//        Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet

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
//        Map map = schoolBasicMapper.findAllSchoolBasic();





        int nums = 0;  //学生信息导入成功数量
        for (StudentOsaas studentOsaas : osaasList) {
            System.out.println(studentOsaas);

            //学生信息导入对象装换及信息处理

            //调用添加一个学生对象接口
            nums += addStudentOsaasinfo(studentOsaas,request);

        }


        return nums;
    }


    //生成学号
    @Override
    public String getStudentNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        return studentStaffPrefix+sdf.format(new Date());
    }

    /*
     * 添加一个学生对象信息
     * */
    @Override
    @Transactional
    public  int addStudentOsaasinfo(StudentOsaas studentOsaas,HttpServletRequest request) {
        //数据库中此学号的数量
//        int i = studentOsaasMapper.selectBySchoolNumber(studentOsaas.getSchoolNumber());
        //学生信息是否保存成功
        int j = 0;


        //添加账号
        Admin admin = new Admin();
        admin.setRoleId(6l);
        if(studentOsaas.getSchoolNumber() != null && studentOsaas.getSchoolNumber().length() > 0){
            admin.setAdminName("s"+studentOsaas.getSchoolNumber());  //取学号做账号
        }else {
            admin.setAdminName("s"+studentOsaas.getRegisterNumber());  //取学籍号做账号
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