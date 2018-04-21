package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.SchoolGrade;
import com.cj.witcommon.entity.GradeInfo;
import com.cj.witcommon.entity.PeriodUnderGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolGradeMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long gradeId);

    /**
     *
     * @mbggenerated
     */
    int insert(SchoolGrade record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(SchoolGrade record);

    /**
     *
     * @mbggenerated
     */
    SchoolGrade selectByPrimaryKey(Long gradeId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolGrade record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolGrade record);

    /**
     * 根据学段Id，删除对应的年级信息
     * 批量删除
     */
    int deleteBatchByPeriodId(List<Long> periodList);

    /**
     * 根据学点Id,删除对应信息(修改状态)
     */
    int deleteByPeriodId(SchoolGrade grade);

    /**
     * 根据实体对象，更新实体类型
     */
    int updateByPeriodId(SchoolGrade grade);


    /**
     * 根据年级名称，查重
     */
    int selectByGradeName(@Param("gradeName") String gradeName,
                          @Param("schoolId") Long schoolId,
                          @Param("periodId") Long periodId);

    /**
     * 根据年级ID，修改状态，（删除操作）
     */
    int deleteByGradeId(Long gradeId);


    /**
     * 根据学校ID和学段ID，查询年级信息
     */
    List<GradeInfo> selectBySchoolIdAndPeriodId(@Param("schoolId") Long schoolId, @Param("periodId") Long periodId);


    /**
     * 根据学校ID,查询年级信息
     */
    List<PeriodUnderGrade> selectBySchoolId(Long schoolId);

    /**
     * 根据学段ID,年级id查询年级名称
     */
    String selectByPIdAndGrId(@Param("periodId") Long periodId, @Param("gradeId") Long gradeId);


    /**
     * 批量更新
     */
    int updateBatchInfo(List<SchoolGrade> list);

    //查询学段下所有的年级
    public List<SchoolGrade> findAllGradeByPeriodId(Long periodId);

}