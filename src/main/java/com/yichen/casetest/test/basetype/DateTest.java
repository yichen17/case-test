package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.FastJsonUtils;
import com.yichen.casetest.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/11 9:18
 * @describe
 *      相关文件：
 *          date的缺陷：https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/
 *
 */
public class DateTest {
    public static void main(String[] args) {
        msTest();
        StringUtils.divisionLine();
        dateTimeZoneTest();
    }

    private static void dateTimeZoneTest(){
        // The default time zone makes no difference when constructing
        // a Date from a milliseconds-since-Unix-epoch value
        Date date = new Date(-14159020000L);

        // Display the instant in three different time zones
        TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
        System.out.println(date);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        System.out.println(date);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Riyadh"));
        System.out.println(date);

        // Prove that the instant hasn't changed...
        System.out.println(date.getTime());
    }

    private static void msTest(){
        Date date = new Date();
        System.out.println(date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(FastJsonUtils.toJson(date));
    }

}
