package com.yichen.casetest.test.jsch;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 10:35
 * @describe 并发请求测试
 */
@Slf4j
public class ConcurrentRequestTestFour {

    public static void main(String[] args) throws InterruptedException {
        Runnable taskTemp = new Runnable() {
            private int iCounter;

            @Override
            public void run() {
                for(int i = 0; i < 3; i++) {
                    // Initiate request
                   HttpUtil.get("http://localhost:8089/sftp/download");
                    iCounter++;
                    System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        ConcurrentRequestTestFour latchTest = new ConcurrentRequestTestFour();
        latchTest.startTaskAllInOnce(3, taskTemp);
    }

    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for(int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // Since only one switch is needed to open the door, the start door is opened immediately.
        startGate.countDown();
        // Wait till the end door opens.
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }
}
