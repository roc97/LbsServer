package com.roc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/8 22:17
 * @description 日期转换工具类
 */
public class DateUtil {

    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s+"000");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
