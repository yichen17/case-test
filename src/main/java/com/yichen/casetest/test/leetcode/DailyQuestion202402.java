package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.ArrayDeque;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/2/1 08:27
 * @describe
 */
class DailyQuestion202402 {

    public static void main(String[] args) {
        DailyQuestion202402 dq = new DailyQuestion202402();
        stoneGameVITest(dq);
        StringUtils.divisionLine();
        canWinNimTest(dq);
        StringUtils.divisionLine();
        maxResultTest(dq);
        StringUtils.divisionLine();
    }

    // 1686. 石子游戏 VI

    private static void stoneGameVITest(DailyQuestion202402 dq){
        //  1
        System.out.println(dq.stoneGameVI(new int[]{1,3}, new int[]{2,1}));
        //  0
        System.out.println(dq.stoneGameVI(new int[]{1,2}, new int[]{3,1}));
        //  -1
        System.out.println(dq.stoneGameVI(new int[]{2,4,3}, new int[]{1,6,7}));
    }

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        return 0;
    }

    // 1696. 跳跃游戏 VI

    private static void maxResultTest(DailyQuestion202402 dq){
        // 198
        System.out.println(dq.maxResult(new int[]{100,-1,-100,-1,100}, 2));
        // 7
        System.out.println(dq.maxResult(new int[]{1,-1,-2,4,-7,3}, 2));
        // 17
        System.out.println(dq.maxResult(new int[]{10,-5,-2,4,0,3}, 3));
        // 0
        System.out.println(dq.maxResult(new int[]{1,-5,-20,4,-1,3,-6,-3}, 2));
        System.out.println(dq.maxResult(StringUtils.randomIntArray(1000, -9999, 9999), 200));
    }

    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[len];
        for (int i=0; i<len; i++){
            // 维持有效长度
            if (!stack.isEmpty() && i - stack.peekFirst() > k){
                stack.removeFirst();
            }
            dp[i] =  nums[i] + (stack.isEmpty() ? 0 : dp[stack.peekFirst()]);
            // 维持递减
            while (!stack.isEmpty() && dp[i] > dp[stack.peekLast()]){
                stack.removeLast();
            }
            stack.addLast(i);
        }
        return dp[len-1];
    }

    // 292. Nim 游戏

    private static void canWinNimTest(DailyQuestion202402 dq){
        System.out.println(dq.canWinNim(4));
        System.out.println(dq.canWinNim(2));
        System.out.println(dq.canWinNim(1));
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

}
