package com.yichen.casetest.aspect.requestPrint;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/20 16:08
 * @describe
 */
//@Configuration
public class AppLogConfiguration {

    @Bean
    public WebMvcConfigurer appLogWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 添加拦截器
                registry.addInterceptor(new AppLogInterceptor());
            }
        };
    }

}
