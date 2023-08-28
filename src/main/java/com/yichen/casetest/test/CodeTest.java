package com.yichen.casetest.test;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
//        mapTest();
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(null, 1);

//        Byte b = Byte.valueOf("2010");
        bitCal();
        StringUtils.divisionLine();
        timeTest();
    }

    private static void timeTest(){
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }


    private static void bitCal(){
        final int base = 0x7fff;
        int a = 10086, b = 7421;
        int pos = getPos(a, b);

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(pos));

        System.out.println(pos);
        System.out.println(pos >> 10);
        System.out.println(pos & base);
    }

    private static int getPos(int i, int j){
        if (i>j){
            return (j << 15) | i;
        }
        return (i << 15) | j;
    }

    private static void mapTest(){
        Map<String, String> map = new HashMap<>();
        map.put("11", null);
        System.out.println(map.getOrDefault("11", "2322").toString());
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
