package com.yichen.casetest.config;

import com.yichen.casetest.interceptor.DecryptInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 21:46
 * @describe 解密配置
 */
@Configuration
public class DecryptConfiguration implements WebMvcConfigurer {

    @Autowired
    private DecryptInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
