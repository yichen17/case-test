package com.yichen.casetest.controller;

import com.yichen.casetest.module.event.EatEvent;
import com.yichen.casetest.module.event.EatMsg;
import com.yichen.casetest.module.event.GetEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @date 2023/4/26 16:00
 * @describe  学习文章
 *      => https://mp.weixin.qq.com/s/iXMF6mH9MWIhT1UyqPBr9A
 */
@RestController
@Slf4j
@RequestMapping("/event")
@Api(value = "事件测试", tags = "事件测试")
public class EventController {

    @Resource
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/getEvent")
    @ApiOperation(value = "get事件")
    public String getEvent(@RequestParam String desc){
        applicationContext.publishEvent(GetEvent.builder().desc(desc).build());
        return "ok";
    }

    @PostMapping("/eatEvent")
    @ApiOperation(value = "通过类实现事件")
    public String eatEvent(@RequestParam String name, @RequestParam BigDecimal price){
        applicationEventPublisher.publishEvent(new EatEvent(this,
                EatMsg.builder().foodName(name).foodPrice(price).build()));
        return "ok";
    }

}
