package com.yichen.casetest.test;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/23 18:20
 * @describe 线程池测试
 * 核心线程数 5，最大线程数 10， 缓冲队列 10
 * 当同时有8个线程时，是在创建3个线程，还是往 缓冲队列放 3个
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 2000
                ,  TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.DiscardPolicy());
        for(int i=0;i<28;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务===>");
                    try{
                        Thread.sleep(2000);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            });
            System.out.println(" ===> "+i+" 开始了,当前线程池核心线程数"+threadPoolExecutor.getPoolSize());
        }
        threadPoolExecutor.shutdown();

    }
}
