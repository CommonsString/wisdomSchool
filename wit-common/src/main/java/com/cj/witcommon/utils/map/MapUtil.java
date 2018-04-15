package com.cj.witcommon.utils.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by XD on 2017/7/31.
 */
public class MapUtil {


    /**
     * 实体类转map
     */
    public static <K, V> Map<K, V> bean2Map(Object javaBean) {
        Map<K, V> ret = new TreeMap<K, V>();
        try {
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            for (Method method : methods) {

                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    if(value !=null && !"sign".equals(field)){
                        ret.put((K) field, (V) (null == value ? "" : value));
                    }

                }
            }
        } catch (Exception e) {
        }
        return ret;
    }




    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }


    /**
     * 使用 Map按value进行排序
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * hashMap 转化成表单字符串
     * @param map
     * @return
     */
    public static String map2Form(HashMap<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if(map == null) {
            return stringBuilder.toString();
        } else {
            for (Map.Entry<String, String> entry:
                    map.entrySet() ) {
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
    }

    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     * @return
     * @throws //JsonGenerationException
     * @throws //JsonMappingException
     * @throws //IOException
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }
}
