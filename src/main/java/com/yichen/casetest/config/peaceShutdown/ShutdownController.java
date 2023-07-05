package com.yichen.casetest.config.peaceShutdown;

import com.netflix.discovery.DiscoveryManager;
import com.yichen.casetest.CaseTestApplication;
import com.yichen.casetest.config.kafka.CustomizeConcurrentKafkaListenerContainerFactory;
import com.yichen.casetest.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @date 2023/7/3 11:18
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/shutdown")
@Api(tags = "关闭服务处理")
public class ShutdownController {


    // 应用销毁

    @PostMapping("/all")
    public String allDestroy(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 异步线程池销毁
                    for (Map.Entry<String, ThreadPoolTaskExecutor> item : threadPoolTaskExecutorMap.entrySet()) {
                        log.info("异步线程池{}开始销毁", item.getKey());
                        item.getValue().shutdown();
                    }
                    // kafka暂停消费
                    Collection<ConcurrentMessageListenerContainer> values = (((CustomizeConcurrentKafkaListenerContainerFactory)
                            concurrentKafkaListenerContainerFactory).getConsumersMap().values());
                    for (ConcurrentMessageListenerContainer container : values) {
                        container.stop();
                    }
                    // 睡眠3秒，等待处理
                    Thread.sleep(3000);
                    // eureka下线
                    DiscoveryManager.getInstance().shutdownComponent();
                    // 应用关闭
                    CaseTestApplication.close();
                }
                catch (Exception e){
                    log.error("shutdown all {}", e.getMessage(), e);
                }
            }
        }).start();
        return "ok";
    }

    // 异步线程池处理

    @Autowired
    private Map<String, ThreadPoolTaskExecutor> threadPoolTaskExecutorMap;

    @PostMapping("/asyncThreadPoolDeal")
    public String asyncThreadPoolDeal(){
        for (Map.Entry<String, ThreadPoolTaskExecutor> item : threadPoolTaskExecutorMap.entrySet()) {
            log.info("异步线程池{}开始销毁", item.getKey());
            item.getValue().shutdown();
        }
        return "async thread pool deal";
    }


    // xxl-job 关闭
    // 参考文章  https://www.jianshu.com/p/27e2105ed12e

    @PostMapping("/xxlJobDeal")
    public String xxlJobDeal(){

        return "xxl-job stop ok";
    }


    // 参考文档
    // https://blog.csdn.net/jiuyuemo1/article/details/128410207

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/eurekaInfo")
    public String eurekaInfo(@RequestParam String serviceName){
        List<ServiceInstance> instances = client.getInstances(serviceName);
        return FastJsonUtils.toJson(instances);
    }


    @PostMapping("/eurekaDeal")
    public String eurekaDeal(){
        DiscoveryManager.getInstance().shutdownComponent();
        return "ok";
    }

    // kafka 开始消费、停止消费处理

    @Qualifier("serviceKafkaListenerContainerFactory")
    @Autowired
    private ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    @PostMapping("/kafkaDeal")
    public String kafkaDeal(@RequestParam boolean start){
        Collection<ConcurrentMessageListenerContainer> values = (((CustomizeConcurrentKafkaListenerContainerFactory)
                concurrentKafkaListenerContainerFactory).getConsumersMap().values());

        for (ConcurrentMessageListenerContainer container : values) {
            if (start){
                container.start();
            }
            else {
                container.stop();
            }
        }
        return "kafka deal ok";
    }

}
