package com.yichen.casetest.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/24 12:06
 * @describe 基础测试
 */
@Slf4j
public class BaseTest {

    public static void main(String[] args) throws Exception {
//        testResult();
//        testCountDownLatch();

        Constructor<ArthasTest> constructor = ArthasTest.class.getDeclaredConstructor(int.class);
        System.out.println(constructor.getName());

        typeTrans();

        transCompare();

        Boolean c = null;
        System.out.println(Boolean.TRUE.equals(c));

    }

    private static void transCompare(){
        int[] data = {1, 2, 3};
        String a = "" + data[0] + data[1];
        String b = Integer.toString(data[0]) + Integer.toString(data[1]);
        System.out.printf("%s %s%n", a, b);
    }

    private static void typeTrans(){
        int a = 0;
        double b = (double) a;
        System.out.println(b);
    }

    private static class ArthasTest{
        ArthasTest(int n){
            System.out.println(n);
        }
    }

    public static void testCountDownLatch() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(0);
        countDownLatch.await();
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
