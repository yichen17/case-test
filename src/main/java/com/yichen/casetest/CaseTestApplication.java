package com.yichen.casetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        SpringApplication.run(CaseTestApplication.class, args);
    }

}
