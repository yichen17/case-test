package com.yichen.casetest.test.jsch;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentRequestTestThree {
    public static void main(String[] args) {
        ThreadFactory threadFactory =  Executors.defaultThreadFactory();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(30);

        ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(5, 10,1000, TimeUnit.SECONDS, arrayBlockingQueue, threadFactory, new RejectedExecutionHandlerImpl());
        MonitorThread monitor = new MonitorThread(threadPoolExecutor, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new DummyRunnableTask(i));
        }
        //threadPoolExecutor.shutdown();
        //monitor.shutDown();
    }
}

class DummyRunnableTask implements Runnable {

    private int i;

    public DummyRunnableTask(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
    /*try {
        Thread.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }*/
        System.out.println("Thread Name:=" + Thread.currentThread().getName()+ " is working for id=" + i);
    }

}

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println();
    }

}

class MonitorThread implements Runnable{
    private ThreadPoolExecutor executor;
    private int seconds;
    private Boolean run = true;
    public MonitorThread(ThreadPoolExecutor executor, int seconds) {
        super();
        this.executor = executor;
        this.seconds = seconds;
    }

    public void shutDown() {
        this.run = false;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated()));
            try {
                Thread.sleep(seconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}