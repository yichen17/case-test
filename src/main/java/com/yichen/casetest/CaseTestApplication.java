package com.yichen.casetest;

import com.yichen.casetest.config.position.LocationPropertiesListener;
import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class},
        scanBasePackages = {"com.yichen.casetest.problem.notInject.config"})
// 开启缓存
@EnableCaching
@ConfigurationPropertiesScan("com.yichen.casetest.config")
@EnableSwagger2
@EnableFeignClients
@EnableScheduling
//@ServletComponentScan("com.yichen.casetest.filter.sort")
//@EnableHystrix
@EnableWebMvc
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableHasor()
@EnableHasorWeb()
@EnableBatchProcessing
public class CaseTestApplication {

    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args)throws Exception {

//        System.setProperty(TransportConfig.HEARTBEAT_CLIENT_IP, "127.0.0.1");

        // 手动重启容器
        // https://stackoverflow.com/questions/39386168/programmatically-restart-spring-boot-application-refresh-spring-context
        SpringApplication application = new SpringApplication(CaseTestApplication.class);
        // 添加自定义
        application.addListeners(new LocationPropertiesListener("config/keyValueData.properties"));
        CaseTestApplication.args = args;


        // 启动时长监控   浏览器就能打开
        // curl --location --request POST 'http://localhost:8088/test/yichen/actuator/startup'
        // https://www.amitph.com/spring-boot-startup-monitoring/
        application.setApplicationStartup(new BufferingApplicationStartup(10000));
        CaseTestApplication.context = application.run(args);
    }

    public static void close(){
        context.close();
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
