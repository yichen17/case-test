package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/11/2 07:59
 * @describe
 */
public class DailyQuestion202311 {

    public static void main(String[] args) {
        DailyQuestion202311 dq = new DailyQuestion202311();
        countPointsTest(dq);
        StringUtils.divisionLine();
        findMaximumXORTest(dq);
        StringUtils.divisionLine();
        findRepeatedDnaSequencesTest(dq);
        StringUtils.divisionLine();
        maximumScoreAfterOperationsTest(dq);
        StringUtils.divisionLine();
        maxProductTest(dq);
        StringUtils.divisionLine();
        findTheLongestBalancedSubstringTest(dq);
        StringUtils.divisionLine();
        numArrayTest();
        StringUtils.divisionLine();
        successfulPairsTest(dq);
        StringUtils.divisionLine();
        findTheCityTest(dq);
        StringUtils.divisionLine();
        maximizeSumTest(dq);
        StringUtils.divisionLine();
        longestAlternatingSubarrayTest(dq);
        StringUtils.divisionLine();
        maximumSumQueriesTest(dq);
        StringUtils.divisionLine();
        maximumSumTest(dq);
        StringUtils.divisionLine();
        maximumXorProductTest(dq);
        StringUtils.divisionLine();
        maxSubArrayTest(dq);
        StringUtils.divisionLine();
        maximunMinutes(dq);
        StringUtils.divisionLine();
        minDeletionTest(dq);
        StringUtils.divisionLine();
        entityParserTest(dq);
        StringUtils.divisionLine();
    }

    // 1410. HTML 实体解析器

    private static void entityParserTest(DailyQuestion202311 dq){
        System.out.println(dq.entityParser("&amp; is an HTML entity but &ambassador; is not."));
        System.out.println(dq.entityParser("and I quote: &quot;...&quot;"));
        System.out.println(dq.entityParser("&amp;gt;"));
    }

    public String entityParser(String text) {
        Map<String, String> maps = new HashMap<>();
        maps.put("&quot;", "\"");
        maps.put("&apos;", "'");
        maps.put("&amp;", "&");
        maps.put("&gt;", ">");
        maps.put("&lt;", "<");
        maps.put("&frasl;", "/");
        StringBuilder result = new StringBuilder(), compareData = new StringBuilder();
        for (int i=0; i<text.length(); i++){
            if (text.charAt(i) == '&' ){
                if (compareData.length() != 0){
                    result.append(compareData);
                    compareData = new StringBuilder();
                }
                compareData.append(text.charAt(i));
                continue;
            }
            if (compareData.length() != 0){
                compareData.append(text.charAt(i));
                if (compareData.length() < 3){

                }
                else if (compareData.length() < 8){
                    if (maps.containsKey(compareData.toString())){
                        result.append(maps.get(compareData.toString()));
                        compareData = new StringBuilder();
                    }
                }
                else {
                    result.append(compareData);
                    compareData = new StringBuilder();
                }
            }
            else {
                result.append(text.charAt(i));
            }
        }
        result.append(compareData);
        return result.toString();
    }

    // 2216. 美化数组的最少删除数

    private static void minDeletionTest(DailyQuestion202311 dq){
        System.out.println(dq.minDeletion(new int[]{1,1,2,3,5}));
        System.out.println(dq.minDeletion(new int[]{1,1,2,2,3,3}));
        System.out.println(dq.minDeletion(StringUtils.randomIntArray(2000, 1, 30)));
    }

    public int minDeletion(int[] nums) {
        int result = 0;
        int i = 0, len = nums.length;
        while (i < len) {
            if (i+1 == len){
                result++;
                break;
            }
            if (nums[i] == nums[i+1]){
                result++;
                i = i+1;
                continue;
            }
            i += 2;
        }
        return result;
    }

    // 53. 最大子数组和

    private static void maxSubArrayTest(DailyQuestion202311 dq){
        System.out.println(dq.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(dq.maxSubArray(new int[]{1}));
        System.out.println(dq.maxSubArray(new int[]{5,4,-1,7,8}));
        System.out.println(dq.maxSubArray(new int[]{-2, -1}));
        System.out.println(dq.maxSubArray(StringUtils.randomIntArray(1000, -1000, 1000)));
    }

    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int count = nums[0];
        for (int i=1; i<nums.length; i++){
            int num = nums[i];
            if (count + num < num){
                count = num;
            }
            else {
                count += num;
            }
            result = Math.max(result, count);
        }
        return result;
    }


