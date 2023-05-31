package com.yichen.casetest.controller;

import com.yichen.casetest.test.future.CompletableFutureTest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @date 2023/5/31 9:06
 * @describe future相关测试
 */
@Slf4j
@RestController
@RequestMapping("/future")
@Api(tags = "future测试")
public class FutureController {

    @Autowired
    private CompletableFutureTest completableFutureTest;

    @PostMapping("demoExec")
    public String demoExec(@RequestParam String type){
        log.info("====================> {}", type);
        switch (type){
            case "linear":
                completableFutureTest.linear();
                break;
            case "race":
                completableFutureTest.race();
                break;
            case "waitAllExec":
                completableFutureTest.waitAllExec();
                break;
            case "anySuccess":
                completableFutureTest.anySuccess();
                break;
            case "firstExec":
                completableFutureTest.firstExec();
                break;
            default:
                break;
        }
//        log.info("====================> {} ==================> end", type);
        return "ok";
    }

}
