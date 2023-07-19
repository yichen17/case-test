package com.yichen.casetest.test.pointcuts;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 13:51
 * @describe   参考文档：
 *                  https://docs.spring.io/spring-framework/docs/2.0.x/reference/aop.html
 *                  https://eclipse.dev/aspectj/doc/released/progguide/semantics-pointcuts.html
 *             学习文档：
 *                  https://jstobigdata.com/spring/pointcut-expressions-in-spring-aop/
 *      java支持的pointcut:  execution 、within 、this 、target 、args 、@target、@args、@within、@annotation   总共9个
 */
@Component
@Slf4j
@Aspect
 class PointcutsConfig {

    // 功能测试

    @Around("execution(* com.yichen.casetest.test.pointcuts.A.*(..))")
    public Object exec(ProceedingJoinPoint pjp) throws Throwable {
        log.info("exec touch");
        return pjp.proceed();
    }

    @Around("within(com.yichen.casetest.test.pointcuts.*))")
    public Object within(ProceedingJoinPoint pjp) throws Throwable {
        log.info("within touch");
        return pjp.proceed();
    }

    @Around("this(com.yichen.casetest.test.pointcuts.BI) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object thisCl(ProceedingJoinPoint pjp) throws Throwable {
        log.info("thisCl touch");
        return pjp.proceed();
    }

    @Around("target(com.yichen.casetest.test.pointcuts.BI) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object targetCl(ProceedingJoinPoint pjp) throws Throwable {
        log.info("targetCl touch");
        return pjp.proceed();
    }

    @Around("args(java.lang.String) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object args(ProceedingJoinPoint pjp) throws Throwable {
        log.info("args touch");
        return pjp.proceed();
    }

    @Around("@target(com.yichen.casetest.test.pointcuts.CA) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object annoTarget(ProceedingJoinPoint pjp) throws Throwable {
        log.info("annoTarget touch");
        return pjp.proceed();
    }

    @Around("@args(com.yichen.casetest.test.pointcuts.CA) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object annoArgs(ProceedingJoinPoint pjp) throws Throwable {
        log.info("annoArgs touch");
        return pjp.proceed();
    }

    @Around("@within(com.yichen.casetest.test.pointcuts.CA) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object annoWithin(ProceedingJoinPoint pjp) throws Throwable {
        log.info("annoWithin touch");
        return pjp.proceed();
    }

    @Around("@annotation(com.yichen.casetest.test.pointcuts.CA) && within(com.yichen.casetest.test.pointcuts.*)")
    public Object annoAnno(ProceedingJoinPoint pjp) throws Throwable {
        log.info("annoAnno touch");
        return pjp.proceed();
    }

    // 表达式测试

}
