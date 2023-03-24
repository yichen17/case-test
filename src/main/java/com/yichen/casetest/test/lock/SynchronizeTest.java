package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/3/24 15:21
 * @describe synchronize 相关测试
 */
@Slf4j
public class SynchronizeTest {

    public static void main(String[] args) throws Exception {
//        reentrySleepTest();
//        StringUtils.divisionLine();
        reentryWaitTest();
    }

    /**
     * wait 会释放资源
     * @throws Exception
     */
    private static void reentryWaitTest() throws Exception{

        Object object = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t1 start");
                synchronized (object){
                    try {
                        log.info("t1 lock, start sleep");
                        wait();
                        log.info("t1 sleep end");
                    }
                    catch (Exception e){

                    }
                }
                log.info("t1 end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t2 start");
                synchronized (object){
                    log.info("t2 lock");
                }
                log.info("t2 end");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

    /**
     * 线程sleep后资源不会释放(synchronized)
     * @throws Exception
     */
    private static void reentrySleepTest() throws Exception{

        Object object = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t1 start");
                synchronized (object){
                    try {
                        log.info("t1 lock, start sleep");
                        Thread.sleep(10000);
                        log.info("t1 sleep end");
                    }
                    catch (Exception e){

                    }
                }
                log.info("t1 end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t2 start");
                synchronized (object){
                    log.info("t2 lock");
                }
                log.info("t2 end");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

}
