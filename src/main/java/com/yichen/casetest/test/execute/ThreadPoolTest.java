package com.yichen.casetest.test.execute;


import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpUtil;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiuXinChao
 * @date 2022/10/20 8:59
 * @desc 线程池测试
 */
public class ThreadPoolTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    private static ExecutorService poolExecutor = new ThreadPoolExecutor(100, 100,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1024),
            new NamedThreadFactory("DingTalk"));

    public static void main(String[] args) throws Exception {
        try {
//            testConcurrentIncr();
            testConcurrentRequest();
        }
        catch (Exception e){
            logger.error("执行出错 {}", e.getMessage(), e);
        }
        finally {
            poolExecutor.shutdown();
        }
    }

    private static void testConcurrentRequest() throws Exception{
        int count = 200;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        AtomicInteger i = new AtomicInteger(0);
        HttpGlobalConfig.setTimeout(2000);
        for (int t=0; t<count; t++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        String result = HttpUtil.get(String.format("http://localhost:8088/test/concurrentRequest?i=%s", i.getAndIncrement()));
                        String result = HttpUtil.get(String.format("http://localhost:9015/test/concurrentRequest?i=%s", i.getAndIncrement()));
                        logger.info("==> {}", result);
                    }
                    catch (Exception e){
                        logger.info("==> error =================>");
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
    }


    /**
     * 测试多线程下 redis  incr 是否存在并发问题
     * @throws Exception
     */
    private static void testConcurrentIncr() throws Exception{
        int count = 30;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i=0; i<count; i++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String result = HttpUtil.post("http://localhost:8088/redis/concurrentIncr", "");
                    logger.info("result {}", result);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }


    private static void test() throws Exception{
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i=0; i<10; i++){
            int finalI = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " => " + finalI);
                    countDownLatch.countDown();
//                    logger.warn(finalI + "");
                }
            });
        }
        countDownLatch.await();
    }


}
