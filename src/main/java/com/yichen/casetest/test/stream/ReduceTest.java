package com.yichen.casetest.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2022/10/21 16:15
 * @describe reduce 操作测试
 */
public class ReduceTest {

    public static void main(String[] args) {
        List<String> pos = new ArrayList<>(64);
        for (int i=0; i<26; i++){
            pos.add(String.valueOf((char)('A' + i)));
        }
//        String result = pos.stream().reduce("", (a, b) -> a + b, (a, b) -> a + b);
        String result = pos.stream().parallel().reduce("", (a, b) -> a + b, (a, b) -> a + b);
//        String result = pos.parallelStream().reduce("", (a, b) -> a + b, (a, b) -> a + b);
        System.out.println(result);

//        pos.stream().parallel().forEach(System.out::println);

    }

}
