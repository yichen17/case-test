package com.yichen.casetest.aspect.requestPrint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/20 16:14
 * @describe
 */
@ControllerAdvice
@Slf4j
public class AppLogRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(org.springframework.core.MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("AppLogRequestBodyAdvice");
        // 返回true，所有请求都拦截
        return true;
    }


    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 把请求体数据放入线程上下文
        AppLogContextHolder.get().setReqBody(body);
        return body;
    }

}
