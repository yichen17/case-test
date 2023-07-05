package com.yichen.casetest.config.scheduler;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * scheduler-job config
 *
 * @author
 * @date 2018/08/26
 */
@Configuration
@ComponentScan(basePackages = "com.onecard.ordertask.scheduler")
public class SchedulerJobConfig {
    private Logger logger = LoggerFactory.getLogger(SchedulerJobConfig.class);

    @Value("${scheduler.job.admin.addresses}")
    private String adminAddresses;

    @Value("${scheduler.job.accessToken}")
    private String accessToken;

    @Value("${scheduler.job.executor.appname}")
    private String appName;

    @Value("${scheduler.job.executor.address}")
    private String address;

    @Value("${scheduler.job.executor.ip}")
    private String ip;

    @Value("${scheduler.job.executor.port}")
    private int port;

    @Value("${scheduler.job.executor.logpath}")
    private String logPath;

    @Value("${scheduler.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> scheduler-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

}