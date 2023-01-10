package com.yichen.casetest.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @date 2023/1/10 16:22
 * @describe
 */
@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        try {
            if(template != null) {
                log.info("======================================================================================================================");
                log.info("feign调用请求地址:{},请求行:{},请求体:{},请求头:{}",template.url(),
                        template.queryLine(), Objects.nonNull(template.body()) ? new String(template.body(),"UTF-8") :null, template.headers());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
