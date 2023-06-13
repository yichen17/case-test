package com.yichen.casetest.utils;

import com.yichen.casetest.constants.CommonConstants;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/10/25 9:24
 * @describe
 */
public class TimeUtils {

    private static final String BASIC_TIME_PATTERN_12="yyyy-MM-dd hh:mm:ss";

    /**
     * 24小时制展示时间
     */
    private static final String BASIC_TIME_PATTERN_24 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转时间戳
     * @param time  yyyy-MM-dd hh:mm:ss 格式日期
     * @return 时间戳
     */
    public static String dateToTimestamp(String time){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(BASIC_TIME_PATTERN_24);
            Date parse = sdf.parse(time);
            return String.valueOf(parse.getTime());
        }
        catch (ParseException e){
            log.error("转换日期出错，日期数据 {}，转换格式 {}",time,BASIC_TIME_PATTERN_24);
            return null;
        }
    }

    /**
     * 将给定的时间戳转换成 年月日形式的格式
     * @param timestamp  待转化的时间戳
     * @return  年月日形式的日期格式
     */
    public static String timestampToDate(String timestamp){
        return timestampToDate(timestamp, CommonConstants.DATE_FORMAT);
    }

    /**
     * 将给定的时间戳转换成 年月日形式的格式
     * @param timestamp  待转化的时间戳
     * @param format  自定义的日期格式
     * @return  年月日形式的日期格式
     */
    public static String timestampToDate(String timestamp, String format){
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

    /**
     * 以秒为单位变更时间
     * @param date 原时间
     * @param seconds 变更表述  负数前移，正数后移
     * @return
     */
    public static Date changeBySeconds(Date date, int seconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        date = c.getTime();
        return date;
    }


    /**
     * 将指定格式的时间字符串转换成对应的毫秒时间戳
     * @param s 格式时间戳字符串
     * @param format  字符串时间格式要求
     * @return
     * @throws Exception
     */
    public  static  long convertDateToTimeStamp(String s, String format)throws  Exception{
        if (StringUtils.hasLength(format)){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(s).getTime();
    }


    /**
     * 获取某月的最后一天
     *
     */
    public static String getLastDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }





    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(changeBySeconds(date, -86400));


        String lastDay = getLastDayOfMonth(2021,7);
        System.out.println("获取当前月的最后一天：" + lastDay);
    }


}
