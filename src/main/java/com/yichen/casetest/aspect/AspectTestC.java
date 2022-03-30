package com.yichen.casetest.aspect;

import com.yichen.casetest.model.AspectVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.DefaultDataBinderFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 10:56
 * @describe 切面测试b
 */
//@Aspect
//@Slf4j
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class AspectTestC {




    @Pointcut("")
    public void decryptPointCut() {
    }

    @Before("decryptPointCut()")
    public void before(JoinPoint joinPoint) throws Exception{
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length == 1 && args[0] instanceof AspectVo){
            AspectVo vo = (AspectVo) args[0];
            vo.setAddress("海底两万里");
            vo.setAge(18);
            vo.setName("狂杀一条街");
        }
    }



}
