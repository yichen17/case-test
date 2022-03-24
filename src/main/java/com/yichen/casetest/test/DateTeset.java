package com.yichen.casetest.test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/11 9:18
 * @describe
 */
public class DateTeset {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}
