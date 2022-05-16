package com.yichen.casetest.test.jsch;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 10:35
 * @describe 并发请求测试
 */
@Slf4j
public class ConcurrentRequestTestFive {

    public static void main(String[] args) throws Exception{

//        ExecutorService executor = Executors.newFixedThreadPool(20, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                System.out.println("初始化线程 => " + count);
//                return new Thread("test thread " + count++);
//            }
//        });

        ThreadFactory threadFactory =  Executors.defaultThreadFactory();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(50);
        ThreadPoolExecutor executor =  new ThreadPoolExecutor(20, 20,1000, TimeUnit.SECONDS, arrayBlockingQueue, threadFactory, new RejectedExecutionHandlerImpl());


        System.out.println("start");
        CountDownLatch countDownLatch = new CountDownLatch(6);
        CountDownLatch requestInSameTime = new CountDownLatch(1);
        for(int i=0;i<3;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        requestInSameTime.await();
                        String result = HttpUtil.get("http://xj-onecard-wkapi-refactor-test.sc.9f.cn/test/download");
                        System.out.println("after result " + result);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        for(int i=0;i<3;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        requestInSameTime.await();
                        String result = HttpUtil.get("http://xj-onecard-wkapi-refactor-test.sc.9f.cn/test/download1");
                        System.out.println("after result " + result);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        Thread.sleep(1000);
        System.out.println("start current request");
        requestInSameTime.countDown();
        System.out.println("await");
        countDownLatch.await();
        executor.shutdown();
        System.out.println("end");
    }
}
