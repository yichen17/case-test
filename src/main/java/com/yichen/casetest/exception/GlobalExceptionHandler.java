package com.yichen.casetest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 14:43
 * @describe
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * validate 校验结果
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String errorHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldErrors().get(0).getDefaultMessage();

        log.warn("[MethodArgumentNotValidException参数验证异常] url:{},message:{}", request.getRequestURI(), message);
        return message;
    }

}
