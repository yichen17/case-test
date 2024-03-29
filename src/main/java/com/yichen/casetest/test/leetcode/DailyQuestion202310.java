package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.MathUtils;
import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/9/30 08:57
 * @describe 10月的每日一题
 */
@Slf4j
class DailyQuestion202310 {

    private static final int mod = 1000000007;
    private static final Random random = new Random();

    public static void main(String[] args) {
        DailyQuestion202310 dq = new DailyQuestion202310();
        earliestFullBloomTest(dq);
        StringUtils.divisionLine();
        collectTheCoinsTest(dq);
        StringUtils.divisionLine();
        maxProfit1Test(dq);
        StringUtils.divisionLine();
        maxProfit2Test(dq);
        StringUtils.divisionLine();
        maxProfit3Test(dq);
        StringUtils.divisionLine();
        maxProfit4Test(dq);
        StringUtils.divisionLine();
        maxProfit5Test(dq);
        StringUtils.divisionLine();
        maxProfit6Test(dq);
        StringUtils.divisionLine();
        stockSpannerTest();
        StringUtils.divisionLine();
        stockPriceTest();
        StringUtils.divisionLine();
        splitNum(dq);
        StringUtils.divisionLine();
        topStudentsTest(dq);
        StringUtils.divisionLine();
        findTheArrayConcValTest(dq);
        StringUtils.divisionLine();
        avoidFloodTest(dq);
        StringUtils.divisionLine();
        sumDistanceTest(dq);
        StringUtils.divisionLine();
        singleNumber1Test(dq);
        StringUtils.divisionLine();
        singleNumber2Test(dq);
        StringUtils.divisionLine();
        singleNumber(dq);
        StringUtils.divisionLine();
        sumOfMultiplesTest(dq);
        StringUtils.divisionLine();
        maxKelementsTest(dq);
        StringUtils.divisionLine();
        tupleSameProductTest(dq);
        StringUtils.divisionLine();
        categorizeBoxTest(dq);
        StringUtils.divisionLine();
        countPairsTest(dq);
        StringUtils.divisionLine();
        maxSatisfactionTest(dq);
        StringUtils.divisionLine("maxSatisfactionTest");
        countSeniorsTest(dq);
        StringUtils.divisionLine();
        numRollsToTargetTest(dq);
        StringUtils.divisionLine();
        punishmentNumberTest(dq);
        StringUtils.divisionLine();
        countDigitsTest(dq);
        StringUtils.divisionLine();
        maxAreaTest(dq);
        StringUtils.divisionLine();
        pickGiftsTest(dq);
        StringUtils.divisionLine();
        hIndex1Test(dq);
        StringUtils.divisionLine("hIndex1Test");
        hIndexTest(dq);
        StringUtils.divisionLine("hIndexTest");
        smallestMissingValueSubtreeTest(dq);
        StringUtils.divisionLine();
        maximumInvitationsTest(dq);
        StringUtils.divisionLine();
    }

    // 2127. 参加会议的最多员工数  错误解法，我是垃圾

    private static void maximumInvitationsTest(DailyQuestion202310 dq){
        System.out.println(dq.maximumInvitations(new int[]{2,2,1,2}));
        System.out.println(dq.maximumInvitations(new int[]{1,2,0}));
        System.out.println(dq.maximumInvitations(new int[]{3,0,1,4,1}));
        System.out.println(dq.maximumInvitations(new int[]{1,0,0,2,1,4,7,8,9,6,7,10,8}));
        System.out.println(dq.maximumInvitations(StringUtils.randomIntArrayNotSelf(1000, 0, 100)));
    }

    public int maximumInvitations(int[] favorite) {
        int len = favorite.length;
        int result = 0;
        List<Integer>[] listMap = new List[len];
        for (int i=0; i<len; i++){
            listMap[i] = new LinkedList<>();
        }
        // 保存映射关系
        for (int i=0; i<len; i++){
            listMap[favorite[i]].add(i);
        }
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> path = new LinkedList<>();
        for (int i=0; i<len; i++){
            if (visited.contains(i)){
                continue;
            }
            Set<Integer> repeat = new HashSet<>();
            int j = i, node, count = 1;
            while (repeat.add(j)){
                path.add(j);
                j = favorite[j];
            }
            Queue<Integer> queue = new LinkedList<>();
            while ((node = path.removeLast()) != j){
                queue.addAll(listMap[node]);
                visited.add(node);
                count++;
            }
            queue.addAll(listMap[j]);
            visited.add(j);
            count = count == 2 ? 2 : 0;
            while (!queue.isEmpty()){
                node = queue.poll();
                if (visited.contains(node)){
                    continue;
                }
                visited.add(node);
                count = count == 0 ? 0 : count + 1;
                queue.addAll(listMap[node]);
            }
            result = Math.max(result, count);
            path.clear();
        }
        return result;
    }



    // 2003. 每棵子树内缺失的最小基因值   超出空间限制，待优化

