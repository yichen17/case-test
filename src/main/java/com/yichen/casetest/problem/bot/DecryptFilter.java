package com.yichen.casetest.problem.bot;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/31 22:28
 * @describe
 */
@Slf4j
public class DecryptFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (log.isDebugEnabled()){
            log.debug("DecryptFilter");
        }
        DecryptServletRequestWrapper requestWrapper = null;
        try {
            requestWrapper = new DecryptServletRequestWrapper(request);
        }
        catch (Exception e){
            log.error("DecryptServletRequestWrapper error {}",e.getMessage(),e);
        }
        filterChain.doFilter((Objects.isNull(requestWrapper) ? request : requestWrapper),response);
    }
}
