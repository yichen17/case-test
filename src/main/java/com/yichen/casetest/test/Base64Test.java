package com.yichen.casetest.test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/8 14:32
 * @describe
 */
public class Base64Test {

    public static void main(String[] args) {
        System.out.println(new String(Base64.getEncoder().encode("112117062452910000".getBytes(StandardCharsets.UTF_8))));
        Long a = 10L;
        Long b = 12L;
        System.out.println(a > b);
    }

}
