package com.yichen.casetest.problem.circleReference.simpleCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 15:35
 * @describe    @DependsOn 强制指定加载顺序
 */
@Service
@Slf4j
//@Lazy
//@DependsOn(value = "AService")
public class BService {

    static {
        log.info("BService init===>");
    }

    @Resource
    private AService aService;

    public String get(){
        return "BService 666";
    }

//    @Async("async-2")
    public void exec(){
        log.info("BService exec");
    }

}
