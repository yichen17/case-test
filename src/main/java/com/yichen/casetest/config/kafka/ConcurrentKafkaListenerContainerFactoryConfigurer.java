package com.yichen.casetest.config.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

import java.time.Duration;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 8:57
 * @describe
 */
@Slf4j
public class ConcurrentKafkaListenerContainerFactoryConfigurer {

    private KafkaProperties properties;

    private RecordMessageConverter messageConverter;

    private KafkaTemplate<Object, Object> replyTemplate;

    private SeekToCurrentErrorHandler seekToCurrentErrorHandler;

    /**
     * Set the {@link KafkaProperties} to use.
     *
     * @param properties the properties
     */
    void setKafkaProperties(KafkaProperties properties) {
        this.properties = properties;
    }

    /**
     * Set the {@link RecordMessageConverter} to use.
     *
     * @param messageConverter the message converter
     */
    void setMessageConverter(RecordMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    /**
     * Set the {@link KafkaTemplate} to use to send replies.
     *
     * @param replyTemplate the reply template
     */
    void setReplyTemplate(KafkaTemplate<Object, Object> replyTemplate) {
        this.replyTemplate = replyTemplate;
    }

    /**
     * Configure the specified Kafka listener container factory. The factory can be further tuned and default settings
     * can be overridden.
     *
     * @param listenerFactory the {@link ConcurrentKafkaListenerContainerFactory} instance to configure
     * @param consumerFactory the {@link ConsumerFactory} to use
     */
    public void configure(ConcurrentKafkaListenerContainerFactory<Object, Object> listenerFactory,
                          ConsumerFactory<Object, Object> consumerFactory) {
        listenerFactory.setConsumerFactory(consumerFactory);
        configureListenerFactory(listenerFactory);
        configureContainer(listenerFactory.getContainerProperties());
    }

    private void configureListenerFactory(ConcurrentKafkaListenerContainerFactory<Object, Object> factory) {
        PropertyMapper map = PropertyMapper.get();
        KafkaProperties.Listener properties = this.properties.getListener();
        map.from(properties::getConcurrency).whenNonNull().to(factory::setConcurrency);
        map.from(() -> this.messageConverter).whenNonNull().to(factory::setMessageConverter);
        map.from(() -> this.replyTemplate).whenNonNull().to(factory::setReplyTemplate);
        map.from(properties::getType).whenEqualTo(KafkaProperties.Listener.Type.BATCH).toCall(() -> factory.setBatchListener(true));
        // 设置异常处理句柄
        factory.setErrorHandler(seekToCurrentErrorHandler());
    }



    private SeekToCurrentErrorHandler seekToCurrentErrorHandler() {
        if (Objects.nonNull(this.seekToCurrentErrorHandler)){
            return this.seekToCurrentErrorHandler;
        }
        //interval 为0标识立即重试，maxAttempts为4标识最多重试四次
        SeekToCurrentErrorHandler errorHandler = new SeekToCurrentErrorHandler(new BiConsumer<ConsumerRecord<?, ?>, Exception>() {
            @Override
            public void accept(ConsumerRecord<?, ?> consumerRecord, Exception e) {
                //重试4次仍然失败后会进去BiConsumer接口的accept方法，可以进行保存数据库等操作
                log.info("消费失败4次,消息保存到数据库. record:{}", JSON.toJSONString(consumerRecord.value()));
            }
        }, new FixedBackOff(1000L * 60, 4L));
        this.seekToCurrentErrorHandler = errorHandler;
        return errorHandler;
    }

    private void configureContainer(ContainerProperties container) {
        PropertyMapper map = PropertyMapper.get();
        KafkaProperties.Listener properties = this.properties.getListener();
        map.from(properties::getAckMode).whenNonNull().to(container::setAckMode);
        map.from(properties::getClientId).whenNonNull().to(container::setClientId);
        map.from(properties::getAckCount).whenNonNull().to(container::setAckCount);
        map.from(properties::getAckTime).whenNonNull().as(Duration::toMillis).to(container::setAckTime);
        map.from(properties::getPollTimeout).whenNonNull().as(Duration::toMillis).to(container::setPollTimeout);
        map.from(properties::getNoPollThreshold).whenNonNull().to(container::setNoPollThreshold);
        map.from(properties::getIdleEventInterval).whenNonNull().as(Duration::toMillis)
                .to(container::setIdleEventInterval);
        map.from(properties::getMonitorInterval).whenNonNull().as(Duration::getSeconds).as(Number::intValue)
                .to(container::setMonitorInterval);
        map.from(properties::getLogContainerConfig).whenNonNull().to(container::setLogContainerConfig);
    }

}
