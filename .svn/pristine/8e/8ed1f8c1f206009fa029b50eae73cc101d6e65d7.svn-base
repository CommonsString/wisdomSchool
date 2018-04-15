package com.cj.witbasics.mapper;


import com.cj.witbasics.entity.DqArea;
import com.cj.witbasics.entity.DqCity;
import com.cj.witbasics.entity.DqProvince;
import com.cj.witcommon.utils.entity.other.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by XD on 2017/12/1..
 * 地区信息
 */
@Repository
@Mapper
public interface RegionMapper {

    public List<Region> findAllRegion();

    //查询还没有省级代理的省
    public List<DqProvince> findProvinces(Map map);

    //查询还没有市级代理的市
    public List<DqCity> findCity(Map map);

    //查询还没有区县级代理的区县
    public List<DqArea> findArea(Map map);

    //查询代理的编号集合
    public List<String> findListId(Map map);




}
