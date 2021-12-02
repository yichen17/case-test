package com.yichen.casetest.test.service;

import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/2 14:24
 * @describe 基础，被注入的bean
 */
@Service
public class Person {
    public Person(){}

    private double random=Math.random();

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }

    public String sayNo(){return "sayNo";}
}
