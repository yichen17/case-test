package com.yichen.casetest.test.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2022/12/30 9:46
 * @describe
 */
@Slf4j
@Service
public class AsyncExecServiceImpl implements AsyncExecService{

    @Override
    @Async("async-2")
    public void printExec() {
        log.info("async exec");
    }
}
