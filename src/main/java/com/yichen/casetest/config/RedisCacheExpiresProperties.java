package com.yichen.casetest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/27 14:57
 * @describe redis 缓存时长配置
 */
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisCacheExpiresProperties {

    /**
     * cacheName:ExpireTime(秒)
     */
    private Map<String, Long> cacheExpires;

    public Map<String, Long> getCacheExpires() {
        return cacheExpires;
    }

    public void setCacheExpires(Map<String, Long> cacheExpires) {
        this.cacheExpires = cacheExpires;
    }

}