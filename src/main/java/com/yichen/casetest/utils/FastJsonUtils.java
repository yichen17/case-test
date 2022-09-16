package com.yichen.casetest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/27 9:46
 * @describe
 */
public class FastJsonUtils {

    private static final SerializeConfig config;
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        config = new SerializeConfig();
//        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 将json转换成字符串(带序列化Feature)
     * <p>
     * 输出空置字段
     * </p>
     * <p>
     * list字段如果为null，输出为[]，而不是null
     * </p>
     * <p>
     * 数值字段如果为null，输出为0，而不是null
     * </p>
     * <p>
     * Boolean字段如果为null，输出为false，而不是null
     * </p>
     * <p>
     * 字符类型字段如果为null，输出为""，而不是null
     * </p>

     */
    public static String toJsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 经json转换成字符串，按实际情况
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, config);
    }

    /**
     * 将json转成object
     */
    public static Object fromJson(String json) {
        return JSON.parse(json);
    }

    /**
     * 将json转成对应的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 按类型转化
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type.getType());
    }

    /**
     * 将json转成数组
     */
    public static <T> Object[] toArray(String json) {
        return toArray(json, null);
    }

    /**
     * 转换为数组
     */
    public static <T> Object[] toArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }

    /**
     * 转换为List
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(Object keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     * @return
     */
    public static Object textToJson(String json) {
        Object objectJson = JSON.parse(json);
        return objectJson;
    }

    /**
     * json字符串转化为map
     */
    public static Map<String, Object> stringToCollect(String json) {
        return JSONObject.parseObject(json);
    }

    /**
     * 指定时间格式 的json转换
     */
    public static String toJSONStringWithDateFormat(Object object, String dateFormat) {

        return JSON.toJSONStringWithDateFormat(object, dateFormat);
    }

    /**
     * 默认时间格式 的json转换
     */
    public static String toJSONStringWithDefaultDateFormat(Object object) {

        return JSON.toJSONStringWithDateFormat(object, DEFAULT_DATE_FORMAT);
    }

    public static JSONObject parseObject(String json) {
        return JSONObject.parseObject(json);
    }

}
