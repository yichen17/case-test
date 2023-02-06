package com.yichen.casetest.test.async;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @date 2023/2/6 18:00
 * @describe
 */
@Slf4j
public class BatchRequest {

    public static void main(String[] args)throws Exception {
        BlockingQueue<Runnable> queue=new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 2000
                ,  TimeUnit.MILLISECONDS, queue, Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.DiscardPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i=0; i<10; i++){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    String result = HttpUtil.get("http://localhost:8088/async/overSize");
                    log.info("==> {}", result);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }

}
