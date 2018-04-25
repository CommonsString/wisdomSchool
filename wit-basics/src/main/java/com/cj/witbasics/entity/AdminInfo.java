package com.cj.witbasics.entity;

import com.cj.witcommon.utils.excle.IsNeeded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfo {
    /**
     * 教职工编号
     */
    @IsNeeded
    private String staffNumber;
    /**
     * 姓名
     */
    @IsNeeded
    private String fullName;
    /**
     * 英文名
     */
    @IsNeeded
    private String englishName;
    /**
     * 教职工来源
     */
    @IsNeeded
    private String staffSource;
    /**
     * 性别，0-保密，1-男，2-女，默认为0
     */
    @IsNeeded
    private String gender;
    /**
     * 出生日期
     */
    @IsNeeded
    private Date birthDate;
    /**
     * 籍贯（精确到区县）
     */
    @IsNeeded
    private String placeOfOrigin;
    /**
     * 民族（前端单选）
     */
    @IsNeeded
    private String nation;
    /**
     * 政治面貌(01 中共党员 02 中共预备党员 03共青团员 04 民革党员 05 民盟盟员 06 民建会员 07 民进会员 08 农工党党员 09 致公党党员 10 九三学社社员 11 台盟盟员 12 无党派人士 13群众)
     */
    @IsNeeded
    private String politicalOutlook;
    /**
     * 入党团时间
     */
    @IsNeeded
    private String partyTime;
    /**
     * 参加工作时间
     */
    @IsNeeded
    private String workDate;
    /**
     * 参加教育工作时间
     */
    @IsNeeded
    private String participateWorkDate;
    /**
     * 原学历(前端单选）
     */
    @IsNeeded
    private String primaryEducation;

    /**
     * 原毕业时间
     */
    @IsNeeded
    private String primaryGraduateInstitutionsDate;

    /**
     * 原毕业院校
     */
    @IsNeeded
    private String primaryGraduateInstitutions;
    /**
     * 学历(1-博士研究生,2-硕士研究生,3-本科,4-专科,5-高中,6-初中,7-小学,8-其他)(前端单选）
     */
    @IsNeeded
    private String highestEducation;

    /**
     * 最高毕业时间
     */
    @IsNeeded
    private String highestGraduateInstitutionsDate;
    /**
     * 最高毕业院校
     */
    @IsNeeded
    private String highestGraduateInstitutions;
    /**
     * 原专业
     */
    @IsNeeded
    private String primaryMajor;
    /**
     * 职务
     */
    @IsNeeded
    private String masterDuties;
    /**
     * 职称(职业资格)
     */
    @IsNeeded
    private String professionalQualification;
    /**
     * 评职时间
     */
    @IsNeeded
    private String timeOfEvaluation;
    /**
     * 低一级评职时间
     */
    @IsNeeded
    private String juniorJobDate;
    /**
     * 普通话等级(前端单选）
     */
    @IsNeeded
    private String putonghuaGrade;


    /**
     * 普通话等级证书编号
     */
    @IsNeeded
    private String putonghuaGradeNumber;
    /**
     * 普通话等级取得时间
     */
    @IsNeeded
    private String putonghuaGradeDate;
    /**
     * 聘任年限
     */
    @IsNeeded
    private String appointmentYear;
    /**
     * 评骨干时间
     */
    @IsNeeded
    private String backboneTeacherDate;
    /**
     * 连续工龄
     */
    @IsNeeded
    private String continuityAge;
    /**
     * 教师资格证书编号
     */
    @IsNeeded
    private String teacherCardCode;

    /**
     * 教师资格种类
     */
    @IsNeeded
    private String teacherKind;

    /**
     * 教师资格学科
     */
    @IsNeeded
    private String teacherKindSubject;
    /**
     * 计算机等级(前端单选）
     */
    @IsNeeded
    private String computerLevel;
    /**
     * 计算机等级证书编号
     */
    @IsNeeded
    private String computerLevelNumber;

    /**
     * 计算机等级取得时间
     */
    @IsNeeded
    private String computerLevelDate;
    /**
     * 教龄
     */
    @IsNeeded
    private String seniorityAge;
    /**
     * 评职详细
     */
    @IsNeeded
    private String detailedEvaluation;
    /**
     * 来我校时间
     */
    @IsNeeded
    private Date comeSchoolDate;
    /**
     * 家庭住址
     */
    @IsNeeded
    private String homeAddress;
    /**
     * 住宅电话
     */
    @IsNeeded
    private String residentialTelephone;
    /**
     * 手机
     */
    @IsNeeded
    private String mobilePhone;
    /**
     * 身份证号码
     */
    @IsNeeded
    private String idCardNo;
    /**
     * 骨干教师级别
     */
    @IsNeeded
    private String backboneTeacherLevel;
    /**
     * 合同起始时间
     */
    @IsNeeded
    private String startOfThe;

    /**
     * 工资岗位
     */
    @IsNeeded
    private String postWage;

    /**
     * 合同结束时间
     */
    @IsNeeded
    private String contractEndTime;
    /**
     * 曾用名
     */
    @IsNeeded
    private String nameUsedBefore;
    /**
     * 办公电话
     */
    @IsNeeded
    private String officePhone;
    /**
     * 家庭邮编
     */
    @IsNeeded
    private String familyZipCode;
    /**
     * 华侨，0-否，1-是
     */
    @IsNeeded
    private String overseasChinese;

    /**
     * 所属部门ID
     */
    private Integer theDepartmentId;
    /**
     * 所属部门
     */
    @IsNeeded
    private String theDepartment;
    /**
     * 专任教师
     */
    @IsNeeded
    private String fullTimeTeacher;

    /**
     * 班主任,0-否，1-是
     */
    @IsNeeded
    private String classHeadmaster;

    /**
     * 班主任年限
     */
    @IsNeeded
    private String classHeadmasterYear;
    /**
     * 身份
     */
    @IsNeeded
    private String identity;
    /**
     * 外语语种(前端单选）
     */
    @IsNeeded
    private String foreignLanguage;

    /**
     * 外语水平(前端单选）
     */
    @IsNeeded
    private String foreignLanguageLevel;

    /**
     * 原学制(前端单选）
     */
    @IsNeeded
    private String primarySchoolSystem;
    /**
     * 最高学制（前端单选）
     */
    @IsNeeded
    private String highestSchoolSystem;
    /**
     * 最高学位(前端单选）
     */
    @IsNeeded
    private String highestAcademicDegree;
    /**
     * 学位数量
     */
    @IsNeeded
    private String academicDegreeNumber;
    //TODO:工作岗位字段没有
    /**
     * 工作岗位
     */
    @IsNeeded
    private String post;
    /**
     * 专业技术岗位分类
     */
    @IsNeeded
    private String expertiseType;

    /**
     * 任教学科级别
     */
    @IsNeeded
    private String teachingLevel;
    /**
     * 任教学科
     */
    @IsNeeded
    private String teachingSubject;
    /**
     * 校区名字
     */
    @IsNeeded
    private String schoolCampus;
    /**
     * 专业技术资格
     */
    @IsNeeded
    private String professionalTechnology;

    /**
     * 专业技术资格取得时间
     */
    @IsNeeded
    private String professionalTechnologyDate;
    /**
     * 英语口语等级(前端单选）
     */
    @IsNeeded
    private String spokenEnglish;

    /**
     * 英语口语等级证书编号
     */
    @IsNeeded
    private String spokenEnglishNumber;
    /**
     * 英语口语等级取得时间
     */
    @IsNeeded
    private String spokenEnglishDate;
    /**
     * 学位类别(前端单选）
     */
    @IsNeeded
    private String academicDegreeType;
    /**
     * 校龄
     */
    @IsNeeded
    private String schoolAge;
    /**
     * 是否随军家属，0-否，1-是
     */
    @IsNeeded
    private String campFamily;
    /**
     * 是否教代会代表，0-否，1-是
     */
    @IsNeeded
    private String teachersCongress;
    /**
     * 是否双肩挑0-否 1-是
     */
    @IsNeeded
    private String isTwoShoulders;
    /**
     * 实职级别
     */
    @IsNeeded
    private String substantiveLevel;

    /**
     * 薪级工资
     */
    @IsNeeded
    private String salaryScale;
    /**
     * 工作岗位(副)
     */
    @IsNeeded
    private String postAttach;
    //TODO:工作岗位(副)字段没有

    /**
     * 岗位工资(副)
     */
    @IsNeeded
    private String postWageAttach;

    /**
     * 薪级工资(副)
     */
    @IsNeeded
    private String salaryScaleAttach;
    /**
     * 紧急联系人
     */
    @IsNeeded
    private String emergencyContact;
    /**
     * 备注
     */
    @IsNeeded
    private String remarks;
    /**
     * 任教学段
     */
    @IsNeeded
    private String teachingSection;

    /**
     * 人事基本信息表(管理员详情表)
     */
    private Long adminInfoId;

    /**
     * 管理员ID
     */
    private Long adminId;

    /**
     * 昵称
     */
    private String adminNick;

    /**
     * 用户UUID
     */
    private String adminUuid;

    /**
     * 用户头像地址
     */
    private String adminHead;

    /**
     * 电子邮箱
     */
    private String mailBox;

    /**
     * 最高专业
     */
    private String highestMajor;



    /**
     * 是否婚配(0-否，1-是)
     */
    private String marriage;

    /**
     * 兼任职务
     */
    private String attachDuties;

    /**
     * 教研组
     */
    private String teachingAndResearch;

    /**
     * 学校（校区）ID
     */
    private Long schoolId;

    /**
     * 所属分组
     */
    private String groupings;

    /**
     * 未计工龄学习时间
     */
    private String noTimeLearning;

    /**
     * 扣减工龄时间
     */
    private String deductionOfWork;

    /**
     * 扣减工龄原因
     */
    private String deductionOfWorkReason;

    /**
     * 是否外聘，0-否，1-是
     */
    private String beEngaged;

    /**
     * 年级组
     */
    private String gradeGroup;

    /**
     * 年级组代码
     */
    private String gradeGroupCode;

    /**
     * 聘任专业技术职务
     */
    private String employment;

    /**
     * 聘任专业技术职务时间
     */
    private String employmentDate;

    /**
     * 低一级职务
     */
    private String juniorJob;

    /**
     * 心理健康级别
     */
    private String mentalHealthLevel;

    /**
     * 获奖类别（含颁奖机构）
     */
    private String teacherWinningType;

    /**
     * 获奖日期
     */
    private Date teacherWinningDate;

    /**
     * 获奖内容
     */
    private String teacherWinningContent;

    /**
     * 学术及相关著作
     */
    private String academicWork;

    /**
     * 学术及相关著作发表日期
     */
    private Date academicWorkDate;

    /**
     * 工作经历
     */
    private String workExperience;

    /**
     * 周课时(可以与授课安排联动)
     */
    private String weekClass;

    /**
     * 年度考核
     */
    private String annualAssessment;

    /**
     * 师德考核
     */
    private String theAssessment;

    /**
     * 技能证书
     */
    private String skillCertificate;

    /**
     * 是否工会委员、纪委委员、团委委员,0-否，1-是
     */
    private String committeeMember;

    /**
     * 教师工作情况记录
     */
    private String recordOfTeacherWork;

    /**
     * 创建人ID
     */
    private Long founderId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作员ID
     */
    private Long operatorId;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 0-已删除，1-正常，默认为1
     */
    private String state;

    /**
     * 用户名
     * adminInfo表不存储
     */
    private String adminName;

    /**
     * 密码
     * adminInfo表不存储
     */
    private String adminPass;


    /**
     * 角色ID
     * adminInfo表不存储
     */
    private Long roleId;

    /**
     * 账号类型，
     * 账号分类，1-管理员，2-用户（老师、学生、家长、其他）
     */
    private String adminType;

    /**
     * 账户状态
     */
    private String adminState;



}