package com.yichen.casetest.controller;

import com.yichen.casetest.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/29 10:35
 * @describe 异步 测试
 */
@RestController
@RequestMapping("/async")
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @GetMapping("/execute")
    public String execute(){
        asyncService.printTime();
        asyncService.printMessage();
        return "执行完毕";
    }

}
