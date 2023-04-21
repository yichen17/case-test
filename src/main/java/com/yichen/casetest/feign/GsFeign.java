package com.yichen.casetest.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Qiuxinchao
 * @date 2023/4/21 8:45
 * @describe
 */
@FeignClient(name = "gs", url = "http://orderservice-test.sc.9f.cn")
public interface GsFeign {

    @SentinelResource("/testHealth")
    @GetMapping("/test/health")
    String testHealth();

}
