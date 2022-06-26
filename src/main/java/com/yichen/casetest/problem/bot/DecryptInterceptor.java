package com.yichen.casetest.problem.bot;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/30 9:59
 * @describe 数据解密拦截器  => 处理逻辑，在这里进行 数据解密，这里处理操作理论上是先于 @Valid 执行的
 *   =>  http://stackoverflow.com/questions/28975025/advise-controller-method-before-valid-annotation-is-handled
 *   =>  https://stackoverflow.com/questions/39271035/how-do-i-get-my-spring-aspects-to-execute-before-valid-validated-annotation-on
 *
 *   other note:
 *   1、多端最好统一风格，这里android有form加密的。。。
 *   2、字段和加密方式最好也统一
 *   3、请求入参风格统一，这个项目大部分是包了 RequestBean<?>的很好。后续开发最好注意这块。。
 */
@Slf4j
public class DecryptInterceptor implements HandlerInterceptor {

    /**
     * 这里做前置处理，将加密的数据解密，并进行赋值
     * <red>前端不管啥端，必须要传 deviceType 字段</red> 兜底可能不可靠
     * 目前暂无一个终端多个解密key的情况
     *
     * AccountController#oldPwdCheck  表单入参的没改，不好改。而且只有一个。
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler)throws Exception  {
        if (log.isDebugEnabled()){
            log.debug("DecryptInterceptor preHandle");
        }
        //必须强转为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取加密注解
        DecryptParam decryptParam = handlerMethod.getMethodAnnotation(DecryptParam.class);
        if (Objects.isNull(decryptParam)){
            return true;
        }
        // 拆除包装
        HttpServletRequestWrapper wrapper = unwrapRequest(request);
        if (wrapper instanceof DecryptServletRequestWrapper){
            if (log.isDebugEnabled()){
                log.debug("interceptor change the value");
            }
            DecryptServletRequestWrapper requestWrapper = (DecryptServletRequestWrapper) wrapper;
            Map<String, Object> requestBody = requestWrapper.getRequestBody();
            // 获取请求头中的设备类型
            String deviceType = getDeviceType(requestWrapper);
            if (log.isDebugEnabled()){
                log.debug("deviceType {}", deviceType);
            }
            // 获取对应的加密字段
            String encryptField =  DecryptEnum.getEncryptField(deviceType);
            if (StringUtils.isEmpty(encryptField) ) {
                log.error("解密数据出错，无法获得加密字段。请求体内容 {}", FastJsonUtils.toJson(requestBody));
                throw new RuntimeException("无法获得加密字段");
            }
            // 获取data字段数据 及实际数据
            JSONObject requestBodyData = getRequestBodyData(requestBody);
            if (Objects.isNull(requestBodyData)){
                log.error("请求数据体为空");
                throw new RuntimeException("请求数据体为空");
            }
            // 获取加密数据字段-值
            String[] encryptFields = encryptField.split(DecryptConstants.COMMA);
            String encryptData = null;
            String realEncryptField  = null;
            for (String field : encryptFields){
                encryptData = Objects.isNull(requestBodyData.get(field)) ? "" : String.valueOf(requestBodyData.get(field));
                if (StringUtils.isNotEmpty(encryptData)){
                    realEncryptField = field;
                    break;
                }
            }
            if (StringUtils.isEmpty(encryptData)) {
                // 测试以及流程保证。 如果设备类型对应的加密字段没有获取到入参数据。可以理解为该设备类型不需要加密
                log.error("非加解密数据，请求来源 {} {}", deviceType, FastJsonUtils.toJson(requestBodyData));
                return true;
            }
            // 获取对应设备类型的解密方式
            String decryptType = getDecryptType(deviceType, decryptParam);
            if (StringUtils.isEmpty(decryptType)){
                log.error("对应数据解密方式不能为空");
                throw new RuntimeException("对应数据解密方式不能为空");
            }
            // 获取解密数据
            String decryptKey = DecryptEnum.getDecryptKey(deviceType);
            if (log.isDebugEnabled()){
                log.debug("deviceType {}, realEncryptField {}, encryptData {}, decryptType {}, decryptKey {}",
                        deviceType, realEncryptField, encryptData, decryptType, decryptKey);
            }
            Map<String, Object> decryptMap = CustomDecryptUtils.getDecryptMap(encryptData, decryptKey, decryptType);
            // 放入入参 map 中
            if (Objects.isNull(decryptMap)){
                log.error("字段解密后无有效信息");
                throw new RuntimeException("字段解密后无有效信息");
            }
            // 写入数据
            requestBodyData.putAll(decryptMap);
            // 移除加密字段，减少请求头数据量
            requestBodyData.remove(realEncryptField);
            if (log.isDebugEnabled()){
                DecryptServletRequestWrapper.printMap(requestBodyData);
            }
        }
        return true;
    }

    /**
     * 获取请求体中的 真正数据  data字段
     * @param requestBody json入参数据
     * @return 真正数据 data字段
     */
    protected JSONObject getRequestBodyData(Map<String, Object> requestBody){
        JSONObject requestBodyData = null;
        Object data = requestBody.get("data");
        if (Objects.nonNull(data) && data instanceof JSONObject){
            requestBodyData = (JSONObject)data;
        }
        return requestBodyData;
    }

    /**
     * 获取解密类型
     * @param deviceType 请求设备类型
     * @param decryptParam 注解参数
     * @return 获取解密数据类型  json  form
     */
    protected String getDecryptType(String deviceType, DecryptParam decryptParam){
        String decryptType = null;
        for (String encryptType : decryptParam.encryptType()){
            String[] items = encryptType.split(DecryptConstants.BAR);
            if (items.length != 2){
                log.error("配置错误，必须为两项 - 分隔");
                throw new RuntimeException("配置错误，必须为两项 - 分隔");
            }
            if (deviceType.equalsIgnoreCase(items[0])){
                decryptType = items[1].toLowerCase();
                break;
            }
        }
        return decryptType;
    }

    /**
     * 获取请求设备类型
     * @param requestWrapper 包装的 request
     * @return 请求类型  ios  android h5
     */
    protected String getDeviceType(DecryptServletRequestWrapper requestWrapper){
        String deviceType = requestWrapper.getHeader(DecryptConstants.DEVICE_TYPE);
        // 为空，则反向搜索， 根据字段去匹配设备类型。 目前加密字段没有重复，可以实现。
        if (StringUtils.isEmpty(deviceType)){
            if (log.isDebugEnabled()){
                log.debug("没有获取deviceType,走保底逻辑");
            }
            loop:
            for (String key : DecryptConstants.DEFAULT_DEVICE_FIELD_MAP.keySet()){
                String[] items = key.split(DecryptConstants.COMMA);
                for (String item : items){
                    if (StringUtils.isNotEmpty(requestWrapper.getHeader(item))){
                        deviceType = key;
                        break loop;
                    }
                }
            }
        }
        return deviceType.toLowerCase();
    }

    /**
     * 去除包装，直至非包装或者为解密包装类
     * @param request 请求 HttpServletRequest
     * @return 非包装或者为解密包装
     */
    private HttpServletRequestWrapper unwrapRequest(HttpServletRequest request){
        HttpServletRequestWrapper result = null;
        ServletRequest servletRequest = request;
        // 去除包装，直到非包装或者未解密包装
        while (servletRequest instanceof HttpServletRequestWrapper){
            result = (HttpServletRequestWrapper)servletRequest;
            if (servletRequest instanceof DecryptServletRequestWrapper){
                return result;
            }
            else {
                servletRequest = result.getRequest();
            }
        }
        return result;
    }



}