    private static void smallestMissingValueSubtreeTest(DailyQuestion202310 dq){
        StringUtils.printIntArray(dq.smallestMissingValueSubtree(new int[]{-1,0,0,2}, new int[]{1,2,3,4}));
        StringUtils.printIntArray(dq.smallestMissingValueSubtree(new int[]{-1,0,1,0,3,3}, new int[]{5,4,6,2,1,3}));
        StringUtils.printIntArray(dq.smallestMissingValueSubtree(new int[]{-1,2,3,0,2,4,1}, new int[]{2,3,4,5,6,7,8}));
        StringUtils.printIntArray(dq.smallestMissingValueSubtree(new int[]{-1,0,45,99,2,52,5,41,88,64,71,54,91,66,9,0,73,41,10,17,99,31,96,17,87,67,72,46,22,6,53,1,57,27,20,22,6,53,30,79,12,0,30,59,2,98,56,49,31,0,72,50,31,54,71,57,11,2,45,53,7,6,41,86,31,9,18,17,37,6,56,0,53,61,43,79,67,32,54,86,67,36,93,56,17,56,54,47,52,2,15,10,87,61,69,15,61,26,56,18}, new int[]{32,77,26,71,43,41,57,84,66,37,99,38,59,73,47,33,94,58,36,65,93,28,20,79,12,68,34,45,87,64,19,8,53,9,92,10,40,13,16,48,98,5,42,15,23,88,83,2,31,6,81,27,82,67,95,96,76,61,30,14,21,25,97,7,24,100,18,55,11,72,51,80,89,17,60,22,52,44,91,4,85,56,35,86,63,29,78,70,69,54,50,39,46,1,62,49,74,3,90,75}));
//        StringUtils.printIntArray(dq.smallestMissingValueSubtree(StringUtils.treeArray(1000), StringUtils.randomNoRepeat(1000, 0, 10000)));
    }

