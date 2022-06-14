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
//@WebFilter(filterName = "SortFilter1", urlPatterns = "/*")
//@Order(1)
public class SortFilter1 implements Filter {

    public SortFilter1() {
        log.info("SortFilter1 construct");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("SortFilter1 doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SortFilter1 init");
    }

    @Override
    public void destroy() {
        log.info("SortFilter1 destroy");
    }
}
