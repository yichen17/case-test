package com.yichen.casetest.test;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/17 19:07
 * @describe 二进制测试
 */
public class BinaryTest {

    public static void main(String[] args) {
        System.out.println(0x007fffff);
        System.out.println(System.currentTimeMillis() & 0x003fffff);
        int i = 1;
        int times = 31;
        while(times > 0){
            if (i > 7200000){
                System.out.println(times);
                System.out.println(i + " | " + (i >> 1));
                break;
            }
            i = i << 1;
            times --;
        }
    }

}
