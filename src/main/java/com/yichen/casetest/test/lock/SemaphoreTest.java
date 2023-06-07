package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/22 20:44
 * @describe semaphore信号量测试
 *      同CountDownLatch和CyclicBarrier的差一点为它是从0开始计数
 */
@Slf4j
public class SemaphoreTest {

    public static void main(String[] args) throws Exception {
        multipyThreadTest();
    }

    private static void multipyThreadTest() throws Exception{
        int num = 3;
        Semaphore semaphore = new Semaphore(0);
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("A在上厕所");
                try {
                    Thread.sleep(4000);
                    semaphore.release();
                    log.info("A上完了");
                }
                catch (Exception e){
                    log.error("A出现错误 {}", e.getMessage(), e);
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("B在上厕所");
                try {
                    Thread.sleep(3000);
                    semaphore.release();
                    log.info("B上完了");
                }
                catch (Exception e){
                    log.error("B出现错误 {}", e.getMessage(), e);
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("C在上厕所");
                try {
                    Thread.sleep(2000);
                    semaphore.release();
                    log.info("C上完了");
                }
                catch (Exception e){
                    log.error("C出现错误 {}", e.getMessage(), e);
                }
            }
        });
        log.info("等待所有人上完厕所开会");
        semaphore.acquire(num);
        log.info("所有人都准备好了，开始开会");
        executorService.shutdown();
    }


}
