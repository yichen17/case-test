package com.yichen.casetest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 11:01
 * @describe
 */
@RequestMapping("/aspect")
@RestController
@Slf4j
public class AspectController {

    @RequestMapping("/test")
    public String test(){
        log.info("请求到达controller");
        int i = 1/0;
        return "controller arrive";
    }

}
