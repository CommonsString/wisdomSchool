package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.RemoteRequest;

public interface RemoteRequestMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long reqId);

    /**
     *
     * @mbggenerated
     */
    int insert(RemoteRequest record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemoteRequest record);

    /**
     *
     * @mbggenerated
     */
    RemoteRequest selectByPrimaryKey(Long reqId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemoteRequest record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemoteRequest record);
}