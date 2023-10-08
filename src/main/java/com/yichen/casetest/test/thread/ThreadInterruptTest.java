package com.yichen.casetest.test.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Qiuxinchao
 * @date 2023/3/22 9:41
 * @describe interrupt test
 */
@Slf4j
public class ThreadInterruptTest {

    private static final Object object = new Object();

    public static void main(String[] args) throws Exception{
        testInterrupt();
//        StringUtils.divisionLine();
//        testInterrupt2();
    }

    /**
     * 模拟AbstractQueuedSynchronizer#acquireQueued 返回结果为true的场景
     * https://stackoverflow.com/questions/50732104/when-will-the-method-acquirequeued-of-abstractqueuedsynchronizer-entry-its-fina
     */
    private static void testInterrupt2(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = false;
                try {
                    for(int i=0; i<1000000; i++){

                        if (i == 5000){
                            log.info("t1 5000 park ===>");
                            LockSupport.park(object);
                            log.info("{}", Thread.interrupted());
                            // 内部执行异常，且没有走到更改 success之前才会触发 finally中打印
                            int tt = 1/0;

                            success = true;
                        }

                        if (i == 70000){
                            log.info("t1 return ===>");
                            return;
                        }
                    }
                }
                finally {
                    if (!success){
                        log.info("interrupt ===>");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("t2 start ===>");
                    Thread.sleep(1000 * 8);
                    log.info("t2 unpark ===>");
                    LockSupport.unpark(t1);
                }
                catch (Exception e){
                    log.error("t2 error {}", e.getMessage());
                }
            }
        });

        t1.start();
        t2.start();
        System.out.println(11);
        System.out.println(22);


    }


    /**
     * 1、当中断被捕获后， interrupt会被重置
     * 2、同步中断时，直接通过线程获取 interrupt 则是正确的
     * 3、如果中断线程后，没有执行触发点，那么也不会被中断
     * 4、Thread.interrupted() 会重置 interrupt状态
     */
    private static void testInterrupt() throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1 start");
                    Thread.sleep(60000);
                    System.out.println("t1 end");
                }
                catch (InterruptedException e){
                    System.out.println("t1 err");
                    if (Thread.interrupted()){
                        System.out.println("t1 interrupt");
                    }
//                    System.out.println("t1 " + e.getMessage());
                }
                finally {
                    System.out.println("t1 finally");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2 start");
                    Thread.sleep(60000);
                    System.out.println("t2 end");
                }
                catch (InterruptedException e){
                    System.out.println("t2 err");
                    if (Thread.interrupted()){
                        System.out.println("t2 interrupt");
                    }
//                    System.out.println("t2 " + e.getMessage());
                }
                finally {
                    System.out.println("t2 finally");
                }
            }
        });

        Thread.currentThread().interrupt();
        System.out.println(String.format("main thread isInterrupted %s", Thread.currentThread().isInterrupted()));
        // 执行完状态会被重置
        System.out.println(String.format("main thread interrupted %s", Thread.interrupted()));
        System.out.println(String.format("main thread isInterrupted %s", Thread.currentThread().isInterrupted()));


        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(11);
        System.out.println(22);
        t1.interrupt();
//        Thread.sleep(1000);
        System.out.println("==>" + t1.isInterrupted());
        System.out.println(33);
        System.out.println(44);
        t2.interrupt();
        // 不sleep的话，主线程执行快
//        Thread.sleep(1000);
        System.out.println("==>" + t2.isInterrupted());
        System.out.println(55);
        System.out.println(66);
    }

}
