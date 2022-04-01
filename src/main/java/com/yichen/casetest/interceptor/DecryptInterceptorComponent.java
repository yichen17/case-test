package com.yichen.casetest.interceptor;

import com.yichen.casetest.servlet.DecryptServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 9:59
 * @describe 数据解密拦截器  => 测试单纯使用拦截器的执行逻辑
 *   =>  http://stackoverflow.com/questions/28975025/advise-controller-method-before-valid-annotation-is-handled
 *   =>  https://stackoverflow.com/questions/39271035/how-do-i-get-my-spring-aspects-to-execute-before-valid-validated-annotation-on
 */
@Slf4j
//@Component
public class DecryptInterceptorComponent extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception  {
        request.setAttribute("name", "yichen");
        log.info("666666");

//        DecryptServletRequestWrapper.printMap(DecryptServletRequestWrapper.getJsonParam(request));
        // 无用
//        request.getInputStream().reset();

        return true;

        // 执行多次？
//        return super.preHandle(request, response, handler);
    }



}
