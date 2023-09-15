package com.yichen.casetest.test.jvm;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/9/15 13:06
 * @describe 基本数据类型空间测试
 */
public class BaseTypeSpaceTest {

    public static void main(String[] args) {
        BaseTypeSpaceTest baseTypeSpaceTest = new BaseTypeSpaceTest();
        baseTypeSpaceTest.simpleCase();
//        baseTypeSpaceTest.simpleCase1();
    }

    /**
     * 80
     * stackOverFlow 参考解释
     *     https://stackoverflow.com/questions/31206851/how-much-memory-does-a-string-use-in-java-8
     */
    private void simpleCase1(){
        long m0 = Runtime.getRuntime().freeMemory();
        String s = "Alexandru Tanasescu";
        long m1 = Runtime.getRuntime().freeMemory();
        System.out.println(m0 - m1);
    }

    /**
     * 104
     */
    private void simpleCase(){
        long m0 = Runtime.getRuntime().freeMemory();
        String s = new String("Alexandru Tanasescu");
        long m1 = Runtime.getRuntime().freeMemory();
        System.out.println(m0 - m1);
    }

}
