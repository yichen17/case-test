package com.yichen.casetest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 20:33
 * @PointCut 切面入参解读  =>  https://www.cnblogs.com/liaojie970/p/7883687.html
 * @describe
 */
//@EnableAspectJAutoProxy
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.yichen.casetest.test.service..*.*(..))")
//    @Pointcut("execution(* com.yichen.casetest.test.service.reflect.impl.ReflectServiceImpl.*(..))")
    public void logAspect() {

    }

    @Before("logAspect()")
    public void before(JoinPoint joinPoint){
        log.info("{} logAspect before", joinPoint.getTarget().getClass().getName());
    }

    @After("logAspect()")
    public void after(JoinPoint joinPoint){
        log.info(" {} logAspect after", joinPoint.getTarget().getClass().getName());
    }
}

