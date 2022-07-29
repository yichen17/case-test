package com.yichen.casetest.test.unit;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 13:48
 * @describe
 */

public class NumberProducerConsumer extends ProducerConsumer<Integer> {

    private final Supplier<Integer> numberGenerator;

    private final Consumer<Integer> numberConsumer;

    public NumberProducerConsumer(Executor executor,
                                  Supplier<Integer> numberGenerator,
                                  Consumer<Integer> numberConsumer) {
        super(executor);
        this.numberGenerator = numberGenerator;
        this.numberConsumer = numberConsumer;
    }

    @Override
    void produce() {
        for (int i = 0; i < 10; i++) {
            produceInner(i + numberGenerator.get());
        }
    }

    @Override
    void consume() {
        while (true) {
            Integer result = consumeInner();
            numberConsumer.accept(result);
        }
    }
}
