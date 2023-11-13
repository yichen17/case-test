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
