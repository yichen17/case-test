package com.yichen.casetest.problem.bot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/26 9:40
 * @describe
 */
public class FastJsonUtils {
    private static final SerializeConfig config = new SerializeConfig();
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SerializerFeature[] features;

    public FastJsonUtils() {
    }

    public static String toJsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJson(Object object) {
        return object == null ? null : JSON.toJSONString(object, config, new SerializerFeature[0]);
    }

    public static Object fromJson(String json) {
        return JSON.parse(json);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type.getType(), new Feature[0]);
    }

    public static <T> Object[] toArray(String json) {
        return toArray(json, (Class)null);
    }

    public static <T> Object[] toArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static Object beanToJson(Object keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    public static Object textToJson(String json) {
        Object objectJson = JSON.parse(json);
        return objectJson;
    }

    public static Map<String, Object> stringToCollect(String json) {
        return JSONObject.parseObject(json);
    }

    public static String toJSONStringWithDateFormat(Object object, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(object, dateFormat, new SerializerFeature[0]);
    }

    public static String toJSONStringWithDefaultDateFormat(Object object) {
        return JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }

    static {
        config.put(Date.class, new JSONLibDataFormatSerializer());
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
        features = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty};
    }
}
