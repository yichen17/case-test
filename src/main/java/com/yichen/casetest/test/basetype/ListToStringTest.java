package com.yichen.casetest.test.basetype;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @date 2022/11/10 13:56
 * @describe list转成 string 打印
 */
public class ListToStringTest {

    public static void main(String[] args) throws Exception{
        toStringTest();
        stringUtilsJoinTest();
        streamTest();
        stringJoinTest();
        wrapperDoubleQuote();
    }

    public static void wrapperDoubleQuote(){
        List<String> names = Arrays.asList("shanliang", "yichen", "banyu");
        System.out.println("======>" + StringUtils.join(names, ","));
        String join = StringUtils.join(names, "\", \"");
        // 两端加括号
        String warpQuotes = StringUtils.wrap(join, "\"");
        System.out.println(warpQuotes);
    }

    public static void stringJoinTest(){
        List<String> names = Arrays.asList("shanliang", "yichen", "banyu");
        System.out.println(String.join(",", names));
    }

    public static void streamTest() throws Exception{
        List<Integer> list = Arrays.asList(0, 1, 2, 3);
        String result = list.stream().map(String::valueOf).collect(Collectors.joining(",", "(", ")"));
        System.out.println(result);
    }

    public static void stringUtilsJoinTest() throws Exception{
        List<Integer> list = Arrays.asList(0, 1, 2, 3);
        System.out.println(StringUtils.join(list, ","));
    }

    public static void toStringTest() throws Exception{
        List<Integer> list = Arrays.asList(0, 1, 2, 3);
        System.out.println(list);
    }

}
