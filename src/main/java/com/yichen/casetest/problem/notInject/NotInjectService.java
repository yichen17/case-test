package com.yichen.casetest.problem.notInject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/8/8 17:21
 * @describe
 */
@Service
@Slf4j
public class NotInjectService {

    static {
        log.info("NotInjectService init");
    }

}
