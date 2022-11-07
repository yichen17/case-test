package com.yichen.casetest.test.basetype;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/4 9:09
 * @describe simpleDateFormat 线程不安全测试
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) {
//        final DateFormat df = new SimpleDateFormat("yyyyMMdd,HHmmss");
//        ExecutorService ts = Executors.newFixedThreadPool(100);
//        for (;;) {
//            ts.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        //生成随机数，格式化日期
//                        String format =  df.format(new Date(Math.abs(new Random().nextLong())));
//                        System.out.println(format);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.exit(1);
//                    }
//                }
//            });
//        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);



    }

}
