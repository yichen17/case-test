package com.yichen.casetest.utils;

import com.yichen.casetest.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @date 2023/7/28 15:56
 * @describe 字段映射工具类
 */
@Slf4j
public class FieldMappingUtils {

    /**
     * 根据mappings里的映射关系构造结果集，如果映射关系没有命中，默认忽略。最终返回序列化的字符串
     * @param mappings 映射关系   keys示例：A.B.C    value示例：D.E
     * @param originObj 数据源对象
     * @param <T>
     * @return
     */
    public static <T> String mappingObject(Map<String, String> mappings, T originObj){
        // 排序 排序后应该是有层级关系的

        // 填充数据，没有数据则为空   类型不匹配，强转以及失败问题

        // 序列化结果集 返回

        return null;
    }


    private static Object parseAndGetField(Object originObj, String mapPath){
        String[] items = mapPath.split(CommonConstants.COMMA);
        Object object = originObj;
        for (String item : items){

        }
        return null;
    }

    private static Field getField(Object object, String field){
        return null;
    }


}
