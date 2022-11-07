package com.yichen.casetest.test.basetype;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/2 16:47
 * @describe 测试生成 uuid
 */
public class UuidTest {
    public static void main(String[] args) {

//        System.out.println(UUID.randomUUID());

        //  Arrays.asList() 不能使用修改集合相关的方法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException异常
//        String[] str = new String[] { "a", "b" };
//        List list = Arrays.asList(str);
//        list.add("ccc");

//        List<String> list = new ArrayList<String>(4);
//        list.add("guan");
//        list.add("bao");
//        String[] array = new String[list.size()];
//        array = list.toArray(array);

//        List<String> a = new ArrayList<String>();
//        a.add("1");
//        a.add("2");
//        for (String temp : a) {
//            if("1".equals(temp)){
//                a.remove(temp);
//            }
//        }


//        System.out.println(show());


//        System.out.println(tableSizeFor(5));

        HashMap<String,String> map = new HashMap<>();
        map.put("name","yichen");
        map.put("sex","boy");
        map.put("address","beijing");
        map.put("age","18");

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("name","yichen");
        concurrentHashMap.put("sex","boy");
        concurrentHashMap.put("address","beijing");
        concurrentHashMap.put("age","18");


    }

    public static String show(){
        try {
            int i = 1;
            return "try";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "catch";
        }
        finally {
            return "finally";
        }
//        return "end";
    }


    static  int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

}
