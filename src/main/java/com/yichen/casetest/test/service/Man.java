package com.yichen.casetest.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/2 14:24
 * @describe 超类 @Resource 注入
 */
@Service
public class Man {

    private static Logger logger= LoggerFactory.getLogger(Man.class);

    @Resource
    Person person;

    private int num=0;

    public String sayNo(){
        logger.info("Man ==> {}",person);
        logger.info("random ==> {}",person.getRandom());
        logger.info(" num ==> {}",num);
        person.setRandom(7421L);
        if(person==null){
            return "@Resource failed";
        }
        else{
            return person.sayNo();
        }
    }

}
