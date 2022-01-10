package com.yichen.casetest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/10 15:48
 * @describe 研究原理的测试controller
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/get")
    public String get(){
        return "test-get";
    }

}
