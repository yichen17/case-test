package com.yichen.casetest.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 9:59
 * @describe 数据解密拦截器  => 处理逻辑，在这里进行 数据解密，这里处理操作理论上是先于 @Valid 执行的
 *   =>  http://stackoverflow.com/questions/28975025/advise-controller-method-before-valid-annotation-is-handled
 *   =>  https://stackoverflow.com/questions/39271035/how-do-i-get-my-spring-aspects-to-execute-before-valid-validated-annotation-on
 */
@Component
@Slf4j
public class DecryptInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception  {

        JSONObject jsonParam = getJSONParam(request);
        jsonParam.forEach((key, value) -> log.info("key =>  {}, value => {}", key, value));
        return super.preHandle(request,response,handler);
    }


    /**
     * 获取 入参
     * @param request 请求
     * @return 入参结果集
     */
    public static JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("getJsonParam => {}", JSON.toJSONString(jsonParam));
        return jsonParam;
    }


}
