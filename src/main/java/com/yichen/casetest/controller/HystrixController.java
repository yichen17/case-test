package com.yichen.casetest.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/1 21:49
 * @describe hystrix 熔断、降级、限流测试
 */
@RestController
@Slf4j
@RequestMapping("/hystrix")
@Api("hystrix相关测试")
public class HystrixController {

    @HystrixCommand
    @PostMapping("/test")
    public String test(){
        return "hystrix test ok";
    }

}
