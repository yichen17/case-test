package com.yichen.casetest.test.execute;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/3 8:57
 * @describe  线上问题，定时任务时常因为线程满了而抛出 outOfMemory
 *   参考文章  https://developpaper.com/will-the-java-thread-pool-automatically-shut-down/
 *      => 问题产生原因   任务执行完毕线程池没有销毁
 *          => 解决办法   1、设置核心线程数为 0 此时等待 keepAliveTime 后核心线程数归0会启动销毁程序
 *                      2、手动在程序执行完毕后 destroy
 *                      3、  pool.allowCoreThreadTimeOut(true)  设置核心线程也允许过期销毁
 */
public class MoreExecutorsTest {
    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
//        ExecutorService pool = new ThreadPoolExecutor(5, 200,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 200,30L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 200,1000L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        pool.allowCoreThreadTimeOut(true);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(pool);
        ListenableFuture<?> future = listeningExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
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
        // 后置解决办法
//        listeningExecutorService.shutdown();
//        pool.shutdown();


    }
}
