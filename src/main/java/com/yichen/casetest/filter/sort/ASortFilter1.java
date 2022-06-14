package com.yichen.casetest.filter.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 17:06
 * @describe
 */
@Slf4j
//@WebFilter(filterName = "ASortFilter1", urlPatterns = "/*")
//@Order(3)
public class ASortFilter1 implements Filter {

    public ASortFilter1() {
        log.info("ASortFilter1 construct");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("ASortFilter1 doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ASortFilter1 init");
    }

    @Override
    public void destroy() {
        log.info("ASortFilter1 destroy");
    }
}
