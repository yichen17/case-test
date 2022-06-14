package com.yichen.casetest.filter.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 17:06
 * @describe
 */
@Slf4j
@WebFilter(filterName = "SameOrderFilter2", urlPatterns = "/*")
@Component
@Order(3)
public class SameOrderFilter2 extends OncePerRequestFilter {

    public SameOrderFilter2() {
        log.info("SameOrderFilter2 construct");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("SameOrderFilter2 doFilterInternal");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("SameOrderFilter2 destroy");
    }

}
