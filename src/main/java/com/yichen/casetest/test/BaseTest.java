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
