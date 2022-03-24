package com.yichen.casetest.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:17
 * @describe 编码测试
 */
public class CodeTest {

    public static void main(String[] args) {
        try {
//            System.out.println(URLDecoder.decode("1232狂杀一条街","utf8"));
            System.out.println(URLEncoder.encode("奕晨","utf8"));
            System.out.println(URLDecoder.decode("12323213%E5%A5%95%E6%99%A8fadsfdsfaf","utf8"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