    // 689. 三个无重叠子数组的最大和

    private static void maxSumOfThreeSubarraysTest(DailyQuestion202311 dq){
        StringUtils.printIntArray(dq.maxSumOfThreeSubarrays(new int[]{1,2,1,2,6,7,5,1}, 2));
        StringUtils.printIntArray(dq.maxSumOfThreeSubarrays(new int[]{1,2,1,2,1,2,1,2,1}, 2));
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] result = new int[3];
        // 计算前缀和、dp动态规划



        return result;
    }

    // 2258. 逃离火灾

    private static void maximunMinutes(DailyQuestion202311 dq){
//        System.out.println(dq.maximumMinutes(StringUtils.convert2Array("[[0,0,0],[0,1,0],[0,0,0]]")));
//        System.out.println(dq.maximumMinutes(StringUtils.convert2Array("[[0,0,0],[2,2,0],[1,2,0]]")));
        System.out.println(dq.maximumMinutes(StringUtils.convert2Array("[[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]")));
        System.out.println(dq.maximumMinutes(StringUtils.convert2Array("[[0,0,0,0],[0,1,2,0],[0,2,0,0]]")));
//        int[][] grid = StringUtils.constructTwoDimensionArray(100, 100, 0, 3);

//        int i = 0;
//        while (i < 10000){
//            int[][] grid = StringUtils.constructTwoDimensionArray(200, 200, new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0});
//            int result = dq.maximumMinutes(grid);
//            if (result > 5){
//                StringUtils.arrayTwoDimensionPrintNormal(grid);
//                break;
//            }
//            i++;
//        }

        int[][] grid = new int[100][200];
        grid[50][100] = 1;
        StringUtils.arrayTwoDimensionPrintNormal(grid);
        System.out.println(dq.maximumMinutes(grid));

    }

    public int maximumMinutes(int[][] grid) {



        return 0;
    }




    // 715. Range 模块

    private  static class RangeModule {

        public RangeModule() {

        }

        public void addRange(int left, int right) {

        }

        public boolean queryRange(int left, int right) {
            return false;
        }

        public void removeRange(int left, int right) {

        }
    }

    // 周赛 100119 最大异或乘积  位运算别理解成 ==0 和 ==1 应该是 ==0 和 > 0

    private static void maximumXorProductTest(DailyQuestion202311 dq){
        System.out.println(dq.maximumXorProduct(53449611838892L, 712958946092406L, 6));
        System.out.println(dq.maximumXorProduct(12L, 5L, 4));
        System.out.println(dq.maximumXorProduct(6L, 7L, 5));
        System.out.println(dq.maximumXorProduct(1L, 6L, 3));
    }
    private static final int MOD = 1_000_000_007;
    public int maximumXorProduct(long a, long b, int n) {
        Boolean compare = null;
        boolean defaultSelect = false;
        for (int i=n-1; i>=0; i--){
            long tmp = 1L << i;
            if ((a & tmp) == 0 && (b & tmp) == 0){
                a |= tmp;
                b |= tmp;
            }
            else if ((a & tmp) >0 && (b & tmp) > 0){

            }
            else {
                for (int j = 63; j!=i && compare == null; j--){
                    long t = 1L << j;
                    if ((a & t) >0 && (b & t) == 0){
                        compare = true;
                        break;
                    }
                    if ((a & t) == 0 && (b & t) >0){
                        compare = false;
                        break;
                    }
                }
                if (compare == null){
                    defaultSelect = true;
                    compare = false;
                }

                // a大且当前为a大，b大且当前为a大
                if (compare && (b & tmp) == 0){
                    b |= tmp;
                    a &= ~tmp;
                }
                else if (!compare && (b & tmp) > 0){
                    b &= ~tmp;
                    a |= tmp;
                }
                else if (compare && (b & tmp) > 0){
                    b |= tmp;
                    a &= ~tmp;
                }
                else if (!compare && (b & tmp) == 0){
                    a |= tmp;
                    b &= ~tmp;
                }
                if (defaultSelect){
                    compare = true;
                    defaultSelect = false;
                }
            }
        }
        return (int) ((a % MOD) * (b % MOD) % MOD);
    }


    // 周赛 100122 区分黑球与白球
    public long minimumSteps(String s) {
        long result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i) == '1'){
                pq.offer(i);
                continue;
            }
            if (!pq.isEmpty() && pq.peek() < i){
                result += i - pq.poll();
                pq.offer(i);
            }
        }
        return result;
    }

    // 2342. 数位和相等数对的最大和

    private static void maximumSumTest(DailyQuestion202311 dq){
        System.out.println(dq.maximumSum(new int[]{18,43,36,13,7}));
        System.out.println(dq.maximumSum(new int[]{10,12,19,14}));
        System.out.println(dq.maximumSum(StringUtils.randomIntArray(1000, 1, 10000)));
    }

    public int maximumSum(int[] nums) {
        int result = -1;
        Integer[] dp = new Integer[nums.length];
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums){
            int pos = getBitCount(num);
            Queue<Integer> queue = map.computeIfAbsent(pos, k -> new PriorityQueue<>());
            if (queue.size() < 2){
                queue.offer(num);
                continue;
            }
            if (queue.peek() < num){
                queue.poll();
                queue.offer(num);
            }
        }
        for (PriorityQueue<Integer> items : map.values()) {
            if (items == null || items.size() != 2){
                continue;
            }
            result = Math.max(result, items.poll() + items.poll());
        }
        return result;
    }

    private int getBitCount(int n){
        int result = 0;
        while (n > 0){
            result += n%10;
            n = n/10;
        }
        return result;
    }

    // 2736. 最大和查询

    private static void maximumSumQueriesTest(DailyQuestion202311 dq){
        StringUtils.printIntArray(dq.maximumSumQueries(new int[]{3,4,5,2,1}, new int[]{6,4,1,3,4}, StringUtils.convert2Array("[[4,5]]")));
        StringUtils.printIntArray(dq.maximumSumQueries(new int[]{4,3,1,2}, new int[]{2,4,9,5}, StringUtils.convert2Array("[[4,1],[1,3],[2,5]]")));
        StringUtils.printIntArray(dq.maximumSumQueries(new int[]{3,2,5}, new int[]{2,3,4}, StringUtils.convert2Array("[[4,4],[3,2],[1,1]]")));
        StringUtils.printIntArray(dq.maximumSumQueries(new int[]{2,1}, new int[]{2,3}, StringUtils.convert2Array("[[3,3]]")));
    }

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums1[i];
            sortedNums[i][1] = nums2[i];
        }
        Arrays.sort(sortedNums, (a, b) -> b[0] - a[0]);
        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = i;
            sortedQueries[i][1] = queries[i][0];
            sortedQueries[i][2] = queries[i][1];
        }
        Arrays.sort(sortedQueries, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<int[]>();
        int[] answer = new int[q];
        Arrays.fill(answer, -1);
        int j = 0;
        for (int[] query : sortedQueries) {
            int i = query[0], x = query[1], y = query[2];
            while (j < n && sortedNums[j][0] >= x) {
                int[] pair = sortedNums[j];
                int num1 = pair[0], num2 = pair[1];
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[1] <= num1 + num2) {
                    stack.remove(stack.size() - 1);
                }
                if (stack.isEmpty() || stack.get(stack.size() - 1)[0] < num2) {
                    stack.add(new int[]{num2, num1 + num2});
                }
                j++;
            }
            int k = binarySearch(stack, y);
            if (k < stack.size()) {
                answer[i] = stack.get(k)[1];
            }
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // 2760. 最长奇偶子数组

    private static void longestAlternatingSubarrayTest(DailyQuestion202311 dq){
        System.out.println(dq.longestAlternatingSubarray(new int[]{4,10,3}, 10));
        System.out.println(dq.longestAlternatingSubarray(new int[]{3,2,5,4}, 5));
        System.out.println(dq.longestAlternatingSubarray(new int[]{1,2}, 3));
        System.out.println(dq.longestAlternatingSubarray(new int[]{2,3,4,5}, 4));
        System.out.println(dq.longestAlternatingSubarray(StringUtils.randomIntArray(90, 1, 99), 40));
    }

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        boolean even = true;
        int result = 0, count, i=0;
        while (i < nums.length){
            if  (nums[i] % 2 != 0 || nums[i] > threshold){
                i++;
                continue;
            }
            count = 0;
            while (i< nums.length){
                if (nums[i] <= threshold && ((even && nums[i] % 2 == 0) || (!even && nums[i] % 2 == 1))){
                    count++;
                    i++;
                    even = !even;
                    continue;
                }
                break;
            }
            result = Math.max(result, count);
            even = true;
        }
        return result;
    }

    // 2656. K 个元素的最大和

    private static void maximizeSumTest(DailyQuestion202311 dq){
        System.out.println(dq.maximizeSum(new int[]{1,2,3,4,5}, 3));
        System.out.println(dq.maximizeSum(new int[]{5,5,5}, 2));
    }


    public int maximizeSum(int[] nums, int k) {
        int max = nums[0];
        for (int i=1; i<nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        int result = max * k;
        k--;
        result += (k+1)*k/2;
        return result;
    }
    // 1334. 阈值距离内邻居最少的城市  floyd算法 Dijkstra算法  我是垃圾，都还给老师了。。

    private static void findTheCityTest(DailyQuestion202311 dq){
        System.out.println(dq.findTheCity(4, StringUtils.convert2Array("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), 4));
        System.out.println(dq.findTheCity(39, StringUtils.convert2Array("[[32,33,6066],[9,24,4482],[12,23,1781],[6,25,1897],[7,15,8633],[12,16,2890],[1,30,349],[30,31,9738],[11,33,9791],[12,34,2418],[18,21,4112],[25,29,7258],[1,3,4596],[1,8,2224],[8,17,9142],[13,23,6498],[29,38,9590],[6,28,6956],[4,31,9774],[2,30,3967],[6,19,8528],[11,13,3068],[2,36,2987],[29,37,5395],[14,21,5175],[2,4,3214],[17,29,196],[9,20,4655],[19,36,9637],[15,25,1418],[6,33,5843],[22,27,2500],[13,34,2553],[0,16,1409],[20,30,795],[5,34,8623],[9,33,2352],[21,29,525],[11,30,1720],[14,17,7672],[2,34,8525],[3,29,6520],[26,29,847],[14,18,1323],[27,33,2360],[14,23,4009],[21,37,7194],[14,38,7686],[2,25,8244],[3,21,7009],[20,27,8794],[4,32,1865],[14,20,3548],[2,3,6502],[21,28,1577],[9,15,1030],[24,32,5566],[3,5,4979],[18,26,4109],[25,33,6545],[12,36,5506],[5,33,564],[13,22,691],[8,13,1955],[18,19,4031],[15,37,841],[7,27,318],[1,25,1626],[15,18,7242],[11,12,1446],[24,26,725],[5,24,7100],[7,37,9453],[20,26,2597],[2,10,6982],[19,25,1081],[1,35,7350],[4,37,8618],[4,17,3751],[16,38,1582],[8,15,2040],[18,36,3113],[2,11,4287],[13,28,3813],[0,32,4375],[3,33,5513],[19,26,244],[11,23,2454],[16,28,3209],[3,34,7579],[2,24,6368],[10,25,6483],[8,22,5691],[7,19,4154],[17,23,8757],[7,11,1931],[4,19,7856],[22,32,8456],[2,12,2615],[29,36,4506],[14,37,9937],[11,27,4164],[26,38,7275],[6,11,9853],[3,31,9498],[6,27,835],[6,35,9750],[14,28,2564],[8,21,2069],[3,38,6068],[0,25,2793],[4,23,5182],[15,36,6692],[18,25,8000],[12,31,8724],[15,27,146],[1,7,6611],[1,36,5780],[9,23,5532],[20,28,3097],[30,38,108],[15,17,7243],[6,36,2094],[32,34,6015],[11,26,5442],[16,17,1454],[18,35,5012],[28,38,73],[0,38,5039],[17,33,8088],[33,35,1675],[10,38,2895],[29,31,1275],[13,38,7541],[13,17,3776],[13,26,3980],[0,22,5068],[5,14,420],[11,38,3823],[24,37,6245],[7,18,745],[11,22,894],[14,19,7170],[0,15,7181],[10,18,5059],[0,20,2448],[8,33,9989],[28,30,5110],[6,20,8021],[5,15,4099],[3,37,1375],[8,29,2438],[5,27,3915],[16,37,1430],[10,30,5871],[8,9,4053],[23,24,2305],[23,30,1723],[11,35,43],[23,25,377],[11,28,949],[2,27,2637],[26,36,1856],[9,25,994],[7,8,9375],[19,24,9937],[6,23,1727],[3,10,6053],[22,28,9815],[12,24,1033],[17,30,1795],[2,23,9458],[0,34,4091],[21,34,8096],[1,18,1031],[20,34,944],[2,5,4024],[0,24,285],[11,20,8137],[22,24,4782],[0,17,8309],[15,28,3969],[15,21,2276],[31,34,5448],[10,34,6433],[1,31,1736],[10,16,8362],[16,22,4084],[2,6,7867],[7,32,1865],[2,16,3438],[11,16,1160],[8,32,3509],[6,9,1658],[5,19,2762],[0,5,4162],[19,30,2333],[3,16,3306],[25,27,3425],[22,23,8181],[9,18,3861],[16,34,7057],[14,34,9239],[9,16,1192],[16,32,8649],[23,28,2251],[10,37,9831],[4,36,1830],[0,28,4997],[35,36,1370],[21,38,1609],[4,18,2630],[5,20,8504],[10,22,1379],[26,35,9343],[16,18,2038],[10,23,491],[24,38,6111],[35,38,8084],[8,20,7034]]"), 6586));
        System.out.println(dq.findTheCity(5, StringUtils.convert2Array("[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]"), 2));
    }



    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n], mapper = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i=0; i<n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE/2);
            Arrays.fill(mapper[i], Integer.MAX_VALUE/2);
        }
        for (int[] edge : edges){
            int from = edge[0], to = edge[1], weight = edge[2];
            mapper[from][to] = weight;
            mapper[to][from] = weight;
        }
        // 每个点作为起始点，都做一次floyd
        for (int i=0; i<n; i++){
            dp[i][i] = 0;
            // 每个点都要选一次
            for (int k=0; k<n; k++){
                int newPos = -1;
                // 找距离最近的新节点
                for (int j=0; j<n; j++){
                    if (!visited[i][j] && (newPos == -1 || dp[i][newPos] > dp[i][j])){
                        newPos = j;
                    }
                }
                // 变更新节点相关的距离
                for (int j=0; j<n; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][newPos] + mapper[newPos][j]);
                }
                // 标记新节点
                visited[i][newPos] = true;
            }
        }
        // 统计结果
        int result = 0, minCount = Integer.MAX_VALUE, count;
        for (int i=0; i<n; i++){
            count = 0;
            for (int j=0; j<n; j++){
                if (dp[i][j] <= distanceThreshold){
                    count++;
                }
            }
            if (minCount >= count){
                minCount = count;
                result = i;
            }
        }
        return result;
    }



    // 2300. 咒语和药水的成功对数

    private static void successfulPairsTest(DailyQuestion202311 dq){
        StringUtils.printIntArray(dq.successfulPairs(new int[]{5,1,3}, new int[]{1,2,3,4,5}, 7));
        StringUtils.printIntArray(dq.successfulPairs(new int[]{3,1,2}, new int[]{8,5,8}, 16));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int len = spells.length;
        int[] result = new int[len];
        Arrays.sort(potions);
        for (int i=0; i<len; i++){
            result[i] = this.getPos(spells[i], potions, success);
        }
        return result;
    }

    private int getPos(int spell, int[] potions, long target){
        int left = 0, right = potions.length-1, mid;
        while (left <= right){
            mid = (left + right) >> 1;
            if ((long) potions[mid] * spell < target){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return potions.length - left;
    }

    // 307. 区域和检索 - 数组可修改

    private static void numArrayTest(){
        NumArray numArray = new NumArray(new int[]{1,3,5});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0, 2));
    }

    private static class NumArray {

        private final int[] preCount;
        private final int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            this.preCount = new int[nums.length+1];
            this.doPreCount(0);
        }

        private void doPreCount(int start){
            int count = preCount[start];
            for(int i=start+1; i<=nums.length; i++){
                count += nums[i-1];
                preCount[i] = count;
            }
        }

        public void update(int index, int val) {
            nums[index] = val;
            this.doPreCount(index);
        }

        public int sumRange(int left, int right) {
            return preCount[right+1] - preCount[left];
        }
    }

    // 2609. 最长平衡子字符串

    private static void findTheLongestBalancedSubstringTest(DailyQuestion202311 dq){
        System.out.println(dq.findTheLongestBalancedSubstring("01000111"));
        System.out.println(dq.findTheLongestBalancedSubstring("00111"));
        System.out.println(dq.findTheLongestBalancedSubstring("111"));
    }

    public int findTheLongestBalancedSubstring(String s) {
        int p = 0, q = 0, i = 0, len = s.length(), result = 0;
        while (i < len) {
            while (i < len && s.charAt(i) == '0'){
                p++;
                i++;
            }
            while (i < len && s.charAt(i) == '1'){
                q++;
                i++;
            }
            result = Math.max(result, Math.min(p, q) * 2);
            p = 0;
            q = 0;
        }
        return result;
    }

    // 2586. 统计范围内的元音字符串数

    private static final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');;
    public int vowelStrings(String[] words, int left, int right) {
        int result = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            if (vowels.contains(word.charAt(0))
                    && vowels.contains(word.charAt(word.length()-1))){
                result++;
            }
        }
        return result;
    }

    // 318. 最大单词长度乘积   用数组可以节省时间和空间。。。

    private static void maxProductTest(DailyQuestion202311 dq){
        System.out.println(dq.maxProduct(new String[]{"a", "b"}));
        System.out.println(dq.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
        System.out.println(dq.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}));
        System.out.println(dq.maxProduct(new String[]{"a","aa","aaa","aaaa"}));
    }

    public int maxProduct(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<words.length; i++){
            map.put(i, create(words[i]));
        }
        int result = 0;
        for (int i=0; i<words.length; i++){
            for (int j=i+1; j<words.length; j++){
                if ((map.get(i) & map.get(j)) == 0){
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

    private int create(String n){
        int result = 0;
        for (int i=0; i<n.length(); i++){
            result |= 1 << (n.charAt(i) - 'a');
        }
        return result;
    }




    // 100118. 在树上执行操作以后得到的最大分数   直接构造树，逻辑更简单     链式的场景没考虑到。。。

    private static void maximumScoreAfterOperationsTest(DailyQuestion202311 dq){
//        System.out.println(dq.maximumScoreAfterOperations(StringUtils.convert2Array("[[0,1],[0,2],[0,3],[2,4],[4,5]]"), new int[]{5,2,5,2,1,1}));
//        System.out.println(dq.maximumScoreAfterOperations(StringUtils.convert2Array("[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]"), new int[]{20,10,9,7,4,3,5}));
        System.out.println(dq.maximumScoreAfterOperations(StringUtils.convert2Array("[[0,1],[0,2],[0,3]]"), new int[]{1000000000,1000000000,1000000000,1000000000}));
    }

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        Set<Integer> chooseSet = new HashSet<>();
        long max = 0L;
        int len = values.length;
        List<Integer>[] degree = new List[len];
        for (int i=0; i<len; i++){
            degree[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            degree[edge[0]].add(edge[1]);
            degree[edge[1]].add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            int item = queue.poll();
            if (chooseSet.contains(item)){
                continue;
            }
            long subCount = 0L;
            for (int v : degree[item]){
                if (chooseSet.contains(v)){
                    continue;
                }
                subCount += values[v];
            }
            // 叶子节点
            if (subCount == 0){
                continue;
            }
            chooseSet.add(item);
            if (values[item] < subCount){
                max += this.getAllSub(values, item, chooseSet, degree);
            }
            // 用子节点
            else {
                max += values[item];
                queue.addAll(degree[item]);
            }
        }
        return max;
    }

    private long getAllSub(int[] values, int pos, Set<Integer> chooseSet, List<Integer>[] degree){
        long result = 0L;
        Queue<Integer> queue = new LinkedList<>();
        for (int item : degree[pos]){
            if (chooseSet.add(item)){
                queue.offer(item);
            }
        }
        while (!queue.isEmpty()){
            int val = queue.poll();
            result += values[val];
            for (int item : degree[val]){
                if (chooseSet.add(item)){
                    queue.offer(item);
                }
            }
        }
        return result;
    }



    // 187. 重复的DNA序列

    private static void findRepeatedDnaSequencesTest(DailyQuestion202311 dq){
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> stringCases = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i=10; i<=s.length(); i++){
            String item = s.substring(i-10, i);
            if (result.contains(item)){
                continue;
            }
            if (!stringCases.add(item)){
                stringCases.remove(item);
                result.add(item);
            }
        }
        return new LinkedList<>(result);
    }

    // 数组中两个数的最大异或值

    private static void findMaximumXORTest(DailyQuestion202311 dq){
        System.out.printf("%s%n%s%n%s%n%s", Integer.toBinaryString(670116204), Integer.toBinaryString(693454773),
                Integer.toBinaryString(234881024), Integer.toBinaryString(245657305));
        System.out.println(dq.findMaximumXOR(new int[]{670116204,693454773}));
        System.out.println(dq.findMaximumXOR(new int[]{3,10,5,25,2,8}));
        System.out.println(dq.findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
        System.out.println(dq.findMaximumXOR(StringUtils.randomIntArray(3000, 0, 1_000_000_000)));
    }


    public int findMaximumXOR(int[] nums) {
        DictNode root = new DictNode();
        int result = 0;
        this.create(nums[0], root);
        for (int i=1; i<nums.length; i++){
            result = Math.max(result, this.getMaxXor(nums[i], root));
            this.create(nums[i], root);
        }
        return result;
    }

    private int getMaxXor(int compare, DictNode root){
        int i = 1 << 30;
        int result = 0;
        while (i > 0){
            if ((compare & i) > 0){
                if (root.right != null){
                    result = result * 2 + 1;
                    root = root.right;
                }
                else {
                    root = root.left;
                    result = result * 2;
                }
            }
            else {
                if (root.left != null){
                    result = result * 2 + 1;
                    root = root.left;
                }
                else {
                    root = root.right;
                    result = result * 2;
                }
            }
            i = i >> 1;
        }
        return result;
    }

    private void create(int n, DictNode root){
        int i = 1 << 30;
        while (i > 0){
            if ((n & i) > 0){
                if (root.left == null){
                    root.left = new DictNode();
                }
                root = root.left;
            }
            else {
                if (root.right == null){
                    root.right = new DictNode();
                }
                root = root.right;
            }
            i = i >> 1;
        }
    }

    private static class DictNode {
        public DictNode left, right;
    }



    // 117. 填充每个节点的下一个右侧节点指针 II

    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            Node pre = null, next;
            while (len > 0){
                next = queue.poll();
                if (next.left != null){
                    queue.offer(next.left);
                }
                if (next.right != null){
                    queue.offer(next.right);
                }
                if (pre != null){
                    pre.next = next;
                }
                pre = next;
                len--;
            }
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    // 2103. 环和杆
    private static void countPointsTest(DailyQuestion202311 dq){
        System.out.println(dq.countPoints("B0B6G0R6R0R6G9"));
        System.out.println(dq.countPoints("B0R0G0R9R0B0G0"));
        System.out.println(dq.countPoints("G4"));
    }

    public int countPoints(String rings) {
        int result = 0;
        int[] poles = new int[10];
        for(int i=0; i<rings.length(); i+=2){
            poles[rings.charAt(i+1) - '0'] |= this.getColor(rings.charAt(i));
        }
        for(int pole : poles){
            if (pole == 7){
                result++;
            }
        }
        return result;
    }

    private int getColor(char s){
        if (s == 'R'){
            return 1;
        }
        else if (s == 'B'){
            return 2;
        }
        return 4;
    }
}
