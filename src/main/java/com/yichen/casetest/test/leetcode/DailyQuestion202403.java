package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import com.yichen.casetest.utils.TreeUtils;

import java.util.*;

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
        myStackTest(dq);
        StringUtils.divisionLine();
        MyQueueTest(dq);
        StringUtils.divisionLine();
        countPathsTest(dq);
        StringUtils.divisionLine();
        findKOrTest(dq);
        StringUtils.divisionLine();
    }

    // 2917. 找出数组中的 K-or 值

    private static void findKOrTest(DailyQuestion202403 dq){
        // 9
        System.out.println(dq.findKOr(new int[]{7,12,9,8,9,15}, 4));
        // 0
        System.out.println(dq.findKOr(new int[]{2,12,1,11,4,5}, 6));
        // 15
        System.out.println(dq.findKOr(new int[]{10,8,5,9,11,6,8}, 1));

        System.out.println(dq.findKOr(StringUtils.randomIntArray(40, 0, 1000000000), 20));
    }

    public int findKOr(int[] nums, int k) {
        int[] f = new int[32];
        for (int num : nums){
            int i = 0;
            while (num > 0){
                f[31 - i] += num & 1;
                num = num >> 1;
                i++;
            }
        }
        int result = 0;
        for (int i=0; i<32; i++){
            result *= 2;
            if (f[i] >= k){
                result += 1;
            }
        }
        return result;
    }

    // 1976. 到达目的地的方案数

    private static void countPathsTest(DailyQuestion202403 dq){
        // 4
        System.out.println(dq.countPaths(7, StringUtils.convert2Array("[[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]")));
        // 1
        System.out.println(dq.countPaths(2, StringUtils.convert2Array("[[1,0,10]]")));
    }

    private int minTime = Integer.MAX_VALUE;
    private int result = 0;

    public int countPaths(int n, int[][] roads) {

        result = 0;
        minTime = Integer.MAX_VALUE;

        Set<Integer> visited = new HashSet<>();
        int[][] times = new int[n][n];
        List<Integer>[] link = new List[n];
        for (int i=0; i<n; i++){
            link[i] = new LinkedList<>();
        }
        for (int[] road : roads){
            int from = road[0];
            int to = road[1];
            int w = road[2];
            times[from][to] = w;
            times[to][from] = w;
            link[from].add(to);
            link[to].add(from);
        }
        visited.add(0);
        dfs(times, link, visited, 0, 0);
        return result;
    }

    private void dfs(int[][] times, List<Integer>[] link, Set<Integer> visited, int pos, int count){
        if (count > minTime){
            return;
        }
        if (pos == times.length-1){
            if (count < minTime){
                minTime = count;
                result = 1;
            }
            else {
                result ++;
            }
            return;
        }
        for (Integer i : link[pos]) {
            if (visited.add(i)){
                dfs(times, link, visited, i, count + times[i][pos]);
                visited.remove(i);
            }
        }
    }

    // 232. 用栈实现队列

    private static void MyQueueTest(DailyQuestion202403 dq){
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
        myQueue.push(3);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }

    static class MyQueue {

        Stack<Integer> left, right;

        public MyQueue() {
            left = new Stack<>();
            right = new Stack<>();
        }

        public void push(int x) {
            right.push(x);
        }

        public int pop() {
            ttt();
            return left.pop();
        }

        private void ttt(){
            if (left.isEmpty()){
                while (!right.isEmpty()){
                    left.push(right.pop());
                }
            }
        }

        public int peek() {
            ttt();
            return left.peek();
        }

        public boolean empty() {
            return left.isEmpty() && right.isEmpty();
        }
    }

    // 225. 用队列实现栈

    private static void myStackTest(DailyQuestion202403 dq){
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
        // 后续边界值判断
        System.out.println(myStack.pop());

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());

    }



    static class MyStack {

        Queue<Integer> left, right;

        public MyStack() {
            left = new LinkedList<>();
            right = new LinkedList<>();
        }

        public void push(int x) {
            right.offer(x);
        }

        public int pop() {
            ttt();
            return right.poll();
        }

        private void ttt(){
            if (right.isEmpty()){
                Queue<Integer> tmp = right;
                right = left;
                left = tmp;
            }
            while (right.size() > 1){
                left.offer(right.poll());
            }
        }

        public int top() {
            ttt();
            return right.peek();
        }

        public boolean empty() {
            return left.isEmpty() && right.isEmpty();
        }
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
