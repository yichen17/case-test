package com.yichen.casetest.test.jvm;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Qiuxinchao
 * @date 2023/4/14 9:42
 * @describe OSR(On-Stack Replacement )相关测试
 *   ==>  https://mp.weixin.qq.com/s/8x7SAA3f6vrazJp9SBsc2Q
 *    -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */
@Slf4j
public class OSRTest {

    public static void main(String[] args)throws Exception {
        asyncTest();
        StringUtils.divisionLine();
//        multiplyExec();

    }

    /**
     * 两个线程分开跑，耗时基本相同
     * @throws Exception
     */
    private static void multiplyExec() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                List<Integer> list = new ArrayList<>();
                for(int i=0; i<10000000; i++){
                    list.add(i);
                }
                log.info("执行耗时{}", System.currentTimeMillis() - start);
                countDownLatch.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start1 = System.currentTimeMillis();
                List<Integer> list1 = new ArrayList<>();
                for(int i=10000000; i<20000000; i++){
                    list1.add(i);
                }
                log.info("执行耗时{}", System.currentTimeMillis() - start1);
                countDownLatch.countDown();
            }
        });
        t1.start();
        t2.start();
        countDownLatch.await();
    }

    /**
     * 放一起跑  后者明显变短
     */
    private static void asyncTest(){
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<10000000; i++){
            list.add(i);
        }
        log.info("执行耗时{}", System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        List<Integer> list1 = new ArrayList<>();
        for(int i=10000000; i<20000000; i++){
            list1.add(i);
        }
        log.info("执行耗时{}", System.currentTimeMillis() - start1);
    }

}
