package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.MathUtils;
import com.yichen.casetest.utils.StringUtils;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/1/1 15:07
 * @describe
 */
public class DailyQuestion202401 {

    public static void main(String[] args) {
        DailyQuestion202401 dq = new DailyQuestion202401();
        minOperationsMaxProfitTest(dq);
        StringUtils.divisionLine();
        removeNodesTest(dq);
        StringUtils.divisionLine();
        insertGreatestCommonDivisorsTest(dq);
        StringUtils.divisionLine();
        minExtraCharTest(dq);
        StringUtils.divisionLine();
        minLengthTest(dq);
        StringUtils.divisionLine();
        numberOfBoomerangsTest(dq);
        StringUtils.divisionLine();
        addMinimumTest(dq);
        StringUtils.divisionLine();
        countWordsTest(dq);
        StringUtils.divisionLine();
        canSeePersonsCountTest(dq);
        StringUtils.divisionLine();
        repeatLimitedStringTest(dq);
        StringUtils.divisionLine();
        deleteDuplicates1Test(dq);
        StringUtils.divisionLine();
        maximumRowsTest(dq);
        StringUtils.divisionLine();
        deleteDuplicatesTest(dq);
        StringUtils.divisionLine();
        maximumNumberOfStringPairsTest(dq);
        StringUtils.divisionLine();
        minimumRemovalTest(dq);
        StringUtils.divisionLine();
        splitWordsBySeparatorTest(dq);
        StringUtils.divisionLine();
        minimumTimeTest(dq);
        StringUtils.divisionLine();
        splitArrayTest(dq);
        StringUtils.divisionLine();
        maximumSwapTest(dq);
        StringUtils.divisionLine();
        alternatingSubarrayTest(dq);
        StringUtils.divisionLine();
        maximumSumOfHeightsTest(dq);
        StringUtils.divisionLine();
        sumIndicesWithKSetBitsTest(dq);
        StringUtils.divisionLine();
        canMeasureWaterTest(dq);
        StringUtils.divisionLine();
        maxNumberOfAlloysTest(dq);
        StringUtils.divisionLine();
        distinctDifferenceArrayTest(dq);
        StringUtils.divisionLine();
    }

    // 2670. 找出不同元素数目差数组

    private static void distinctDifferenceArrayTest(DailyQuestion202401 dq){
        StringUtils.printIntArray(dq.distinctDifferenceArray(new int[]{1,2,3,4,5}));
        StringUtils.printIntArray(dq.distinctDifferenceArray(new int[]{3,2,3,4,2}));
        StringUtils.printIntArray(dq.distinctDifferenceArray(StringUtils.randomIntArray(50, 1, 50)));
    }

    public int[] distinctDifferenceArray(int[] nums) {
        int len=nums.length, count = 0;
        int[] ascend = new int[len], dp = new int[51];
        for (int i=0; i<len; i++){
            if (dp[nums[i]] == 0){
                count++;
            }
            dp[nums[i]]++;
            ascend[i] = count;
        }
        dp = new int[51];
        count = 0;
        int[] result = new int[len];
        int pre = 0;
        for (int i=len-1; i>=0; i--){
            if (dp[nums[i]] == 0){
                count++;
            }
            dp[nums[i]]++;
            result[i] = ascend[i] - pre;
            pre = count;
        }
        return result;
    }

    // 2861. 最大合金数

    private static void maxNumberOfAlloysTest(DailyQuestion202401 dq){
        System.out.println(dq.maxNumberOfAlloys(2, 5, 48, StringUtils.convert2List("[[6,3],[9,5],[1,9], [1,8],[3,3]]"), Arrays.asList(4,8), Arrays.asList(10,1)));
        System.out.println(dq.maxNumberOfAlloys(3, 2, 15, StringUtils.convert2List("[[1,1,1],[1,1,10]]"), Arrays.asList(0,0,0), Arrays.asList(1,2,3)));
        System.out.println(dq.maxNumberOfAlloys(3, 2, 15, StringUtils.convert2List("[[1,1,1],[1,1,10]]"), Arrays.asList(0,0,100), Arrays.asList(1,2,3)));
        System.out.println(dq.maxNumberOfAlloys(2, 3, 10, StringUtils.convert2List("[[2,1],[1,2],[1,1]]"), Arrays.asList(1,1), Arrays.asList(5,5)));
    }

