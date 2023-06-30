package com.yichen.casetest.config.kafka;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @date 2023/6/30 15:55
 * @describe  保存topic信息，用于开始/暂停kafka消费
 *      https://bikas-katwal.medium.com/start-stop-kafka-consumers-or-subscribe-to-new-topic-programmatically-using-spring-kafka-2d4fb77c9117
 */
public class CustomizeConcurrentKafkaListenerContainerFactory<K, V> extends ConcurrentKafkaListenerContainerFactory<K, V> {

    private  Map<String, ConcurrentMessageListenerContainer<K, V>> consumersMap =
            new HashMap<>();

    public Map<String, ConcurrentMessageListenerContainer<K, V>> getConsumersMap() {
        return consumersMap;
    }

    @Override
    protected ConcurrentMessageListenerContainer<K, V> createContainerInstance(KafkaListenerEndpoint endpoint) {
        ConcurrentMessageListenerContainer<K, V> instance = super.createContainerInstance(endpoint);
        for (String topic : endpoint.getTopics()) {
            consumersMap.put(topic, instance);
        }
        return instance;
    }

}
