package com.yichen.casetest.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @date 2023/4/20 9:40
 * @describe
 */
@Slf4j
@RequestMapping("/sentinel")
@RestController
public class SentinelController {

    @GetMapping("/get")
    @SentinelResource(value = "get interface", entryType = EntryType.IN)
    public String get(){
        log.info("sentinel get");
        return "ok";
    }


}
