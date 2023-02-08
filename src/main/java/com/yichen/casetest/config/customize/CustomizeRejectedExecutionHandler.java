package com.yichen.casetest.config.customize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Qiuxinchao
 * @date 2023/2/7 8:52
 * @describe 定制化丢弃策略
 */
public class CustomizeRejectedExecutionHandler {


    @Slf4j
    public static class CustomizeDiscardPolicy extends ThreadPoolExecutor.DiscardPolicy {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.warn("执行丢弃拒绝策略");
            super.rejectedExecution(r, e);
        }
    }

    @Slf4j
    public static class CustomizeDiscardOldestPolicy extends ThreadPoolExecutor.DiscardOldestPolicy {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.warn("执行丢弃最老拒绝策略");
            super.rejectedExecution(r, e);
        }
    }

    @Slf4j
    public static class CustomizeAbortPolicy extends ThreadPoolExecutor.AbortPolicy {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.warn("执行中断策略");
            super.rejectedExecution(r, e);
        }
    }

    @Slf4j
    public static class CustomizeCallerRunsPolicy extends ThreadPoolExecutor.CallerRunsPolicy {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.warn("执行丢弃最老拒绝策略");
            super.rejectedExecution(r, e);
        }
    }

}
