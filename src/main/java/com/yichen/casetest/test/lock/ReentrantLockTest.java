package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Qiuxinchao
 * @date 2023/3/24 15:21
 * @describe reentrantLock 相关测试
 */
@Slf4j
public class ReentrantLockTest {

    public static void main(String[] args)throws Exception {
//        reentryTest();
        conditionTest();
    }

    private static void conditionTest() throws Exception {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();


        // 有则消费，无责等待      怎么判断有没有
//        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
//        queue.offer(22);
//        queue.put(11);
//        queue.poll();
//        queue.take();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
//                lock.tryLock();
                lock.lock();
                try {
                    log.info("producer wait");
                    Thread.sleep(1000 * 10);
                    condition.signalAll();
                    log.info("producer end");
                }
                catch (Exception e){
                    log.error("producer error {}", e.getMessage(), e);
                }
                finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    log.info("consumer start wait");
                    condition.await();
                    log.info("consumer data");
                }
                catch (Exception e){
                    log.error("consumer errror {}", e.getMessage(), e);
                }
                finally {
                    lock.unlock();
                }
            }
        });


        consumer.start();
        // 确保 consumer先执行
        Thread.sleep(500);
        producer.start();

//        while (true){
//            Scanner scanner =new Scanner(System.in);
//            String s = scanner.nextLine();
//            if ("quit".equals(s)){
//                break;
//            }
//        }

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
