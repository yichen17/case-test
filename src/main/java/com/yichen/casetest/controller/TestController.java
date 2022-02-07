package com.yichen.casetest.controller;

import com.yichen.casetest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/10 15:48
 * @describe 研究原理的测试controller
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/get")
    public String get(){
        return "test-get";
    }

    /**
     * 测试是否能从数据库中读取 decimal 类型的null 值  =>  可以
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public String getDataById(Long id){
        return testService.getByid(id).toString();
    }


}
