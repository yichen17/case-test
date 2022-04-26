package com.yichen.casetest.test;

import org.apache.tomcat.util.buf.Utf8Decoder;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/24 12:06
 * @describe 基础测试
 */
public class BaseTest {

    public static void main(String[] args) {


        String tt = "1234567";
        String[] s1 = tt.split(" ");
        tt.replace(" ","1234");
        tt.replaceAll("\\s","%20");
        tt.indexOf("12");
        Collections.unmodifiableSet(new HashSet<>());

//        String caseTet = "boo:and::foo";
//        String[] items = caseTet.split("o",0);
//        for (String item : items){
//            System.out.println(item);
//        }

    }

}
