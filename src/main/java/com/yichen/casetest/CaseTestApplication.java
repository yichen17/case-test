package com.yichen.casetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// 开启缓存
@EnableCaching
@ConfigurationPropertiesScan("com.yichen.casetest.config")
//@ServletComponentScan("com.yichen.casetest.filter.sort")
public class CaseTestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CaseTestApplication.class);
        // 启动时长监控   浏览器就能打开
        // curl --location --request POST 'http://localhost:8088/test/yichen/actuator/startup'
        // https://www.amitph.com/spring-boot-startup-monitoring/
        application.setApplicationStartup(new BufferingApplicationStartup(10000));
        application.run(args);
    }

}
