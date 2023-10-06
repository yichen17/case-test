package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/9/30 08:57
 * @describe 10月的每日一题
 */
@Slf4j
public class DailyQuestion202310 {

    public static void main(String[] args) {
        DailyQuestion202310 dq = new DailyQuestion202310();
        earliestFullBloomTest(dq);
        StringUtils.divisionLine();
        collectTheCoinsTest(dq);
        StringUtils.divisionLine();
        maxProfit1Test(dq);
        StringUtils.divisionLine();
        maxProfit2Test(dq);
        StringUtils.divisionLine();
        maxProfit3Test(dq);
        StringUtils.divisionLine();
        maxProfit4Test(dq);
        StringUtils.divisionLine();
        maxProfit5Test(dq);
        StringUtils.divisionLine();
        maxProfit6Test(dq);
        StringUtils.divisionLine();
    }

    // 714. 买卖股票的最佳时机含手续费

    private static void maxProfit6Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit6(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(dq.maxProfit6(new int[]{1,3,7,5,10,3}, 3));
        System.out.println(dq.maxProfit6(StringUtils.randomIntArray(1000, 1, 9999), 300));
    }

    public int maxProfit6(int[] prices, int fee) {
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        for (int i=1; i<len; i++){
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i] + prices[i] - fee);
        }
        return sell[len-1];
    }

    //309. 买卖股票的最佳时机含冷冻期

    private static void maxProfit5Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit5(new int[]{1,2,4}));
        System.out.println(dq.maxProfit5(new int[]{1,3,2,4,1,5,3,6,2,7}));
        System.out.println(dq.maxProfit5(new int[]{1,2,3,0,2}));
        System.out.println(dq.maxProfit5(new int[]{1}));
        System.out.println(dq.maxProfit5(StringUtils.randomIntArray(900, 1, 900)));
    }

    /**
     * 为啥我这不行呢？？
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length == 1){
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        // cao、我是傻逼
//        buy[1] = -Math.max(prices[0], prices[1]);
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i=2; i<len; i++){
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i] + prices[i]);
        }
        return sell[len-1];
    }

    // 188. 买卖股票的最佳时机 IV  在3的基础上改，很快

    private static void maxProfit4Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit4(2, new int[]{2,4,1}));
        System.out.println(dq.maxProfit4(2, new int[]{3,2,6,5,0,3}));
        System.out.println(dq.maxProfit4(50, StringUtils.randomIntArray(900, 1, 900)));
    }

    public int maxProfit4(int k, int[] prices) {
        int[][] dp = new int[k][2];
        for (int i=0; i<k; i++){
            dp[i][0] = -prices[0];
        }
        for (int j=1; j<prices.length; j++){
            int price = prices[j];
            dp[0][0] = Math.max(dp[0][0], -price);
            dp[0][1] = Math.max(dp[0][0] + price, dp[0][1]);
            for (int i=1; i<k; i++){
                dp[i][0] = Math.max(dp[i][0], dp[i-1][1] - price);
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + price);
            }
        }
        return dp[k-1][1];
    }

    // 123. 买卖股票的最佳时机 III

    private static void maxProfit3Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit3(new int[]{1,2,4,2,5,7,2,4,9,0}));
//        System.out.println(dq.maxProfit3(new int[]{3,3,5,0,0,3,1,4}));
//        System.out.println(dq.maxProfit3(new int[]{1,2,3,4,5}));
//        System.out.println(dq.maxProfit3(new int[]{7,6,4,3,1}));
//        System.out.println(dq.maxProfit3(StringUtils.randomIntArray(1000, 1, 9999)));
    }




    /**
     * cao，对着题解都看不太懂。。？？
     * 怎么推算出来的还有待研究哎，两者的关系是怎么得出来的。
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            // 取最大值时因为是负数
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            // 第一次卖出赚的尽可能多，第二次买入尽可能低  动态平衡，前者尽可能多，后者尽可能少
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
            System.out.printf("prices:%sbuy1:%s,sell1:%s,buy2:%s,sell2:%s%n", prices[i], buy1, sell1, buy2, sell2);
        }
        return sell2;
    }



    // 122. 买卖股票的最佳时机 II

    private static void maxProfit2Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(dq.maxProfit2(new int[]{7,6,4,3,1}));
        System.out.println(dq.maxProfit2(StringUtils.randomIntArray(1000, 1, 9999)));
    }

    public int maxProfit2(int[] prices) {
        int result = 0, min = prices[0], len = prices.length;
        for (int i=1; i<len; i++){
            if (prices[i] < prices[i-1]){
                result = result +  prices[i-1] - min;
                min = prices[i];
            }
        }
        result = result + prices[len-1] - min;
        return result;
    }

    // 121. 买卖股票的最佳时机

    private static void maxProfit1Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit1(new int[]{7,1,5,3,6,4}));
        System.out.println(dq.maxProfit1(new int[]{7,6,4,3,1}));
        System.out.println(dq.maxProfit1(StringUtils.randomIntArray(1000, 1, 9999)));
    }

    public int maxProfit1(int[] prices) {
        int max,min=0,result=0;
        Stack<Integer> stack = new Stack<>();
        for (int price : prices){
            while (!stack.isEmpty() && stack.peek() > price){
                stack.pop();
            }
            if (stack.isEmpty()){
                min = price;
            }
            else {
                result = Math.max(result, price-min);
            }
            stack.push(price);
        }
        return result;
    }

    // 2603. 收集树中金币

    private static void collectTheCoinsTest(DailyQuestion202310 dq){
        System.out.println(dq.collectTheCoins(new int[]{1,0,0,0,0,1}, StringUtils.convert2Array("[[0,1],[1,2],[2,3],[3,4],[4,5]]")));
        System.out.println(dq.collectTheCoins(new int[]{0,0,0,1,1,0,0,1}, StringUtils.convert2Array("[[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]")));
        System.out.println(dq.collectTheCoins(new int[]{0,0,0}, StringUtils.convert2Array("[[0,1],[0,2]]")));
    }

    public int collectTheCoins(int[] coins, int[][] edges) {
        if (coins.length < 3){
            return 0;
        }
        int n = coins.length,result = n;
        int i=0, p, q;
        Set<Integer>[] degree = new HashSet[n];
        for (int[] edge : edges){
            p = edge[0]; q = edge[1];
            if (degree[p] == null){
                degree[p] = new HashSet<>();
            }
            degree[p].add(q);
            if (degree[q] == null){
                degree[q] = new HashSet<>();
            }
            degree[q].add(p);
        }

        Queue<Integer> queue;
        queue = new LinkedList<>();
        for (i=0; i<n; i++){
            if (degree[i] != null && degree[i].size() == 1 && coins[i] == 0){
                queue.offer(i);
                result--;
            }
        }
        while (!queue.isEmpty()){
            p = queue.poll();
            if (degree[p] == null || degree[p].isEmpty()){
                continue;
            }
            q = degree[p].iterator().next();
            degree[p] = null;
            degree[q].remove(p);
            if (degree[q].size() == 1 && coins[q] == 0){
                queue.offer(q);
                result--;
            }
        }

        for (int t=0; t<2; t++){
            for(i=0; i<n; i++){
                if (degree[i] != null && degree[i].size() == 1){
                    queue.offer(i);
                }
            }
            result -= queue.size();
            while (!queue.isEmpty()){
                p = queue.poll();
                if (degree[p] == null || degree[p].isEmpty()){
                    continue;
                }
                q = degree[p].iterator().next();
                degree[p] = null;
                degree[q].remove(p);
            }
        }
        return result < 1 ? 0 : 2 * (result - 1);
    }

    // 2136. 全部开花的最早一天 寻找规律，推断验证规律。动手，别靠猜哎。。

    private static void earliestFullBloomTest(DailyQuestion202310 dq){
        System.out.println(dq.earliestFullBloom(new int[]{1,4,3}, new int[]{2,3,1}));
    }

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int len = plantTime.length;
        List<Integer> dp = IntStream.range(0, len).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return growTime[o2] - growTime[o1];
            }
        }).collect(Collectors.toList());
        int prev = 0, ans = 0;
        for (int i : dp){
            ans = Math.max(ans, prev + plantTime[i] + growTime[i]);
            prev += plantTime[i];
        }
        return ans;
    }



}
