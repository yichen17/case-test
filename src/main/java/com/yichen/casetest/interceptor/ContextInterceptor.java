package com.yichen.casetest.interceptor;

import com.yichen.casetest.context.GlobalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiuxincha
 * @date 2023/6/20 13:34
 * @describe
 */
@Slf4j
public class ContextInterceptor implements HandlerInterceptor {


    /**
     * 如果没有全局异常处理器配置   @ControllerAdvice 会触发两次，例如
     *          context interceptor trigger /test/throwError
     *          具体异常 ...
     *          context interceptor trigger /error
     *              ==>  StandardHostValve#throwable()会映射到异常页面
     * 但如果配置了，则只会触发一次，例如
     *          具体异常 ...
     *          context interceptor trigger /test/throwError
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("context interceptor trigger {}", request.getRequestURI());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        GlobalContext.release();
    }
}
