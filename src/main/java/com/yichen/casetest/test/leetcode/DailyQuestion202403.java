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
        divisibilityArrayTest(dq);
        StringUtils.divisionLine();
        minimumPossibleSumTest(dq);
        StringUtils.divisionLine();
        kSumTest(dq);
        StringUtils.divisionLine();
        getHintTest(dq);
        StringUtils.divisionLine();
        capitalizeTitleTest(dq);
        StringUtils.divisionLine();
        maximumOddBinaryNumberTest(dq);
        StringUtils.divisionLine();
        maxArrayValueTest(dq);
        StringUtils.divisionLine();
        minNonZeroProductTest(dq);
        StringUtils.divisionLine();
        frequencyTrackerTest();
        StringUtils.divisionLine();
        distinctIntegersTest(dq);
        StringUtils.divisionLine();
        minimumVisitedCellsTest(dq);
        StringUtils.divisionLine();
        coinChangeTest(dq);
        StringUtils.divisionLine();
        changeTest(dq);
        StringUtils.divisionLine();
    }



    // 2386. 找出数组的第 K 大和

    private static void kSumTest(DailyQuestion202403 dq){
        System.out.println(dq.kSum(StringUtils.randomIntArray(9999, -100000, 100000), 1888));
        // 2
        System.out.println(dq.kSum(new int[]{2,4,-2}, 5));
        // 10
        System.out.println(dq.kSum(new int[]{1,-2,3,4,-10,12}, 16));


    }

    public long kSum(int[] nums, int k) {
         return -1L;
    }

    // 518. 零钱兑换 II

    private static void changeTest(DailyQuestion202403 dq){
        // 4
        System.out.println(dq.change(5, new int[]{1,2,5}));
        // 0
        System.out.println(dq.change(3, new int[]{2}));
        // 1
        System.out.println(dq.change(10, new int[]{10}));

        System.out.println(dq.change(5000, StringUtils.randomNoRepeat(10, 1, 500)));
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins){
            for (int i=coin; i<=amount; i++){
                dp[i] = dp[i] + dp[i-coin];
            }
        }
        return dp[amount];
    }

    // 322. 零钱兑换

    private static void coinChangeTest(DailyQuestion202403 dq){
        // 3
        System.out.println(dq.coinChange(new int[]{1,2,5}, 11));
        // -1
        System.out.println(dq.coinChange(new int[]{2}, 3));
        // 0
        System.out.println(dq.coinChange(new int[]{1}, 0));
        // 105
        System.out.println(dq.coinChange(new int[]{12,93,31,24,55,90,15,8,56,96,60,53}, 10000));

        System.out.println(dq.coinChange(StringUtils.randomIntArray(12, 1, 100), 10000));
    }


    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, 200000);
        for (int coin : coins){
            if (coin > amount){
                continue;
            }
            dp[coin] = 1;
        }
        for (int i=1; i<amount; i++){
            for (int coin : coins){
                if (dp[i] == 0){
                    continue;
                }
                if (coin > amount || i + coin > amount){
                    continue;
                }
                dp[i+coin] = Math.min(dp[i+coin], dp[i]+1);
            }
        }
        return dp[amount] == 200000 ? -1 : dp[amount];
    }








    // 2617. 网格图中最少访问的格子数

    private static void minimumVisitedCellsTest(DailyQuestion202403 dq){
        // 4
        System.out.println(dq.minimumVisitedCells(StringUtils.convert2Array("[[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]")));
        //3
        System.out.println(dq.minimumVisitedCells(StringUtils.convert2Array("[[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]")));
        // -1
        System.out.println(dq.minimumVisitedCells(StringUtils.convert2Array("[[2,1,0],[1,0,0]]")));
        System.out.println(dq.minimumVisitedCells(StringUtils.constructTwoDimensionArray(1000, 10, 1, 10)));
    }

    /**
     * dp 存储任意一点到右下角之间的最小移动格子数
     * @param grid
     * @return
     */
    public int minimumVisitedCells(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                dp[i][j] = 100001;
            }
        }
        dp[row-1][col-1]=1;
        for (int i=row-1; i>=0; i--){
            for (int j=col-1; j>=0; j--){
                // 向右搜索
                for (int k=1; k<=grid[i][j] && j+k<col; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k+j] + 1);
                }
                // 向下搜索
                for (int k=1; k<=grid[i][j] && i+k<row; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[k+i][j] + 1);
                }
            }
        }
        return dp[0][0] == 100001 ? -1 : dp[0][0];
    }

    // 2549. 统计桌面上的不同数字


    private static void distinctIntegersTest(DailyQuestion202403 dq){
        // 4
        System.out.println(dq.distinctIntegers(5));
        // 2
        System.out.println(dq.distinctIntegers(3));
    }

    public int distinctIntegers(int n) {
        Set<Integer> valid = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        valid.add(n);
        int p = n, q;
        while (!queue.isEmpty()){
            int len = queue.size();
            q = Integer.MIN_VALUE;
            while (len > 0){
                int item = queue.poll();
                for (int i=1; i<p; i++){
                    if ((item % i == 1) && valid.add(i)){
                        queue.offer(i);
                        q = Math.max(q, i);
                    }
                }
                len --;
            }
            p = q;
        }
        return valid.size();
    }

    // 2671. 频率跟踪器

    private static void frequencyTrackerTest(){
        // [null, null, null, true]
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        frequencyTracker.add(3);
        frequencyTracker.add(3);
        System.out.println(frequencyTracker.hasFrequency(2));
        StringUtils.divisionLine();
        // [null, null, null, false]
        frequencyTracker = new FrequencyTracker();
        frequencyTracker.add(1);
        frequencyTracker.deleteOne(1);
        System.out.println(frequencyTracker.hasFrequency(1));
        StringUtils.divisionLine();
        // [null, false, null, true]
        frequencyTracker = new FrequencyTracker();
        System.out.println(frequencyTracker.hasFrequency(2));
        frequencyTracker.add(3);
        System.out.println(frequencyTracker.hasFrequency(1));
    }

    static class FrequencyTracker {

        private Map<Integer, Integer> countMap, timesMap;

        public FrequencyTracker() {
            // 和总数  次数
            countMap = new HashMap<>();
            // 值  次数
            timesMap = new HashMap<>();
        }

        public void add(int number) {
            Integer val;
            if ((val = timesMap.get(number)) != null && val > 0) {
                countMap.put(val, countMap.get(val) - 1);
            }
            timesMap.put(number, timesMap.getOrDefault(number, 0) + 1);
            val = timesMap.get(number);
            countMap.put(val, countMap.getOrDefault(val, 0) + 1);
        }

        public void deleteOne(int number) {
            Integer val;
            if ((val = timesMap.get(number)) != null && val > 0){
                countMap.put(val, countMap.get(val) - 1);
            }
            if (val == null || val < 1){
                return;
            }
            timesMap.put(number, timesMap.getOrDefault(number, 0) -1);
            val = timesMap.get(number);
            countMap.put(val, countMap.getOrDefault(val, 0) + 1);
        }

        public boolean hasFrequency(int frequency) {
            return countMap.get(frequency) != null && countMap.get(frequency) > 0;
        }
    }

    // 1969. 数组元素的最小非零乘积

    private static void minNonZeroProductTest(DailyQuestion202403 dq){
        // 1
        System.out.println(dq.minNonZeroProduct(1));
        // 6
        System.out.println(dq.minNonZeroProduct(2));
        // 1512
        System.out.println(dq.minNonZeroProduct(3));
    }

    /**
     * 一的个数为 p * 2的(p-1)次幂
     * 计算快速幂。。。
     * @param p
     * @return
     */
    public int minNonZeroProduct(int p) {
        long tmp=0, times;
        int mod = 1_000_000_007;
        for (int i=0; i<p; i++){
            tmp = (tmp << 1) | 1;
        }
        times = tmp/2;
        return (int)(quickPower((tmp-1)%mod, times, mod) * (tmp % mod) % mod);
    }

    private long quickPower(long n, long times, int mod){
        long result = 1;
        while (times > 0){
            if ((times & 1) == 1){
                result = result * n % mod;
            }
            n = n * n % mod;
            times >>= 1;
        }
        return result;
    }

    // 2789. 合并后数组中的最大元素

    private static void maxArrayValueTest(DailyQuestion202403 dq){
        // 21
        System.out.println(dq.maxArrayValue(new int[]{2,3,7,9,3}));
        // 11
        System.out.println(dq.maxArrayValue(new int[]{5,3,3}));

        System.out.println(dq.maxArrayValue(StringUtils.randomIntArray(9999, 1, 100000)));
    }

    public long maxArrayValue(int[] nums) {
        long result = nums[nums.length - 1];
        for (int i=nums.length-2; i>=0; i--){
            if (result >= nums[i]){
                result += nums[i];
                continue;
            }
            result = nums[i];
        }
        return result;
    }

    // 2864. 最大二进制奇数

    private static void maximumOddBinaryNumberTest(DailyQuestion202403 dq){
        // 001
        System.out.println(dq.maximumOddBinaryNumber("010"));
        // 1001
        System.out.println(dq.maximumOddBinaryNumber("0101"));
    }

    public String maximumOddBinaryNumber(String s) {
        if (s.length() < 2){
            return s;
        }
        char[] charArray = s.toCharArray();
        int left = 0, right = charArray.length - 1;
        while (left < right){
            while (left < right && charArray[left] == '1'){
                left ++;
            }
            while (left < right && charArray[right] == '0'){
                right --;
            }
            if (left >= right){
                break;
            }
            char tmp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = tmp;
        }
        char tmp = charArray[left-1];
        charArray[left-1] = charArray[charArray.length-1];
        charArray[charArray.length-1] = tmp;
        return new String(charArray);
    }

    // 1261. 在受污染的二叉树中查找元素



    static class FindElements {

        private Set<Integer> set;

        public FindElements(TreeNode root) {
            set = new HashSet<>();
            dfs(root, 0);
        }

        private void dfs(TreeNode root, int pre){
            if (root == null){
                return;
            }
            set.add(pre);
            if (root.left != null){
                dfs(root.left, pre * 2 + 1);
            }
            if (root.right != null){
                dfs(root.right, pre * 2 + 2);
            }
        }

        public boolean find(int target) {
            return set.contains(target);
        }
    }

    // 2129. 将标题首字母大写

    private static void capitalizeTitleTest(DailyQuestion202403 dq){
        // Capitalize The Title
        System.out.println(dq.capitalizeTitle("capiTalIze tHe titLe"));
        // First Letter of Each Word
        System.out.println(dq.capitalizeTitle("First leTTeR of EACH Word"));
        // i lOve leetcode
        System.out.println(dq.capitalizeTitle("i Love Leetcode"));
    }

    public String capitalizeTitle(String title) {
        String[] items = title.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<items.length; i++){
            if (items[i].length() < 3){
                sb.append(items[i].toLowerCase());
            }
            else {
                String item = items[i].toLowerCase();
                sb.append((char) (item.charAt(0) - 'a' + 'A'))
                        .append(item.substring(1));
            }
            if (i != items.length -1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 299. 猜数字游戏

    private static void getHintTest(DailyQuestion202403 dq){
        // 1A3B
        System.out.println(dq.getHint("1807", "7810"));
        // 1A1B
        System.out.println(dq.getHint("1123", "0111"));

        System.out.println(dq.getHint(StringUtils.randomArrayInSpecificCharacters("0123456789", 1, 900)[0],
                StringUtils.randomArrayInSpecificCharacters("0123456789", 1, 900)[0]));
    }

    public String getHint(String secret, String guess) {
        int match = 0, contains = 0;
        int[] times = new int[10];
        for (int i=0; i<secret.length(); i++){
            if (secret.charAt(i) == guess.charAt(i)){
                match ++;
                continue;
            }
            if (times[guess.charAt(i) - '0'] > 0){
                contains ++;
            }
            times[guess.charAt(i) - '0'] --;
            if (times[secret.charAt(i) - '0'] < 0){
                contains ++;
            }
            times[secret.charAt(i) - '0'] ++;
        }
        return match + "A" + contains + "B";
    }



    // 2834. 找出美丽数组的最小和

    private static void minimumPossibleSumTest(DailyQuestion202403 dq){
        System.out.println(dq.minimumPossibleSum(1000000000, 1000000000));
        // 4
        System.out.println(dq.minimumPossibleSum(2, 3));
        // 8
        System.out.println(dq.minimumPossibleSum(3, 3));
        // 1
        System.out.println(dq.minimumPossibleSum(1, 1));

        System.out.println(dq.minimumPossibleSum(10000000, 10000000));
    }

    /**
     * 切分  n小于target/2，直接算[1,target/2],不然后拼 【target,target+剩余]
     * @param n
     * @param target
     * @return
     */
    public int minimumPossibleSum(int n, int target) {
        int mod = 1000000007;
        long tt = target / 2;
        if (n <= tt) {
            long result = (long) (1 + n) * n / 2;
            return (int) (result % mod);
        }
        long resultA = (tt + 1) * tt / 2;
        long resultB = ((((long) target + target + n) - tt - 1) % mod) * (n - tt) / 2;
        return (int) ((resultA % mod + resultB % mod) % mod);
    }

    // 2575. 找出字符串的可整除数组

    private static void divisibilityArrayTest(DailyQuestion202403 dq){
        // [1,1,0,0,0,1,1,0,0]
        StringUtils.printIntArray(dq.divisibilityArray("998244353", 3));
        // [0,1,0,1]
        StringUtils.printIntArray(dq.divisibilityArray("1010", 10));
    }

    public int[] divisibilityArray(String word, int m) {
        int len = word.length();
        int[] result = new int[len];
        long ttt = 0;
        for (int i=0; i<len; i++){
            ttt = ttt * 10 + word.charAt(i) - '0';
            if (ttt % m == 0){
                result[i] = 1;
            }
            ttt = ttt % m;
        }
        return result;
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
