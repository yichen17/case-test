package com.yichen.casetest.test.atom;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/26 14:07
 * @describe 原子性 基础测试
 *   ==>  概率出现：1、不打印a；2、a打印的数值为0
 */
public class Normal {
    private static boolean ready;
    private static int a;

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(100);
        a = 32;
        ready = true;


    }

    private static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(a);
        }
    }
}
