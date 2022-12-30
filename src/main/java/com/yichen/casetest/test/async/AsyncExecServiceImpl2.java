package com.yichen.casetest.test.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2022/12/30 9:46
 * @describe  没有实现接口
 */
@Slf4j
@Service
public class AsyncExecServiceImpl2{

    @Async("async-2")
    public void printExec() {
        log.info("async exec");
    }
}
