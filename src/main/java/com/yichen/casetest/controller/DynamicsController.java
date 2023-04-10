package com.yichen.casetest.controller;

import com.yichen.casetest.service.DynamicsExecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @date 2023/4/10 9:02
 * @describe 动态运行配置相关入口
 */
@Slf4j
@RestController
@RequestMapping("/dynamics")
public class DynamicsController {

    @Autowired
    private DynamicsExecService dynamicsExecService;

    @PostMapping("/changeCorn")
    public String changeCorn(@RequestParam String newCorn){
        log.info("changeCorn {}", newCorn);
        dynamicsExecService.setCron(newCorn);
        return "ok";
    }


    @PostMapping("/changeTimes")
    public String changeTimes(@RequestParam Long newInterval){
        log.info("changeTimes {}", newInterval);
        dynamicsExecService.setTimer(newInterval);
        return "ok";
    }

}
