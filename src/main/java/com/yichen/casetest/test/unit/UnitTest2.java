package com.yichen.casetest.test.unit;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/29 13:46
 * @describe
 */
public class UnitTest2 {

    /**
     * 案例模拟执行
     */
    public static void execute(){
        new ProducerConsumer<Integer>(Executors.newFixedThreadPool(2)) {
            @Override
            void produce() {
                for (int i = 0; i < 10; i++) {
                    produceInner(i + ThreadLocalRandom.current().nextInt(100));
                }
            }

            @Override
            void consume() {
                while (true) {
                    Integer result = consumeInner();
                    System.out.println(result);
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        new ProducerConsumer<Integer>(Runnable::run) {
            @Override
            void produce() {
                produceInner(1);
                produceInner(2);
            }

            @Override
            void consume() {
                assert consumeInner() == 1;
                assert consumeInner() == 2;
            }
        }.start();
    }

}
