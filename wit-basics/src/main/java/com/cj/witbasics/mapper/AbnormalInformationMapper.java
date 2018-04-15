package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.AbnormalInformation;

public interface AbnormalInformationMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long informationId);

    /**
     *
     * @mbggenerated
     */
    int insert(AbnormalInformation record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(AbnormalInformation record);

    /**
     *
     * @mbggenerated
     */
    AbnormalInformation selectByPrimaryKey(Long informationId);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AbnormalInformation record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AbnormalInformation record);
}