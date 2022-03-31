package com.yichen.casetest.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yichen.casetest.servlet.DecryptServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 9:59
 * @describe 数据解密拦截器  => 处理逻辑，在这里进行 数据解密，这里处理操作理论上是先于 @Valid 执行的
 *   =>  http://stackoverflow.com/questions/28975025/advise-controller-method-before-valid-annotation-is-handled
 *   =>  https://stackoverflow.com/questions/39271035/how-do-i-get-my-spring-aspects-to-execute-before-valid-validated-annotation-on
 */
@Slf4j
public class DecryptInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception  {
        if (request instanceof DecryptServletRequestWrapper){
            DecryptServletRequestWrapper requestWrapper = (DecryptServletRequestWrapper) request;
            Map<String, Object> requestBody = requestWrapper.getRequestBody();
            requestBody.put("name","yichen");
            DecryptServletRequestWrapper.printMap(requestBody);
        }
        return true;
    }



}
