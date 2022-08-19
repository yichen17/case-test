package com.yichen.casetest.test;

import org.apache.tomcat.util.buf.Utf8Decoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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

        String cache = "2022-06-01";
        System.out.println(cache.replaceAll("-",":"));

//        String caseTet = "boo:and::foo";
//        String[] items = caseTet.split("o",0);
//        for (String item : items){
//            System.out.println(item);
//        }

        System.out.println(testResult());

        List<String>  testErgodic = new ArrayList<>();
        for (String item : testErgodic){
            System.out.println(item);
        }

    }


    public static String testResult(){
        try {
            System.out.println("hello");
            return "111";
        }
        catch (Exception e ){
            System.out.println("error");
            return "222";
        }
        finally {
            System.out.println("finally");
//            return "finally";
        }
    }

}
