package com.yichen.casetest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:56
 * @describe 切面测试a
 */
@Aspect
@Slf4j
@Component
@Order(-999)
public class AspectTestA {

    @Pointcut("execution(* com.yichen.casetest.controller.AspectController.*(..))")
    public void decryptPointCut() {
    }

    @Before("decryptPointCut()")
    public void before(){
        log.info("AspectTestA before");
    }

    @After("decryptPointCut()")
    public void after(){
        log.info("AspectTestA after");
    }

    @AfterReturning("decryptPointCut()")
    public void afterReturning(){
        log.info("AspectTestA afterReturning");
    }

    @AfterThrowing("decryptPointCut()")
    public void afterThrowing(){
        log.info("AspectTestA AfterThrowing");
    }


    @Around("decryptPointCut()")
    public Object around(ProceedingJoinPoint proceedJoinPoint) {
        Object proceed = null;
        log.info("AspectTestA around start");
        try {
            proceed = proceedJoinPoint.proceed();
            log.info("AspectTestA 请求结果 {}",proceed);
        }
        catch (Throwable e){
            log.error("AspectTestA 错误 {}",e.getMessage(),e);
        }
        log.info("AspectTestA around stop");
        return proceed;
    }

}
