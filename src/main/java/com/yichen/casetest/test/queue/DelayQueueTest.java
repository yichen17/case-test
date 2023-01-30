package com.yichen.casetest.test.queue;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/1/30 9:45
 * @describe 延迟队列相关测试
 * 参考文章： https://tech.youzan.com/queuing_delay/
 *
 */
@Slf4j
public class DelayQueueTest {

    public static void main(String[] args) {
        try {
            jdkTest();
        }
        catch (Exception e){
            log.error("执行异常 {}", e.getMessage(), e);
        }
    }

    private static void jdkTest() throws Exception{
        Order Order1 = new Order("Order1", 5, TimeUnit.SECONDS);
        Order Order2 = new Order("Order2", 10, TimeUnit.SECONDS);
        Order Order3 = new Order("Order3", 15, TimeUnit.SECONDS);

        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.put(Order1);
        delayQueue.put(Order2);
        delayQueue.put(Order3);
        log.info("订单延迟队列开始时间:{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            Order task = delayQueue.poll();
            if (task != null) {
                log.info("订单:{}被取消, 取消时间:{}", task.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.name,
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);
        }
    }

}
