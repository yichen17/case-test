package com.yichen.casetest.test.basetype;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/14 21:19
 * @describe  字节码层面比较
 */
public class JavacTest {

    public static void main(String[] args) {
        int a = 1, b = 3;
        System.out.println(String.format("%s %s",a++*2, ++b*2));
    }

}
