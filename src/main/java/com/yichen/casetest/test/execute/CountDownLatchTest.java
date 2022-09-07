package com.yichen.casetest.test.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
  倒计时和线程池测试
 */
public class CountDownLatchTest {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(200, 200,1000L,
            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());



    public static void main(String[] args) {
//        test2();
        test3();
    }


    /**
     * 高并发下 arrayList不可靠  100000 会丢失 4个
     */
    private static void test3(){
        List<Integer> result = new ArrayList<>();
        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i=0; i<size; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        result.add(0);
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        try {
            countDownLatch.await();
        }
        catch (Exception e){

        }
        for (Integer item : result){
            if (Objects.isNull(item)){
                System.out.println("===> ");
            }
        }
        System.out.println(result.size());
        pool.shutdown();
    }


    /**
     * 多线程用 ArrayList 交互是否会出现错误
     */
    private static  void test2(){
        List<String> result = new ArrayList<>();
        int size = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i=0; i<size; i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
//                    result.add(String.valueOf(Math.random()));
                    result.add("0");
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        }
        catch (Exception e){

        }
        System.out.println(result.size());
        for (String item : result){
            System.out.print(item + " ");
        }
        pool.shutdown();
    }

    /**
     * 字符串切分测试没问题
     */
    private static void test1(){
        String idsStr = "11,23,45";
        String[] ids = idsStr.split(",");
        CountDownLatch countDownLatch = new CountDownLatch(ids.length);
        for (String id : ids){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(id);
                    }
                    catch (Exception e){

                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("6666");
        pool.shutdown();
    }

}
