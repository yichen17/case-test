package com.yichen.casetest.test.basetype;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/2/7 21:17
 * @describe map测试
 */
public class MapTest {

    public static void main(String[] args) {

    }

    private static void testFunction(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "shanliang");
        map.getOrDefault("age", 18);
    }

}
