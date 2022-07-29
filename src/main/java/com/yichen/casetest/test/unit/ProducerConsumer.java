package com.yichen.casetest.test.unit;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 13:45
 * @describe
 */
public abstract class ProducerConsumer<T> {

    private final Executor executor;

    private final BlockingQueue<T> blockingQueue;

    public ProducerConsumer(Executor executor) {
        this.executor = executor;
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    public void start() {
        executor.execute(this::produce);
        executor.execute(this::consume);
    }

    abstract void produce();

    abstract void consume();

    protected void produceInner(T item) {
        blockingQueue.add(item);
    }

    protected T consumeInner() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
