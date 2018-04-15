package com.cj.witbasics.service;


import com.cj.witbasics.entity.DqArea;
import com.cj.witbasics.entity.DqCity;
import com.cj.witbasics.entity.DqProvince;
import com.cj.witcommon.utils.entity.other.Region;

import java.util.List;

/**
 * Created by XD on 2017/12/1.
 * 地区信息
 */

public interface RegionService {



    //查询所有的省
    public List<Region> findAllProvinces();

    //查询还没有省级代理的省
    public List<DqProvince> findProvinces(Integer type);

    //查询还没有市级代理的市
    public List<DqCity> findCity(String provinceid, Integer type);

    //查询还没有区县级代理的区县
    public List<DqArea> findArea(String cityid, Integer type);
}
