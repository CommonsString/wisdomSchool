package com.cj.witbasics.entity;

public class RemoteRequest {
    /**
     * 远程请求资源ID
     */
    private Long reqId;

    /**
     * 请求名
     */
    private String reqName;

    /**
     * 请求地址
     */
    private String reqUrl;

    /**
     * 请求类型
     */
    private String reqMode;

    /**
     * 请求参数列表说明
     */
    private String reqParameter;

    /**
     * 请求返回值
     */
    private String reqReturn;

    /**
     * 其他信息
     */
    private String msg;

    /**
     * 请求开关，0-不用使用远程请求，1-必须使用远程请求
     */
    private String reqState;

    /**
     * 状态，0-已删除，1-正常使用
     */
    private String state;

    /**
     * 远程请求资源ID
     * @return req_id 远程请求资源ID
     */
    public Long getReqId() {
        return reqId;
    }

    /**
     * 远程请求资源ID
     * @param reqId 远程请求资源ID
     */
    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    /**
     * 请求名
     * @return req_name 请求名
     */
    public String getReqName() {
        return reqName;
    }

    /**
     * 请求名
     * @param reqName 请求名
     */
    public void setReqName(String reqName) {
        this.reqName = reqName == null ? null : reqName.trim();
    }

    /**
     * 请求地址
     * @return req_url 请求地址
     */
    public String getReqUrl() {
        return reqUrl;
    }

    /**
     * 请求地址
     * @param reqUrl 请求地址
     */
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl == null ? null : reqUrl.trim();
    }

    /**
     * 请求类型
     * @return req_mode 请求类型
     */
    public String getReqMode() {
        return reqMode;
    }

    /**
     * 请求类型
     * @param reqMode 请求类型
     */
    public void setReqMode(String reqMode) {
        this.reqMode = reqMode == null ? null : reqMode.trim();
    }

    /**
     * 请求参数列表说明
     * @return req_parameter 请求参数列表说明
     */
    public String getReqParameter() {
        return reqParameter;
    }

    /**
     * 请求参数列表说明
     * @param reqParameter 请求参数列表说明
     */
    public void setReqParameter(String reqParameter) {
        this.reqParameter = reqParameter == null ? null : reqParameter.trim();
    }

    /**
     * 请求返回值
     * @return req_return 请求返回值
     */
    public String getReqReturn() {
        return reqReturn;
    }

    /**
     * 请求返回值
     * @param reqReturn 请求返回值
     */
    public void setReqReturn(String reqReturn) {
        this.reqReturn = reqReturn == null ? null : reqReturn.trim();
    }

    /**
     * 其他信息
     * @return msg 其他信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 其他信息
     * @param msg 其他信息
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * 请求开关，0-不用使用远程请求，1-必须使用远程请求
     * @return req_state 请求开关，0-不用使用远程请求，1-必须使用远程请求
     */
    public String getReqState() {
        return reqState;
    }

    /**
     * 请求开关，0-不用使用远程请求，1-必须使用远程请求
     * @param reqState 请求开关，0-不用使用远程请求，1-必须使用远程请求
     */
    public void setReqState(String reqState) {
        this.reqState = reqState == null ? null : reqState.trim();
    }

    /**
     * 状态，0-已删除，1-正常使用
     * @return state 状态，0-已删除，1-正常使用
     */
    public String getState() {
        return state;
    }

    /**
     * 状态，0-已删除，1-正常使用
     * @param state 状态，0-已删除，1-正常使用
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}