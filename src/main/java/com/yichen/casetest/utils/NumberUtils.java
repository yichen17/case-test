package com.yichen.casetest.utils;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/26 21:04
 * @describe 数值工具类
 */
public class NumberUtils {

    /**
     * 获取全二叉树深度
     * @param num 全二叉树个数
     * @return
     */
    public static int getDepth(int num){
        int i = 2, depth = 1;
        while(num > i -1){
            depth++;
            i *= 2;
        }
        return depth;
    }

    public static void main(String[] args) {
        System.out.println(getDepth(11));
        System.out.println(getDepth(31));
        System.out.println(getDepth(1));
    }

}
