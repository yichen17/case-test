package com.yichen.casetest.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/19 14:41
 * @describe 数学工具类
 */
@Slf4j
public class MathUtils {

    /**
     * 计算组合结果
     * @param n 总数量
     * @param k 选择的数量
     * @return 组合结果
     */
    public static int combination(int n, int k) {
        int a = 1, b = 1;
        if(k > n / 2){
            k = n - k;
        }
        for(int i = 0; i < k; i++){
            a *= n - i;
            b *= k - i;
        }
        return a / b;
    }

    /**
     * 计算从n个元素中选择r个元素进行排列的结果
     * @param n 元素总数
     * @param r 需要选择的元素数量
     * @return 排列结果
     */
    public static int permutation(int n, int r) {
        if (n < r) {
            throw new IllegalArgumentException("n must be greater than or equal to r");
        }

        if (r == 0) {
            return 1;
        }

        return n * permutation(n - 1, r - 1);
    }

    /**
     * 自己实现的平方根比库的垃圾。。。
     * @param num
     * @return
     */
    public static int sqrtFloor(int num) {
        if (num < 0){
            return -1;
        }

        if (num == 0 || num == 1) {
            return num;
        }

        int left = 1;
        int right = num;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (mid <= num / mid) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }


    /**
     * 获取所有bit组合数
     * @param limit 总共bit数
     * @param select 选取的bit数
     * @return
     */
    public static List<Integer> getAllBitCombination(int limit, int select){
        if (limit >= 31 || limit <= 0 || select <= 0 || limit < select){
            return new LinkedList<>();
        }
        List<Integer> result = new LinkedList<>();
        int val = (1 << select) - 1, r, t;
        int max = 1 << limit;
        while (val < max){
            // 将符合条件的数加入结果集
            result.add(val);
            // 获取val中最右边的1
            r = val & -val;
            // 最右边的1进位左移，替换左边的第一个0槽位
            t = val + r;
            val = (((val ^ t) >> 2) / r) | t;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permutation(4,2));
        StringUtils.divisionLine();
        System.out.println(getAllBitCombination(4,2));;
    }

}
