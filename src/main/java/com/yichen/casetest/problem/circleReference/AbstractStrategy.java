package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 14:53
 * @describe
 */
@Slf4j
public class AbstractStrategy {


    protected void close(){
        log.info("close");
    }

}
