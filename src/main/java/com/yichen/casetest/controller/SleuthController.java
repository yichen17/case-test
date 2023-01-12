package com.yichen.casetest.controller;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

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

    /**
     * 进入后必然会被sleuth赋值traceId 想测试的是 tomcat 线程以及定时线程 线程是否公用，
     * 如果公用的话涉及一个问题，外部请求会自动填充traceId但是会不会清除，不清除定时任务使用不就有问题了。
     */
    @GetMapping(value = "/traceIdConstruct")
    public String traceIdConstruct(){
        log.warn("======> 当前traceId {}", MDC.get("traceId"));
        MDC.put("traceId", DigestUtils.md5Hex(UUID.randomUUID().toString()).substring(8, 24));
        log.warn("======> 复制后 {}", MDC.get("traceId"));
        return "ok";
    }

    @GetMapping(value = "/traceIdConstruct1")
    public String traceIdConstruct1(){
        log.warn("当前traceId {}", MDC.get("traceId"));
        return "ok";
    }


}
