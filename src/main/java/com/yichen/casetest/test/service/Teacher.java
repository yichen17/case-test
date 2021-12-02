package com.yichen.casetest.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/2 14:24
 * @describe 子类 @Autowired 注入
 */
@Service
public class Teacher extends Man{

    private static Logger logger= LoggerFactory.getLogger(Man.class);

    @Autowired
    Person person;

    private int num=10;

    @Override
    public String sayNo() {
        logger.info("Teacher ==> {}",person);
        logger.info("random ==> {}",person.getRandom());
        logger.info(" num ==> {}",num);
        if(person==null){
            return "@Autowired failed";
        }
        else{
            return person.sayNo();
        }
    }
}