    public int maxNumberOfAlloys(int materialNums, int machineNums, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
         return -66;
    }

    private int getRealCost(List<Integer> composition, List<Integer> stock, List<Integer> cost){
        int total = 0;
        for (int i=0; i<composition.size(); i++){
            if (stock.get(i) >= composition.get(i)){
                continue;
            }
            total += (composition.get(i) - stock.get(i)) * cost.get(i);
        }
        return total;
    }

    private int getCost(List<Integer> composition, List<Integer> cost){
        int total = 0;
        for (int i=0; i<composition.size(); i++){
            total += cost.get(i) * composition.get(i);
        }
        return total;
    }


    // 365. 水壶问题

    private static void canMeasureWaterTest(DailyQuestion202401 dq){
        System.out.println(dq.canMeasureWater(6, 9, 1));
        System.out.println(dq.canMeasureWater(3, 5, 4));
        System.out.println(dq.canMeasureWater(2, 6, 5));
        System.out.println(dq.canMeasureWater(1, 2, 3));
        System.out.println(dq.canMeasureWater(2468, 6, 780));
    }

    public boolean canMeasureWater(int a, int b, int t) {
         if(a + b < t){
             return false;
         }
        if (a + b == t){
            return true;
        }
        return t % MathUtils.gcd(a, b) == 0;
    }



    // 2859. 计算 K 置位下标对应元素的和

