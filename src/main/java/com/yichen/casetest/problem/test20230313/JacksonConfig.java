package com.yichen.casetest.problem.test20230313;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/13 20:29
 * @describe
 */
@Configuration
@Slf4j
public class JacksonConfig implements WebMvcConfigurer {

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * @EnableWebMvc 使用该注解后，需要手动配置  addInterceptors() 和 addResourceHandlers()
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        log.info("JacksonConfig");
                        return HandlerInterceptor.super.preHandle(request, response, handler);
                    }
                }).addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/null/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController(
//                "/null/api-docs",
//                "/api-docs").setKeepQueryParams(true);
//        registry.addRedirectViewController(
//                "/null/swagger-resources/configuration/ui",
//                "/swagger-resources/configuration/ui");
//        registry.addRedirectViewController(
//                "/null/swagger-resources/configuration/security",
//                "/swagger-resources/configuration/security");
//        registry.addRedirectViewController(
//                "/null/swagger-resources",
//                "/swagger-resources");
//    }


    /**
     * 填充全局 objectMapper
     * https://stackoverflow.com/questions/45734108/spring-boot-not-using-configured-jackson-objectmapper-with-enablewebmvc
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(p -> p instanceof MappingJackson2HttpMessageConverter)
                .map(p -> (MappingJackson2HttpMessageConverter)p).forEach(p -> p.setObjectMapper(objectMapper));
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

//    @Bean
//    public Docket webApiConfig(Environment environment){
//        Profiles profiles = Profiles.of("dev","test","default");
//        boolean flag = environment.acceptsProfiles(profiles);
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(webApiInfo())
//                //.groupName("icar-web")
//                .enable(flag)
//                .select()
//                .build();
//    }
//
//    private ApiInfo webApiInfo(){
//        return new ApiInfoBuilder()
//                //.title("icar-web")
//                .description("API接口文档")
//                .version("1.0")
//                .build();
//    }

}
