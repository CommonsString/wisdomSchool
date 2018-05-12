package com.cj.witcommon.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiCode {


    // -1为通用失败（根据ApiResult.java中的构造方法注释而来）
    public static final int FAIL = -1;// "common fail");//
    public static final String FAIL_MSG =  "处理失败";
    // 1为成功
    public static final int SUCCESS = 1;// "success");//
    public static final String SUCCESS_MSG =  "成功";

    public static final int error_pic_file = 3;//"非法图片文件");//
    public static final String error_pic_file_MSG =  "非法图片文件";

    public static final int error_pic_upload = 4;//"图片上传失败");//
    public static final String error_pic_upload_MSG =  "文件上传失败";

    public static final int error_record_not_found = 5;// "没有找到对应的数据");//
    public static final String error_record_not_found_MSG =  "没有找到对应的数据";
    public static final int error_max_page_size = 6;// "请求记录数超出每次请求最大允许值");//
    public static final String error_max_page_size_MSG =  "请求记录数超出每次请求最大允许值";
    public static final int error_create_failed = 7;//"新增失败");//
    public static final String error_create_failed_MSG =  "新增失败";
    public static final int error_update_failed = 8;//"修改失败");//
    public static final String error_update_failed_MSG =  "修改失败";
    public static final int error_delete_failed = 9;//"删除失败");//
    public static final String error_delete_failed_MSG =  "删除失败";
    public static final int error_search_failed = 10;//"查询失败");//
    public static final String error_search_failed_MSG =  "查询失败";
    public static final int error_count_failed = 11;//"查询数据总数失败");//
    public static final String error_count_failed_MSG =  "查询数据总数失败";
    public static final int error_string_to_obj = 12;//"字符串转java对象失败");//
    public static final String error_string_to_obj_MSG =  "字符串转java对象失败";
    public static final int error_invalid_argument = 13;//"参数不合法");//
    public static final String error_invalid_argument_MSG =  "参数不合法";
    public static final int error_update_not_allowed = 14;//"更新失败：%s");//
    public static final String error_update_not_allowed_MSG =  "更新失败";
    public static final int error_duplicated_data = 15;//"数据已存在");//
    public static final String error_duplicated_data_MSG =  "数据已存在";
    public static final int error_unknown_database_operation = 16;//"未知数据库操作失败，请联系管理员解决");//
    public static final String error_unknown_database_operation_MSG =  "未知数据库操作失败，请联系管理员解决";
    public static final int error_column_unique = 17;//"字段s%违反唯一约束性条件");//
    public static final String error_column_unique_MSG =  "字段s%违反唯一约束性条件";
    public static final int error_file_download = 18;//"文件下载失败");//
    public static final String error_file_download_MSG =  "文件下载失败";
    public static final int error_file_upload = 19;//"文件上传失败");//
    public static final String error_file_upload_MSG =  "文件上传失败";

    //100-511为http 状态码
    public static final int http_status_bad_request = 400;// "Bad Request");//
    // --- 4xx Client Error ---
    public static final int http_status_unauthorized = 401;// "Unauthorized");//
    public static final String http_status_unauthorized_MSG = "权限不足";// "Unauthorized");//
    public static final int http_status_payment_required = 402;// "Payment Required");//
    public static final int http_status_forbidden = 403;// "Forbidden");//
    public static final int http_status_not_found = 404;// "Not Found");//
    public static final String http_status_not_found_MSG = "资源不存在";// "Not Found");//
    public static final int http_status_method_not_allowed = 405;// "Method Not Allowed");//
    public static final String http_status_method_not_allowed_MSG = "请求方式或参数错误";// "Method Not Allowed");//
    public static final int http_status_not_acceptable = 406;// "Not Acceptable");//
    public static final int http_status_proxy_authentication_required = 407;// "Proxy Authentication Required");//
    public static final int http_status_request_timeout = 408;// "Request Timeout");//
    public static final int http_status_conflict = 409;// "Conflict");//
    public static final int http_status_gone = 410;// "Gone");//
    public static final int http_status_length_required = 411;// "Length Required");//
    public static final int http_status_precondition_failed = 412;// "Precondition Failed");//
    public static final int http_status_payload_too_large = 413;// "Payload Too Large");//
    public static final int http_status_uri_too_long = 414;// "URI Too Long");//
    public static final int http_status_unsupported_media_type = 415;// "Unsupported Media Type");//
    public static final int http_status_requested_range_not_satisfiable = 416;// "Requested range not satisfiable");//
    public static final int http_status_expectation_failed = 417;// "Expectation Failed");//
    public static final int http_status_im_a_teapot = 418;// "I'm a teapot");//
    public static final int http_status_unprocessable_entity = 422;// "Unprocessable Entity");//
    public static final int http_status_locked = 423;// "Locked");//
    public static final int http_status_failed_dependency = 424;// "Failed Dependency");//
    public static final int http_status_upgrade_required = 426;// "Upgrade Required");//
    public static final int http_status_precondition_required = 428;// "Precondition Required");//
    public static final int http_status_too_many_requests = 429;// "Too Many Requests");//
    public static final int http_status_request_header_fields_too_large = 431;// "Request Header Fields Too Large");//

    // --- 5xx Server Error ---
    public static final int http_status_internal_server_error = 500;// "系统错误");//
    public static final String http_status_internal_server_error_MSG = "系统错误";
    public static final int http_status_not_implemented = 501;// "Not Implemented");//
    public static final int http_status_bad_gateway = 502;// "Bad Gateway");//
    public static final int http_status_service_unavailable = 503;// "Service Unavailable");//
    public static final int http_status_gateway_timeout = 504;// "Gateway Timeout");//
    public static final int http_status_http_version_not_supported = 505;// "HTTP Version not supported");//
    public static final int http_status_variant_also_negotiates = 506;// "Variant Also Negotiates");//
    public static final int http_status_insufficient_storage = 507;// "Insufficient Storage");//
    public static final int http_status_loop_detected = 508;// "Loop Detected");//
    public static final int http_status_bandwidth_limit_exceeded = 509;// "Bandwidth Limit Exceeded");//
    public static final String http_status_bandwidth_limit_exceeded_MSG = "带宽超过限制";
    public static final int http_status_not_extended = 510;// "Not Extended");//
    public static final String http_status_not_extended_MSG = "没有扩展";
    public static final int http_status_network_authentication_required = 511;// "Network Authentication Required");//
    public static final String http_status_network_authentication_required_MSG = "网络认证要求";

    // --- 8xx common error ---
    public static final int EXCEPTION = 800;// "exception");//
    public static final String EXCEPTION_MSG = "其他异常";
    public static final int INVALID_PARAM = 801;// "invalid.param");//
    public static final String INVALID_PARAM_MSG = "无效的参数";
    public static final int INVALID_PRIVI = 802;// "invalid.privi");//
    public static final String INVALID_PRIVI_MSG = "无效的特权";

    //1000以内是系统错误，
    public static final int no_login = 1000;
    public static final String no_login_MSG = "没有登录";
    public static final int account_login = 1001;
    public static final String account_login_MSG =  "该账号已在其他设备登录";
    public static final int user_exist = 1002;
    public static final String user_exist_MSG = "用户名已存在";
    public static final int userpwd_not_exist = 1003;
    public static final String userpwd_not_exist_MSG =  "用户名不存在或者密码错误";
    public static final int role_exist = 1004;
    public static final String role_exist_MSG =  "角色已存在";
    public static final int role_admin_exist = 1005;
    public static final String role_admin_exist_MSG =  "角色下有管理员存在，无法删除";
    public static final int pass_exist = 1006;
    public static final String pass_exist_MSG =  "原密码错误，无法修改密码";
    public static final int account_exist = 1007;
    public static final String account_exist_MSG =  "用户名或密码错误";
    public static final int config_error = 1008;
    public static final String config_error_MSG =  "参数配置表错误";
    public static final int subjects_subject_exist = 1015;
    public static final String subjects_subject_exist_MSG =  "科目下有课程未删除，无法删除";

    public static final int period_error = 1051;
    public static final String period_error_MSG = "学段不存在";
    public static final int grade_error = 1052;
    public static final String grade_error_MSG = "年级不存在";
    public static final int thetime_error = 1053;
    public static final String thetime_error_MSG = "届次不存在";
    public static final int class_error = 1054;
    public static final String class_error_MSG = "班级不存在";


    public static final int import_success = 1100;
    public static final String import_success_MSG =  "导入成功";
    public static final int import_failed = 1101;
    public static final String import_failed_MSG =  "导入失败";

    public static final int export_success = 1110;
    public static final String export_success_MSG =  "导出成功";
    public static final int export_failed = 1111;
    public static final String export_failed_MSG =  "导出失败";

}