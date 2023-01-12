package com.yichen.casetest.test.sleuth;

import brave.internal.Platform;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/1/12 11:43
 * @describe traceId相关测试
 */
@Slf4j
public class TraceIdTest {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10,1000L,
            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(100), new ThreadPoolExecutor.AbortPolicy());



    public static void main(String[] args) throws Exception {
//        test();
        batchRequest();
    }

    private static void batchRequest() throws Exception{
        int size = 20;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i=0; i<size; i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String url = String.format("http://localhost:8088/sleuth/traceIdConstruct%s",
                                Platform.get().randomLong() % 2 == 1 ? "" : "1");
                        String result = HttpUtil.get(url, 1000);
                        log.warn("===>url {} result {}", url, result);
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        pool.shutdown();
    }


    /**
     * 线程池测试，不清空后复用 traceId还是同一个
     */
    private static void test(){
        int size = 10 * 2;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i=0; i<size; i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.warn("======> 当前traceId {}", MDC.get("traceId"));
                        MDC.put("traceId", DigestUtils.md5Hex(UUID.randomUUID().toString()).substring(8, 24));
                        log.warn("======> 复制后 {}", MDC.get("traceId"));
                    }
                    finally {
                        MDC.clear();
                        countDownLatch.countDown();
                    }
                }
            });
        }

        for (int i=0; i<size; i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.warn("当前traceId {}", MDC.get("traceId"));
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        try {
            countDownLatch.await();
        }
        catch (Exception e){

        }
        pool.shutdown();
    }

}
