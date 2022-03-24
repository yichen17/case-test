package com.yichen.casetest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:56
 * @describe 切面测试b
 */
@Aspect
@Slf4j
@Component
@Order(-100)
public class AspectTestB {

    @Pointcut("execution(* com.yichen.casetest.controller.AspectController.*(..))")
    public void decryptPointCut() {
    }

    @Before("decryptPointCut()")
    public void before(){
        log.info("AspectTestB before");
    }

    @After("decryptPointCut()")
    public void after(){
        log.info("AspectTestB after");
    }

    @AfterReturning("decryptPointCut()")
    public void afterReturning(){
        log.info("AspectTestB afterReturning");
    }

    @AfterThrowing("decryptPointCut()")
    public void afterThrowing(){
        log.info("AspectTestB AfterThrowing");
    }


    @Around("decryptPointCut()")
    public Object around(ProceedingJoinPoint proceedJoinPoint) {
        Object proceed = null;
        log.info("AspectTestB around start");
        try {
            proceed = proceedJoinPoint.proceed();
            log.info("AspectTestB 请求结果 {}",proceed);
        }
        catch (Throwable e){
            log.error("AspectTestB 错误 {}",e.getMessage(),e);
        }
        log.info("AspectTestB around stop");
        return proceed;
    }

}
