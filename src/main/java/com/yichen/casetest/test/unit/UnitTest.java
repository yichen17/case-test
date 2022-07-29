package com.yichen.casetest.test.unit;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 13:40
 * @describe https://mp.weixin.qq.com/s/ahLjLKmxk5bPZ_Zt52Zc-g
 */
@Slf4j
public class UnitTest {

    public static  <T> void  producerConsumerInner(Executor executor,
                                           Consumer<Consumer<T>> producer,
                                           Consumer<Supplier<T>> consumer) {
        BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<>();
        executor.execute(() -> producer.accept(blockingQueue::add));
        executor.execute(() -> consumer.accept(() -> {
            try {
                return blockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }




    public static void main(String[] args) {
        producerConsumerInner(Runnable::run,
                (Consumer<Consumer<Integer>>) producer -> {
                    producer.accept(1);
                    producer.accept(2);
                },
                consumer -> {
//                    assert consumer.get() == 1;
//                    assert consumer.get() == 2;
                    log.info(String.valueOf(consumer.get()));
                    log.info(String.valueOf(consumer.get()));
                });
    }
}
