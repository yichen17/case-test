package com.yichen.casetest.interceptor;

import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @date 2023/1/10 15:27
 * @describe
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            // 打印请求头
//            printRequestHeader(request);
            return true;
        }


        private void printRequestHeader(HttpServletRequest request) {
            if (log.isDebugEnabled()) {
                log.debug("uri {}", request.getRequestURI());
                log.debug("query param {}", FastJsonUtils.toJson(request.getParameterMap()));
                log.debug("query string {}", FastJsonUtils.toJson(request.getQueryString()));
                Map<String, Object> headers = new HashMap<>(16);
                Enumeration<String> headerNames = request.getHeaderNames();
                // 请求头区分大小写吗
                log.debug("check case in header {} {}", request.getHeader("traceId"), request.getHeader("traceid"));
                String name;
                while (headerNames.hasMoreElements()) {
                    name = headerNames.nextElement();
                    headers.put(name, request.getHeader(name));
                }
                log.debug("请求头：{}", FastJsonUtils.toJson(headers));
            }
        }
}
