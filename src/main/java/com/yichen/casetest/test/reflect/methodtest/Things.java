package com.yichen.casetest.test.reflect.methodtest;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 21:44
 * @describe
 */
public interface Things {

    default String getName(){
        return "things";
    }

    static String howAreYou(){
        return "things";
    }

}
