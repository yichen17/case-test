package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Qiuxinchao
 * @date 2023/6/21 13:47
 * @describe
 */
@Slf4j
class ThreadPoolExec {

    public static Integer arr[];

    private static final int THRESHOLD = 20;
    private  int left;
    private  int right;
    public static AtomicInteger result = new AtomicInteger(0);
    public static AtomicInteger times = new AtomicInteger(0);

    public static  ExecutorService executorService = Executors.newFixedThreadPool(7);


    public ThreadPoolExec(int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 存在问题：
     *      1、子任务拆分等待会导致线程池被占满
     *      2、发起任务拆分获取最终结果，没有触发点，但是线程池可以重用
     * 计算并等待处理完
     */
    public int cal() throws Exception{
        if (left > right){
            return 0;
        }
        left = Math.max(left, 0);
        right = Math.min(right, arr.length-1);
        if (right - left + 1 > THRESHOLD) {
            return subTaskExec();
        } else {
            return processing();
        }
    }

    public void cal2() throws Exception{
//        log.info("cal2 => {}", times.incrementAndGet());
        if (left > right){
            return ;
        }
        left = Math.max(left, 0);
        right = Math.min(right, arr.length-1);
        if (right - left + 1 > THRESHOLD) {
            subTaskExec2();
        } else {
            processing2();
        }
//        log.info("processing2 {}", times.decrementAndGet());
    }

    private void subTaskExec2() throws Exception{
//        Future<Integer> leftVal = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return new ThreadPoolExec(left, (left + right) >> 1).cal();
//            }
//        });
//        Future<?> rightVal = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return new ThreadPoolExec((left + right) >> 1 + 1, right).cal();
//            }
//        });



        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    new ThreadPoolExec(((left + right) >> 1) + 1, right).cal2();
                } catch (Exception e) {
                    log.error("执行异常 {}", e.getMessage(), e);
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    new ThreadPoolExec(left, (left + right) >> 1).cal2();
                } catch (Exception e) {
                    log.error("执行异常 {}", e.getMessage(), e);
                }
            }
        });


    }

    private int subTaskExec() throws Exception{
        CompletableFuture<Integer>[] items = new CompletableFuture[2];
        items[0] = CompletableFuture.supplyAsync(() -> {
            try {
                return new ThreadPoolExec(left, (left + right) >> 1).cal();
            } catch (Exception e) {
                log.error("执行异常 {}", e.getMessage(), e);
                return 0;
            }
        }, executorService);
        items[1] = CompletableFuture.supplyAsync(() -> {
            try {
                return new ThreadPoolExec(((left + right) >> 1) + 1, right).cal();
            } catch (Exception e) {
                log.error("执行异常 {}", e.getMessage(), e);
                return 0;
            }
        }, executorService);
        CompletableFuture<Integer> rr = CompletableFuture.allOf(items).thenApplyAsync(v -> {
            int result = 0;
            for (CompletableFuture<Integer> item : items) {
                try {
                    result += item.get();
                } catch (Exception e) {
                    log.error("执行异常 {}", e.getMessage(), e);
                }
            }
            return result;
        });
        return rr.get();
    }

    private void processing2(){
        for (int i=left; i<=right; i++){
            result.getAndAdd(arr[i]);
        }
    }

    private int processing(){
        if (left > right){
            return 0;
        }
        left = Math.max(left, 0);
        right = Math.min(right, arr.length-1);
        int result = 0;
        for (int i=left; i<=right; i++){
//            if (arr[i] % 15 > 7){
                result += arr[i];
//            }
        }
        return result;
    }


}
