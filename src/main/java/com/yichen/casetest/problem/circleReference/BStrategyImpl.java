package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 14:54
 * @describe
 */
@Service("BStrategy")
@Slf4j
public class BStrategyImpl extends AbstractStrategy{
    public void exec(){
        log.info("B strategy");
    }
}
