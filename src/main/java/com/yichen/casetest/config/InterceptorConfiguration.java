package com.yichen.casetest.config;

import com.yichen.casetest.interceptor.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qiuxinchao
 * @date 2022/12/19 13:48
 * @describe 拦截器配置
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**").order(-1);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;
//
//    @Bean
//    @ConditionalOnMissingBean
//    public Encoder feignEncoder() {
//        SpringFormEncoder springFormEncoder = new SpringFormEncoder(new SpringEncoder(this.messageConverters));
//        return springFormEncoder;
//    }


}
