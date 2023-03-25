package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.FastJsonUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/11 9:18
 * @describe
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(FastJsonUtils.toJson(date));
    }
}
