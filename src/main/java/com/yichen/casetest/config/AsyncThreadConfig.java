package com.yichen.casetest.config;

import com.yichen.casetest.config.customize.CustomizeRejectedExecutionHandler;
import com.yichen.casetest.model.AsyncThreadConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/29 10:30
 * @describe 异步线程池配置
 *  线程池自带 traceId  =>   https://www.baeldung.com/spring-cloud-sleuth-single-application
 */
@EnableAsync
@Configuration
public class AsyncThreadConfig {

    @Resource
    private AsyncThreadConfigProperties properties;

    @Bean("async-1")
    @Lazy
    public ThreadPoolTaskExecutor threadPoolAsync1(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(properties.getCorePoolSize1());
        executor.setMaxPoolSize(properties.getMaxPoolSize1());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds1());
        executor.setQueueCapacity(properties.getQueueCapacity1());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix1());
        executor.setRejectedExecutionHandler(new CustomizeRejectedExecutionHandler.CustomizeCallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(properties.getAwaitTerminationSeconds1());
        executor.initialize();

        return executor;
    }

    @Bean("async-2")
    @Lazy
    public ThreadPoolTaskExecutor threadPoolAsync2(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(properties.getCorePoolSize2());
        executor.setMaxPoolSize(properties.getMaxPoolSize2());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds2());
        executor.setQueueCapacity(properties.getQueueCapacity2());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix2());
        executor.setRejectedExecutionHandler(new CustomizeRejectedExecutionHandler.CustomizeCallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(properties.getAwaitTerminationSeconds2());

        executor.initialize();

        return executor;
    }

    @Bean("async-3")
    @Lazy
    public ThreadPoolTaskExecutor threadPoolAsync3(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
//        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(2);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix("async-3-");
        executor.setRejectedExecutionHandler(new CustomizeRejectedExecutionHandler.CustomizeDiscardOldestPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

}
