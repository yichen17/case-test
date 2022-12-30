package com.yichen.casetest.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @date 2022/12/30 9:31
 * @describe synchronize 测试
 */
@Slf4j
public class SynchronizeTest {

    public static void main(String[] args) throws Exception{
        currentExec();
    }

    /**
     * 多线程进入  synchronize 方法
     * @throws Exception
     */
    public static void currentExec() throws Exception{
        int size = 20;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(size, size, 2000
                ,  TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.DiscardPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i=0; i<size; i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Exec.exec();
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }

    @Slf4j
    public static class Exec{
        public static final Object LOCK = new Object();

        public static void exec(){
            log.info("线程执行前 {}", Thread.currentThread().getName());
            synchronized (LOCK){
                log.info("当前线程 {}", Thread.currentThread().getName());
            }
        }

    }

}
