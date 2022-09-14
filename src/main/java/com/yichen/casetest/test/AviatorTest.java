package com.yichen.casetest.test;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/13 17:09
 * @describe
 */
public class AviatorTest {

    public static void main(String[] args) {
        System.out.println("abc".contains("b"));
//        System.out.println(AviatorEvaluator.execute("'abc'.contains('a')"));
        Object execute = AviatorEvaluator.execute("string.contains(\"abc\",\"a\")");
        System.out.println(AviatorEvaluator.execute("1==1"));
    }

}
