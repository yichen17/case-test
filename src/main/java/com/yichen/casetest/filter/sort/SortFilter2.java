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
//@WebFilter(filterName = "SortFilter2", urlPatterns = "/*")
//@Order(2)
public class SortFilter2 implements Filter {

    public SortFilter2() {
        log.info("SortFilter2 construct");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("SortFilter2 doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SortFilter2 init");
    }

    @Override
    public void destroy() {
        log.info("SortFilter2 destroy");
    }
}
