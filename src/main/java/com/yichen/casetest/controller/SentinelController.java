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
 * @describe  使用场景：
 *      1、外部请求统一经过网关，网关对各个服务接口进行熔断、限流、降级等操作
 *      2、A/B测试等  新老接口设定相同的窗口大小
 *      3、幂等、分布式锁等  @Idempotent
 *      4、
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
