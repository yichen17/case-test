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
        findPeakGridTest(dq);
        StringUtils.divisionLine();
        isAcronymTest(dq);
        StringUtils.divisionLine();
        maximumSumOfHeightsTest(dq);
        StringUtils.divisionLine();
        minStoneSumTest(dq);
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

    // 1962. 移除石子使总数最小

    private static void minStoneSumTest(DailyQuestion202312 dq){
        System.out.println(dq.minStoneSum(new int[]{5,4,9}, 2));
        System.out.println(dq.minStoneSum(new int[]{4,3,6,7}, 3));
        System.out.println(dq.minStoneSum(StringUtils.randomIntArray(1000, 1, 10000), 50000));
    }

    public int minStoneSum(int[] piles, int k) {
        int total = 0;
        int[] dp = new int[10001];
        for (int pile : piles){
            total += pile;
            dp[pile]++;
        }
        int pos = 10000;
        while (k>0 && pos != 1){
            while (k>0 && dp[pos] > 0){
                total -= pos >> 1;
                dp[pos]--;
                dp[pos - (pos >> 1)]++;
                k--;
            }
            pos--;
        }
        return total;
    }

    // 2866. 美丽塔 II
    //

    private static void maximumSumOfHeightsTest(DailyQuestion202312 dq){
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(5,3,4,1,1)));
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(6,5,3,9,2,7)));
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(3,2,5,5,2,3)));
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int len = maxHeights.size();
        long[] ascend = new long[len], descend = new long[len];
        // 左到右， 升序计算
        ascend[0] = maxHeights.get(0);
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i< len; i++){
            if (maxHeights.get(i) >= maxHeights.get(i-1)){
                ascend[i] = ascend[i-1] + maxHeights.get(i);
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.pop();
            }
            long gap = stack.isEmpty() ? i+1 : i - stack.peek();
            long preVal = stack.isEmpty() ? 0L : ascend[stack.peek()];
            ascend[i] = preVal + gap * maxHeights.get(i);
            stack.push(i);
        }
        // 右到左 升序计算
        long result = ascend[len-1];
        stack = new Stack<>();
        stack.add(len-1);
        descend[len-1] = maxHeights.get(len-1);
        for (int i=len-2; i>=0; i--){
            result = Math.max(result, ascend[i] + descend[i+1]);
            if (maxHeights.get(i+1) <= maxHeights.get(i)){
                descend[i] = descend[i+1] + maxHeights.get(i);
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.pop();
            }
            long gap = stack.isEmpty() ? len-i : stack.peek()-i;
            long preVal = stack.isEmpty() ? 0L : descend[stack.peek()];
            descend[i] = preVal + gap * maxHeights.get(i);
            stack.push(i);
        }
        return result;
    }

    // 2828. 判别首字母缩略词

    private static void isAcronymTest(DailyQuestion202312 dq){
        System.out.println(dq.isAcronym(Arrays.asList("alice","bob","charlie"), "abc"));
        System.out.println(dq.isAcronym(Arrays.asList("an","apple"), "a"));
        System.out.println(dq.isAcronym(Arrays.asList("never","gonna","give","up","on","you"), "ngguoy"));
    }

    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()){
            return false;
        }
        for (int i=0; i<s.length(); i++){
            if (words.get(i).charAt(0) != s.charAt(i)){
                return false;
            }
        }
        return true;
    }

    // 1901. 寻找峰值 II

    private static void findPeakGridTest(DailyQuestion202312 dq){
        StringUtils.printIntArray(dq.findPeakGrid(StringUtils.convert2Array("[[49,58,49,11,88,85,70,18,84,45,16,23,69],[51,68,17,94,29,1,97,3,39,60,87,93,70],[55,69,49,30,32,59,45,20,36,25,93,98,15],[5,85,54,79,99,3,31,27,9,13,77,5,58],[77,87,91,7,32,95,6,52,57,30,70,28,97],[100,8,18,65,38,30,94,74,41,74,77,99,87],[42,31,39,19,85,67,29,53,39,36,8,72,76],[95,23,81,87,55,72,64,7,45,83,86,49,68],[41,90,54,53,20,39,49,24,97,69,61,31,29],[39,91,19,60,8,72,53,54,72,68,18,50,32],[33,27,43,83,11,58,48,12,69,93,25,7,35],[49,87,78,6,10,25,98,76,93,86,72,28,62],[27,43,39,2,28,95,16,96,97,62,32,90,78],[91,27,51,31,71,42,100,37,49,60,69,84,46],[40,34,47,66,64,60,13,93,61,98,41,27,48]]")));
        StringUtils.printIntArray(dq.findPeakGrid(StringUtils.convert2Array("[[47,30,35,8,25],[6,36,19,41,40],[24,37,13,46,5],[3,43,15,50,19],[6,15,7,25,18]]")));
        StringUtils.printIntArray(dq.findPeakGrid(StringUtils.convert2Array("[[1,4],[3,2]]")));
        StringUtils.printIntArray(dq.findPeakGrid(StringUtils.convert2Array("[[10,20,15],[21,30,14],[7,16,32]]")));
    }

    public int[] findPeakGrid(int[][] mat) {
        int left = 0, right = mat[0].length-1, mid, colMax, row=mat.length;
        while (left <= right){
            mid = (left + right) >> 1;
            colMax = 0;
            for (int i=1; i<row; i++){
                if (mat[colMax][mid] < mat[i][mid]){
                    colMax = i;
                }
            }
            if ((mid-1<0 || mat[colMax][mid] > mat[colMax][mid-1]) && (mid+1>=mat[0].length || mat[colMax][mid] > mat[colMax][mid+1])){
                return new int[]{colMax, mid};
            }
            else if ((mid-1<0 || mat[colMax][mid] > mat[colMax][mid-1])){
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }
        return null;
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
