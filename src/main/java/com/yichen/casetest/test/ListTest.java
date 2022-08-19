package com.yichen.casetest.test;

import com.yichen.casetest.model.User;

import java.util.ArrayList;
import java.util.List;
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
