package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/22 20:54
 * @describe 回环屏蔽测试
 *      特点：可重用
 */
@Slf4j
public class CyclicBarrierTest {

    public static void main(String[] args) throws Exception{
        multiplyThreadTest();

    }

    private static void multiplyThreadTest() throws Exception{
        int num = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("A在上厕所");
                try {
                    Thread.sleep(4000);
                    cyclicBarrier.await();
                    log.info("A上完了");
                    Thread.sleep(2000);
                    log.info("A开始收拾东西");
                    cyclicBarrier.await();
                    log.info("会议结束，A退出");
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
                    cyclicBarrier.await();
                    log.info("B上完了");
                    Thread.sleep(4000);
                    log.info("B开始收拾东西");
                    cyclicBarrier.await();
                    log.info("会议结束，B退出");
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
                    cyclicBarrier.await();
                    log.info("C上完了");
                    Thread.sleep(3000);
                    log.info("C开始收拾东西");
                    cyclicBarrier.await();
                    log.info("会议结束，C退出");
                }
                catch (Exception e){
                    log.error("C出现错误 {}", e.getMessage(), e);
                }
            }
        });
        executorService.shutdown();
    }

}
