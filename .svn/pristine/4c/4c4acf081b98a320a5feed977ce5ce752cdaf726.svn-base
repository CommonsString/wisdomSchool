package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.Admin;
import com.cj.witbasics.entity.AdminRole;
import com.cj.witbasics.entity.RemoteRequest;
import com.cj.witbasics.entity.SchoolPeriod;
import com.cj.witbasics.service.CloudService;
import com.cj.witbasics.service.RemoteRequestService;
import com.cj.witcommon.entity.SynBasicInformation;
import com.cj.witcommon.utils.entity.other.ApiReturnCloud;
import com.cj.witcommon.utils.entity.other.ApiReturnUpload;
import com.cj.witcommon.utils.http.APIHttpClient;
import com.cj.witcommon.utils.map.MapUtil;
import com.cj.witcommon.utils.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.cj.witcommon.utils.http.APIHttpClient.doGet;
import static com.cj.witcommon.utils.json.JSONUtil.parseObject;
import static com.cj.witcommon.utils.map.MapUtil.map2Form;

@Service
public class CloudServiceImpl implements CloudService {

    @Value("${server_port1}")
    String server_port1;

    @Value("${server_port2}")
    String server_port2;

    @Value("${server_path1}")
    String server_path1;

    @Value("${server_path2}")
    String server_path2;

    @Value("${web.upload-path}")
    String web_upload_path;

    @Value("${school_name}")
    String name;

    @Value("${school_pass}")
    String password;

    @Value("${platform}")
    String platform;

    @Value("${school_id}")
    String schoolId;

    @Autowired
    private RemoteRequestService remoteRequestService;

    /**
     * 登录
     *
     * @return
     */
    @Override
    public boolean cloudLogin(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();

        //检查是否校验登录
        RemoteRequest remoteRequest = remoteRequestService.findLogin();
        if (remoteRequest.getReqState().equals("1")) {  //必须校验
            APIHttpClient ac = new APIHttpClient(server_path1 + server_port1 + remoteRequest.getReqUrl());

            Map<String, String> map = new HashMap();
            map.put("name", name);
            map.put("password", Md5Utils.MD5Encode(password, "UTF-8", false));
            map.put("platform", platform);

            String str = ac.post(map2Form((HashMap<String, String>) map), 0l, "");
            ApiReturnCloud apiReturnCloud = parseObject(str, ApiReturnCloud.class);
            System.out.println("返回信息====>>>" + apiReturnCloud);
            if (apiReturnCloud.getCode() == 200) {
                String schoolToken = apiReturnCloud.getData().getToken();
                Long userId = apiReturnCloud.getData().getId();

                System.out.println("获取到的schoolToken===>>" + schoolToken);
                //登录校验成功,将token保存到session
                session.setAttribute("schoolToken", schoolToken);
                session.setAttribute("userId", userId);
                return true;
            } else {
                //校验失败
                return false;
            }
        } else {
            //无需云端校验
            return true;
        }


    }

    /**
     * 云端注册(管理员、单个)
     *
     * @return
     */
    @Override
    public boolean cloudRegisterPM(HttpServletRequest request, Admin admin, AdminRole adminRole) {
        HttpSession session = request.getSession();
        //检查是否校验注册
        RemoteRequest remoteRequest = remoteRequestService.findRegister();

        if (remoteRequest.getReqState().equals("1")) {  //必须校验
            String userkey = adminRole.getType();

            String url = server_path1 + server_port1 + remoteRequest.getReqUrl();
            System.out.println("url====>>" + url);

            APIHttpClient ac = new APIHttpClient(url);

            Map<String, String> map = new HashMap();
            map.put("name", admin.getAdminName());
            map.put("password", Md5Utils.MD5Encode(admin.getAdminPass(), "UTF-8", false));
            map.put("userkey", userkey); //用户类型
            map.put("platform", platform);

            String schoolToken = (String) request.getSession().getAttribute("schoolToken");
            Long loginId = (Long) request.getSession().getAttribute("userId");

            String str = ac.post(map2Form((HashMap<String, String>) map), loginId, schoolToken);
            ApiReturnCloud apiReturnCloud = parseObject(str, ApiReturnCloud.class);
            System.out.println("返回信息====>>>" + apiReturnCloud);
            if (apiReturnCloud.getCode() == 200) {  //添加账户成功
                session.setAttribute("newUserId", apiReturnCloud.getData().getId());
                session.setAttribute("newUserUUID", apiReturnCloud.getData().getUuid());

            }

            return true;
        } else {
            //无需校验
            return true;
        }

    }