    private static void sumIndicesWithKSetBitsTest(DailyQuestion202401 dq){
        System.out.println(dq.sumIndicesWithKSetBits(Arrays.asList(5,10,1,5,2), 1));
        System.out.println(dq.sumIndicesWithKSetBits(Arrays.asList(4,3,2,1), 2));
        System.out.println(dq.sumIndicesWithKSetBits(StringUtils.randomList(500, 1, 1000), 5));
    }

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int result = 0;
        for (int i=0; i<nums.size(); i++){
            if (this.bitCount(i) == k){
                result += nums.get(i);
            }
        }
        return result;
    }

    private int bitCount(int n){
        int result = 0;
        while (n != 0){
            n = n & (n-1);
            result++;
        }
        return result;
    }

    // 2865. 美丽塔 I

    private static void maximumSumOfHeightsTest(DailyQuestion202401 dq){
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(5,3,4,1,1)));
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(6,5,3,9,2,7)));
        System.out.println(dq.maximumSumOfHeights(Arrays.asList(3,2,5,5,2,3)));
        System.out.println(dq.maximumSumOfHeights(StringUtils.randomList(300, 1, 10000)));
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int len = maxHeights.size();
        long[] preSum = new long[len], sufSum = new long[len];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<len; i++){
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.pop();
            }
            int start = stack.isEmpty() ? -1 : stack.peek();
            long count = stack.isEmpty() ? 0 : preSum[stack.peek()];
            preSum[i] = count + (long) (i - start) * maxHeights.get(i);
            stack.push(i);
        }
        stack = new Stack<>();
        for (int i=len-1; i>=0; i--){
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.pop();
            }
            int start = stack.isEmpty() ? len : stack.peek();
            long count = stack.isEmpty() ? 0 : sufSum[stack.peek()];
            sufSum[i] = count + (long) (start - i) * maxHeights.get(i);
            stack.push(i);
        }
        long result = 0;
        for (int i=0; i<len; i++){
            result = Math.max(result, preSum[i] + sufSum[i] - maxHeights.get(i));
        }
        return result;
    }

    // 2765. 最长交替子数组

    private static void alternatingSubarrayTest(DailyQuestion202401 dq){
        System.out.println(dq.alternatingSubarray(new int[]{2,3,4,3,4}));
        System.out.println(dq.alternatingSubarray(new int[]{4,5,6}));
        System.out.println(dq.alternatingSubarray(StringUtils.randomIntArray(100, 1, 4)));
    }

    public int alternatingSubarray(int[] nums) {
        int result = -1, count, i=1, len=nums.length, nextTrip;
        while (i<len){
            nextTrip = 1;
            count = 0;
            while (i<len && nums[i] - nums[i-1] == nextTrip){
                nextTrip *= -1;
                i++;
                count++;
            }
            if (count != 0){
                result = Math.max(result, count+1);
            }
            else {
                i++;
            }
        }
        return result;
    }

    // 670. 最大交换

    private static void maximumSwapTest(DailyQuestion202401 dq){
        System.out.println(dq.maximumSwap(2736));
        System.out.println(dq.maximumSwap(9973));
    }

    public int maximumSwap(int num) {
        int result = num;
        char[] charArray = String.valueOf(num).toCharArray();
        for (int i=0; i<charArray.length; i++){
            for (int j=i+1; j<charArray.length; j++){
                swap(charArray, i, j);
                result = Math.max(result, Integer.parseInt(String.valueOf(charArray)));
                swap(charArray, i, j);
            }
        }
        return result;
    }

    private void swap(char[] chars, int a, int b){
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }

    // 410. 分割数组的最大值

    private static void splitArrayTest(DailyQuestion202401 dq){
        System.out.println(dq.splitArray(new int[]{7,2,5,10,8}, 2));
        System.out.println(dq.splitArray(new int[]{1,2,3,4,5}, 2));
        System.out.println(dq.splitArray(new int[]{1,4,4}, 3));
        System.out.println(dq.splitArray(StringUtils.randomIntArray(500, 1, 10000), 100));
    }

    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        int[] preSum = new int[len+1];
        for (int i=1; i<len+1; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        // 在[1,i]中拆分成j个连续子数组的最大值中的最小
        int[][] dp = new int[len+1][m+1];
        for (int i=0; i<len+1; i++){
            for (int j=0; j<m+1; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i=1; i<=len; i++){
            dp[i][1] = preSum[i];
        }
        for (int i=1; i<=len; i++){
            for (int j=2; j<=Math.min(i, m); j++){
                for (int k=1; k<i; k++){
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], preSum[i] - preSum[k]));
                }
            }
        }
        return dp[len][m];
    }

    // 2809. 使数组和小于等于 x 的最少时间

    private static void minimumTimeTest(DailyQuestion202401 dq){
        System.out.println(dq.minimumTime(Arrays.asList(1,2,3), Arrays.asList(1,2,3), 4));
        System.out.println(dq.minimumTime(Arrays.asList(1,2,3), Arrays.asList(3,3,3), 4));
        System.out.println(dq.minimumTime(Arrays.asList(1,2,3), Arrays.asList(3,3,3), 14));
        System.out.println(dq.minimumTime(StringUtils.randomList(1000, 1, 300), StringUtils.randomList(1000, 0, 50), 151234));
    }

    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        return 0;
    }

    // 2788. 按分隔符拆分字符串

    private static void splitWordsBySeparatorTest(DailyQuestion202401 dq){
        System.out.println(dq.splitWordsBySeparator(Arrays.asList("one.two.three","four.five","six"), '.'));
        System.out.println(dq.splitWordsBySeparator(Arrays.asList("$easy$","$problem$"), '$'));
        System.out.println(dq.splitWordsBySeparator(Arrays.asList("|||"), '|'));
    }

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> result = new LinkedList<>();
        int left, right;
        for (String word : words){
            left = 0;
            while (left < word.length()){
                right = left;
                while (right < word.length() && word.charAt(right) != separator){
                    right++;
                }
                if (left != right){
                    result.add(word.substring(left, right));
                }
                left = right + 1;
            }
        }
        return result;
    }

    // 2171. 拿出最少数目的魔法豆

    private static void minimumRemovalTest(DailyQuestion202401 dq){
        System.out.println(dq.minimumRemoval(new int[]{4,1,6,5}));
        System.out.println(dq.minimumRemoval(new int[]{2,10,3,2}));
        System.out.println(dq.minimumRemoval(StringUtils.randomIntArray(9999, 1, 3000)));
    }

    public long minimumRemoval(int[] beans) {
        Map<Integer, Integer> timesMap = new HashMap<>();
        long total = 0;
        long result = 0L;
        for (int bean : beans){
            total += bean;
            timesMap.put(bean, timesMap.getOrDefault(bean, 0) + 1);
        }
        ArrayList<Integer> keys = new ArrayList<>(timesMap.keySet());
        keys.sort((p, q) -> (p - q));
        int key = keys.get(keys.size()-1);
        long pre = total - (long) key * timesMap.get(key), suf = 0, times = timesMap.get(key);
        result = pre;
        for (int i=keys.size()-2; i>=0; i--){
            key = keys.get(i);
            // 前面删除
            pre -= (long) key * timesMap.get(key);
            // 后面抚平
            suf += times * (keys.get(i+1) - key);
            // 次数打平
            times += timesMap.get(key);
            result = Math.min(result, pre + suf);
        }

        return result;
    }

    // 2744. 最大字符串配对数目

    private static void maximumNumberOfStringPairsTest(DailyQuestion202401 dq){
        System.out.println(dq.maximumNumberOfStringPairs(new String[]{"cd","ac","dc","ca","zz"}));
        System.out.println(dq.maximumNumberOfStringPairs(new String[]{"ab","ba","cc"}));
        System.out.println(dq.maximumNumberOfStringPairs(new String[]{"aa","ab"}));
    }

    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> set = new HashSet<>();
        int result = 0;
        for (String word : words){
            if (set.contains(word)){
                result++;
                continue;
            }
            set.add(this.getReverseStr(word));
        }
        return result;
    }

    private String getReverseStr(String s){
        char[] chars = s.toCharArray();
        for (int i=0, j=chars.length-1; i<j; i++,j--){
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }

    // 82. 删除排序链表中的重复元素 II

    private static void deleteDuplicatesTest(DailyQuestion202401 dq){
        ListNode.printPath(dq.deleteDuplicates(ListNode.buildListedList("1,2,3,3,4,4,5")));
        ListNode.printPath(dq.deleteDuplicates(ListNode.buildListedList("1,1,1,2,3")));
        ListNode.printPath(dq.deleteDuplicates(ListNode.buildSortedListedList(StringUtils.randomIntArrayWrapper(250, -99, 99), false)));
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=null, p=null, q=head;
        int count;
        while (q!=null){
            count=0;
            while (q.next!=null && q.val == q.next.val){
                count++;
                q=q.next;
            }
            // 存在相同值节点，跳过
            if (count!=0){
                q = q.next;
                continue;
            }
            // 首个节点
            if (dummy == null){
                dummy = q;
                p =  dummy;
                q = q.next;
                continue;
            }
            p.next = q;
            p = p.next;
            q = q.next;
        }
        if (p!=null){
            p.next = null;
        }
        return dummy;
    }

    // 2397. 被列覆盖的最多行数

    private static void maximumRowsTest(DailyQuestion202401 dq){
        System.out.println(dq.maximumRows(StringUtils.convert2Array("[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]"), 2));
        System.out.println(dq.maximumRows(StringUtils.convert2Array("[[1],[0]]"), 1));
        System.out.println(dq.maximumRows(StringUtils.constructTwoDimensionArray(11, 11, new int[]{0, 1}), 7));
    }

    public int maximumRows(int[][] matrix, int numSelect) {
        int[] nums = new int[matrix.length];
        for (int j=0; j<matrix.length; j++){
            int val = 0;
            for (int i=0; i<matrix[j].length; i++){
                val |= matrix[j][i] << (matrix[j].length -1 -i);
            }
            nums[j] = val;
        }
        int result = 0;
        int val = (1 << numSelect) - 1, c, r;
        int max = 1 << 12;
        while (val < max){
            int count = 0;
            for (int num : nums){
                if ((val & num) == num){
                    count++;
                }
            }
            result = Math.max(result, count);
            c = val & -val;
            r = val + c;
            val = (((val ^ r) >> 2) / c) | r;
        }
        return result;
    }

    /**
     *
     * @param retain 剩余可选1的个数
     * @param start 下一个可选坐标，范围[0,max)
     * @param max 最大边界值
     * @param val 路径val
     * @param result 结果集
     */
    private void getSelectOptions(int retain, int start, int max, int val, List<Integer> result){
        if (retain == 0){
            result.add(val);
            return;
        }
        if (start >= max){
            return;
        }
        // 这个位置不填1
        this.getSelectOptions(retain,  start+1, max, val, result);
        // 这个位置填1
        this.getSelectOptions(retain-1,  start+1, max, val | (1 << (max - 1 - start)),result);
    }

    // 83. 删除排序链表中的重复元素

    private static void deleteDuplicates1Test(DailyQuestion202401 dq){
        ListNode.printPath(dq.deleteDuplicates1(ListNode.buildListedList("1,1,2")));
        ListNode.printPath(dq.deleteDuplicates1(ListNode.buildListedList("1,1,2,3,3")));
        ListNode.printPath(dq.deleteDuplicates1(ListNode.buildListedList("2,3,3,4,4,4,4,5")));
    }

    public ListNode deleteDuplicates1(ListNode head) {
        ListNode root = new ListNode(-1000);
        root.next = head;
        ListNode pre = root, next = head;
        while (next != null){
            if (pre.val == next.val){
                pre.next = next.next;
                next = next.next;
                continue;
            }
            pre = pre.next;
            next = next.next;
        }
        head = root.next;
        root.next = null;
        return head;
    }

    // 2182. 构造限制重复的字符串

    private static void repeatLimitedStringTest(DailyQuestion202401 dq){
        System.out.println(dq.repeatLimitedString("cczazcc", 3));
        System.out.println(dq.repeatLimitedString("aababab", 2));
        System.out.println(dq.repeatLimitedString("aaaabbbbbccccdddd", 2));
        System.out.println(dq.repeatLimitedString(StringUtils.getLowerCaseLetters(10000), 100));
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] dp = new int[26];
        for (int i=0; i<s.length(); i++){
            dp[25 - s.charAt(i) + 'a']++;
        }
        int p =0, q = 1;
        StringBuilder sb = new StringBuilder();
        while (p < 26){
            // 小于最大次数，直接放进去
            if (dp[p] <= repeatLimit){
                while (dp[p] > 0){
                    sb.append((char) ('a' - p + 25));
                    dp[p]--;
                }
                p++;
                continue;
            }
            // 大于最大次数，直接放进去
            for (int i=0; i<repeatLimit; i++){
                sb.append((char) ('a' - p + 25));
                dp[p]--;
            }
            // 不符合条件时初始化
            if (q <= p){
                q = p+1;
            }
            // 往后找第一个大于0的字母
            while (q < 26 && dp[q] == 0){
                q++;
            }
            // 找不到下一个插入点了
            if (q == 26){
                break;
            }
            // 拼入节点
            sb.append((char) ('a' - q + 25));
            dp[q]--;
        }
        return sb.toString();
    }

    // 1944. 队列中可以看到的人数

    private static void canSeePersonsCountTest(DailyQuestion202401 dq){
        StringUtils.printIntArray(dq.canSeePersonsCount(new int[]{10,6,8,5,11,9}));
        StringUtils.printIntArray(dq.canSeePersonsCount(new int[]{5,1,2,3,10}));
        StringUtils.printIntArray(dq.canSeePersonsCount(StringUtils.randomNoRepeat(1000, 1, 9999)));
    }

    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length, cnt;
        int[] result = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=len-1; i>=0; i--){
            cnt = 0;
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]){
                cnt++;
                stack.pop();
            }
            result[i] = stack.isEmpty() ? cnt : cnt+1;
            stack.push(i);
        }
        return result;
    }

    // 2085. 统计出现过一次的公共字符串

    private static void countWordsTest(DailyQuestion202401 dq){
        System.out.println(dq.countWords(new String[]{"leetcode","is","amazing","as","is"}, new String[]{"amazing","leetcode","is"}));
        System.out.println(dq.countWords(new String[]{"b","bb","bbb"}, new String[]{"a","aa","aaa"}));
        System.out.println(dq.countWords(new String[]{"a","ab"}, new String[]{"a","a","a","ab"}));
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> pMap = new HashMap<>();
        for (String word : words1){
            pMap.put(word, pMap.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> qMap = new HashMap<>();
        for (String word : words2){
            qMap.put(word, qMap.getOrDefault(word, 0) + 1);
        }
        int result = 0;
        Integer val;
        for (Map.Entry<String, Integer> item : pMap.entrySet()){
            if (item.getValue() == 1 && (val = qMap.get(item.getKey())) != null && val == 1){
                result++;
            }
        }
        return result;
    }



    // 2645. 构造有效字符串的最少插入数
    // 某个点往后扫，只存在两种情况：1、有序状态，继续往后扫。2、乱序，重复场景，此时只能人为补充节点   ==>  我是傻逼，  ac bc的咋处理？？？
    // 根据有序性处理

    private static void addMinimumTest(DailyQuestion202401 dq){
        System.out.println(dq.addMinimum("b"));
        System.out.println(dq.addMinimum("aaa"));
        System.out.println(dq.addMinimum("abc"));
        System.out.println(dq.addMinimum(StringUtils.randomArrayInSpecificCharacters(new char[]{'a', 'b', 'c'},  50)));
    }



    public int addMinimum(String word) {
        int result = 0, i=0;
        Stack<Character> stack = new Stack<>();
        while (true){
            while (i<word.length() && (stack.isEmpty() || stack.peek() < word.charAt(i))){
                stack.push(word.charAt(i));
                i++;
            }
            result += 3 - stack.size();
            stack.clear();
            if (i == word.length()){
                break;
            }
        }
        return result;
    }


    // 447. 回旋镖的数量

    private static void numberOfBoomerangsTest(DailyQuestion202401 dq){
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[0,0],[1,0],[2,0]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[1,1],[2,2],[3,3]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[1,1]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.constructTwoDimensionArray(400, 2, -300, 300)));
    }

    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for (int[] point : points){
            Map<Integer, Integer> distinceMap = new HashMap<>();
            for (int i=0; i<points.length; i++){
                int distince = (points[i][0] - point[0]) * (points[i][0] - point[0]) + (points[i][1] - point[1]) * (points[i][1] - point[1]);
                distinceMap.put(distince, distinceMap.getOrDefault(distince, 0) + 1);
            }
            for (Integer times : distinceMap.values()) {
                if (times >= 2){
                    result += (times-1) * times;
                }
            }
        }
        return result;
    }

    // 2696. 删除子串后的字符串最小长度

    private static void minLengthTest(DailyQuestion202401 dq){
        System.out.println(dq.minLength("ABFCACDB"));
        System.out.println(dq.minLength("ACBBD"));
    }

    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++){
            // 可以组合的移除
            if (!stack.empty() && ((stack.peek() == 'A' && s.charAt(i) == 'B') || (stack.peek() == 'C' && s.charAt(i) == 'D'))){
                stack.pop();
                continue;
            }
            // 不能组合的加入
            stack.push(s.charAt(i));
        }
        return stack.size();
    }

    // 2707. 字符串中的额外字符

    private static void minExtraCharTest(DailyQuestion202401 dq){
        System.out.println(dq.minExtraChar("leetscode", new String[]{"leet","code","leetcode"}));
        System.out.println(dq.minExtraChar("sayhelloworld", new String[]{"hello","world"}));
        System.out.println(dq.minExtraChar("hello", new String[]{"hel","ello"}));
    }

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        int maxLen = 0;
        for (String item : dictionary){
            set.add(item);
            maxLen = Math.max(maxLen, item.length());
        }
        int[] dp = new int[s.length()+1];
        for (int i=0; i<dp.length; i++){
            dp[i] = i+1;
        }
        for (int i=0; i<s.length(); i++){
            this.calMinExtraChar(set, dp, i, s, maxLen);
        }
        return dp[s.length()-1];
    }

    private void calMinExtraChar(Set<String> set, int[] dp, int pos, String s, int maxLen){
        for (int i=pos; i<s.length() && i<pos+maxLen; i++){
            if (set.contains(s.substring(pos, i+1))){
                int pre = pos == 0 ? 0 : dp[pos-1];
                dp[i] = Math.min(dp[i], pre);
            }
        }
        if (pos != 0){
            dp[pos] = Math.min(dp[pos], dp[pos-1]+1);
        }
    }




    // 2807. 在链表中插入最大公约数

    private static void insertGreatestCommonDivisorsTest(DailyQuestion202401 dq){
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(new Integer[]{18,6,10,3})));
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(new Integer[]{7})));
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(StringUtils.randomIntArrayWrapper(2000, 1, 999))));
    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode pre = null, pos = head;
        while (pos != null){
            if (pre == null){
                pre = pos;
                pos = pos.next;
                continue;
            }
            ListNode midNode = new ListNode(this.getGreatestCommonDivisor(pre.val, pos.val));
            midNode.next = pre.next;
            pre.next = midNode;
            pre = pos;
            pos = pos.next;
        }
        return head;
    }

    private int getGreatestCommonDivisor(int a, int b){
        if (a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (a % b != 0){
            int remainder = a%b;
            a=b;
            b=remainder;
        }
        return b;
    }

    // 383. 赎金信

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] f = new int[26];
        for (int i=0; i<magazine.length(); i++){
            f[magazine.charAt(i) - 'a']++;
        }
        for (int i=0; i<ransomNote.length(); i++){
            f[ransomNote.charAt(i)-'a']--;
            if (f[ransomNote.charAt(i) - 'a'] <0){
                return false;
            }
        }
        return true;
    }

    // 2487. 从链表中移除节点

    private static void removeNodesTest(DailyQuestion202401 dq){
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("2,3")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("5,2,13,3,8")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("1,1,1,1")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList(StringUtils.randomIntArrayWrapper(10000, 1, 99999))));
    }

    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head, next, tail = null;
        while (p != null){
            next = p.next;
            while (!stack.isEmpty() && stack.peek().val < p.val){
                stack.pop();
            }
            stack.push(p);
            p = next;
        }
        while (!stack.isEmpty()){
            if (tail == null){
                tail = stack.pop();
                tail.next = null;
                continue;
            }
            head = stack.pop();
            head.next = tail;
            tail = head;
        }
        return tail;
    }

    // 1599. 经营摩天轮的最大利润

    private static void minOperationsMaxProfitTest(DailyQuestion202401 dq){
        System.out.println(dq.minOperationsMaxProfit(new int[]{0,43,37,9,23,35,18,7,45,3,8,24,1,6,37,2,38,15,1,14,39,27,4,25,27,33,43,8,44,30,38,40,20,5,17,27,43,11,6,2,30,49,30,25,32,3,18,23,45,43,30,14,41,17,42,42,44,38,18,26,32,48,37,5,37,21,2,9,48,48,40,45,25,30,49,41,4,48,40,29,23,17,7,5,44,23,43,9,35,26,44,3,26,16,31,11,9,4,28,49,43,39,9,39,37,7,6,7,16,1,30,2,4,43,23,16,39,5,30,23,39,29,31,26,35,15,5,11,45,44,45,43,4,24,40,7,36,10,10,18,6,20,13,11,20,3,32,49,34,41,13,11,3,13,0,13,44,48,43,23,12,23,2}, 43, 54));
        System.out.println(dq.minOperationsMaxProfit(new int[]{8,3}, 5, 6));
        System.out.println(dq.minOperationsMaxProfit(new int[]{10,9,6}, 6, 4));
        System.out.println(dq.minOperationsMaxProfit(new int[]{3,4,0,5,1}, 1, 92));
        System.out.println(dq.minOperationsMaxProfit(StringUtils.randomIntArray(10000, 0, 50), 4, 9));
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (boardingCost * 4 <= runningCost){
            return -1;
        }
        int preRetain = 0, runCost, times=0, sum=0, resultTimes=0, result=0;
        for (int i=0; i<customers.length; i++){
            preRetain += customers[i];
            runCost = Math.min(preRetain, 4);
            times ++;
            sum += runCost * boardingCost - runningCost;
            preRetain -= runCost;
            if (sum > result){
                resultTimes = times;
                result = sum;
            }
        }
        if (preRetain > 4){
            times += preRetain / 4;
            sum += preRetain / 4 * 4 * boardingCost - runningCost;
            preRetain %= 4;
        }
        if (sum == 0 && preRetain * boardingCost - runningCost <= 0){
            return -1;
        }
        if (preRetain * boardingCost - runningCost > 0){
            times++;
            sum++;
        }
        if (sum > result){
            resultTimes = times;
        }
        return resultTimes;
    }


}
