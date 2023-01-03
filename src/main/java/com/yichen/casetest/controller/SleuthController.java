package com.yichen.casetest.controller;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Qiuxinchao
 * @date 2023/1/3 9:26
 * @describe sleuth 相关测试
 */
@RestController
@RequestMapping("/sleuth")
@Slf4j
public class SleuthController {

    @Resource
    private Tracer tracer;

    @PostMapping("/log")
    public String log(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        String name;
        while (headerNames.hasMoreElements()){
            name = headerNames.nextElement();
            log.info("header {} {}", name, request.getHeader(name));
        }
        log.info("log test");
        return "ok";
    }

}
