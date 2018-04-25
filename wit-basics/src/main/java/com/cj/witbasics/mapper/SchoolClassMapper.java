package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolClass;
import com.cj.witcommon.entity.SchoolClassInfo;
import com.cj.witcommon.entity.SchoolPeriodInfo;
import com.cj.witcommon.utils.entity.other.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchoolClassMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long classId);

    /**
     * @mbggenerated
     */
    int insert(SchoolClass record);

    /**
     * @mbggenerated
     */
    int insertSelective(SchoolClass record);

    /**
     * @mbggenerated
     */
    SchoolClass selectByPrimaryKey(Long classId);

    /**
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolClass record);

    /**
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolClass record);


    //根据班级号修改信息
    int updateByClassNumberSelective(SchoolClass schoolClass);

    //查询班级ID
    long selectByClassNumber(Integer classNumber);

    //查询信息总数
    int selectCountClassInfo(Map<String, Object> map);

    //返回结果
    List<SchoolClassInfo> selectByInfoForLsit(Map<String, Object> map);

    //根据学段ID，判断该学段下有无班级
    int selectCountPeriodId(Long periodId);

    //判断该班级类型下，是否存在班级
    int selectCountClassType(int classTypeId);

    //判断该班级层次下，是否存在班级
    int selectCountClassLevel(int classLevel);

    //根据实体List,批量插入信息
//    int insertBathInfo(SchoolClass info);

    //根据学校ID，返回所有学段和对于的年级信息
    List<SchoolPeriodInfo> findPeriodAndGradeInfo(Long schoolId);

    //根据校区名称查询校区ID
    Long selectByClassName(String classCampus); //待定

    //根据学段ID,查询班级
    List<SchoolClassInfo> selectByPeriodId(@Param("classPeriodId") Long classPeriodId,
                                       @Param("pager") Pager pager);

    //根据年级筛选
    SchoolClassInfo selectByPeriodAndGrade(@Param("sClass") SchoolClassInfo sClass,
                                       @Param("gradeAge") int gradeAge,
                                       @Param("pager") Pager pager);

    //第三层筛选
    SchoolClassInfo selectByVagueParam(@Param("sClass")SchoolClassInfo sclass,
                                             @Param("vague") String vague,
                                             @Param("pager")Pager pager);

    //考试模块,查询班级
    SchoolClassInfo findAllClassForYeah(@Param("sClass")SchoolClassInfo sClass, @Param("gradeAge") int gradeAge);

    //获取年级下的班级
    List<SchoolClassInfo> selectByPeriodIdNoPager(Long periodId);

    //根据班级查重
    int selectCountByClassNumber(int classNumber);

    //返回所有无班主任的班级
    List<Map> selectAllNoHeadmaster();

    //置空
    int updateByHeadmasterId(Long classId);


    /*********************************************************/
    /*********************************************************/

    //查询具有班主任权限的角色
    List<Map> findHasPowerForHeadmaster(@Param("vague") String vague);

    //查询具有年级主任权限的角色
    List<Map> findHasPowerForDirector(@Param("vague") String vague);


}

