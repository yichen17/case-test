package com.yichen.casetest.utils;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/5/17 08:30
 * @describe
 */
public class ToolUtils {

    /**
     * 构造两个对象之间的比对逻辑
     *
     * @param clazz
     * @param ignoreFieldNames
     * @param <T>
     */
    public static void buildEqualsSentence(Class<?> clazz, Set<String> ignoreFieldNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("if (first == second) {return true;}");
        sb.append("if (first == null || second == null) { return false; }");
        for (Field declaredField : clazz.getDeclaredFields()) {
            String fieldName = declaredField.getName();
            if (ignoreFieldNames.contains(fieldName)) {
                continue;
            }
            String getMethodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            // 如果是boolean类型，可能是is起头
            if (declaredField.getType().isAssignableFrom(boolean.class) || declaredField.getType().isAssignableFrom(Boolean.class)) {
                String isMethodName = "is" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                try {
                    clazz.getDeclaredMethod(isMethodName);
                    getMethodName = isMethodName;
                } catch (Exception e) {
                    System.out.println("boolean类型字段无is起头方法：" + fieldName);
                }
            }
            sb.append(String.format("if (!Objects.equals(first.%s(), second.%s())) { return false; }",
                    getMethodName, getMethodName));
        }
        System.out.println("构造出来的方法内容：" + sb);
    }

    public static void main(String[] args) {
        buildEqualsSentence(Test.class, new HashSet<>());
    }

    public boolean nnn(Test first, Test second) {
        
        return false;
    }


    @Data
    private static class Test {
        private String name;
        private Integer age;
        private Boolean sex;
        private boolean marry;
    }


}
