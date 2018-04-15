package com.cj.witbasics.service;


import com.cj.witbasics.entity.RemoteRequest;

/**
 * 校验注册登录是否需要云端校验
 */
public interface RemoteRequestService {

    //校验登录
    public RemoteRequest findLogin();

    //校验注册
    public RemoteRequest findRegister();

    //校验图片上传
    public RemoteRequest findUpload();

    //校验基础信息同步
    public RemoteRequest findSyn();

    //获取用户基础信息
    public RemoteRequest getUserInfo();
}
