package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
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
        treeSetTest();
        StringUtils.divisionLine();
    }

    /**
     * treeSet原理测试
     * treeMap、treeSet的所有处理都是基于comparator来索引的，20231008 理解
     *
     */
    private static void treeSetTest(){

        TreeSet<TreeSetNode> set = new TreeSet<>(new Comparator<TreeSetNode>() {
            @Override
            public int compare(TreeSetNode o1, TreeSetNode o2) {
                String str1 = o1.age + o1.name;
                String str2 = o2.age + o2.name;
                return str1.compareTo(str2);
            }
        });
        set.add(TreeSetNode.builder().age(18).name("yichen").build());
        set.add(TreeSetNode.builder().age(19).name("shanliang").build());
        set.add(TreeSetNode.builder().age(18).name("banyu").build());
        set.add(TreeSetNode.builder().age(16).name("xiaoliang").build());
        System.out.println(set.remove(TreeSetNode.builder().age(18).name("yichen").build()));
        System.out.println(set.remove(TreeSetNode.builder().age(18).name("banyu").build()));

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

    @Builder
    private static class TreeSetNode{
        public int age;
        public String name;
    }

}
