package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import javafx.util.Pair;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/12/2 19:22
 * @describe
 */
public class DailyQuestion202312 {

    public static void main(String[] args) {
        DailyQuestion202312 dq = new DailyQuestion202312();
        carPoolingTest(dq);
        StringUtils.divisionLine();
        maxScoreTest(dq);
        StringUtils.divisionLine();
        bstToGstTest(dq);
        StringUtils.divisionLine();
        minReorderTest(dq);
        StringUtils.divisionLine();
        climbStairsTest(dq);
        StringUtils.divisionLine();
        makeSmallestPalindromeTest(dq);
        StringUtils.divisionLine();
        findPeakElementTest(dq);
        StringUtils.divisionLine();
        minCostClimbingStairsTest(dq);
        StringUtils.divisionLine();
    }

    // 1631. 最小体力消耗路径



    public int minimumEffortPath(int[][] heights) {
        return 0;
    }

    // 2276. 统计区间中的整数数目
    // 查询加插入场景   点查询-数组二分法，BFS   插入-双向链表     批量变更逻辑
    // 维持线段，新插入的线段：1、与原有线段合并(大范围合并，双端蔓延)  2、独立的线段插入
    // 值查询 找小于它的节点的范围是否包含它   TreeMap<>  key为下限、value为上限

    private static class CountIntervals {



        public CountIntervals() {

        }

        public void add(int left, int right) {

        }

        public int count() {
            return 0;
        }
    }

    // 746. 使用最小花费爬楼梯

