package com.yichen.casetest.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yichen.casetest.feign.GsFeign;
import com.yichen.casetest.service.sentinel.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qiuxinchao
 * @date 2023/4/20 9:40
 * @describe  使用场景：
 *      1、外部请求统一经过网关，网关对各个服务接口进行熔断、限流、降级等操作
 *      2、A/B测试等  新老接口设定相同的窗口大小
 *      3、幂等、分布式锁等  @Idempotent
 *      4、
 *
 *      奇怪问题：
 *      1、每次服务重启，sentinel dashboard上面的配置就没了。?
 */
@Slf4j
@RequestMapping("/sentinel")
@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @Autowired
    private GsFeign gsFeign;

    /**
     * ==> 学习文章
     *      => https://blog.csdn.net/weixin_44780078/article/details/128242453
     *
     * =>  设置了 blockHandler后，对应的方法名必须是public，入参出参同请求接口，入参额外加一个BlockException异常
     *      sentinel dashboard后台添加流控规则是，如果配置了 value得手动添加，默认是根据请求路径走的
     * @return
     */
    @GetMapping("/get")
    @SentinelResource(value = "get interface", blockHandler = "blockHandler")
    public String get(){
        log.info("sentinel get");
        sentinelService.message();
        return "ok";
    }

    @PostMapping("/hotQuery")
    @SentinelResource(value = "hotQuery interface", blockHandler = "blockHandler")
    public String hotQuery(@RequestParam String param){
        log.info("hotQuery {}", param);
        sentinelService.message();
        return param;
    }

    /**
     * 熔断测试：慢调用比例、异常比例、异常数
     * 最长RT: Response Time 最大响应时长
     * 最小请求数 触发熔断的请求数，没有到数量不会触发
     * 统计时长：触发熔断后，过了熔断时长后，再次统计时长，失败则失败
     * @return
     */
    @GetMapping("/blow")
    @SentinelResource(value = "blow", blockHandler = "blockHandler")
    public String blow(){
        log.info("blow");
        try {
            Thread.sleep(200);
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }
        return "blow ok";
    }

    @GetMapping("/outBlow")
    public String outBlow(){
        log.info("outBlow");
        return gsFeign.testHealth();
    }

    /**
     * 理论上
     *      blockHandler是熔断降级方法
     *      fallback是异常降级方法   => 如果方法执行异常，都会让fallback指定的方法去处理，并返回结果。
     */
    @GetMapping("/allTest")
    @SentinelResource(value = "allTest", blockHandler = "blockHandler",
//            fallback = "fallback"
            fallback = "fallbackNoError"
    )
    public String allTest(){
        log.info("allTest");
        if (Math.random() > 0.5f){
            throw new RuntimeException();
        }
        return "all test ok";
    }

    /**
     * 必须同参数加一个 {@linkplain BlockException }
     * @param blockException
     * @return
     */
    public String blockHandler(BlockException blockException){
        log.warn("touch blockHandler");
        return "blockHandler";
    }

    public String blockHandler(String param, BlockException blockException){
        log.warn("touch blockHandler {}", param);
        return param + "blockHandler";
    }

    /**
     * 同参数或者同参数后面多一个 {@linkplain Throwable}
     * @param throwable
     * @return
     */
    public String fallback(Throwable throwable){
        log.warn("touch fallback {}", throwable.getMessage());
        return "fallback";
    }

    public String fallbackNoError(){
        log.warn("touch fallbackNoError");
        return "fallback";
    }


}
