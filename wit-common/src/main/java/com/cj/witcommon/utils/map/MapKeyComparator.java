package com.cj.witcommon.utils.map;

import java.util.Comparator;

/**
 * Created by XD on 2017/7/31.
 * key 比较器类
 */
public class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}
