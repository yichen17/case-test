package com.yichen.casetest.test.basetype;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/12 15:28
 * @describe  时区测试
 */
public class TimeZoneTest {

    public static void main(String[] args)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("default ==> "+sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT ==> "+sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC ==> "+sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("CST"));
        System.out.println("CST ==> "+sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println("GMT+8 ==> "+sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai ==> "+sdf.format(date));


        Date parse = sdf.parse("2022-03-12 00:00:00");
        System.out.println(sdf.format(parse));
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(parse));

    }

}
