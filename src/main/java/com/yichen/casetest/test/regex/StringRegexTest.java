package com.yichen.casetest.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/15 14:48
 * @describe 个人测试
 */
public class StringRegexTest {
    public static void main(String[] args) {
        String addr = "狂杀一条街";
        String street = "";
        String regex3="(?<street>.+街)";
        Matcher m3 = Pattern.compile(regex3).matcher(addr);
        while (m3.find()){
            street = m3.group("street");
            if (street != null){
                addr=addr.replaceAll(street,"");
                break;
            }
        }
        System.out.println(street);
    }
}
