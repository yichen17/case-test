package com.yichen.casetest.controller;

import com.yichen.casetest.test.service.Man;
import com.yichen.casetest.test.service.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/2 14:30
 * @describe 注入bean 测试， @Resource 和 @Autowired
 */
@RestController
@RequestMapping("/beanTest")
public class BeanTestController {


    @Autowired
    private Man man;

    @Autowired
    private Teacher teacher;

    @RequestMapping("/sayNo")
    public String sayNo(){
        return man.sayNo()+" === "+teacher.sayNo();
    }

}
