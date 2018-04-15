package com.cj.witbasics.mapper;

import com.cj.witbasics.entity.DqCity;

public interface DqCityMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(DqCity record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(DqCity record);

    /**
     *
     * @mbggenerated
     */
    DqCity selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DqCity record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DqCity record);
}