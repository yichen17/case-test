package com.yichen.casetest.service.threadPool;

import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Qiuxinchao
 * @date 2023/4/24 11:17
 * @describe 线程池相关测试
 */
@Slf4j
public class ThreadPooShutdownService {

    public ExecutorService executorService = Executors.newSingleThreadExecutor(
            new NamedThreadFactory("sentinel-command-center-executor"));


    /**
     * 一个线程 先往线程池里面丢入一个 Runnable 然后shutdown线程池，这时候运行线程都不会结束
     *  如下，死循环的线程池，Worker中state一直为1 此时即使线程池shutdown，线程仍不会中断
     * @throws Exception
     */
    public void threadRunWhenShutdown() throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long i = 0;
                while(true){
                    i++;
                    i %= 1000000000L;
                    if (i == 7421){
                        log.info("到了7421！！！");
//                        try {
//                            Thread.sleep(20);
//                        } catch (InterruptedException e) {
//                            log.error("运行中断{}", e.getMessage());
//                            break;
//                        }
                    }

//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        log.error("运行中断{}", e.getMessage());
//                        break;
//                    }

                }
            }
        };

        Runnable test = new Runnable() {
            @Override
            public void run() {
                executorService.submit(runnable);
                log.info("executorService submit");
                executorService.shutdown();
            }
        };
        new Thread(test).start();
    }

    public static void main(String[] args) throws Exception {
        ThreadPooShutdownService service = new ThreadPooShutdownService();
        service.threadRunWhenShutdown();
    }


}
