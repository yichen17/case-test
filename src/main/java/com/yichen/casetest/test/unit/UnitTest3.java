package com.yichen.casetest.test.unit;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 13:51
 * @describe
 */
public class UnitTest3 {

    /**
     * 模拟测试
     */
    private static void testProducerConsumerInner() {
        AtomicInteger expectI = new AtomicInteger();
        new NumberProducerConsumer(Runnable::run, () -> 0, i -> {
            assert i == expectI.getAndIncrement();
        });
        assert expectI.get() == 10;
    }

    /**
     * 真实执行
     */
    private static void execute(){
        new NumberProducerConsumer(Executors.newFixedThreadPool(2),
                () -> ThreadLocalRandom.current().nextInt(100),
                System.out::println).start();
    }


    public static void main(String[] args) {
        testProducerConsumerInner();
    }

}
