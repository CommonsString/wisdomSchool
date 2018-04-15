package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.RemoteRequest;
import com.cj.witbasics.mapper.RemoteRequestMapper;
import com.cj.witbasics.service.RemoteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RemoteRequestServiceImpl  implements RemoteRequestService {

    @Autowired
    private RemoteRequestMapper remoteRequestMapper;

    @Override
    public RemoteRequest findLogin() {
        return remoteRequestMapper.selectByPrimaryKey(1l);
    }

    @Override
    public RemoteRequest findRegister() {
        return remoteRequestMapper.selectByPrimaryKey(2l);
    }

    @Override
    public RemoteRequest findUpload() {
        return remoteRequestMapper.selectByPrimaryKey(3l);
    }

    @Override
    public RemoteRequest findSyn() {
        return remoteRequestMapper.selectByPrimaryKey(4l);
    }

    @Override
    public RemoteRequest getUserInfo() {
        return remoteRequestMapper.selectByPrimaryKey(5l);
    }
}
