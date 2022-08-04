package com.yichen.casetest.test.execute;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/4 8:47
 * @describe 对 MoreExecutorsTest 问题的优化
 */
@Slf4j
@Component
public class MoreExecutorsOptimize implements DisposableBean {

    private static ThreadPoolExecutor threadPoolExecutor;
    private static ListeningExecutorService listeningExecutorServiceGlobal;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("global-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(5, 200,100L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        listeningExecutorServiceGlobal = MoreExecutors.listeningDecorator(threadPoolExecutor);
    }

    public void executeOld(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 200,100L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(pool);
        ListenableFuture<?> future = listeningExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    log.error("出现错误 {}", e.getMessage(), e);
                }
            }
        });
        Futures.addCallback(future, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@NullableDecl Object result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("error");
            }
        });
    }

    /**
     * 优化后可以一直使用同一个线程池，缺点是无法自动销毁
     */
    public void executeOptimize(){
        log.info("当前激活的线程总数 {} 已执行的任务总数 {}", threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getCompletedTaskCount());
        ListenableFuture<?> future = listeningExecutorServiceGlobal.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    log.error("出现错误 {}", e.getMessage(), e);
                }
            }
        });
        Futures.addCallback(future, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@NullableDecl Object result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("error");
            }
        });
    }

//    public static void main(String[] args)  throws Exception{
//        Thread.sleep(10000);
//        for (int i=0; i<100; i++){
////            executeOld();
//            executeOptimize();
//        }
//    }

    @Override
    public void destroy() throws Exception {
        log.info("OrderCheckJobHandler 线程池开始销毁");
        threadPoolExecutor.shutdown();
        listeningExecutorServiceGlobal.shutdown();
    }
}
