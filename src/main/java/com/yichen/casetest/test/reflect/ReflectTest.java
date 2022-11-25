package com.yichen.casetest.test.reflect;

import com.yichen.casetest.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/9 14:58
 * @describe 反射测试
 *   1、反射会根据字段值自动匹配，只要确认 Map 数据和对象数据一致，可以使用 Object
 *   2、
 */
@Slf4j
public class ReflectTest {

    private static ReflectTestClass reflectTestClass = new ReflectTestClass();

    {
        Map<String,Object> map = new HashMap<>();
        map.put("name","shanliang");
        map.put("age",18);
        map.put("id",1000001L);

        ReflectDTO dto = new ReflectDTO();

        for(Map.Entry<String,Object> entry : map.entrySet()){
            try {
                Field declaredField = dto.getClass().getDeclaredField(entry.getKey());
                System.out.println(declaredField.getType() == String.class);
                declaredField.setAccessible(true);
                declaredField.set(dto,entry.getValue());
            } catch (NoSuchFieldException e) {

            } catch (IllegalAccessException e) {

            }
        }

        System.out.printf(dto.toString());
    }

    public static void main(String[] args) {

        // 反射填充类属性
//        ReflectTest test = new ReflectTest();
        // 获取类的属性
//        Set<String> names = Arrays.stream(ReflectDTO.class.getDeclaredFields()).flatMap(p -> Stream.of(p.getName())).collect(Collectors.toSet());
//        for(String name : names){
//            System.out.println(name);
//        }

        StringUtils.divisionLine();
        executeMethodByClass();
        StringUtils.divisionLine();
        executeMethodByInstance();
    }

    /**
     * 通过类实现方法调用
     */
    private static void executeMethodByClass(){
        try {
            Method method = ReflectTestClass.class.getDeclaredMethod("getCombineData", String.class, String.class);
            method.setAccessible(true);
            log.info("{}", method.invoke(reflectTestClass, "shanliang", "yichen"));
        }
        catch (Exception e){
            log.error("executeMethodByClass出现错误{}", e.getMessage(), e);
        }
    }

    private static void executeMethodByInstance(){
        try {
            Method method = reflectTestClass.getClass().getDeclaredMethod("getCombineData", String.class, String.class);
            method.setAccessible(true);
            log.info("{}", method.invoke(reflectTestClass, "shanliang", "yichen"));
        }
        catch (Exception e){
            log.error("executeMethodByInstance出现错误{}", e.getMessage(), e);
        }
    }


    public static class ReflectTestClass{
        private String getCombineData(String name, String age){
            return name + "-" + age;
        }
    }

}
