package com.yichen.casetest.controller;

import com.yichen.casetest.test.execute.MoreExecutorsOptimize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/4 9:04
 * @describe 线程池测试类
 */
@Slf4j
@RestController
@RequestMapping("/threadPool")
public class ThreadPoolController {

    @Autowired
    private MoreExecutorsOptimize moreExecutorsOptimize;

    @GetMapping("/executeOld")
    public String executeOld(){
        log.info("execute old start");
        for (int i=0; i<100; i++){
            moreExecutorsOptimize.executeOld();
        }
        return "ok";
    }

    @GetMapping("/executeOptimize")
    public String executeOptimize(){
        log.info("execute optimize start");
        for (int i=0; i<100; i++){
            moreExecutorsOptimize.executeOptimize();
        }
        return "ok";
    }

}
