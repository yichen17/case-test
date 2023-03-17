package com.yichen.casetest.problem.circleReference.beanInitOrder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/17 11:23
 * @describe
 */
@Service
@Slf4j
public class InitService2 {

    static {
        log.info("InitService2");
    }

}
