package com.yichen.casetest.problem.circleReference.simpleCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 15:35
 * @describe
 */
@Service
@Slf4j
@Lazy
public class AService {

    static {
        log.info("AService init===>");
    }

    @Resource
//    @Lazy
    private BService bService;

    public String a2bService(){
        return bService.get();
    }

    @Async("async-2")
    public void exec(){
        log.info("aService exec");
    }

}
