package com.yichen.casetest.mq.kafka;

import com.yichen.casetest.config.kafka.KafkaCustomizeConfiguration;
import com.yichen.casetest.mq.kafka.dto.TestDTO;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 8:49
 * @describe 测试kafka消费
 */
@Slf4j
@Component
public class TestConsumer {

    /**
     * 接收消息未确认时   SeekToCurrentErrorHandler  可以自定义 errorHandler处理逻辑，默认重试9次，间隔0秒
     * 缺陷，如果有后面的数据进来，且成功，则这次抛异常或者没有提交的数据也会确认。  ==> 不区分backOff实现，满次数执行后面的如果提交了就会覆盖
     * partition内是有序的，前一个一直在重试，后一个会阻塞！！！  如果延迟执行可能会导致消息积压问题
     * demo {@link KafkaCustomizeConfiguration#seekToCurrentErrorHandler()}
     * @param record
     */
    @KafkaListener(topics = {"yichen.test"}, containerFactory = "serviceKafkaListenerContainerFactory")
    public void testConsumer(ConsumerRecord<String, String> record, Acknowledgment ack){
        log.info("test获取kafka消息 {}", record.value());
        TestDTO testDTO = FastJsonUtils.fromJson(record.value(), TestDTO.class);
        if ("yichen".equals(testDTO.getName())){
            log.info("test执行抛出异常");
            throw new RuntimeException("手动抛出异常");
        }
        else if ("shanliang".equals(testDTO.getName())){
            log.info("test不确认消费");
            return;
        }
        log.info("test确认消费");
        ack.acknowledge();
    }

}
