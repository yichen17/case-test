package com.yichen.casetest.config.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.function.BiConsumer;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 9:45
 * @describe 自定义异常 handler
 */
@Configuration
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class KafkaCustomizeConfiguration {

    /**
     * 配合类加载顺序问题， configureListenerFactory 先加载
     * @return
     */
    @Bean
    @Primary
    public SeekToCurrentErrorHandler seekToCurrentErrorHandler() {
        //interval 为0标识立即重试，maxAttempts为4标识最多重试四次
        SeekToCurrentErrorHandler errorHandler = new SeekToCurrentErrorHandler(new BiConsumer<ConsumerRecord<?, ?>, Exception>() {
            @Override
            public void accept(ConsumerRecord<?, ?> consumerRecord, Exception e) {
                //重试4次仍然失败后会进去BiConsumer接口的accept方法，可以进行保存数据库等操作
                log.info("消费失败4次,消息保存到数据库. record:{}", JSON.toJSONString(consumerRecord.value()));
            }
        }, new FixedBackOff(5L, 4L));
        return errorHandler;
    }


}
