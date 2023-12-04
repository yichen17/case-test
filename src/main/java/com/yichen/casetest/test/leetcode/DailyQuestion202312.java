package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
