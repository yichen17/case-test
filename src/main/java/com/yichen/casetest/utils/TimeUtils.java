package com.yichen.casetest.utils;

import com.yichen.casetest.constants.CommonConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/10/25 9:24
 * @describe
 */
public class TimeUtils {

    /**
     * 将给定的时间戳转换成 年月日形式的格式
     * @param timestamp  待转化的时间戳
     * @return  年月日形式的日期格式
     */
    public static String timeStampToDate(String timestamp){
        return timeStampToDate(timestamp, CommonConstants.DATE_FORMAT);
    }

    /**
     * 将给定的时间戳转换成 年月日形式的格式
     * @param timestamp  待转化的时间戳
     * @param format  自定义的日期格式
     * @return  年月日形式的日期格式
     */
    public static String timeStampToDate(String timestamp,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(timestamp)));
    }

    /**
     * 获取两个日期之间的时间戳,使用默认的日期格式 yyyy-MM-dd hh:mm:ss
     * @param from 开始时间
     * @param to 结束时间
     * @return 两个日期的时间戳
     */
    public static Long getTimestampBetweenTwoDays(String from,String to){
        return  getTimestampBetweenTwoDays(from,to,CommonConstants.DATE_FORMAT);
    }

    /**
     * 取两个日期之间的时间戳
     * @param from  开始时间
     * @param to  结束时间
     * @param format  指定日期格式
     * @return 两个日期的时间戳
     */
    public static Long getTimestampBetweenTwoDays(String from,String to,String format){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMAT);
            Date dateFrom=sdf.parse(from);
            Date dateTo=sdf.parse(to);
            return dateTo.getTime()-dateFrom.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
            return -1L;
        }
    }

}
