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
