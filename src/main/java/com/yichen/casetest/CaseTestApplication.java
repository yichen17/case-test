package com.yichen.casetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
// 开启缓存
@EnableCaching
@ConfigurationPropertiesScan("com.yichen.casetest.config")
//@ServletComponentScan("com.yichen.casetest.filter.sort")
public class CaseTestApplication {

    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // 手动重启容器
        // https://stackoverflow.com/questions/39386168/programmatically-restart-spring-boot-application-refresh-spring-context
        SpringApplication application = new SpringApplication(CaseTestApplication.class);
        CaseTestApplication.args = args;
        CaseTestApplication.context = application.run(args);

        // 启动时长监控   浏览器就能打开
        // curl --location --request POST 'http://localhost:8088/test/yichen/actuator/startup'
        // https://www.amitph.com/spring-boot-startup-monitoring/
        application.setApplicationStartup(new BufferingApplicationStartup(10000));
        application.run(args);
    }

    /**
     * 重启 spring context
     */
    public static void restart() {
        // close previous context
        context.close();

        // and build new one
        CaseTestApplication.context = SpringApplication.run(CaseTestApplication.class, args);

    }

}
