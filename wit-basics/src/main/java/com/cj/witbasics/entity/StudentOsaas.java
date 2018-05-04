package com.cj.witbasics.entity;

import com.cj.witcommon.utils.excle.IsNeeded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOsaas {
    /**
     * 学籍基本信息表(用户详情拓展表)
     */
    private Long osaasId;

    /**
     * 管理员表ID
     */
    private Long adminId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户UUID
     */
    private String userUuid;

    /**
     * 昵称
     */
    private String userNike;

    /**
     * 头像地址
     */
    private String userHead;

    /**
     * 校内学号
     */
    @IsNeeded
    private String schoolNumber;

    /**
     * 班内学号
     */
    @IsNeeded
    private String classNumber;

    /**
     * 会考号
     */
    @IsNeeded
    private String examinationNumber;

    /**
     * 学籍号
     */
    @IsNeeded
    private String registerNumber;

    /**
     * 姓名
     */
    @IsNeeded
    private String fullName;

    /**
     * 性别（0-保密，1-男，2-女,默认为0）
     */
    @IsNeeded
    private String gender;

    public void setGender(String gender) {
        if("男".equals(gender) || "1".equals(gender)){
            this.gender = "1";
        }else if("女".equals(gender) || "2".equals(gender)){
            this.gender = "2";
        }else {
            this.gender = "0";
        }
    }

    /**
     * 校区ID（pk-学校基本信息表）
     */
    private Long basicId;

    //校区名称
    @IsNeeded
    private String basicIdName;


    /**
     * 学段（pk-学校学段信息表）
     */
    private Long periodId;

    //学段名称
    @IsNeeded
    private String periodIdName;

    /**
     * 年级（pk-学校年级管理表），年级是一个变量
     */
    private Long gradeId;

    //年级名称
    @IsNeeded
    private String gradeIdName;
    /**
     * 班级（pk-学校班级管理表）
     */
    private Long classId;

    //班级名称
    @IsNeeded
    private String classIdName;

    /**
     * 出生日期
     */
    @IsNeeded
    private Date birthDate;

    /**
     * 有效证件类型(01-居民身份证、02-出生证、03-护照)
     */
    @IsNeeded
    private String cardType;

    /**
     * 有效证件号
     */
    @IsNeeded
    private String cardNumber;

    /**
     * 照片
     */
    private String photoUrl;

    /**
     * 教育Id号
     */
    @IsNeeded
    private String educationId;

    /**
     * 全国学籍号
     */
    @IsNeeded
    private String nationalRegisterNumber;

    /**
     * 国别
     */
    @IsNeeded
    private String differentCountries;

    /**
     * 港澳台侨
     */
    @IsNeeded
    private String hongKong;

    /**
     * 户口所在地
     */
    @IsNeeded
    private String registeredResidence;

    /**
     * 户口所在地-详细地址
     */
    @IsNeeded
    private String registeredResidenceDetailed;

    /**
     * 政治面貌(01 中共党员 02 中共预备党员 03共青团员 04 民革党员 05 民盟盟员 06 民建会员 07 民进会员 08 农工党党员 09 致公党党员 10 九三学社社员 11 台盟盟员 12 无党派人士 13群众)
     */
    @IsNeeded
    private String politicalOutlook;

    /**
     * 民族(前端单选）
     */
    @IsNeeded
    private String nation;

    /**
     * 学生类别（前端单选）
     */
    @IsNeeded
    private String studentType;

    /**
     * 就读方式（前端单选）
     */
    @IsNeeded
    private String wayOfStudying;

    /**
     * 是否按本市户口学生对待(0-否，1-是)
     */
    @IsNeeded
    private String treatType;

    /**
     * 现住址
     */
    @IsNeeded
    private String presentAddress;

    /**
     * 家庭住址
     */
    @IsNeeded
    private String homeAddress;

    /**
     * 家庭住址(详细)
     */
    @IsNeeded
    private String homeAddressDetailed;

    /**
     * 通讯地址
     */
    @IsNeeded
    private String postalAddress;

    /**
     * 出生地
     */
    @IsNeeded
    private String nativeHeath;

    /**
     * 籍贯
     */
    @IsNeeded
    private String placeOfOrigin;

    /**
     * 健康状况（前端单选）
     */
    @IsNeeded
    private String healthCondition;

    /**
     * 户口性质
     */
    @IsNeeded
    private String householdRegistration;

    /**
     * 手机号
     */
    @IsNeeded
    private String mobilePhone;

    /**
     * 邮政编码
     */
    @IsNeeded
    private String zipCode;

    /**
     * 过敏史
     */
    @IsNeeded
    private String anaphylaxis;

    /**
     * 既往病史
     */
    @IsNeeded
    private String pastMedicalHistory;

    /**
     * 独生子女(0-否，1-是)
     */
    @IsNeeded
    private String oneChild;

    /**
     * 是否是军区子弟(0-否，1-是)
     */
    @IsNeeded
    private String militaryRegion;

    /**
     * 受过学前教育(0-否，1-是)
     */
    @IsNeeded
    private String preschoolEducation;

    /**
     * 留守儿童(0-否，1-是)
     */
    @IsNeeded
    private String leftBehindChildren;

    /**
     * 进城务工子女(0-否，1-是)
     */
    @IsNeeded
    private String workForChildren;

    /**
     * 姓名拼音
     */
    @IsNeeded
    private String namePinyin;

    /**
     * 英文名
     */
    @IsNeeded
    private String englishName;

    /**
     * 是否本市学籍(0-否，1-是)
     */
    @IsNeeded
    private String thisCityOsaas;

    /**
     * 招生类别（前端单选）
     */
    @IsNeeded
    private String enrolmentCategory;

    /**
     * 学籍辅号
     */
    @IsNeeded
    private String registerAssistNumber;

    /**
     * 随班就读
     */
    @IsNeeded
    private String learningInClass;

    /**
     * 学生状态
     */
    @IsNeeded
    private String studentState;

    /**
     * 是否残疾 0-否 1-是
     */
    @IsNeeded
    private String isDisability;

    /**
     * 居住地0—住校 1-走读 2-借宿 3-其他
     */
    @IsNeeded
    private String domicile;

    /**
     * 是否孤儿 0-否 1-是
     */
    @IsNeeded
    private String orphan;

    /**
     * 是否烈士优抚子女0-否 1-是
     */
    @IsNeeded
    private String martyrChild;

    /**
     * 是否需要资助0-否 1-是
     * 是否需要申请资助
     */
    @IsNeeded
    private String needSubsidy;

    /**
     * 是否享受一补0-否 1-是
     */
    @IsNeeded
    private String isSubsidy;

    /**
     * 入学方式（前端单选）
     */
    @IsNeeded
    private String enrolmentMode;

    /**
     * 学生来源
     */
    @IsNeeded
    private String studentSource;

    /**
     * 入学日期
     */
    @IsNeeded
    private Date dateOfAdmission;

    /**
     * 学生来处
     */
    @IsNeeded
    private String studentsComeIn;

    /**
     * 原学校名称
     */
    @IsNeeded
    private String originalSchool;

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
     * 邮件地址
     */
    private String zipAddress;



    /**
     * 入学年度
     */
    private String classYear;

    /**
     * 所属届次
     */
    private String belongToYear;

    /**
     * 在籍状态0-不在籍，1-在籍
     */
    private String registerState;

    /**
     * 在校状态0-离校，1-在校
     */
    private String schoolState;

    /**
     * 毕业去向
     */
    private String graduationGoto;




    /**
     * 备注
     */
    private String remarks;

}