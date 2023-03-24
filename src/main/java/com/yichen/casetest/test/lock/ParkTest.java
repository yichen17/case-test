package com.yichen.casetest.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Qiuxinchao
 * @date 2023/3/24 15:22
 * @describe {@code lockSupport.park()}  {@code lockSupport.unpark()}  相关测试
 */
@Slf4j
public class ParkTest {

    public static void main(String[] args)throws Exception {
        reentryTest();
//        StringUtils.divisionLine();
    }

    /**
     * 等待期间不会释放资源
     * @throws Exception
     */
    private static void reentryTest() throws Exception{
        Object object = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t1 start");
                synchronized (object){
                    log.info("t1 start park");
                    // 不加 L 会失去进度
                    LockSupport.parkNanos(1000 * 1000 * 1000 * 10L);
                    log.info("t1 park end");
                }
                log.info("t1 end");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t2 start");
                synchronized (object){
                    log.info("t2 exec");
                }
                log.info("t2 end");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().equalsIgnoreCase("bye")){
                System.out.println("再见");
                break;
            }else {
                System.out.println("指令错误");
            }

        }
    }



}
