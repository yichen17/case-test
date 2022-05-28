package com.yichen.casetest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/28 19:13
 * @describe 自定义 redisCacheManage
 */
@Configuration
@AutoConfigureAfter({CacheAutoConfiguration.class})
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties({RedisProperties.class, CacheProperties.class, RedisCacheExpiresProperties.class})
public class CustomRedisCacheManagerConfiguration {

    private final CacheProperties cacheProperties;


    public CustomRedisCacheManagerConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean(name = "serviceRedisCacheManager")
    @ConditionalOnBean(name = "customRedisConnectionFactory")
    public RedisCacheManager serviceRedisCacheManager(
            @Qualifier("customRedisConnectionFactory") RedisConnectionFactory customRedisConnectionFactory,
            RedisCacheExpiresProperties redisCacheExpiresProperties) {

        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.builder(customRedisConnectionFactory).cacheDefaults(determineConfigurationDefault());

        // 设置自定义redis cacheName以及对应的失效时间
        Map<String, Long> cacheConfigurations = redisCacheExpiresProperties.getCacheExpires();
        if (cacheConfigurations != null && cacheConfigurations.size() > 0) {
            Map<String, RedisCacheConfiguration> redisCacheConfigurations = new HashMap<>();
            for (String cacheName : cacheConfigurations.keySet()) {
                Assert.notNull(cacheName, "CacheName must not be null!");

                long ttl = cacheConfigurations.get(cacheName);
                Assert.isTrue(ttl > 0, "Expire must not be null!");

                RedisCacheConfiguration redisCacheConfiguration = determineConfiguration(cacheName, ttl);
                redisCacheConfigurations.put(cacheName, redisCacheConfiguration);

            }
            builder.withInitialCacheConfigurations(redisCacheConfigurations);
        }

        return builder.build();
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration determineConfigurationDefault() {

        CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
        org.springframework.data.redis.cache.RedisCacheConfiguration config =
                org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration determineConfiguration(String cacheName,long ttl) {
        org.springframework.data.redis.cache.RedisCacheConfiguration config =
                org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
//        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//        config = config.prefixCacheNameWith(cacheName);
        config = config.entryTtl(Duration.ofSeconds(ttl));
        config = config.disableCachingNullValues();
        return config;
    }

}
