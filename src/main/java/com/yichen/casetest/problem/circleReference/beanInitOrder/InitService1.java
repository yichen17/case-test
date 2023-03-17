package com.yichen.casetest.problem.circleReference.beanInitOrder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/17 11:22
 * @describe
 */
@Service
@Slf4j
public class InitService1 {

    static {
        log.info("InitService1");
    }

}
