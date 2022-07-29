package com.yichen.casetest.test.unit;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 14:15
 * @describe 最初待优化代码
 */
public class OriginalUnitTest {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        Thread producerThread  = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                blockingQueue.add(i + ThreadLocalRandom.current().nextInt(100));
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    Integer result = blockingQueue.take();
                    System.out.println(result);
                }
            } catch (InterruptedException ignore) {
            }
        });
        producerThread.start();
        consumerThread.start();
    }

}
