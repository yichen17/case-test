package com.yichen.casetest.test.thread;

/**
 * @author Qiuxinchao
 * @date 2023/3/22 9:35
 * @describe join 测试
 */
public class ThreadJoinTest {

    /**
     * 初步理解
     * join 是让该线程先执行，当前线程阻塞
     */
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("thread");
                }
                catch (Exception e){

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("t2");
                }
                catch (Exception e){

                }
            }
        });
        thread.start();
        t2.start();
        System.out.println(11);
        thread.join();
        System.out.println(22);
        System.out.println(33);
        t2.join();
        System.out.println(44);
    }

}
