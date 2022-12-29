package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/23 10:26
 * @describe
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        matchesTest();
        StringUtils.divisionLine();
        splitTest();
    }

    private static void matchesTest(){
        log.info("{}", "com.yichen".matches("com.shanliang"));
        log.info("{}", "com.yichen".matches("com.yichen"));
    }

    private static void splitTest(){
        String s = "hello|name";
        int pos = s.indexOf("|");
        String[] items = s.split("\\|");
        log.info("{}", String.join(",", items));
        log.info("{}, {} {}", pos, s.substring(0, pos), s.substring(pos+1));
    }

}
