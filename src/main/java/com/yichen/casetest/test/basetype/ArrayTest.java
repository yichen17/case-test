package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/2/12 16:34
 * @describe 数组测试
 */
@Slf4j
public class ArrayTest {

    public static void main(String[] args) {
        contain();
        StringUtils.divisionLine();
    }

    private static void testArraysAsList(){
        char[] chars= {'[', '{', '('};
        List<char[]> list = Arrays.asList(chars);
        String[] str = {"hello", "world"};
        List<String> strList = Arrays.asList(str);
        Character[] characters= {'[', '{', '('};
        List<Character> characterList = Arrays.asList(characters);
    }

    /**
     * 包含元素测试
     */
    private static void contain(){
        char[] chars= {'[', '{', '('};
        Character[] characters= {'[', '{', '('};
        List<Character> list = new ArrayList<>();
        list.add('[');
        list.add('(');
        log.info("list ==> {}", list.contains('['));
        //   大坑
        log.info("==> {} {}", Arrays.asList(chars).contains('['), Arrays.asList(characters).contains('['));

        log.info("==> {}", Stream.of('[', '{', '(').anyMatch(p -> p == '['));
    }

}
