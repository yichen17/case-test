package com.yichen.casetest.config;

import com.yichen.casetest.interceptor.ContextInterceptor;
import com.yichen.casetest.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
        registry.addInterceptor(new ContextInterceptor()).addPathPatterns("/**").order(Ordered.HIGHEST_PRECEDENCE);
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