    private int[] genes;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int len = parents.length;
        this.genes = nums;
        int[] result = new int[len];
        // 比特位标记空位置
        int[] marks = new int[(len+31)/32];
        // 构造树节点 填充对应的基因
        Node[] nodes = new Node[len];
        for (int i=0; i<len; i++){
            nodes[i] = new Node();
        }
        nodes[0].pos = 0;
        // 构造父子关系
        for (int i=1; i<len; i++){
            nodes[parents[i]].children.add(nodes[i]);
            nodes[i].pos = i;
        }
        // 比特位排序，后续遍历节点计算最小基因数  顺带填充结果
        this.dfs(result, marks, nodes[0]);
        return result;
    }

    /**
     *
     * @param result 最终处理结果
     * @param marks 标记处理前结果
     * @param root 处理节点
     * @return 标记处理后的结果
     */
    private int[] dfs(int[] result, int[] marks, Node root){
        int[] combine = new int[marks.length];
        // 优先处理字节点，且字节点相互隔离。都处理完之后字节点汇总，在处理父节点
        for (int i=0; i<root.children.size(); i++){
            this.merge(combine, dfs(result, Arrays.copyOf(marks, marks.length), root.children.get(i)));
        }
        // 父节点置入
        this.fillMarks(combine, this.genes[root.pos]);
        // 填充结果
        result[root.pos] = this.getPos(combine);
        return combine;
    }

    private void merge(int[] combine, int[] data){
        for(int i=0; i<combine.length; i++){
            combine[i] |= data[i];
        }
    }

    private void fillMarks(int[] marks, int pos){
        // 跨界忽略
        if (pos >= 32 * marks.length){
            return;
        }
        int divisor = 0;
        while (pos > 32){
            divisor++;
            pos -= 32;
        }
        int remainder = pos;
        int d = 1;
        while (32 - remainder > 0){
            remainder++;
            d = d << 1;
        }
        marks[divisor] |= d;
    }

    private int getPos(int[] marks){
        int result = 1;
        for(int mark : marks){
            if (mark == 0xffffffff){
                result += 32;
                continue;
            }
            int i= 0x80000000;
            while ((mark & i) != 0){
                i = i >>> 1;
                result++;
            }
            break;
        }
        return result;
    }

    private static class Node{
        public List<Node> children;
        /**
         * 节点数组坐标
         */
        public Integer pos;

        public Node() {
            this.children = new ArrayList<>();
        }
    }

    // 275. H 指数 II    思路有点混乱。。。唉

    private static void hIndexTest(DailyQuestion202310 dq){
        System.out.println(dq.hIndex(new int[]{0,1,3,5,6}));
        System.out.println(dq.hIndex(new int[]{1,2,100}));
        int[] data = StringUtils.randomIntArray(2000, 0, 999);
        Arrays.sort(data);
        StringUtils.printIntArray(data);
        System.out.println(dq.hIndex(data));
    }

    public int hIndex(int[] citations) {
        if (citations[0] >= citations.length){
            return citations.length;
        }
        int left = 0, len = citations.length, right = citations.length-1,mid;
        while (left <= right){
            mid = (left+right) >> 1;
            if (citations[mid] == len - mid){
                left = mid;
                break;
            }
            else if (citations[mid] > len - mid){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return Math.min(citations[left], len - left);
    }

    // 274. H 指数

    private static void hIndex1Test(DailyQuestion202310 dq){
        System.out.println(dq.hIndex1(new int[]{3,0,6,1,5}));
        System.out.println(dq.hIndex1(new int[]{1,3,1}));
        System.out.println(dq.hIndex1(StringUtils.randomIntArray(2000, 0, 999)));
    }

    public int hIndex1(int[] citations) {
        int[] buckets = new int[1001];
        for (int citation : citations){
            buckets[citation]++;
        }
        int count = 0;
        for (int i=1000; i>=0; i--){
            if (count + buckets[i] >= i){
                return i;
            }
            count += buckets[i];
        }
        return 0;
    }

    // 2558. 从数量最多的堆取走礼物

    private static void pickGiftsTest(DailyQuestion202310 dq){
        System.out.println(dq.pickGifts(new int[]{25,64,9,4,100}, 4));
        System.out.println(dq.pickGifts(new int[]{1,1,1,1}, 4));
        System.out.println(dq.pickGifts(StringUtils.randomIntArray(500, 1, 100000000), 400));
    }

    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> {
            return b-a;
        });
        for(int gift : gifts){
            priorityQueue.offer(gift);
        }
        while (k > 0 && !priorityQueue.isEmpty()){
            k--;
            priorityQueue.offer((int)Math.sqrt(1.0 * priorityQueue.poll()));
//            priorityQueue.offer(MathUtils.sqrtFloor(priorityQueue.poll()));
        }
        long result = 0;
        while (!priorityQueue.isEmpty()){
            result += priorityQueue.poll();
        }
        return result;
    }



    // 1465. 切割后面积最大的蛋糕

    private static void maxAreaTest(DailyQuestion202310 dq){
        System.out.println(dq.maxArea(5, 4, new int[]{1,2,4}, new int[]{1,3}));
        System.out.println(dq.maxArea(5,4,new int[]{3,1}, new int[]{1}));
        System.out.println(dq.maxArea(5,4,new int[]{3}, new int[]{3}));
//        System.out.println(dq.maxArea(100000000, 100000000,
//                StringUtils.randomNoRepeat(100000, 1, 100000000), StringUtils.randomNoRepeat(5000, 1, 100000000)));
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        return (int)((long) this.getMax(horizontalCuts, h) * this.getMax(verticalCuts, w) % mod);
    }

    private int getMax(int[] data, int limit){
        Arrays.sort(data);
        int len = data.length;
        int max = Math.max(data[0], limit - data[len-1]);
        for(int i=1; i<len; i++){
            max = Math.max(max, data[i] - data[i-1]);
        }
        return max;
    }

    // 2520. 统计能整除数字的位数

    private static void countDigitsTest(DailyQuestion202310 dq){
        System.out.println(dq.countDigits(7));
        System.out.println(dq.countDigits(121));
        System.out.println(dq.countDigits(1248));
    }

    public int countDigits(int num) {
        int result = 0;
        String s = String.valueOf(num);
        for (int i=0; i<s.length(); i++){
            if (num % (s.charAt(i) - '0') == 0){
                result++;
            }
        }
        return result;
    }

    // 2698. 求一个整数的惩罚数

    private static void punishmentNumberTest(DailyQuestion202310 dq){
        System.out.println(dq.punishmentNumber(10));
        System.out.println(dq.punishmentNumber(37));
    }

    public int punishmentNumber(int n) {
        int result = 0;
        for (int i=1; i<=n; i++){
            if (check(i, i*i)){
                result += i * i;
            }
        }
        return result;
    }

    private boolean check(int i,int target){
        return this.check(0, i, String.valueOf(target));
    }

    private boolean check(int pos, int count, String data){
        if (pos >= data.length()){
            return count == 0;
        }
        if (data.charAt(pos) == '0'){
            return this.check(pos+1, count, data);
        }
        int val = 0;
        boolean result = false;
        for(int i=pos; i<data.length(); i++){
            val = val * 10 + data.charAt(i) - '0';
            if (val > count){
                break;
            }
            result = this.check(i + 1, count - val, data);
            if (result){
                break;
            }
        }
        return result;
    }

    // 1155. 掷骰子等于目标和的方法数

    private static void numRollsToTargetTest(DailyQuestion202310 dq){
        System.out.println(dq.numRollsToTarget(1, 6, 3));
        System.out.println(dq.numRollsToTarget(2, 6, 7));
        System.out.println(dq.numRollsToTarget(30, 30, 500));
        System.out.println(dq.numRollsToTarget(30, 30, 520));
    }

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n+1][target+1];
        dp[n][target] = 1;
        for(int i=n-1; i>=0; i--){
            for(int j=1; j<=target; j++){
                for (int p=1; p<=k; p++){
                    if (j < p ){
                        continue;
                    }
                    dp[i][j-p] += dp[i+1][j];
                    dp[i][j-p] %= mod;
                }
            }
        }
        return dp[0][0];
    }

    // 2678. 老人的数目

    private static void countSeniorsTest(DailyQuestion202310 dq){
        System.out.println(dq.countSeniors(new String[]{"7868190130M7522","5303914400F9211","9273338290F4010"}));
        System.out.println(dq.countSeniors(new String[]{"1313579440F2036","2921522980M5644"}));
    }

    public int countSeniors(String[] details) {
        int result = 0;
        for (String detail : details){
            if ((detail.charAt(11) == '6' && detail.charAt(12) > '0') || detail.charAt(11) > '6'){
                result++;
            }
        }
        return result;
    }

    // 1402. 做菜顺序

    private static void maxSatisfactionTest(DailyQuestion202310 dq){
        System.out.println(dq.maxSatisfaction(new int[]{-1,-8,0,5,-9}));
        System.out.println(dq.maxSatisfaction(new int[]{4,3,2}));
        System.out.println(dq.maxSatisfaction(new int[]{-1,-4,-5}));
        System.out.println(dq.maxSatisfaction(StringUtils.randomIntArray(400, -999, 999)));
    }

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int start = -1, count=0, result=0, len=satisfaction.length;
        for (int i=0; i<len; i++){
            if (satisfaction[i] < 0){
                continue;
            }
            if (start == -1){
                start = i;
            }
            count += satisfaction[i];
            result += satisfaction[i] * (i - start + 1);
        }
        for (int i=start-1; i>=0; i--){
            if (satisfaction[i] + count >= 0){
                count += satisfaction[i];
                result += count;
            }
        }
        return result;
    }

    // 2316. 统计无向图中无法互相到达点对数

    private static void countPairsTest(DailyQuestion202310 dq){
        System.out.println(dq.countPairs(3, StringUtils.convert2Array("[[0,1],[0,2],[1,2]]")));
        // 4 2 1
        System.out.println(dq.countPairs(7, StringUtils.convert2Array("[[0,2],[0,5],[2,4],[1,6],[5,4]]")));
        System.out.println(dq.countPairs(1000, StringUtils.constructEdges(0, 1000, 2000, false, true, false)));
    }

    public long countPairs(int n, int[][] edges) {
        List<Integer>[] dp = new List[n];
        for (int i=0; i<dp.length; i++){
            dp[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            int p = edge[0], q = edge[1];
            dp[p].add(q);
            dp[q].add(p);
        }
        Set<Integer> visited = new HashSet<>();
        int preSize;
        List<Integer> subCircle = new ArrayList<>();
        for(int i=0; i<n; i++){
            // 如果为空或者已经dfs访问过了
            if (dp[i].isEmpty() || visited.contains(i)){
                continue;
            }
            preSize = visited.size();
            this.bfs(dp, visited, i);
            subCircle.add(visited.size() - preSize);
        }
        long result = 0;
        preSize = n;
        for(int i=0; i<subCircle.size(); i++){
            result = result + (long) (preSize - subCircle.get(i)) * subCircle.get(i);
            preSize -= subCircle.get(i);
        }
        if (preSize != 0){
            result = result + (long) (preSize - 1) * preSize / 2;
        }
        return result;
    }

    private void bfs(List<Integer>[] dp, Set<Integer> visited, int start){
        if (!visited.add(start)){
            return;
        }
        for(int i=0; i<dp[start].size(); i++){
            this.bfs(dp, visited, dp[start].get(i));
        }
    }



    // 2525. 根据规则将箱子分类

    private static void categorizeBoxTest(DailyQuestion202310 dq){
        System.out.println(dq.categorizeBox(1000, 35, 700, 300));
        System.out.println(dq.categorizeBox(200, 50, 800, 50));
    }

    private static final int TEN_THOUSAND = 10_000;
    private static final int ONE_BILLION = 1_000_000_000;

    public String categorizeBox(int length, int width, int height, int mass) {
        long volume = (long) length * width * height;
        boolean isBuiky = length >= TEN_THOUSAND || width >= TEN_THOUSAND
                || height >= TEN_THOUSAND || volume >= ONE_BILLION;
        boolean isHeavy = mass >= 100;
        if (isBuiky && isHeavy){
            return "Both";
        }
        else if (isHeavy){
            return "Heavy";
        }
        else if (isBuiky){
            return "Bulky";
        }
        else {
            return "Neither";
        }
    }

    // 1726. 同积元组

    private static void tupleSameProductTest(DailyQuestion202310 dq){
        System.out.println(dq.tupleSameProduct(new int[]{2,3,4,6}));
        System.out.println(dq.tupleSameProduct(new int[]{1,2,4,5,10}));
        System.out.println(dq.tupleSameProduct(StringUtils.randomNoRepeat(1000, 2, 9999)));
    }

    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, i = 1, target, len = nums.length;
        while (start < len){
            for(; i<len; i++){
                target = nums[start] * nums[i];
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
            start++;
            i = start+1;
        }
        int result = 0;
        // 计算结果
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 2){
                continue;
            }
            // 计算组合数量
            result += MathUtils.combination(entry.getValue(), 2);
        }
        return result * 8;
    }



    // 2530. 执行 K 次操作后的最大分数

    private static void maxKelementsTest(DailyQuestion202310 dq){
        System.out.println(dq.maxKelements(new int[]{10,10,10,10,10}, 5));
        System.out.println(dq.maxKelements(new int[]{1,10,3,3,3}, 3));
    }

    public long maxKelements(int[] nums, int k) {
        long result = 0L;
        Arrays.sort(nums);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i=nums.length-1, len = nums.length; i>=0 && len - i <= k; i--){
            pq.offer(nums[i]);
        }
        while (k>0){
            if (pq.isEmpty() || pq.peek() == 0){
                break;
            }
            k--;
            int max = pq.poll();
            result += max;
            pq.offer(max % 3 == 0 ? max/3 : max/3+1);
        }

        return result;
    }

    // 2652. 倍数求和

    private static void sumOfMultiplesTest(DailyQuestion202310 dq){
        System.out.println(dq.sumOfMultiples(7));
        System.out.println(dq.sumOfMultiples(10));
        System.out.println(dq.sumOfMultiples(9));
    }

    public int sumOfMultiples(int n) {
        int result = 0;
        for (int i=1; i<=n; i++){
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0){
                result += i;
            }
        }
        return result;
    }

    // 260. 只出现一次的数字 III

    private static void singleNumber(DailyQuestion202310 dq){
        StringUtils.printIntArray(dq.singleNumber(new int[]{1,2,1,3,2,5}));
        StringUtils.printIntArray(dq.singleNumber(new int[]{1,0}));
        StringUtils.printIntArray(dq.singleNumber(new int[]{-1, 0}));
    }

    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++){
            if (!set.add(nums[i])){
                set.remove(nums[i]);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        return new int[]{iterator.next(), iterator.next()};
    }

    // 137. 只出现一次的数字 II

    private static void singleNumber2Test(DailyQuestion202310 dq){
        System.out.println(dq.singleNumber2(new int[]{2,2,3,2}));
        System.out.println(dq.singleNumber2(new int[]{0,1,0,1,0,1,99}));
    }

    public int singleNumber2(int[] nums) {
        int[] dp = new int[32];
        for (int num : nums){
            for (int i=1; i<=32; i++){
                dp[i-1] += (num >> (32 - i)) & 1;
            }
        }
        int result = 0;
        for (int i=1; i<=32; i++){
            if (dp[i-1]%3 == 1){
                result += (1 << (32 - i));
            }
        }
        return result;
    }

    // 136. 只出现一次的数字

    private static void singleNumber1Test(DailyQuestion202310 dq){
        System.out.println(dq.singleNumber1(new int[]{2, 2, 1}));
        System.out.println(dq.singleNumber1(new int[]{4,1,2,1,2}));
        System.out.println(dq.singleNumber1(new int[]{1}));
    }

    public int singleNumber1(int[] nums) {
        int n = 0;
        for (int num : nums){
            n = n ^ num;
        }
        return n;
    }

    // 1488. 避免洪水泛滥

    private static void avoidFloodTest(DailyQuestion202310 dq){
        StringUtils.printIntArray(dq.avoidFlood(new int[]{1,2,3,4}));
        StringUtils.printIntArray(dq.avoidFlood(new int[]{1,2,0,0,2,1}));
        StringUtils.printIntArray(dq.avoidFlood(new int[]{1,2,0,1,2}));
        int[] data = StringUtils.randomIntArray4SizeAndLength(1600, 1000, 0, 1000);
        StringUtils.randomSwapIntArray(data, 5000);
        StringUtils.printIntArray(data);
        StringUtils.printIntArray(dq.avoidFlood(data));
    }

    public int[] avoidFlood(int[] rains) {
        int len = rains.length;
        int[] result = new int[len];
        Set<Integer> set = new HashSet<>();
        List<Integer> remove = new LinkedList<>();
        Map<Integer, Integer> pre = new HashMap<>();
        for (int i=0; i<len; i++){
            if (rains[i] == 0){
                remove.add(i);
                continue;
            }
            result[i] = -1;
            // 如果历史已经有过了
            if (!set.add(rains[i])){
                if (remove.isEmpty()){
                    return new int[0];
                }
                int j, l = remove.size();
                for (j=0; j<remove.size(); j++){
                    if (remove.get(j) < pre.get(rains[i])){
                        continue;
                    }
                    result[remove.remove(j)] = rains[i];
                    break;
                }
                if (j == l){
                    return new int[0];
                }
            }
            pre.put(rains[i], i);
        }
        while (!remove.isEmpty()){
            result[remove.remove(0)] = 1;
        }
        return result;
    }

    // 2731. 移动机器人  cao，我是废物

    private static void sumDistanceTest(DailyQuestion202310 dq){
        int times = random.nextInt(mod);
        log.info("times {}", times);
        System.out.println(dq.sumDistance(StringUtils.randomIntArray(1000,-1000, 1000),
                StringUtils.randomArrayInSpecificCharacters(new char[]{'R', 'L'}, 1000), 100));
        System.out.println(dq.sumDistance(new int[]{-2,0,2}, "RLL", 3));
        System.out.println(dq.sumDistance(new int[]{1,0}, "RL", 2 ));
    }

    private static final char left = 'L';
    private static final char right = 'R';

    public int sumDistance(int[] nums, String s, int d) {
        Map<Character, Integer> dirMap = new HashMap<>(4);
        dirMap.put(left, -1);
        dirMap.put(right, 1);
        int len = nums.length;
        long pre = 0;
        int result = 0;
        char[] dir = s.toCharArray();
        this.sort(nums, dir);
        long[] pos = new long[len];
        for (int i=0; i<len; i++){
            pos[i] = nums[i];
        }
        while (d > 0){
            // 判断是否有序
            boolean LROrder = false;
            int r;
            int leftCount = 0, rightCount = 0;
            for (r=0; r<len; r++){
                if (dir[r] == left){
                    leftCount++;
                    continue;
                }
                rightCount++;
                // 已经有序了
                if (LROrder){
                    break;
                }
                if (dir[r] == right && !LROrder){
                    LROrder = true;
                }
            }
            // 已经有序，直接计算结果
            if (r == len){
                for (int i=1; i<len; i++){
                    pre = pre + (pos[i] - pos[i-1]) * i;
                    result = (int)((result + pre) % mod);
                }
                result = (int) ((result + (2L * leftCount * rightCount * d) % mod ) % mod);
                return result;
            }
            d--;
            for (int i=0; i<len; i++){
                // 判断是否会相撞，想撞转向
                if (i != len-1){
                    if ((pos[i] + dirMap.get(dir[i])) == (pos[i+1] + dirMap.get(dir[i+1]))){
                        pos[i] += dirMap.get(dir[i]);
                        pos[i+1] += dirMap.get(dir[i+1]);
                        dir[i] = dir[i] == left ? right : left;
                        dir[i+1] = dir[i+1] == left ? right : left;
                        i += 2;
                        continue;
                    }
                    if (pos[i] + dirMap.get(dir[i]) == pos[i+1]){
                        dir[i] = dir[i] == left ? right : left;
                        dir[i+1] = dir[i+1] == left ? right : left;
                        i += 2;
                        continue;
                    }
                }
                // 正常移动
                pos[i] += dirMap.get(dir[i]);
            }
        }
        for (int i=1; i<len; i++){
            pre = pre + (pos[i] - pos[i-1]) * i;
            result = (int)((result + pre) % mod);
        }
        return result;
    }

    private void sort(int[] nums, char[] chars){
        this.sort(nums, chars, 0, nums.length-1);
    }

    private void sort(int[] nums, char[] chars, int left, int right){
        if (left >= right){
            return;
        }
        int p = left, q = right;
        while (p < q){
            while (p <  q && nums[p] <= nums[q]){
                q--;
            }
            this.swap(nums, chars, p, q);
            while (p < q && nums[p] <= nums[q]){
                p ++;
            }
            this.swap(nums, chars, p, q);
            q--;
        }
        this.sort(nums, chars, left, p-1);
        this.sort(nums, chars, p+1, right);
    }

    private void swap(int[] nums, char[] chars, int a, int b){
        if (a == b){
            return;
        }
        int p = nums[a];
        char q = chars[a];
        nums[a] = nums[b];
        chars[a] = chars[b];
        nums[b] = p;
        chars[b] = q;
    }

    // 2562. 找出数组的串联值
    // Integer.parseInt("" + nums[i] + nums[j])
    // 和 Integer.parseInt(Integer.toString(nums[i]) + Integer.toString(nums[j]))
    // 两者之间的差异，以及为什么后者执行快一些呢？？

    private static void findTheArrayConcValTest(DailyQuestion202310 dq){
        System.out.println(dq.findTheArrayConcVal(new int[]{7,52,2,4}));
        System.out.println(dq.findTheArrayConcVal(new int[]{5,14,13,8,12}));
    }

    public long findTheArrayConcVal(int[] nums) {
        long result = 0;
        int left = 0, right = nums.length-1;
        while (left <= right){
            if (left == right){
                result += nums[left];
            }
            else {
                result += Long.parseLong("" + nums[left] + nums[right]);
            }
            left ++;
            right --;
        }
        return result;
    }

    // 2512. 奖励最顶尖的 K 名学生

    private static void topStudentsTest(DailyQuestion202310 dq){
        StringUtils.rowPrintList(dq.topStudents(new String[]{"smart","brilliant","studious"}, new String[]{"not"},
                new String[]{"this student is studious","the student is smart"}, new int[]{1,2}, 2));
        StringUtils.rowPrintList(dq.topStudents(new String[]{"smart","brilliant","studious"}, new String[]{"not"},
                new String[]{"this student is not studious","the student is smart"}, new int[]{1,2}, 2));
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> positive = new HashSet<>(Arrays.asList(positive_feedback));
        Set<String> negative = new HashSet<>(Arrays.asList(negative_feedback));
        int[] scores = new int[report.length];
        for (int i=0; i<report.length; i++){
            String[] items = report[i].split(" ");
            int score = 0;
            for (String item : items){
                if (positive.contains(item)){
                    score += 3;
                }
                if (negative.contains(item)){
                    score -= 1;
                }
            }
            scores[i] = score;
        }
        // 维护最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (scores[o1] != scores[o2]){
                    return scores[o1] - scores[o2];
                }
                return student_id[o2] - student_id[o1];
            }
        });
        for (int i=0; i<report.length; i++){
            if (priorityQueue.size() < k){
                priorityQueue.add(i);
                continue;
            }
            int top = priorityQueue.peek();
            if (scores[top] < scores[i] || (scores[top] == scores[i] && student_id[top] > student_id[i])){
                priorityQueue.poll();
                priorityQueue.offer(i);
            }
        }
        List<Integer> result = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            result.add(0, student_id[priorityQueue.poll()]);
        }
        return result;
    }

    // 2578. 最小和分割

    private static void splitNum(DailyQuestion202310 dq){
        System.out.println(dq.splitNum(4325));
        System.out.println(dq.splitNum(687));
    }

    public int splitNum(int num) {
        StringBuilder p = new StringBuilder();
        StringBuilder q = new StringBuilder();
        List<Integer> items = new ArrayList<>();
        while (num > 0){
            items.add(num % 10);
            num /= 10;
        }
        Collections.sort(items);
        for (int i=0; i<items.size(); i++){
            if (i % 2 == 0){
                p.append(items.get(i));
            }
            else {
                q.append(items.get(i));
            }
        }
        return Integer.parseInt(q.toString()) + Integer.parseInt(p.toString());
    }

    // 2034. 股票价格波动

    private static void stockPriceTest(){
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1, 3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4,2);
        System.out.println(stockPrice.minimum());

    }

    private static class StockPrice {

        private static class StockPriceNode{
            public int timestamp;
            public int price;

            public StockPriceNode(int timestamp, int price) {
                this.timestamp = timestamp;
                this.price = price;
            }
        }

        TreeSet<StockPriceNode> set;
        Map<Integer, StockPriceNode> timestamp2price;
        int current;

        public StockPrice() {
            set = new TreeSet<>(new Comparator<StockPriceNode>() {
                @Override
                public int compare(StockPriceNode o1, StockPriceNode o2) {
                    if (o1.price != o2.price){
                        return o1.price - o2.price;
                    }
                    return o1.timestamp - o2.timestamp;
                }
            });
            timestamp2price = new HashMap<>();
            current = -1;
        }

        public void update(int timestamp, int price) {
            if (current == -1){
                current = timestamp;
            }
            else {
                current = Math.max(current, timestamp);
            }
            StockPriceNode his = timestamp2price.getOrDefault(timestamp, null);
            if (his != null){
                set.remove(his);
                his.price = price;
                set.add(his);
            }
            else {
                StockPriceNode newNode = new StockPriceNode(timestamp, price);
                set.add(newNode);
                timestamp2price.put(timestamp, newNode);
            }
        }

        public int current() {
            return timestamp2price.get(current).price;
        }

        public int maximum() {
            return set.last().price;
        }

        public int minimum() {
            return set.first().price;
        }
    }

    // 901. 股票价格跨度

    private static void stockSpannerTest(){
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }

    private static class StockSpanner {

        Map<Integer, Integer> map;
        int pos;
        Stack<Integer> stack;

        public StockSpanner() {
            map = new HashMap<>();
            stack = new Stack<>();
            pos = 0;
        }

        public int next(int price) {

            while (!stack.isEmpty() && map.get(stack.peek()) <= price){
                int item = stack.pop();
                map.remove(item);
            }
            int result = stack.isEmpty() ? pos : pos - stack.peek();
            stack.push(pos);
            map.put(pos, price);
            pos++;
            return result;
        }
    }

    // 714. 买卖股票的最佳时机含手续费

    private static void maxProfit6Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit6(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(dq.maxProfit6(new int[]{1,3,7,5,10,3}, 3));
        System.out.println(dq.maxProfit6(StringUtils.randomIntArray(1000, 1, 9999), 300));
    }

    public int maxProfit6(int[] prices, int fee) {
        int len = prices.length;
        int buy = -prices[0] ,sell = 0;
        for (int i=1; i<len; i++){
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }

    //309. 买卖股票的最佳时机含冷冻期

    private static void maxProfit5Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit5(new int[]{1,2,4}));
        System.out.println(dq.maxProfit5(new int[]{1,3,2,4,1,5,3,6,2,7}));
        System.out.println(dq.maxProfit5(new int[]{1,2,3,0,2}));
        System.out.println(dq.maxProfit5(new int[]{1}));
        System.out.println(dq.maxProfit5(StringUtils.randomIntArray(900, 1, 900)));
    }

    /**
     * 为啥我这不行呢？？
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length == 1){
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        // cao、我是傻逼
//        buy[1] = -Math.max(prices[0], prices[1]);
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i=2; i<len; i++){
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i] + prices[i]);
        }
        return sell[len-1];
    }

    // 188. 买卖股票的最佳时机 IV  在3的基础上改，很快

    private static void maxProfit4Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit4(2, new int[]{2,4,1}));
        System.out.println(dq.maxProfit4(2, new int[]{3,2,6,5,0,3}));
        System.out.println(dq.maxProfit4(50, StringUtils.randomIntArray(900, 1, 900)));
    }

    public int maxProfit4(int k, int[] prices) {
        int[][] dp = new int[k][2];
        for (int i=0; i<k; i++){
            dp[i][0] = -prices[0];
        }
        for (int j=1; j<prices.length; j++){
            int price = prices[j];
            dp[0][0] = Math.max(dp[0][0], -price);
            dp[0][1] = Math.max(dp[0][0] + price, dp[0][1]);
            for (int i=1; i<k; i++){
                dp[i][0] = Math.max(dp[i][0], dp[i-1][1] - price);
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + price);
            }
        }
        return dp[k-1][1];
    }

    // 123. 买卖股票的最佳时机 III

    private static void maxProfit3Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit3(new int[]{1,2,4,2,5,7,2,4,9,0}));
//        System.out.println(dq.maxProfit3(new int[]{3,3,5,0,0,3,1,4}));
//        System.out.println(dq.maxProfit3(new int[]{1,2,3,4,5}));
//        System.out.println(dq.maxProfit3(new int[]{7,6,4,3,1}));
//        System.out.println(dq.maxProfit3(StringUtils.randomIntArray(1000, 1, 9999)));
    }




    /**
     * cao，对着题解都看不太懂。。？？
     * 怎么推算出来的还有待研究哎，两者的关系是怎么得出来的。
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            // 取最大值时因为是负数
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            // 第一次卖出赚的尽可能多，第二次买入尽可能低  动态平衡，前者尽可能多，后者尽可能少
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
            System.out.printf("prices:%sbuy1:%s,sell1:%s,buy2:%s,sell2:%s%n", prices[i], buy1, sell1, buy2, sell2);
        }
        return sell2;
    }



    // 122. 买卖股票的最佳时机 II

    private static void maxProfit2Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(dq.maxProfit2(new int[]{7,6,4,3,1}));
        System.out.println(dq.maxProfit2(StringUtils.randomIntArray(1000, 1, 9999)));
    }

    public int maxProfit2(int[] prices) {
        int result = 0, min = prices[0], len = prices.length;
        for (int i=1; i<len; i++){
            if (prices[i] < prices[i-1]){
                result = result +  prices[i-1] - min;
                min = prices[i];
            }
        }
        result = result + prices[len-1] - min;
        return result;
    }

    // 121. 买卖股票的最佳时机

    private static void maxProfit1Test(DailyQuestion202310 dq){
        System.out.println(dq.maxProfit1(new int[]{7,1,5,3,6,4}));
        System.out.println(dq.maxProfit1(new int[]{7,6,4,3,1}));
        System.out.println(dq.maxProfit1(StringUtils.randomIntArray(1000, 1, 9999)));
    }

    public int maxProfit1(int[] prices) {
        int max,min=0,result=0;
        Stack<Integer> stack = new Stack<>();
        for (int price : prices){
            while (!stack.isEmpty() && stack.peek() > price){
                stack.pop();
            }
            if (stack.isEmpty()){
                min = price;
            }
            else {
                result = Math.max(result, price-min);
            }
            stack.push(price);
        }
        return result;
    }

    // 2603. 收集树中金币

    private static void collectTheCoinsTest(DailyQuestion202310 dq){
        System.out.println(dq.collectTheCoins(new int[]{1,0,0,0,0,1}, StringUtils.convert2Array("[[0,1],[1,2],[2,3],[3,4],[4,5]]")));
        System.out.println(dq.collectTheCoins(new int[]{0,0,0,1,1,0,0,1}, StringUtils.convert2Array("[[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]")));
        System.out.println(dq.collectTheCoins(new int[]{0,0,0}, StringUtils.convert2Array("[[0,1],[0,2]]")));
    }

    public int collectTheCoins(int[] coins, int[][] edges) {
        if (coins.length < 3){
            return 0;
        }
        int n = coins.length,result = n;
        int i=0, p, q;
        Set<Integer>[] degree = new HashSet[n];
        for (int[] edge : edges){
            p = edge[0]; q = edge[1];
            if (degree[p] == null){
                degree[p] = new HashSet<>();
            }
            degree[p].add(q);
            if (degree[q] == null){
                degree[q] = new HashSet<>();
            }
            degree[q].add(p);
        }

        Queue<Integer> queue;
        queue = new LinkedList<>();
        for (i=0; i<n; i++){
            if (degree[i] != null && degree[i].size() == 1 && coins[i] == 0){
                queue.offer(i);
                result--;
            }
        }
        while (!queue.isEmpty()){
            p = queue.poll();
            if (degree[p] == null || degree[p].isEmpty()){
                continue;
            }
            q = degree[p].iterator().next();
            degree[p] = null;
            degree[q].remove(p);
            if (degree[q].size() == 1 && coins[q] == 0){
                queue.offer(q);
                result--;
            }
        }

        for (int t=0; t<2; t++){
            for(i=0; i<n; i++){
                if (degree[i] != null && degree[i].size() == 1){
                    queue.offer(i);
                }
            }
            result -= queue.size();
            while (!queue.isEmpty()){
                p = queue.poll();
                if (degree[p] == null || degree[p].isEmpty()){
                    continue;
                }
                q = degree[p].iterator().next();
                degree[p] = null;
                degree[q].remove(p);
            }
        }
        return result < 1 ? 0 : 2 * (result - 1);
    }

    // 2136. 全部开花的最早一天 寻找规律，推断验证规律。动手，别靠猜哎。。

    private static void earliestFullBloomTest(DailyQuestion202310 dq){
        System.out.println(dq.earliestFullBloom(new int[]{1,4,3}, new int[]{2,3,1}));
    }

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int len = plantTime.length;
        List<Integer> dp = IntStream.range(0, len).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return growTime[o2] - growTime[o1];
            }
        }).collect(Collectors.toList());
        int prev = 0, ans = 0;
        for (int i : dp){
            ans = Math.max(ans, prev + plantTime[i] + growTime[i]);
            prev += plantTime[i];
        }
        return ans;
    }



}
