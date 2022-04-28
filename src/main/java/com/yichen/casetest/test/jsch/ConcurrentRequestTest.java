package com.yichen.casetest.test.jsch;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 10:35
 * @describe 并发请求测试
 */
@Slf4j
//@Component
public class ConcurrentRequestTest implements CommandLineRunner {

    static {
        log.info("ConcurrentRequestTest construct");
    }

    public static boolean flag = false;

    private static int count = 0;

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(20, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            log.info("初始化线程 => {}",count);
            return new Thread("test thread "+ count++);
        }
    });

    @Override
    public void run(String... args) throws Exception {
        while(true){
            if (flag){
                try {
                    for(int i=0;i<20;i++){
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                log.info("run");
                                for(int j=0;j<20;j++){
                                    log.info("before");
                                    String result = HttpUtil.get("http://localhost:8088/sftp/download");
                                    log.info("after");
                                }
                            }
                        });
                    }
                }
                catch (Exception e){
                    log.error("执行错误 {}",e.getMessage(),e);
                }
            }
            else {
                Thread.sleep(1000);
            }
        }
    }
}
