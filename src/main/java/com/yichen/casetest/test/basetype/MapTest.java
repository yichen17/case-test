package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/2/7 21:17
 * @describe map测试
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
//        treeMapTest();
//        StringUtils.divisionLine();
        currentHashMapTest();
        StringUtils.divisionLine();
    }

    private static void currentHashMapTest(){
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();
        fillMap(map);
    }

    private static void treeMapTest(){
        TreeMap<String, Object> treeMap = new TreeMap<>();
        fillMap(treeMap);
    }

    private static void fillMap(Map treeMap){
        treeMap.put("tt", 1);
        treeMap.put("tt1", 1);
        treeMap.put("tt2", 1);
        treeMap.put("tt3", 1);
        treeMap.put("tt4", 1);
        treeMap.put("tt5", 1);

        treeMap.put("name", "yichen");
        treeMap.put("age", 18);
        treeMap.put("address", "shanghai");
        treeMap.put("sex", "boy");
    }

    private static void testFunction(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "shanliang");
        map.getOrDefault("age", 18);
    }

}
