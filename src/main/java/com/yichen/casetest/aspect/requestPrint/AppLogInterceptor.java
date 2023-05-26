package com.yichen.casetest.aspect.requestPrint;

import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/20 16:07
 * @describe
 */
@Slf4j
public class AppLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获得一个线程上下文log对象
        AppLog appLog = AppLogContextHolder.get();

        // 把请求路径也放入到线程上下文中
        appLog.setPath(request.getServletPath());

        // 把请求参数放入到线程上下文对象中
        appLog.setParameters(request.getParameterMap());

        // 这里也可以把header信息也放进去，我这里就不放了哈
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AppLog appLog = AppLogContextHolder.get();
        // 打印本次请求的相关日志
        log.info("接口请求信息 {}", FastJsonUtils.toJson(appLog));
        // 请求结束后清空掉当前线程上下文的内容，防止内存泄漏
        AppLogContextHolder.remove();
    }

}
