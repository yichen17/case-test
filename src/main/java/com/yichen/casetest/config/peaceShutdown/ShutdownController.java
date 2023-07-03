package com.yichen.casetest.config.peaceShutdown;

import com.netflix.discovery.DiscoveryManager;
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
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @Autowired
    private DiscoveryClient client;

    // 内部异步线程池，守护线程做处理

    @PostMapping("threadPoolDeal")
    @ResponseBody
    public String threadPoolDeal(){

        return "thread pool deal";
    }

    // xxl-job 关闭
    // 参考文章  https://www.jianshu.com/p/27e2105ed12e

    @PostMapping("/xxlJobDeal")
    @ResponseBody
    public String xxlJobDeal(){

        return "xxl-job stop ok";
    }


    // 参考文档
    // https://blog.csdn.net/jiuyuemo1/article/details/128410207

    @PostMapping("/eurekaInfo")
    @ResponseBody
    public String eurekaInfo(@RequestParam String serviceName){
        List<ServiceInstance> instances = client.getInstances(serviceName);
        return FastJsonUtils.toJson(instances);
    }


    @PostMapping("/eurekaDeal")
    @ResponseBody
    public String eurekaDeal(){
        DiscoveryManager.getInstance().shutdownComponent();
        return "ok";
    }

    // kafka 开始消费、停止消费处理

    @Qualifier("serviceKafkaListenerContainerFactory")
    @Autowired
    private ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    @PostMapping("/kafkaDeal")
    @ResponseBody
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
