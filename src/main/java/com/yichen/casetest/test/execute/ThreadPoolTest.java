package com.yichen.casetest.test.execute;


import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpUtil;
import com.yichen.casetest.utils.StringUtils;
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

    private static ExecutorService poolExecutor = new ThreadPoolExecutor(30, 30,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10240),
            new NamedThreadFactory("DingTalk"));

    public static void main(String[] args) throws Exception {
        try {
//            test();
//            testConcurrentIncr();
//            testConcurrentRequest();
            testMultiplyStaticRequest();
        }
        catch (Exception e){
            logger.error("执行出错 {}", e.getMessage(), e);
        }
        finally {
            poolExecutor.shutdown();
        }
    }


    private static void testMultiplyStaticRequest() throws Exception{
        int n = 200;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i=0; i<n; i++){
            poolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(StringUtils.getRandomString(5));
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

    private static void testConcurrentRequest() throws Exception{
        int count = 30;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        AtomicInteger i = new AtomicInteger(0);
        HttpGlobalConfig.setTimeout(10000);
        for (int t=0; t<count; t++){
            poolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
//                        String result = HttpUtil.get(String.format("http://localhost:8088/test/concurrentRequest?i=%s", i.getAndIncrement()));
                        String result = HttpUtil.get(String.format("http://localhost:9015/test/concurrentRequest?i=%s", i.getAndIncrement()));
                        logger.info("==> {}", result);
                    }
                    catch (Exception e){
                        logger.info("==> error =================> {}", e.getMessage());
//                        logger.error("{}", e.getMessage(), e);
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
        int count = 10000;
        AtomicInteger limit = new AtomicInteger(1);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i=0; i<count; i++){
            int finalI = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.get("http://localhost:8088/test/aviatorTest?age=10");
//                    try {
//                        Thread.sleep(1000 * limit.getAndIncrement() * 20L);
//                    }
//                    catch (Exception e){
//                        logger.error("{}", e.getMessage(), e);
//                    }
//                    System.out.println(Thread.currentThread().getName() + " => " + finalI);
                    countDownLatch.countDown();
                    logger.warn(finalI + "");
                }
            });
        }
        countDownLatch.await();
        logger.info("test end");
    }


}
