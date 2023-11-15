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
        System.out.println(dq.findTheCity(39, StringUtils.convert2Array("[[32,33,6066],[9,24,4482],[12,23,1781],[6,25,1897],[7,15,8633],[12,16,2890],[1,30,349],[30,31,9738],[11,33,9791],[12,34,2418],[18,21,4112],[25,29,7258],[1,3,4596],[1,8,2224],[8,17,9142],[13,23,6498],[29,38,9590],[6,28,6956],[4,31,9774],[2,30,3967],[6,19,8528],[11,13,3068],[2,36,2987],[29,37,5395],[14,21,5175],[2,4,3214],[17,29,196],[9,20,4655],[19,36,9637],[15,25,1418],[6,33,5843],[22,27,2500],[13,34,2553],[0,16,1409],[20,30,795],[5,34,8623],[9,33,2352],[21,29,525],[11,30,1720],[14,17,7672],[2,34,8525],[3,29,6520],[26,29,847],[14,18,1323],[27,33,2360],[14,23,4009],[21,37,7194],[14,38,7686],[2,25,8244],[3,21,7009],[20,27,8794],[4,32,1865],[14,20,3548],[2,3,6502],[21,28,1577],[9,15,1030],[24,32,5566],[3,5,4979],[18,26,4109],[25,33,6545],[12,36,5506],[5,33,564],[13,22,691],[8,13,1955],[18,19,4031],[15,37,841],[7,27,318],[1,25,1626],[15,18,7242],[11,12,1446],[24,26,725],[5,24,7100],[7,37,9453],[20,26,2597],[2,10,6982],[19,25,1081],[1,35,7350],[4,37,8618],[4,17,3751],[16,38,1582],[8,15,2040],[18,36,3113],[2,11,4287],[13,28,3813],[0,32,4375],[3,33,5513],[19,26,244],[11,23,2454],[16,28,3209],[3,34,7579],[2,24,6368],[10,25,6483],[8,22,5691],[7,19,4154],[17,23,8757],[7,11,1931],[4,19,7856],[22,32,8456],[2,12,2615],[29,36,4506],[14,37,9937],[11,27,4164],[26,38,7275],[6,11,9853],[3,31,9498],[6,27,835],[6,35,9750],[14,28,2564],[8,21,2069],[3,38,6068],[0,25,2793],[4,23,5182],[15,36,6692],[18,25,8000],[12,31,8724],[15,27,146],[1,7,6611],[1,36,5780],[9,23,5532],[20,28,3097],[30,38,108],[15,17,7243],[6,36,2094],[32,34,6015],[11,26,5442],[16,17,1454],[18,35,5012],[28,38,73],[0,38,5039],[17,33,8088],[33,35,1675],[10,38,2895],[29,31,1275],[13,38,7541],[13,17,3776],[13,26,3980],[0,22,5068],[5,14,420],[11,38,3823],[24,37,6245],[7,18,745],[11,22,894],[14,19,7170],[0,15,7181],[10,18,5059],[0,20,2448],[8,33,9989],[28,30,5110],[6,20,8021],[5,15,4099],[3,37,1375],[8,29,2438],[5,27,3915],[16,37,1430],[10,30,5871],[8,9,4053],[23,24,2305],[23,30,1723],[11,35,43],[23,25,377],[11,28,949],[2,27,2637],[26,36,1856],[9,25,994],[7,8,9375],[19,24,9937],[6,23,1727],[3,10,6053],[22,28,9815],[12,24,1033],[17,30,1795],[2,23,9458],[0,34,4091],[21,34,8096],[1,18,1031],[20,34,944],[2,5,4024],[0,24,285],[11,20,8137],[22,24,4782],[0,17,8309],[15,28,3969],[15,21,2276],[31,34,5448],[10,34,6433],[1,31,1736],[10,16,8362],[16,22,4084],[2,6,7867],[7,32,1865],[2,16,3438],[11,16,1160],[8,32,3509],[6,9,1658],[5,19,2762],[0,5,4162],[19,30,2333],[3,16,3306],[25,27,3425],[22,23,8181],[9,18,3861],[16,34,7057],[14,34,9239],[9,16,1192],[16,32,8649],[23,28,2251],[10,37,9831],[4,36,1830],[0,28,4997],[35,36,1370],[21,38,1609],[4,18,2630],[5,20,8504],[10,22,1379],[26,35,9343],[16,18,2038],[10,23,491],[24,38,6111],[35,38,8084],[8,20,7034]]"), 6586));
        System.out.println(dq.findTheCity(4, StringUtils.convert2Array("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), 4));
        System.out.println(dq.findTheCity(5, StringUtils.convert2Array("[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]"), 2));
    }



    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] path = new int[n][n];
        for (int i=0; i<n; i++){
            Arrays.fill(path[i], Integer.MAX_VALUE/2);
        }
        for (int[] edge : edges){
            int from = edge[0], to = edge[1], weight = edge[2];
            path[from][to] = weight;
            path[to][from] = weight;
        }
        // 以每个点作为起始点
        for (int i=0; i<n; i++){
            // 每次都比较，如果直连大于中转和，用中转和
            path[i][i] = 0;
            for (int j=0; j<n; j++){
                for (int k=0; k<n; k++){
                    path[j][k] = Math.min(path[j][k], path[j][i] + path[i][k]);
                }
            }
        }
        // 计算每个点的数量
        int minCount = Integer.MAX_VALUE, pos = 0;
        for (int i=0; i<n; i++){
            int count = 0;
            for (int j=0; j<n; j++){
                if (i != j && path[i][j] <= distanceThreshold){
                    count++;
                }
            }
            if (minCount >= count){
                minCount = count;
                pos = i;
            }
        }
        return pos;
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