    private static void minCostClimbingStairsTest(DailyQuestion202312 dq){
        System.out.println(dq.minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println(dq.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0]; dp[1] = cost[1];
        for (int i=2; i<dp.length; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[dp.length-1], dp[dp.length-2]);
    }

    // 162. 寻找峰值

    private static void findPeakElementTest(DailyQuestion202312 dq){
        System.out.println(dq.findPeakElement(new int[]{1,2,3,1}));
        System.out.println(dq.findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println(dq.findPeakElement(new int[]{1}));
        System.out.println(dq.findPeakElement(new int[]{1,2}));
        System.out.println(dq.findPeakElement(new int[]{2,1}));
        System.out.println(dq.findPeakElement(new int[]{1,2,3}));
        System.out.println(dq.findPeakElement(new int[]{3,2,1}));
        System.out.println(dq.findPeakElement(new int[]{1,3,2}));
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1, mid;
        while (left < right){
            mid = (left + right) >> 1;
            // 当前为峰值
            if ((mid-1<0 || nums[mid] > nums[mid-1]) && (mid+1 >= nums.length || nums[mid] > nums[mid+1])){
                return mid;
            }
            // 递增
            else if (mid-1 <0 || nums[mid] > nums[mid-1]){
                left = mid+1;
            }
            // 递减
            else {
                right = mid-1;
            }
        }
        return left;
    }

    // 2697. 字典序最小回文串

    private static void makeSmallestPalindromeTest(DailyQuestion202312 dq){
        System.out.println(dq.makeSmallestPalindrome("egcfe"));
        System.out.println(dq.makeSmallestPalindrome("abcd"));
        System.out.println(dq.makeSmallestPalindrome("seven"));
    }

    public String makeSmallestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        for (int i=0,j=charArray.length-1; i<j; i++,j--){
            if (charArray[i] == charArray[j]){
                continue;
            }
            else if (charArray[i] > charArray[j]){
                charArray[i] = charArray[j];
            }
            else {
                charArray[j] = charArray[i];
            }
        }
        return new String(charArray);
    }



    // 70. 爬楼梯

    private static void climbStairsTest(DailyQuestion202312 dq){
        System.out.println(dq.climbStairs(2));
        System.out.println(dq.climbStairs(3));
        System.out.println(dq.climbStairs(45));
    }

    public int climbStairs(int n) {
        if (n <=2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 1466. 重新规划路线

    private static void minReorderTest(DailyQuestion202312 dq){
        System.out.println(dq.minReorder(6, StringUtils.convert2Array("[[0,1],[1,3],[2,3],[4,0],[4,5]]")));
        System.out.println(dq.minReorder(5, StringUtils.convert2Array("[[1,0],[1,2],[3,2],[3,4]]")));
        System.out.println(dq.minReorder(3, StringUtils.convert2Array("[[1,0],[2,0]]")));
    }

    public int minReorder(int n, int[][] connections) {
        Set<Integer>[] inDirs = new Set[n];
        Set<Integer>[] outDirs = new Set[n];
        for (int i=0; i<n; i++){
            inDirs[i] = new HashSet<>();
            outDirs[i] = new HashSet<>();
        }
        for(int[] connection : connections){
            inDirs[connection[1]].add(connection[0]);
            outDirs[connection[0]].add(connection[1]);
        }
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            int item = queue.poll();
            // 正常指向
            for (int c : inDirs[item]){
                queue.offer(c);
                outDirs[c].remove(item);
            }
            // 需要改变指向的
            for (int c : outDirs[item]){
                result++;
                queue.offer(c);
                inDirs[c].remove(item);
            }
        }
        return result;
    }

    // 2477. 到达首都的最少油耗

    public long minimumFuelCost(int[][] roads, int seats) {

        long result = 0L;
        return result;
    }



    // 1038. 从二叉搜索树到更大和树

    private static void bstToGstTest(DailyQuestion202312 dq){
        // [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
        TreeNode.printTree(dq.bstToGst(TreeNode.buildTree(new Integer[]{4,1,6,0,2,5,7,null,null,null,3,null,null,null,8})));
        // [1,null,1]
        TreeNode.printTree(dq.bstToGst(TreeNode.buildTree(new Integer[]{0,null,1})));
    }

    public TreeNode bstToGst(TreeNode root) {
        this.dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode root, int n){
        if (root == null){
            return n;
        }
        root.val += this.dfs(root.right, n);
        return this.dfs(root.left, root.val);
    }

    // 1423. 可获得的最大点数

    private static void maxScoreTest(DailyQuestion202312 dq){
        System.out.println(dq.maxScore(new int[]{1,2,3,4,5,6,1}, 3));
        System.out.println(dq.maxScore(new int[]{2,2,2}, 2));
        System.out.println(dq.maxScore(new int[]{9,7,7,9,7,7,9}, 7));
        System.out.println(dq.maxScore(new int[]{1,1000,1}, 1));
        System.out.println(dq.maxScore(new int[]{1,79,80,1,1,1,200,1}, 3));
    }

    public int maxScore(int[] cardPoints, int k) {
        int total = 0, len = cardPoints.length;
        int[] preSum = new int[len+1];
        for (int i=0; i<len; i++){
            total += cardPoints[i];
            preSum[i+1] = total;
        }
        int result = 0;
        for (int i=0; i<=k; i++){
            result = Math.max(result, total - preSum[len-k+i] + preSum[i]);
        }
        return result;
    }

    // 1094. 拼车

    private static void carPoolingTest(DailyQuestion202312 dq){
        System.out.println(dq.carPooling(StringUtils.convert2Array("[[4,3,4],[3,2,4],[1,8,9],[7,2,5]]"), 14));
        System.out.println(dq.carPooling(StringUtils.convert2Array("[[2,1,5],[3,5,7]]"), 3));
        System.out.println(dq.carPooling(StringUtils.convert2Array("[[2,1,5],[3,3,7]]"), 4));
        System.out.println(dq.carPooling(StringUtils.convert2Array("[[3,3,7],[2,1,5]]"), 5));
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int n = capacity;
        Arrays.sort(trips, Comparator.comparingInt(p -> p[1]));
        // pair key为下车点 value为下车人数
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        // 逐项放入比对
        int start = 0;
        for (int i=0; i<trips.length; i++){
            // 更新地点
            start = trips[i][1];
            //  下车乘客
            while (!queue.isEmpty() && queue.peek().getKey() <= start){
                n += queue.poll().getValue();
            }
            if (n < trips[i][0]){
                return false;
            }
            n -= trips[i][0];
            queue.offer(new Pair<>(trips[i][2], trips[i][0]));
        }
        return true;
    }

}
