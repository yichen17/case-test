package com.yichen.casetest.config;

import com.yichen.casetest.filter.DecryptFilter;
import com.yichen.casetest.interceptor.DecryptInterceptor;
import com.yichen.casetest.interceptor.DecryptInterceptorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 21:46
 * @describe 解密配置  => 连同  DecryptInterceptorComponent 测试拦截器实现逻辑
 *     参考解决办法  https://yaoyuanyy.github.io/2019/09/21/springboot%20HandlerIntercepter%E6%8B%A6%E6%88%AA%E5%99%A8%E5%AE%9E%E7%8E%B0%E4%BF%AE%E6%94%B9request%20body%E6%95%B0%E6%8D%AE/
 */
//@Configuration
public class DecryptConfigurationComponent implements WebMvcConfigurer {

    @Autowired
    private DecryptInterceptorComponent interceptorComponent;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorComponent);
//        WebMvcConfigurer.super.addInterceptors(registry);
    }




}
