package com.yichen.casetest.controller;

import com.yichen.casetest.CaseTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/4 9:28
 * @describe spring context controller
 */
@RestController
@Slf4j
@RequestMapping("/context")
public class ContextController {

    @GetMapping("/refresh")
    public String refresh(){
        log.info("context refresh");
        CaseTestApplication.restart();
        return "ok";
    }

}