    /**
     * 云端注册(学生、单个)
     *
     * @return
     */
    @Override
    public boolean cloudRegisterSM(HttpServletRequest request, Admin admin, AdminRole adminRole, SchoolPeriod schoolPeriod) {
        HttpSession session = request.getSession();
        //检查是否校验注册
        RemoteRequest remoteRequest = remoteRequestService.findRegister();

        if (remoteRequest.getReqState().equals("1")) {  //必须校验
            String userkey = adminRole.getType();
            String stageId = "";
            if (schoolPeriod.getPeriodName().equals("小学")) {
                stageId = "1";
            } else if (schoolPeriod.getPeriodName().equals("初中")) {
                stageId = "2";
            } else if (schoolPeriod.getPeriodName().equals("高中")) {
                stageId = "3";

            }

            String url = server_path1 + server_port1 + remoteRequest.getReqUrl();
            System.out.println("url====>>" + url);

            APIHttpClient ac = new APIHttpClient(url);

            Map<String, String> map = new HashMap();
            map.put("name", admin.getAdminName());
            map.put("password", Md5Utils.MD5Encode(admin.getAdminPass(), "UTF-8", false));
            map.put("userkey", userkey); //用户类型
            map.put("stageId", stageId);  //学段、学生才有
            map.put("platform", platform);

            String schoolToken = (String) request.getSession().getAttribute("schoolToken");
            Long loginId = (Long) request.getSession().getAttribute("userId");

            String str = ac.post(map2Form((HashMap<String, String>) map), loginId, schoolToken);
            ApiReturnCloud apiReturnCloud = parseObject(str, ApiReturnCloud.class);
            System.out.println("返回信息====>>>" + apiReturnCloud);
            if (apiReturnCloud.getCode() == 200) {  //添加账户成功
                session.setAttribute("newUserId", apiReturnCloud.getData().getId());
                session.setAttribute("newUserUUID", apiReturnCloud.getData().getUuid());

            }

            return true;
        } else {
            //无需校验
            return true;
        }
    }

    /**
     * 文件上传
     *
     * @return
     */
    @Override
    public String cloudUpload(File file, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //检查是否校验注册
        RemoteRequest remoteRequest = remoteRequestService.findUpload();

        String httpUrl = server_path1 + server_port2 + remoteRequest.getReqUrl();

        if (remoteRequest.getReqState().equals("1")) {  //必须校验
            String str = APIHttpClient.upload(httpUrl, file, request);

            ApiReturnUpload apiReturnUpload = parseObject(str, ApiReturnUpload.class);
            System.out.println("返回信息====>>>" + apiReturnUpload);


            return apiReturnUpload.getData().getFileUrl();
        } else {
            return "";
        }
    }


    /**
     * 同步
     *
     * @return
     */
    @Override
    public boolean cloudSynchronization(HttpServletRequest request, SynBasicInformation synBasicInformation) {
        HttpSession session = request.getSession();
        //检查是否校验注册
        RemoteRequest remoteRequest = remoteRequestService.findSyn();

        if (remoteRequest.getReqState().equals("1")) {  //必须校验

            String url = server_path1 + server_port1 + remoteRequest.getReqUrl();
            System.out.println("url====>>" + url);

            APIHttpClient ac = new APIHttpClient(url);

            Map<String, String> map = MapUtil.bean2Map(synBasicInformation);

            String schoolToken = (String) request.getSession().getAttribute("schoolToken");
            Long loginId = (Long) request.getSession().getAttribute("userId");

            String str = ac.post(map2Form((HashMap<String, String>) map), loginId, schoolToken);
            ApiReturnCloud apiReturnCloud = parseObject(str, ApiReturnCloud.class);
            System.out.println("返回信息====>>>" + apiReturnCloud);
            if (apiReturnCloud.getCode() == 200) {  //添加账户成功


            }


            return true;
        } else {
            //无需校验
            return true;
        }
    }



    /**
     * 获取用户信息
     * @return
     */
    @Override
    public Object cloudGet (HttpServletRequest request){
        RemoteRequest remoteRequest = remoteRequestService.getUserInfo();

        String url = server_path1 + server_port1 + remoteRequest.getReqUrl();
        String adminId = (String) request.getSession().getAttribute("adminId");
        //根据用户id查询用户基础信息
        String str = doGet(url,adminId);
        ApiReturnCloud apiReturnCloud = parseObject(str, ApiReturnCloud.class);
        System.out.println("返回信息====>>>" + apiReturnCloud);
        return null;
    }

}
