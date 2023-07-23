package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/7/18 20:55
 * @describe 动态规划相关测试
 */
@Slf4j
public class DynamicProgram {

    public static void main(String[] args) {
        DynamicProgram dy = new DynamicProgram();
        System.out.println(dy.rob(new int[]{1,2,3,1}));
        StringUtils.divisionLine();
        System.out.println(dy.lengthOfLongestFibonacciSubsequence(new int[]{1,2,3,4,5,6,7,8}));
        StringUtils.divisionLine();
        System.out.println(dy.minCut("abcabaa"));
        StringUtils.divisionLine();
        System.out.println(dy.isInterleave("db", "b", "cbb"));
        StringUtils.divisionLine();
        System.out.println(dy.numDistinct("rabbbit", "rabbit"));
        StringUtils.divisionLine();
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Collections.singletonList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));
        System.out.println(dy.minimumTotal(list));
        StringUtils.divisionLine();
    }


//    最少的硬币数目



    // 目标和




    // 分割等和子集




    // 三角形中最小路径之和

    public int minimumTotal(List<List<Integer>> triangle) {
        int rl = triangle.size();
        int[] bp = new int[rl];
        Arrays.fill(bp, 100001);
        int result = triangle.get(0).get(0);
        bp[0] = 0;
        for (int i=0; i<rl; i++){
            result = 100001;
            for (int j=i; j>0; j--){
                bp[j] = Math.min(bp[j], bp[j-1]) + triangle.get(i).get(j);
                result = Math.min(result, bp[j]);
            }
            bp[0] += triangle.get(i).get(0);
            result = Math.min(result, bp[0]);
        }
        return result;
    }

    // 子序列的数目

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()){
            return 0;
        }
        int len1 = s.length(), len2 = t.length();
        int[][] bp = new int[len2+1][len1+1];
        for (int i=0; i<len1+1; i++){
            bp[0][i] = 1;
        }
        for(int i=0; i<len2; i++){
            for(int j=i; j<len1; j++){
                if (t.charAt(i) == s.charAt(j)){
                    bp[i+1][j+1] = bp[i][j];
                }
                bp[i+1][j+1] += bp[i+1][j];
            }
        }
        return bp[len2][len1];
    }

    //字符串交织

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){
            return false;
        }
        if (s3.length() == 0){
            return true;
        }
        if (s1.length() < s2.length()){
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] bp = new boolean[len1+1][len2+1];
        for (int i=0; i<len2; i++){
            bp[0][i+1] = (s2.charAt(i) == s3.charAt(i)) & (i == 0 || bp[0][i]);
        }
        for (int i=0; i<len1; i++){
            bp[i+1][0] = (s1.charAt(i) == s3.charAt(i)) & (bp[i][0] || i ==0);
            for (int j=0; j<len2; j++){
                boolean result = false;
                if (s2.charAt(j) == s3.charAt(i+j+1)){
                    result |= bp[i+1][j];
                }
                if (s1.charAt(i) == s3.charAt(i+j+1)){
                    result |= bp[i][j+1];
                }
                bp[i+1][j+1] = result;
            }
        }
        return bp[len1][len2];
    }

    // 最少回文分割

    public int minCut(String s) {
        int len = s.length();
        int[] bp = new int[len];
        for (int i=1; i<len; i++){
            if (palindrome(s, 0, i)){
                bp[i] = 0;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j=1; j<=i; j++){
                if (palindrome(s, j, i)){
                    min = Math.min(min, 1 + bp[j-1]);
                }
            }
            bp[i] = min;
        }
        return bp[len-1];
    }


    private boolean palindrome(String s, int l, int r){
        if (l > r){
            return false;
        }
        while (l < r && s.charAt(l) == s.charAt(r)){
            l++;
            r--;
        }
        return l >= r;
    }

    // 最长的斐波那契子序列的长度

    public int lengthOfLongestFibonacciSubsequence(int[] arr){
        Map<Integer, Integer> maps = new HashMap<>();
        int len = arr.length, max = 2;
        int[][] dp = new int[len][len];
        for(int i=0; i<len; i++){
            dp[0][i] = 2;
            maps.put(arr[i], i);
        }
        for (int i=1; i<len; i++){
            for(int j=i; j<len; j++){
                int pos = maps.getOrDefault(arr[j] - arr[i], -1);
                if (pos == -1 || pos >= i){
                    dp[i][j] = 2;
                }
                else {
                    dp[i][j] = dp[pos][i] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max == 2 ? 0 : max;
    }

    // 环形房屋偷盗

    public int rob(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int a = getMax(nums, 0, nums.length-2);
        int b = getMax(nums, 1, nums.length-1);
        return Math.max(a, b);
    }

    private int getMax(int[] nums, int start, int end){
        int[] dp = new int[2];
        dp[0] = nums[start];
        if (start < end){
            dp[1] = Math.max(nums[start], nums[start+1]);
        }
        for(int i=start+2; i<=end; i++){
            int j = i - start;
            dp[j%2] = Math.max(dp[(j-1)%2], dp[(j-2)%2]+nums[i]);
        }
        return dp[(end - start) % 2];
    }

}
