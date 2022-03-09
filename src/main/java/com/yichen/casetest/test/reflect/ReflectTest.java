package com.yichen.casetest.test.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/9 14:58
 * @describe 反射测试
 *   1、反射会根据字段值自动匹配，只要确认 Map 数据和对象数据一致，可以使用 Object
 *   2、
 */
public class ReflectTest {

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

        ReflectTest test = new ReflectTest();



    }

}
