package com.yichen.casetest.utils;

import javafx.util.Pair;

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

    /**
     * 获取深度和最底层叶子节点个数
     * @param num 全二叉树总数
     * @return
     */
    public static Pair<Integer, Integer> getDepthAndBottomNum(int num){
        int count = 2;
        int depth = 1;
        while (count - 1 < num){
            depth++;
            count *= 2;
        }
        return new Pair<>(num - count/2 + 1, depth);
    }

    public static void main(String[] args) {
        System.out.println(getDepthAndBottomNum(15));
        System.out.println(getDepthAndBottomNum(6));
        System.out.println(getDepthAndBottomNum(1));
        System.out.println(getDepthAndBottomNum(2));
        System.out.println(getDepthAndBottomNum(28));
        System.out.println(getDepthAndBottomNum(50));
    }

}
