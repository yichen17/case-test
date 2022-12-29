package com.yichen.casetest.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Qiuxinchao
 * @date 2022/12/29 16:54
 * @describe 并发修改测试
 */
@Slf4j
public class ModifyTest {

    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(400, 400, 2000
            ,  TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory()
            , new ThreadPoolExecutor.DiscardPolicy());

    private static AtomicBoolean ab = new AtomicBoolean(false);

    private static CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args)throws Exception {
        currentChangeAtomicBoolean();
    }


    public static void currentChangeAtomicBoolean()throws Exception{
        batchUpdateAtomicBoolean(true);
        Thread.sleep(3000);
        batchUpdateAtomicBoolean(false);
        cdl.await();
        threadPoolExecutor.shutdown();
    }

    public static void batchUpdateAtomicBoolean(boolean state)throws Exception  {
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i=0; i<200; i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("now {}",ab.get());
                    boolean success = ab.compareAndSet(!state, state);
                    log.info("{} {}",state, ab.get());
                    if (!success){
                        log.info("==================================================================================");
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        cdl.countDown();
    }
}
