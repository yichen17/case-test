package com.yichen.casetest.test.execute;


import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author qiuXinChao
 * @date 2022/10/20 8:59
 * @desc 线程池测试
 */
public class ThreadPoolTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    private static ExecutorService poolExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1024),
            new NamedThreadFactory("DingTalk"));

    public static void main(String[] args) throws Exception {
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
        poolExecutor.shutdown();
    }

}
