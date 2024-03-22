package com.yichen.casetest.utils.validator.utils;


import com.yichen.casetest.utils.validator.exception.ValidatorException;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/22 15:50
 * @describe 转换工具类
 */
public class ConvertUtils {

    /**
     * 将基本数据类型转到 {@Set}, 元素中间用逗号分隔
     *
     * @param originStr
     * @return
     */
    public static Set<Object> baseType2Set(String originStr, Class clazz) {
        Set<Object> result = new HashSet<>();
        String[] items = originStr.split(",");
        if (items.length == 0) {
            return result;
        }
        for (String item : items) {
            try {
                result.add(convert(item, clazz));
            } catch (Exception e) {

            }
        }
        return result;
    }

    public static Set<Object> enum2Set(String fieldName, Class clazz) throws ValidatorException {
        if (!clazz.isEnum()){
            throw new ValidatorException("非枚举类不能反向映射");
        }
        Set<Object> result = new HashSet<>();
        try {
            Method method = clazz.getMethod("values");
            Enum enums[] = (Enum[]) method.invoke(clazz);
            for (Enum e : enums) {
                Method getCode = e.getClass().getMethod(fieldName);
                result.add(getCode.invoke(e));
            }
        } catch (Exception e) {
            throw new ValidatorException(String.format("类%s获取枚举信息失败,错误信息%s", clazz.getName(), e.getMessage()));
        }
        return result;
    }


    public static Object convert(String val, Class type) throws ValidatorException {
        if (val == null) {
            throw new ValidatorException("类型转换不能为空");
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            return Long.parseLong(val);
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(val);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.parseDouble(val);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Float.parseFloat(val);
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return Byte.parseByte(val);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return Short.parseShort(val);
        } else if (type.equals(String.class)) {
            return val;
        } else if (type.equals(Character.class) || type.equals(char.class)) {
            if (val.length() != 1) {
                throw new ValidatorException("char转换长度只能为一");
            }
            return val.charAt(0);
        }
        throw new ValidatorException("未命中类型");
    }


}
