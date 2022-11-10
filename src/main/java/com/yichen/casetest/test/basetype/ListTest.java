package com.yichen.casetest.test.basetype;

import com.yichen.casetest.model.User;
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
public class ListTest {

    public static void main(String[] args) {
        testIndexOutOfBoundsException();
        testListStreamRemove();
        System.out.println(UUID.randomUUID().toString().replace("-",""));
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
