package com.yichen.casetest.test.atom;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/26 14:10
 * @describe volatile 测试原子性
 */
public class AtomTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomTest test = new AtomTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
            }).start();
        }
        //保证前面的线程都执行完
        Thread.sleep(3000);
        System.out.println(test.inc);
    }
}
