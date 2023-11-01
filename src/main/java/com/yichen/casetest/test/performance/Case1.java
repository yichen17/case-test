package com.yichen.casetest.test.performance;

import com.yichen.casetest.utils.StringUtils;

import java.util.Random;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/26 20:37
 * @describe
 */
public class Case1 {

    private static final int[] data;
    private static final Random random = new Random();

    private static String temp;

    static {
        data = StringUtils.randomIntArray(10000, 0, 1000000);
    }


    /**
     * 自闭了
     * @param args
     */
    public static void main(String[] args) {
//        singleCheck();
        multiplyCheck();


    }

    private static void singleCheck(){
        int i = random.nextInt(10000), j = random.nextInt(10000);
        System.out.println(caseA(i,j));
        System.gc();
        System.out.println(caseB(i,j));
    }

    private static void multiplyCheck(){
        long a = 0L, b = 0L;
        for (int t=0; t<100; t++){
            int i = random.nextInt(10000), j = random.nextInt(10000);
            System.gc();
            a += caseA(i, j);
            System.gc();
            b += caseB(i, j);
        }
        System.out.printf("A:%s  %nB:%s %n", a, b);
    }

    private static long caseA(int a, int b){
        long start = System.nanoTime();
        for (int i=0; i<1000000; i++){
            temp = "" + data[a] + data[b];
        }
        return System.nanoTime() - start;
    }

    private static long caseB(int a, int b){
        long start = System.nanoTime();
        for (int i=0; i<1000000; i++){
            temp = Integer.toString(a) + Integer.toString(b);
        }
        return System.nanoTime() - start;
    }


}
