package com.cj.witcommon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/30.
 */
public class TimeToString {
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateTimeToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转字符串
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String TimeToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate1(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate2(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToTime(String str) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {

        Date date = new Date();
        System.out.println("日期转字符串：" + TimeToString.DateToStr(date));
        System.out.println("字符串转日期：" + TimeToString.StrToDate(TimeToString.DateToStr(date)));

    }

}

