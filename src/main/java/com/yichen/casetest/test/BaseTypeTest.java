package com.yichen.casetest.test;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/2/7 16:11
 * @describe 基本类型测试
 */
public class BaseTypeTest {

    public static void main(String[] args) {
        // 空指针异常
//        BigDecimal data=null;
//        System.out.println(data.toString());

        Long l = 10L;
        System.out.println(l.equals(10L));

        stringBuilderTest();

    }

    private static void stringBuilderTest(){
        StringBuilder builder = new StringBuilder();
        builder.append("shanliang");
        System.out.println(builder);
//        builder.setLength(1);
        builder.setLength(30);
        System.out.println(builder);
    }

}
