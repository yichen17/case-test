package com.yichen.casetest.config.position;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/13 9:18
 * @describe
 */
@Slf4j
public class LocationPropertiesListenerConfig {

    public static Map<String,String> propertiesMap = new HashMap<>();
    private static void processProperties(Properties props) throws BeansException {
        for (Object key : props.keySet()){
            String keyStr = key.toString();
            try {
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            }
            catch (UnsupportedEncodingException e){
                log.error("processProperties 解析配置出错，错误信息 {}", e.getMessage(), e);
            }
            catch (Exception e){
                log.error("processProperties 出现未知错误 {}", e.getMessage(), e);
            }
        }
    }

    public  static void loadAllProperties(String fileName){
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(fileName);
            processProperties(properties);
        }
        catch (IOException e){
            log.error("processProperties 读取配置出错，错误信息 {}", e.getMessage(), e);
        }
    }

    public static String getProperty(String name){
        return propertiesMap.get(name);
    }

    public static Map<String,String> getAllProperties(){
        return propertiesMap;
    }

}
