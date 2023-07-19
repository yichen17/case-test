package com.yichen.casetest.test.pointcuts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 14:27
 * @describe
 */
@Slf4j
@Component
@CA
class B implements BI{
    @Override
    public String sayHello() {
        return "hello B";
    }

    @Override
    public void notice(String msg) {
        log.info("B notice {}", msg);
    }
}
