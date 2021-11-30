package com.yichen.casetest.test;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/11/29 9:53
 * @describe 测试 thread join
 */
public class ThreadJoinTest {

    private static Logger logger= LoggerFactory.getLogger(ThreadJoinTest.class);

    public static void main(String[] args)throws Exception {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                   logger.info("t1 开始");
                   Thread.sleep(10);
                   logger.info("t1 结束");
               }
               catch (Exception e){
                   logger.error(e.getMessage());
               }
            }
        },"t1");

        Thread t2=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                try{
                    t1.join();
                    logger.info("t2 开始");
                    Thread.sleep(10);
                    logger.info("t2 结束");
                }
                catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        },"t2");

        t1.start();
        t2.start();
//          这里需要让主线程休眠才能让子线程优先执行，缺点 待发现。
        Thread.sleep(10);
        System.out.println("1111");
        Thread.sleep(10);
        System.out.println("222");
        Thread.sleep(10);
        System.out.println("333");
        Thread.sleep(10);
        System.out.println("444");
        Thread.sleep(10);
        System.out.println("555");
        Thread.sleep(10);
        System.out.println("666");
    }

}
