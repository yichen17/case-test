package com.yichen.casetest.controller;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.AspectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 11:01
 * @describe
 */
@RequestMapping("/aspect")
@RestController
@Slf4j
public class AspectController {

    @RequestMapping("/test")
    public String test(){
        log.info("请求到达controller");
        //  错误链路测试
//        int i = 1/0;
        return "controller arrive";
    }

    @RequestMapping("/testJudge")
    public String testJudge(@RequestBody @Valid AspectVo aspectVo){
        log.info("请求到达controller-testJudge");
        return JSON.toJSONString(aspectVo);
    }


}
