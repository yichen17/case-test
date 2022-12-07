package com.yichen.casetest.test.service.reflect.impl;

import com.yichen.casetest.test.service.reflect.ReflectCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 17:02
 * @describe
 */
@Service
@Slf4j
public class ReflectServiceImpl extends AbstractReflectService {

    @Autowired
    private ReflectCallService reflectCallService;

    public String reflectTest(String name, String age){
        String s = name + "-" + age;
        log.info("==> {} ==> ???", s);
        return reflectCallService.getName();
    }

    private String reflectTest1(String name, String age){
        String s = name + "-" + age;
        log.info("==> {} ==> ???", s);
        return reflectCallService.getName();
    }

    @Override
    public String getCombineData(String name, String age){
        String s = name + "-" + age;
        log.info("==> {}", s);
        return reflectCallService.getName();
    }

    @Override
    public String addressFrom() {
        rainNow();
        return reflectCallService.getName();
    }




}
