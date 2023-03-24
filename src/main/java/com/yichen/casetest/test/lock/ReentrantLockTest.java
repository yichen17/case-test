package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Qiuxinchao
 * @date 2023/3/24 15:21
 * @describe reentrantLock 相关测试
 */
@Slf4j
public class ReentrantLockTest {

    public static void main(String[] args)throws Exception {
        reentryTest();
    }

    /**
     * 不会释放资源
     * @throws Exception
     */
    private static void reentryTest()throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t1 start");
                log.info("t1 lock start");
                lock.lock();
                try {
                    Thread.sleep(5000);
                    log.info("t1 lock end");
                }
                catch (Exception e){
                    log.error("");
                }
                finally {
                    lock.unlock();
                    log.info("t1 end");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t2 start");
                log.info("t2 lock start");
                lock.lock();
                try {
                    log.info("t2 lock end");
                }
                catch (Exception e){
                    log.error("");
                }
                finally {
                    lock.unlock();
                    log.info("t2 end");
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

    }

}
