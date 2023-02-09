package com.yichen.casetest.aspect;

import com.yichen.casetest.annotation.Log;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/2/9 14:10
 * @describe 切面测试
 */
@Aspect
@Component
@Slf4j
public class AspectTest {


    /**
     * 测试注解层面
     * @param joinPoint
     * @param controllerLog 对应注解入参
     * @param jsonResult 方法执行结果
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult)
    {
        log.info("执行结束 => {} => {}", controllerLog.title(), FastJsonUtils.toJson(jsonResult));
    }

}
