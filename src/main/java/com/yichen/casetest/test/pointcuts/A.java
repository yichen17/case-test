package com.yichen.casetest.test.pointcuts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 13:48
 * @describe
 */
@Slf4j
@Component
 class A {

     public void print(TT tt){
         log.info("a print");
     }

    public void print(TT tt, TT t){
        log.info("a print");
    }

     public void oneParam(String s){
         log.info("oneParam {}", s);
     }

     public void twoParam(String s, Integer t){
         log.info("twoParam {} {}", s, t);
     }

}
