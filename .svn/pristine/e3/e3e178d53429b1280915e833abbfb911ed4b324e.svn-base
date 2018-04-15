package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.*;
import com.cj.witbasics.mapper.*;
import com.cj.witbasics.service.SchoolClassService;
import com.cj.witcommon.entity.SchoolClassInfo;
import com.cj.witcommon.utils.common.StringHandler;
import com.cj.witcommon.utils.entity.other.Pager;
import com.cj.witcommon.utils.excle.ImportExeclUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;


@Service
public class SchoolClassServiceImpl implements SchoolClassService{

    private static final Logger log = LoggerFactory.getLogger(SchoolClassServiceImpl.class);

    @Autowired(required = false) //忽略bean创建
    private SchoolClassMapper classMapper;

    @Autowired(required = false)
    private SchoolClassTypeMapper classTypeMapper;

    @Autowired(required = false)
    private SchoolClassLevelMapper classLevelMapper;

    @Autowired(required = false)
    private ClassSubjectInfoMapper clsInfoMapper;

    @Autowired(required = false)
    private AdminInfoMapper adminInfoMapper;

    @Autowired(required = false)
    private SchoolPeriodMapper periodMapper;

    @Autowired(required = false)
    private SchoolGradeMapper gradeMapper;


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
     * excel文件导入解析
     */
    @Override
    @Transactional
    public boolean bathImportInfo(MultipartFile file, InputStream in, Long operatorId){
        int result = 0;
        System.out.println("进入2");
        //TODO:班级名重名?
        try{
            String fileName = file.getOriginalFilename(); //获取文件名
            Workbook workbook = ImportExeclUtil.chooseWorkbook(fileName, in);
            int sheets = workbook.getNumberOfSheets(); //获取sheet数量
            for(int i = 0; i < sheets; i++){
                SchoolClass baseInfo = new SchoolClass(); //创建列表信息
                List<SchoolClass> readBaseInfo = ImportExeclUtil.readDateListT(workbook, baseInfo, 4, 0, i);
System.out.println(readBaseInfo);
                Date createTime = new Date();//创建的时间
System.out.println(readBaseInfo.size() + " 长度");
                for(SchoolClass info : readBaseInfo){
//                    System.out.println(info.toString());
                    //根据校区名称查询校区ID(备注，校区ID需要校区表)
//                    Long schoolId = this.classMapper.selectByClassName(info.getClassCampus());
                    Long schoolId = toLong();
                    //根据班级类型名，查询班级类型Id
//System.out.println(" 班级类型 " + info.getClassType());
                    Integer classTypeId = this.classTypeMapper.selectByClassTypeName(info.getClassType());
//System.out.println("班级类型ID  " + classTypeId);
                    //注入学段
//System.out.println("学段名称 " + info.getClassPeriod());
                    Long periodId = this.periodMapper.selectPeriodIdByPeriodName(info.getClassPeriod());
//System.out.println("学段ID： " + PeriodId);
//System.out.println("班级类型ID： " + classTypeId/*  + " 学段ID " + PeriodId*/);
                    //注入属性
                    info.setSchoolId(schoolId);
                    info.setClassTypeId(classTypeId);
                    //学段ID
                    info.setClassPeriodId(new Long(periodId).intValue());
                    //处理时间
//                    String classYear = info.getClassYear();

                    info.setCreateTime(createTime);
                    info.setOperatorId(operatorId);
                    result = this.classMapper.insertSelective(info);
System.out.println(info.toString() + "  插入对象");
                }
            }
        }catch (Exception e){
            log.error("读取文件失败！");
            e.printStackTrace();
        }
System.out.println(result + " 标志");
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 返回类型/层次/学段
     */
    @Override
    @Transactional
    public List findSchoolLevelTypePeriod(Long schoolId) {
        //根据学校ID，查询班级类型
        List<SchoolClassType> type = this.classTypeMapper.selectTypeBySchoolId(schoolId);
        List<SchoolClassLevel> level = this.classLevelMapper.sselectLevelBySchoolId(schoolId);
        List<SchoolPeriod> period = this.periodMapper.selectInfoBySchoolId(schoolId);
        List result = new ArrayList();
        result.add(type);
        result.add(level);
        result.add(period);
        return result;
    }


    /**
     * 返回班级类型
     */
    @Override
    @Transactional
    public List findSchoolLevelType(Long schoolId) {
        List<SchoolClassType> type = this.classTypeMapper.selectTypeBySchoolId(schoolId);
        System.out.println(type.size());
        return type;
    }

    /**
     * 返回班级层次
     */
    @Override
    public List findSchoolLevel(Long schoolId) {
        List<SchoolClassLevel> level = this.classLevelMapper.sselectLevelBySchoolId(schoolId);
        return level;
    }

    /**
     * 新增班级类型
     */
    @Override
    @Transactional
    public boolean addClassType(Long schoolId, List<String> classType) {
        //参数检测
        if(classType.size() <= 0) return false;
        //返回标志
        boolean success = false;
        //查重标志
        boolean isCopy = false;
        //判断类型,查重
        for(String typeName : classType){
            int flag = this.classTypeMapper.selectByClassType(typeName);
            if(flag > 0){
                isCopy = true;
                break;
            }
        }
        if(!isCopy){ //批量插入
            ArrayList<SchoolClassType> data = new ArrayList<SchoolClassType>();
            for(int i = 0, len = classType.size(); i < len; i++){
                //构造对象
                SchoolClassType type = new SchoolClassType();
                type.setSchoolId(schoolId);
                type.setClasstypeName(classType.get(i));
                data.add(type);
            }
System.out.println(data.size() + " 长度");
            int result = this.classTypeMapper.addBathClassType(data);
System.out.println(result + "结果");
            if(result > 0){
                log.info("插入成功");
                success = true;
            }
        }else{
            log.info("存在重复字段");
            success = false;
        }
        return success;
    }

    /**
     * 更新班级类型，根据id来修改对应的班级类型名称
     */
    @Override
    @Transactional
    public boolean updateClassType(int classTypeId, String classTypeName) {
        SchoolClassType classType = new SchoolClassType(); //构造对象
        classType.setClassTypeId(classTypeId);
        classType.setClasstypeName(classTypeName);
        int result = this.classTypeMapper.updateByPrimaryKeySelective(classType);
        if(result <= 0) throw new RuntimeException(); //更新失败
        return true;
    }


    /**
     * 删除班级类型，物理删除
     */
    @Override
    @Transactional
    public boolean updateClassTypeDel(int classTypeId) {
        //判断班级类型下有无班级
        int flag = this.classMapper.selectCountClassType(classTypeId);
        if(flag > 0) return false; //存在，无法删除
        SchoolClassType classType = new SchoolClassType(); //构造对象
        int result = this.classTypeMapper.deleteByPrimaryKey(classTypeId);
        if(result <= 0) throw new RuntimeException(); //更新失败
        return true;
    }

    /**
     * 新增班级层次
     */
    @Override
    @Transactional
    public boolean addClassLevel(Long schoolId, List<String> classLevel) {
        //参数检测
        if(classLevel.size() <= 0) return false;
        //返回标志
        boolean success = false;
        //查重标志
        boolean isCopy = false;
        //判断类型,查重
        for(String levelName : classLevel){
            int flag = this.classLevelMapper.selectByClassLevel(levelName);
System.out.println(levelName + "  " + flag);
            if(flag > 0){
                isCopy = true;
                break;
            }
        }
        if(!isCopy){ //批量插入
            ArrayList<SchoolClassLevel> data = new ArrayList<SchoolClassLevel>();
            for(int i = 0, len = classLevel.size(); i < len; i++){
                //构造对象
                SchoolClassLevel type = new SchoolClassLevel();
                type.setSchoolId(schoolId);
                type.setClasslevelName(classLevel.get(i));
                //添加数据
                data.add(type);
            }
System.out.println(data.size() + " 长度");
            int result = this.classLevelMapper.addBathClassLevel(data);
System.out.println(result + "结果");
            if(result > 0){
                log.info("插入成功");
                success = true;
            }
        }else{
            log.info("存在重复字段");
            success = false;
        }
        return success;
    }


    /**
     *修改班级层次
     */
    @Override
    @Transactional
    public boolean updateClassLevel(int classLevelId, String classTypeName) {
        SchoolClassLevel classLevel = new SchoolClassLevel(); //构造对象
//System.out.println("新名字, " + classTypeName + "层次ID： " + classLevelId);
        classLevel.setClassLevelId(classLevelId);
        classLevel.setClasslevelName(classTypeName);
        int result = this.classLevelMapper.updateByPrimaryKeySelective(classLevel);
        if(result <= 0) throw new RuntimeException(); //更新失败
        return true;
    }



    /**
     * 根据Id,删除班级层次,物理删除
     */
    @Override
    @Transactional
    public boolean updateClassLevelDel(Integer classLevelId) {
        int flag = this.classMapper.selectCountClassLevel(classLevelId);
System.out.println(flag + " 班级层次");
        if(flag > 0){
            log.info("该班级层次下，存在班级，无法删除");
            return false; //存在，无法删除
        }
        int result = this.classLevelMapper.deleteByPrimaryKey(classLevelId);
        if(result <= 0){
            log.error("删除失败");
            return false;
        }else{
            log.error("删除成功");
            return true;
        }
    }


    /**
     *修改班级信息
     */
    @Override
    @Transactional
    public boolean updateSchoolClassInfo(SchoolClass schoolClass, Long chineseId, Long englishId, Long mathId,
                                         String chineseTea, String englishTea, String mathTea) {
        Long classId = schoolClass.getClassId(); //获取ID
System.out.println(classId  + " 班级ID");
        //更新班级基本信息
        int classFlag = this.classMapper.updateByPrimaryKeySelective(schoolClass); //更新班级信息表，根据班级号
        boolean flag1 = false, flag2 = false, flag3 = false; //默认未更新
        if(chineseId != null){
            flag1 = updateInfo(classId, chineseId, chineseTea); //更新语文
        }
        if(englishId != null){
            flag2 = updateInfo(classId, englishId, englishTea); //更新英语
        }
        if(mathId != null){
            flag3 = updateInfo(classId, mathId, mathTea); //更新语文
        }
        if(classFlag > 0 && (flag1 || flag2 || flag3)){ //控制返回
            return true;
        }else{
            return false;
        }
    }

    /**
     * 模糊查询
     * 数据总数,待定,未用查询
     */
    @Override
    @Transactional
    public Pager findSchoolClassInfo(Long periodId, Long gradeId, String vague, Pager pager) {
System.out.println(periodId + " " + gradeId + " " + vague);
        //学段过滤
        if(periodId != null){
            //根据学段Id,查询对于班级
            List<SchoolClassInfo> underPeriodClass = this.classMapper.selectByPeriodId(periodId, pager);
            for(SchoolClassInfo info : underPeriodClass){
                System.out.println(info.toString());
            }
System.out.println(underPeriodClass.size() + " 数量");
            //查询学段ID下的总条数,测试
            pager.setPageTotal(underPeriodClass.size());
            //结果集
            pager.setContent(underPeriodClass);
            //年级查询(二层)
            if(gradeId != null){
System.out.println("进入第二层");
                //根据学段ID和年级ID,查询年级名称
                String gradeName = this.gradeMapper.selectByPIdAndGrId(periodId, gradeId);
                //年级数据
                int gradeAge = StringHandler.getGradeAge(gradeName);
System.out.println(gradeName + "  年级名称   " + "年级age: " + gradeAge);
                if(gradeAge < 0){
                    log.error("年级出错");
                    return null;
                }
                //二层数据
                List<SchoolClassInfo> undesrGradeClass = new ArrayList<SchoolClassInfo>();
                //循环处理
System.out.println("二层数据");
                for(SchoolClassInfo item : underPeriodClass){
                    //获取数据
//System.out.println(item.getClassId()  + " id信息" + "   时间 " + item.getClassYear());
                    SchoolClassInfo temp = this.classMapper.selectByPeriodAndGrade(item, gradeAge, pager);
                    if(temp != null){
                        //存在,添加进结果集合
                        undesrGradeClass.add(temp);
System.out.println(temp.toString());
                    }
                }
System.out.println(undesrGradeClass.size() + "二层数量");
                //结果集
                pager.setContent(undesrGradeClass);
                //查询总数,测试
                pager.setPageTotal(undesrGradeClass.size());
                //第三层结果集
                List<SchoolClassInfo> undesrGradeVagueClass = new ArrayList<SchoolClassInfo>();
                //结果集覆盖
                if(vague == null || "".equals(vague)) return pager;
                //模糊查询
                if(vague != null || !"".equals(vague)){
System.out.println("进入第三层 ~");
                    for(SchoolClassInfo sClass : undesrGradeClass){
                        SchoolClassInfo underClassForVague = this.classMapper.selectByVagueParam(sClass, vague,  pager);
                        if(underClassForVague != null){
                            //数据添加
System.out.println(underClassForVague.toString());
                            undesrGradeVagueClass.add(underClassForVague);
                        }
                    }
                    //查询总数,测试
                    pager.setPageTotal(undesrGradeVagueClass.size());
                    //结果集
                    pager.setContent(undesrGradeVagueClass);
                }else{
                    return pager;
                }
            }else{
                return pager;
            }
        }else{
            log.error("无记录");
            return pager;
        }
        return pager;
}




    /**
     * 私有方法
     * 切割teacher，分离教职工
     */
    private boolean updateInfo(Long classId, Long subjectId, String teacher){
        String staffNum = StringHandler.paramHandler(teacher); //获取员工编号

System.out.println(staffNum + "  : 员工编号");
        Long adminId = this.adminInfoMapper.selectByAdminInfoId(staffNum);
System.out.println(adminId + "员工ID");
        if(adminId != null){
            int result = this.clsInfoMapper.updateBySubjectIdAndAdmin(classId, subjectId, adminId);
            if(result > 0){
                return true;
            }
        }
        return false;
    }








}
