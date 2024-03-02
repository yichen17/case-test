package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import com.yichen.casetest.utils.TreeUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/1 08:17
 * @describe
 */
public class DailyQuestion202403 {

    public static void main(String[] args) {
        DailyQuestion202403 dq = new DailyQuestion202403();
        validPartitionTest(dq);
        StringUtils.divisionLine();
        reachableNodesTest(dq);
        StringUtils.divisionLine();
    }

    // 2368. 受限条件下可到达节点的数目

    private static void reachableNodesTest(DailyQuestion202403 dq) {
        System.out.println(dq.reachableNodes(100, TreeUtils.buildNoDirectTree(100), StringUtils.randomNoRepeat(20, 0, 99)));
        // 4
        System.out.println(dq.reachableNodes(7, StringUtils.convert2Array("[[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]]"), new int[]{4, 5}));
        // 3
        System.out.println(dq.reachableNodes(7, StringUtils.convert2Array("[[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]]"), new int[]{4, 2, 1}));


    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer>[] link = new Set[n];
        for (int[] edge : edges){
            if (link[edge[0]] == null){
                link[edge[0]] = new HashSet<>();
            }
            link[edge[0]].add(edge[1]);
            if (link[edge[1]] == null){
                link[edge[1]] = new HashSet<>();
            }
            link[edge[1]].add(edge[0]);
        }
        Set<Integer> excludeNodes = new HashSet<>();
        for (int i : restricted){
            excludeNodes.add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int result = 0;
        while (!queue.isEmpty()){
            result ++;
            int item = queue.poll();
            if (link[item] == null){
                continue;
            }
            for (int i : link[item]){
                if (excludeNodes.contains(i)){
                    continue;
                }
                // 可以加入的节点
                queue.offer(i);
                // 移除链接，防止死循环
                link[i].remove(item);
            }
        }
        return result;
    }

    // 2369. 检查数组是否存在有效划分

    private static void validPartitionTest(DailyQuestion202403 dq) {
        // false
        System.out.println(dq.validPartition(new int[]{993335, 993336, 993337, 993338, 993339, 993340, 993341}));
        // true
        System.out.println(dq.validPartition(new int[]{4, 4, 4, 5, 6}));
        // false
        System.out.println(dq.validPartition(new int[]{1, 1, 1, 2}));
    }

    public boolean validPartition(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i <= len; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 2] && nums[i - 2] == nums[i - 1];
            }
            if (i >= 3) {
                dp[i] = dp[i] || dp[i - 3] && (nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1 || nums[i - 3] == nums[i - 1] && nums[i - 3] == nums[i - 2]);
            }
        }
        return dp[len];
    }


}
