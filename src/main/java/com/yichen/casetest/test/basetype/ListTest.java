package com.yichen.casetest.test.basetype;

import com.google.common.collect.Lists;
import com.yichen.casetest.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/17 9:42
 * @describe 数组测试
 */
@Slf4j
public class ListTest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Person{
        private String name;
        private String address;
        private Integer age;
        private Integer height;
    }

    public static void main(String[] args) {
        testIndexOutOfBoundsException();
        testListStreamRemove();
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        com.yichen.casetest.utils.StringUtils.divisionLine();
        listToArray();
        com.yichen.casetest.utils.StringUtils.divisionLine();
        stringFormat();
        com.yichen.casetest.utils.StringUtils.divisionLine();
        addAllTest();
        com.yichen.casetest.utils.StringUtils.divisionLine();
        listRemove();
    }


    private static void listRemove(){
        List<String> list = new ArrayList<>(8);
        list.add("333");
        list.add("666");
        list.remove(list.size() - 1);
        log.info("==> listRemove {}", String.join(",", list));
    }

    private static void  listToArray(){
        List<String> list = Arrays.asList("111", "222", "333", "444");
//        list.remove(list.size()-1);
        String[] data = list.toArray(new String[0]);
        log.info(String.join(",", data));
    }

    /**
     * list addAll不会去重， removeALl重复也只会移除一次
     */
    private static void addAllTest(){
        Person p1 = Person.builder().age(10).height(100).build();
        Person p2 = Person.builder().age(10).height(110).build();
        Person p3 = Person.builder().age(15).height(120).build();
        Person p4 = Person.builder().age(18).height(90).build();
        List<Person> list = new ArrayList<>(16);
        list.add(p1);list.add(p2);list.add(p3);list.add(p4);
        List<Person> filter = new ArrayList<>();
        filter.addAll(list.stream().filter(p -> p.getAge() == 10).collect(Collectors.toList()));
        filter.addAll(list.stream().filter(p -> p.getHeight() > 105).collect(Collectors.toList()));
        log.info(list.stream().map(p -> String.format("%s-%s", p.getAge(), p.getHeight())).collect(Collectors.joining(",")));
        log.info(filter.stream().map(p -> String.format("%s-%s", p.getAge(), p.getHeight())).collect(Collectors.joining(",")));
        list.removeAll(filter);
        log.info(list.stream().map(p -> String.format("%s-%s", p.getAge(), p.getHeight())).collect(Collectors.joining(",")));
    }

    private static void stringFormat(){
        log.info(String.format("%s有%s块钱","yichen", 50L));
    }


    /**
     * 测试从list中移除Stream流过滤出来的数据
     */
    private static void testListStreamRemove(){
//        List<String> data = Arrays.asList("shanliang", "yichen", "banyu", "monkey");
        List<String> data = new ArrayList<>();
        data.add("shanliang"); data.add("yichen"); data.add("banyu"); data.add("monkey");
        List<String> filterData = data.stream().filter(p -> p.contains("e")).collect(Collectors.toList());
        data.removeAll(filterData);
        System.out.println(StringUtils.join(data, ","));
    }

    /**
     * 测试 java.lang.IndexOutOfBoundsException: Index: 0, Size: 0   异常出现原因
     * 参考解答  https://stackoverflow.com/questions/31595762/traversing-a-listfuture-object-throws-indexoutofbounds-exception
     */
    private static void testIndexOutOfBoundsException(){
        List<List<User>>  users = new ArrayList<>();
        List<User> users1 = new ArrayList<>();
        User user = new User();
        user.setAge(18);
        user.setName("shanliang");
//        users1.add(user);
        users.add(users1);
//        List<List<User>> collect = users.stream().filter(p -> "yichen".equals(p.get(0).getName())).collect(Collectors.toList());
        List<List<User>> collect = users.stream().filter(p -> p.size() > 0).collect(Collectors.toList());

        // 会报错   IndexOutOfBoundsException
//        users1.get(0);

        // 会先判断长度 0直接结束
        for (User u : users1){
            System.out.println(user.getName());
        }

    }

}
