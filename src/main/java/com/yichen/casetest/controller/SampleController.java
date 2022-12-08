package com.yichen.casetest.controller;

import io.prometheus.client.Counter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Qiuxinchao
 * @date 2022/12/8 16:19
 * @describe doc参考   =>  https://prometheus.io/docs/prometheus/latest/getting_started/
 *   springboot 集成  prometheus
 *   =>  https://blog.csdn.net/m0_52789121/article/details/126515961    启动不起来
 *   =>  https://www.jb51.net/article/192881.htm
 */
@RestController
public class SampleController {

    private static Random random = new Random();

    private static final Counter requestTotal = Counter.build()
            .name("my_sample_counter")
            .labelNames("status")
            .help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus").register();

    @RequestMapping("/endpoint")
    public void endpoint() {
        if (random.nextInt(2) > 0) {
            requestTotal.labels("success").inc();
        } else {
            requestTotal.labels("error").inc();
        }
    }
}

