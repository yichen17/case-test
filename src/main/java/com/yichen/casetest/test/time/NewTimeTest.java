package com.yichen.casetest.test.time;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/1 10:08
 * @describe  jdk8新的时间类，代替 calendar
 * 参考文章  =>
 * https://javarevisited.blogspot.com/2017/04/5-reasons-why-javas-old-date-and-Calendar-API-bad.html#axzz7aftiZjUF
 * 1、date  初始化诡异，月份从0开始，年从1900开始
 * 2、calendar  Timezones 定义为字符串，而不是常量或者枚举，很多错误无法发现
 * 3、calendar 复杂且容易出错
 * 4、格式化日志困难，需要反复转换
 * 5、date 可变， 从一个对象中取date属性，改变后原对象不知道
 * 6、calendar 内存消耗  400bytes 一个日期实例
 */
@Slf4j
public class NewTimeTest {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        log.info("今天的日期: {}", today);

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        log.info("today year {}, month {}, day {}", year, month, day);
        // 没有犯 date 错误 年从1900开始，月从0开始。。
        LocalDate date = LocalDate.of(2018,2,6);
        log.info("自定义日期 {}", date);

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018,2,5);
        if(date1.equals(date2)){
            log.info("日期相等");
        }else{
            log.info("日期不等");
        }
        // 判断生日等周期时间
        LocalDate date3 = LocalDate.now();
        LocalDate date4 = LocalDate.of(2018,2,6);
        MonthDay birthday = MonthDay.of(date4.getMonth(),date4.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date3);
        if(currentMonthDay.equals(birthday)){
            log.info("是你的生日");
        }else{
            log.info("你的生日还没有到");
        }

        LocalTime time = LocalTime.now();
        log.info("获取当前的时间,不含有日期: {}", time);

        LocalTime newTime = time.plusHours(3);
        log.info("当前时间 {} 三个小时后 {}", time, newTime);

        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        log.info("今天时间 {} 一周后时间 {}", today, nextWeek);

        Clock clock = Clock.systemUTC();
        log.info("clock utc {}", clock);
        Clock defaultClock = Clock.systemDefaultZone();
        log.info("default utc {}", defaultClock);
        // 日期比较
        LocalDate tomorrow = LocalDate.of(2022,8,1);
        if(tomorrow.isAfter(today)){
            System.out.println("之后的日期:"+tomorrow);
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if(yesterday.isBefore(today)){
            System.out.println("之前的日期:"+yesterday);
        }
        // 时区处理
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localDateAndTime, america );
        log.info("current date time {} newYork date time {}", localDateAndTime, dateAndTimeInNewYork);
        // 信用卡到期日等
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
        // 闰年判定
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("2018 is not a Leap year");
        }
        // 计算两个日期间隔天数
        LocalDate java8Release = LocalDate.of(2018, 12, 14);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths() );
        // 获取当前时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());
        // 格式化日期
        String dayAfterTomorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTomorrow+"  格式化后的日期为:  "+formatted);
        // 字符串互转日期类型
        LocalDateTime date5 = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date5.format(format1);
        System.out.println("日期转换为字符串:"+str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date6 = LocalDate.parse(str,format2);
        System.out.println("日期类型:"+date6);

    }

}
