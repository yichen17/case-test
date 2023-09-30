package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
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
