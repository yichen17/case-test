package com.yichen.casetest.test.thread;

/**
 * @author Qiuxinchao
 * @date 2023/3/22 9:41
 * @describe interrupt test
 */
public class ThreadInterruptTest {


    /**
     * 1、当中断被捕获后， interrupt会被重置
     * 2、同步中断时，直接通过线程获取 interrupt 则是正确的
     * 3、如果中断线程后，没有执行触发点，那么也不会被中断
     * 4、Thread.interrupted() 会重置 interrupt状态
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1 start");
                    Thread.sleep(60000);
                    System.out.println("t1 end");
                }
                catch (InterruptedException e){
                    if (Thread.interrupted()){
                        System.out.println("t1 interrupt");
                    }
//                    System.out.println("t1 " + e.getMessage());
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2 start");
                    Thread.sleep(60000);
                    System.out.println("t2 end");
                }
                catch (InterruptedException e){
                    if (Thread.interrupted()){
                        System.out.println("t2 interrupt");
                    }
//                    System.out.println("t2 " + e.getMessage());
                }
            }
        });

        Thread.currentThread().interrupt();
        System.out.println(String.format("main thread isInterrupted %s", Thread.currentThread().isInterrupted()));
        // 执行完状态会被重置
        System.out.println(String.format("main thread interrupted %s", Thread.interrupted()));
        System.out.println(String.format("main thread isInterrupted %s", Thread.currentThread().isInterrupted()));


        t1.start();
        t2.start();
        System.out.println(11);
        System.out.println(22);
        t1.interrupt();
        System.out.println("==>" + t1.isInterrupted());
        System.out.println(33);
        System.out.println(44);
        t2.interrupt();
        System.out.println("==>" + t2.isInterrupted());
        System.out.println(55);
        System.out.println(66);


    }

}
