package com.yichen.casetest.test;

import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:17
 * @describe 编码测试
 */
@Slf4j
public class CodeTest {

    public static void main(String[] args) {
        test();

    }

    private static void test(){
        System.out.println(System.currentTimeMillis() + (int)(Math.random() * 500));
        System.out.println(System.currentTimeMillis());
    }

    private static void enAndDecode(){
        try {
//            System.out.println(URLDecoder.decode("1232狂杀一条街","utf8"));
            System.out.println(URLEncoder.encode("奕晨","utf8"));
            System.out.println(URLDecoder.decode("12323213%E5%A5%95%E6%99%A8fadsfdsfaf","utf8"));
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
