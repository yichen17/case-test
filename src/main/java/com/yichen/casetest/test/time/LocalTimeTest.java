package com.yichen.casetest.test.time;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/6 10:00
 * @describe joda-time 测试
 */
@Slf4j
public class LocalTimeTest {

    public static void main(String[] args)throws Exception {
        test();
    }

    private static void test() throws Exception{
        Date now = new Date();
        System.out.println(LocalDate.fromDateFields(now).compareTo(LocalDate.fromDateFields(now)));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2023-09-10 13:00:00");
        Date date2 = sdf.parse("2023-09-11 13:00:00");
        System.out.println(LocalDate.fromDateFields(date1).compareTo(LocalDate.fromDateFields(date2)));
    }


}
