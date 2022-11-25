package com.yichen.casetest.test.basetype;

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
    }

    private static void matchesTest(){
        log.info("{}", "com.yichen".matches("com.shanliang"));
        log.info("{}", "com.yichen".matches("com.yichen"));
    }

}
