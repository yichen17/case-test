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
     *
     * 如果ack没确认，后续数据进来成功会直接提交偏移量，前面的数据就丢失了   不包含重试，直接取下一个数据
     * https://stackoverflow.com/questions/62413270/kafka-what-is-the-point-of-using-acknowledgment-nack-if-i-can-simply-not-ack/62414203#62414203
     *
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
            // 阻塞指定时长后再次poll数据   一直卡死，直到处理
//            ack.nack(2000);
            // ack.acknowledge();
            return;
        }
        log.info("test确认消费");
        ack.acknowledge();
    }

}
