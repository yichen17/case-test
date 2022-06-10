package com.yichen.casetest.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.annotation.Validated;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/10/25 9:34
 * @describe redis 配置
 */

//@Configuration
//@ImportResource
@ConditionalOnClass(RedisOperations.class)
@AutoConfigureBefore({CacheAutoConfiguration.class})
@EnableConfigurationProperties({RedisProperties.class})
//@AutoConfigureOrder(-999)
@Slf4j
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 每次连接都会创建新的连接，资源消耗较大
     * @return
     */
    @Bean(name = "customRedisConnectionFactory")
//    @Lazy(value = false)
    public RedisConnectionFactory customRedisConnectionFactory(){
        log.info("当前获取的redis 配置信息 {}", JSON.toJSONString(redisProperties));
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());

//        redisStandaloneConfiguration.setDatabase(12);
//        redisStandaloneConfiguration.setHostName("127.0.0.1");
//        redisStandaloneConfiguration.setPassword("yichen");
//        redisStandaloneConfiguration.setPort(6379);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }

    /**
     * 使用 redis 连接池
     * @return
     */
//    @Bean("jedisConnectionFactory2")
//    public RedisConnectionFactory redisConnectionFactory(){
//        JedisPoolConfig poolConfig=new JedisPoolConfig();
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMaxTotal(maxTotal);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        poolConfig.setNumTestsPerEvictionRun(10);
//        poolConfig.setTimeBetweenEvictionRunsMillis(60000L);
//        poolConfig.setMaxWaitMillis(maxWaitMills);
//        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setDatabase(index);
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPassword(password);
//        redisStandaloneConfiguration.setPort(port);
//        JedisClientConfiguration.JedisClientConfigurationBuilder builder=JedisClientConfiguration.builder();
//        JedisClientConfiguration jedisClientConfiguration=builder.usePooling().poolConfig(poolConfig).build();
//        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
//    }

    /**
     * bean 命名默认规则为  方法名   ===>    通过 @Value 可以在多个匹配的情况下指定使用哪个bean
     * @param factory
     * @return
     */
//    @Bean(name = "redisTemplate")
    public StringRedisTemplate redisTemplate(@Value("jedisConnectionFactory2") JedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    public RedisCacheConfiguration cacheConfiguration(){
//        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(60));
//    }

}