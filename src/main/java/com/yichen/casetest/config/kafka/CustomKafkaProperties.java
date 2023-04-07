package com.yichen.casetest.config.kafka;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 8:57
 * @describe
 */
@ConfigurationProperties(prefix = "spring.kafka")
public class CustomKafkaProperties {

    private String enabled;
    private KafkaProperties properties;

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public KafkaProperties getProperties() {
        return properties;
    }

    public void setProperties(KafkaProperties properties) {
        this.properties = properties;
    }

}
