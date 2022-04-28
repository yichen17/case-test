package com.yichen.casetest.aspect;

import com.yichen.casetest.model.AspectVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:56
 * @describe 切面测试b
 */
//@Aspect
@Slf4j
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class AspectTestC {

    @Pointcut("@annotation(javax.validation.Valid)")
    public void decryptPointCut() {
    }

    @Before("decryptPointCut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length == 1 && args[0] instanceof AspectVo){
            AspectVo vo = (AspectVo) args[0];
            vo.setAddress("海底两万里");
            vo.setAge(18);
            vo.setName("狂杀一条街");
        }
    }

}
