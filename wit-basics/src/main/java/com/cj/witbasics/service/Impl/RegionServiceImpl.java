package com.cj.witbasics.service.Impl;

import com.cj.witbasics.entity.DqArea;
import com.cj.witbasics.entity.DqCity;
import com.cj.witbasics.entity.DqProvince;
import com.cj.witbasics.mapper.RegionMapper;
import com.cj.witbasics.service.RegionService;
import com.cj.witcommon.utils.entity.other.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XD on 2017/12/1.
 * 地区信息
 */

@Service
public class RegionServiceImpl implements RegionService {


    @Autowired
    private RegionMapper regionMapper;


    //查询所有的省、市、区集合
    @Override
    public List<Region> findAllProvinces() {

        return regionMapper.findAllRegion();
    }

    //查询还没有省级代理的省
    @Override
    public List<DqProvince> findProvinces(Integer type) {
        //查询有省级代理的省份ID集合
        List<String> list = findListId(type,"");
        Map map = new HashMap();
        map.put("list",list);
        map.put("type",type);
        return regionMapper.findProvinces(map);
    }

    //查询还没有市级代理的市
    @Override
    public List<DqCity> findCity(String provinceid, Integer type) {
        //查询有市级代理的市ID集合
        List list = findListId(type,provinceid);
        Map map = new HashMap();
        map.put("provinceId",provinceid);
        map.put("list",list);
        map.put("type",type);

        return regionMapper.findCity(map);
    }

    //查询还没有区县级代理的区县
    @Override
    public List<DqArea> findArea(String cityid, Integer type) {
        //查询有区县级代理的区县ID集合
        List list = findListId(type,cityid);
        Map map = new HashMap();
        map.put("cityId",cityid);
        map.put("list",list);
        map.put("type",type);
        return regionMapper.findArea(map);
    }



    //查询代理的编号集合
    public List findListId(Integer type,String str){
        Map map = new HashMap();
        map.put("type",type);
        map.put("provinceId",str);
        map.put("cityId",str);

        List<String> list = regionMapper.findListId(map);
        list.removeAll(Collections.singleton(null)); //移除所有的null元素
        return list;
    }


}
