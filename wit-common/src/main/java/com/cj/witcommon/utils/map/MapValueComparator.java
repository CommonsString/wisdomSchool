package com.cj.witcommon.utils.map;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by XD on 2017/7/31.
 * value 比较器类
 */
public class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {

        return me1.getValue().compareTo(me2.getValue());
    }
}
