package com.yichen.casetest.test.jsch;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 10:35
 * @describe 并发请求测试
 */
@Slf4j
@Configuration
@EnableScheduling
public class ConcurrentRequestTestOther {

    private static int count = 0;

    private static int type = 0;

    @Scheduled(cron = "0 0/10 * * * ?")
//    public static void main(String[] args) throws Exception {
    public static void test() throws Exception {
//        ExecutorService executor = new ThreadPoolExecutor(10,10,0L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.DiscardOldestPolicy());
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                log.info("初始化线程 => {}",count);
//                return new Thread("test thread "+ count++);
//            }
//        });
//        CountDownLatch latch = new CountDownLatch(10);
//        for(int i=0;i<10;i++){
//            executor.execute(() -> {
//                try {
//                    execute();
//                }
//                catch (Exception e){
//                    log.error("出错 {}",e.getMessage(),e);
//                }
//                finally {
//                    latch.countDown();
//                }
//            });
//        }
//        latch.await();
//        executor.shutdown();

        if (type < 5){
            Thread.sleep((int)(Math.random()*2000 + 1));
            String result = HttpUtil.get("http://localhost:8088/sftp/download");
            System.out.println(result);
            type ++;
        }
        else {
            ExecutorService executor = new ThreadPoolExecutor(2,2,0L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.DiscardOldestPolicy());
            CountDownLatch latch = new CountDownLatch(2);
            for(int i=0;i<2;i++){
                executor.execute(() -> {
                    try {
                        execute();
                    }
                    catch (Exception e){
                        log.error("出错 {}",e.getMessage(),e);
                    }
                    finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
            executor.shutdown();
            type = type % 5;
        }
    }
    public static void execute() throws Exception{
        for(int j=0;j<2;j++){
            Thread.sleep(200);
            String result = HttpUtil.get("http://localhost:8088/sftp/download");
            System.out.println(result);
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        final ExecutorService FIXED_THREAD_POOL = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(),
//                new ThreadPoolExecutor.DiscardOldestPolicy());
//
//        CountDownLatch latch = new CountDownLatch(5);
//
//        for (int i = 0; i < 5; i++) {
//            FIXED_THREAD_POOL.execute( () -> {
//                try {
//                    myTest();
//                } catch (Exception e) {
//
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//        latch.await();
//        System.out.println("执行到这里啦。。。。。");
//        FIXED_THREAD_POOL.shutdown();
//    }
//
//    public static void myTest () {
//        System.out.println("this is my test !!!");
//    }



}
