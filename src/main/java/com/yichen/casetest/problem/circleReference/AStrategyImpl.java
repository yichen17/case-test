package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 14:54
 * @describe
 */
@Service("AStrategy")
@Slf4j
public class AStrategyImpl extends AbstractStrategy{

    @Autowired
    private ToolService toolService;

    public void exec(){
        toolService.driverStatus();
        log.info("A strategy");
    }
}
