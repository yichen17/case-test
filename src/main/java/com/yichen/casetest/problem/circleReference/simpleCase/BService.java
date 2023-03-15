package com.yichen.casetest.problem.circleReference.simpleCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 15:35
 * @describe
 */
@Service
@Slf4j
@DependsOn(value = "AService")
public class BService {

    static {
        log.info("BService init===>");
    }

    @Resource
    private AService aService;

//    @Async("async-2")
    public void exec(){
        log.info("aService exec");
    }

}
