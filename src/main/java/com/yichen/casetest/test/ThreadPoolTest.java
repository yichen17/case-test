package com.yichen.casetest.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/23 18:20
 * @describe 线程池测试
 * 核心线程数 5，最大线程数 10， 缓冲队列 10
 * 当同时有8个线程时，是在创建3个线程，还是往 缓冲队列放 3个
 * 具体理解 链接 => https://www.cnblogs.com/nele/p/6502750.html
 */
public class ThreadPoolTest {
    
    private static Logger logger= LoggerFactory.getLogger(ThreadPoolTest.class);


    public static class MyRunnable implements Runnable{

        private  static  final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ssss");

        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            logger.info(name+"   执行任务===>");
            try{
                logger.info(name+"开始睡眠  "+sdf.format(new Date(Long.parseLong(System.currentTimeMillis()+""))));
                Thread.sleep(5000);
                logger.info(name+"  睡眠结束");
            }
            catch (Exception e){
                logger.info("出现异常 => "+e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue=new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 2000
                ,  TimeUnit.MILLISECONDS, queue,Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.DiscardPolicy());
        for(int i=0;i<18;i++){
            threadPoolExecutor.execute(new Thread(new MyRunnable("runnable "+ i),"thread"+i));
            logger.info(" ===> "+i+" 进入线程池,当前线程池核心线程数 => "+threadPoolExecutor.getPoolSize()
                    +"当前使用线程数 => "+threadPoolExecutor.getActiveCount()+"队列大小 => "+threadPoolExecutor.getQueue().size());
        }
        threadPoolExecutor.shutdown();


    }
}
