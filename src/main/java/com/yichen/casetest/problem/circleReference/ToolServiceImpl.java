package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 8:52
 * @describe
 */
@Service
@Slf4j
public class ToolServiceImpl implements ToolService{

    @Override
    public void refund(){
        log.info("退款");
    }
    @Override
    public void recoverQuota(){
        log.info("恢复额度");
    }
    @Override
    public void deductQuota(){
        log.info("扣减额度");
    }
    @Override
    @Async("async-2")
    public void driverStatus(){
        log.info("状态驱动");
    }

}
