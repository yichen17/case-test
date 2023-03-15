package com.yichen.casetest.problem.circleReference.simpleCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 15:35
 * @describe
 */
@Service
@Slf4j
public class AService {

    static {
        log.info("AService init===>");
    }

    @Resource
    private BService bService;

//    @Async("async-2")
    public void exec(){
        log.info("aService exec");
    }

}
