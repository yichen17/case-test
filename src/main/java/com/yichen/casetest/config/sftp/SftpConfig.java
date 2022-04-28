package com.yichen.casetest.config.sftp;

import com.yichen.casetest.model.SftpFactory;
import com.yichen.casetest.model.SftpPool;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYingxin
 * @date 2021/1/22
 * @description
 **/
@Configuration
@EnableConfigurationProperties(SftpProperties.class)
public class SftpConfig {

    @Bean
    public SftpFactory sftpFactory(SftpProperties properties) {
        return new SftpFactory(properties);
    }

    // 连接池
    @Bean
    public SftpPool sftpPool(SftpFactory sftpFactory) {
        return new SftpPool(sftpFactory);
    }
}
