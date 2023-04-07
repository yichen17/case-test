package com.yichen.casetest.config.kafka;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 8:58
 * @describe
 */
@Configuration
@ConditionalOnProperty(prefix = "yichen.kafka.service", name = "enabled", havingValue = "true")
@ConditionalOnClass({KafkaProperties.class, KafkaTemplate.class})
public class ServiceKafkaAutoConfiguration {

    private final RecordMessageConverter messageConverter;

    public ServiceKafkaAutoConfiguration(ObjectProvider<RecordMessageConverter> messageConverter) {
        this.messageConverter = messageConverter.getIfUnique();
    }

    @Bean(name = "serviceKafkaProperties")
    @ConfigurationProperties(prefix = "yichen.kafka.service")
    public CustomKafkaProperties serviceKafkaProperties() {
        return new CustomKafkaProperties();
    }

    @Bean(name = "serviceKafkaTemplate")
    @ConditionalOnMissingBean(name = "serviceKafkaTemplate")
    public KafkaTemplate<?, ?> serviceKafkaTemplate(CustomKafkaProperties serviceKafkaProperties,
                                                    ProducerFactory<Object, Object> serviceKafkaProducerFactory,
                                                    ProducerListener<Object, Object> serviceKafkaProducerListener) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(serviceKafkaProducerFactory);
        if (this.messageConverter != null) {
            kafkaTemplate.setMessageConverter(this.messageConverter);
        }
        kafkaTemplate.setProducerListener(serviceKafkaProducerListener);
        kafkaTemplate.setDefaultTopic(serviceKafkaProperties.getProperties().getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    @Bean(name = "serviceKafkaListenerContainerFactory")
    @ConditionalOnMissingBean(name = "serviceKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> serviceKafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer serviceKafkaListenerContainerFactoryConfigurer,
            ConsumerFactory<Object, Object> serviceKafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        serviceKafkaListenerContainerFactoryConfigurer.configure(factory, serviceKafkaConsumerFactory);
        return factory;
    }

    // 下面是生产者的配置

    @Bean(name = "serviceKafkaProducerListener")
    @ConditionalOnMissingBean(name = "serviceKafkaProducerListener")
    public ProducerListener<Object, Object> serviceKafkaProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean(name = "serviceKafkaProducerFactory")
    @ConditionalOnMissingBean(name = "serviceKafkaProducerFactory")
    public ProducerFactory<?, ?> serviceKafkaProducerFactory(CustomKafkaProperties serviceKafkaProperties) {
        DefaultKafkaProducerFactory<?, ?> factory =
                new DefaultKafkaProducerFactory<>(serviceKafkaProperties.getProperties().buildProducerProperties());
        String transactionIdPrefix = serviceKafkaProperties.getProperties().getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    // 下面是消费者的配置

    @Bean(name = "serviceKafkaConsumerFactory")
    @ConditionalOnMissingBean(name = "serviceKafkaConsumerFactory")
    public ConsumerFactory<?, ?> serviceKafkaConsumerFactory(CustomKafkaProperties serviceKafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(serviceKafkaProperties.getProperties().buildConsumerProperties());
    }

    @Bean(name = "serviceKafkaListenerContainerFactoryConfigurer")
    @ConditionalOnMissingBean(name = "serviceKafkaListenerContainerFactoryConfigurer")
    public ConcurrentKafkaListenerContainerFactoryConfigurer serviceKafkaListenerContainerFactoryConfigurer(
            CustomKafkaProperties serviceKafkaProperties, KafkaTemplate<Object, Object> serviceKafkaTemplate) {
        ConcurrentKafkaListenerContainerFactoryConfigurer configurer =
                new ConcurrentKafkaListenerContainerFactoryConfigurer();
        configurer.setKafkaProperties(serviceKafkaProperties.getProperties());
        configurer.setMessageConverter(this.messageConverter);
        configurer.setReplyTemplate(serviceKafkaTemplate);
        return configurer;
    }

    @Bean(name = "serviceKafkaAdmin")
    @ConditionalOnMissingBean(name = "serviceKafkaAdmin")
    public KafkaAdmin serviceKafkaAdmin(CustomKafkaProperties serviceKafkaProperties) {
        KafkaAdmin kafkaAdmin = new KafkaAdmin(serviceKafkaProperties.getProperties().buildAdminProperties());
        kafkaAdmin.setFatalIfBrokerNotAvailable(serviceKafkaProperties.getProperties().getAdmin().isFailFast());
        return kafkaAdmin;
    }

}
